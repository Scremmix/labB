package ClimateMonitoring;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author alesc
 */
public class ServerCM extends UnicastRemoteObject implements ServerCMInterface
{
    public ServerCM() throws RemoteException
    {
        super();
    }
    public static void main(String args[])
    {
        try {
            Registry r = LocateRegistry.getRegistry();
            r.rebind("ServerCM", new ServerCM());
            System.out.println("ServerCM : pronto per ricevere richieste.");
        } catch (RemoteException ex) {
            System.err.println("ServerCM: impossibile inizializzare la connessione.");
        }
    }
}
