package jrmi.chat; 
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {
public Servidor(){

    try {
Registry registry = LocateRegistry.createRegistry(1098);
InterfaceChat server = new ServidorChatImpl();
Naming.rebind("rmi://localhost:1098/InterfaceChat",server);

} catch (Exception e){
System.out.println("Exeção:"+e.getMessage());
}


}

public static void main (String args[]){
 new Servidor();
 System.out.println("Servidor online.");
}

}
