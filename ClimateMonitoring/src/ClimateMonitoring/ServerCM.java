package ClimateMonitoring;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.sql.*;
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
    // esempio di parametri:
    // localhost/postgres ClimateMonitoring DatabaseCM 
    public static void main(String args[])
    {
        if(args.length == 3)
        {
            DatabaseHelper dbh = new DatabaseHelper(args[0],args[1],args[2]);
            if(dbh.getConnection())
                try {
                    Registry r = LocateRegistry.getRegistry();
                    r.rebind("ServerCM", new ServerCM());
                    System.out.println("ServerCM : pronto per ricevere richieste.");
                } catch (RemoteException ex) {
                    System.err.println("ServerCM: impossibile inizializzare la connessione.");
                }
            else{
                System.err.println("Sono richiesti i seguenti parametri: ");
                System.err.println("[url del database] [username] [password]");
                System.err.println("Esempio di utilizzo: localhost/postgres ClimateMonitoring DatabaseCM");
            }
        }
    }
}
