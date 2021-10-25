
package jrmi.chat;

import java.rmi.Naming;
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteChat {

public static void main( String args[] ) {
 
    
try {
final InterfaceChat chat = (InterfaceChat) Naming.lookup( "rmi://localhost:1098/InterfaceChat");// procura um objeto no stub local
final int controle =1;  
String nome ="";
String msg = "";

Scanner scanner = new Scanner(System.in);



while (nome == null || nome.equals("")) {
    nome=(JOptionPane.showInputDialog("Qual o seu nome?"));//janela de input name.
    chat.enviarMensagem("\t\t\t"+nome+" entrou no grupo...\n");// enviando mensagem para o servidor
   }

System.out.printf("Bem Vindo ao bate-papo da UOL: "+nome+"\nDigite sua mensagem;\n");
Thread thread = new Thread(new Runnable() {
int cont = chat.lerMensagem().size();// tamanho do array

@Override
public void run() {
try {

while(controle ==1){// processo independente THREAD para receber as mensagens enviadas para o servidor
if (chat.lerMensagem().size() > cont){//se chegar uma nova mensagem.....
System.out.println(chat.lerMensagem().get(chat.lerMensagem().size()-1));// imprime sempre as ultimas mensagens enviadas ao servidor
cont++;
}
}
} catch (RemoteException e) {
  System.out.printf("Ocorreu um erro, reinicie o programa!\n");  
  System.out.println("Exeção: "+e.getMessage());
  System.exit(0);
    
}
}
});
thread.start();
int sair =0;
while(sair == 0){// O scanner funcionará enquanto o cliente não sair do chat
       msg = scanner.nextLine();
       System.out.printf("-->");
    
if(msg.equals("1") || msg.toString()=="1"){// Se digitar '1' o scanner é encerrado, uma mensagem é enviada ao servidor
//sobre quem saiu e a conexão é encerrada.
    sair = 1;
    chat.enviarMensagem("\t\t\t"+nome+" Saiu do grupo!\n");
    System.exit(0);
}

chat.enviarMensagem("\t\t\t<-- "+nome+":("+msg+")\n");// enviando a msg ao servidor com o nome do cliente e tabulações para organizar
// a estrutura no console.
msg ="";// zerando a variável para não ter o risco de enviar mensagens repetidas ao servidor.
}

}
catch( Exception e ) {
    System.out.println("Exeção:2 "+e.getMessage()+"Exeção 2");
}
}
}
 