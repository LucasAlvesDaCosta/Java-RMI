
package jrmi.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfaceChat extends Remote {
public void enviarMensagem(String mensagem) throws RemoteException;
public ArrayList<String> lerMensagem() throws RemoteException;
}
