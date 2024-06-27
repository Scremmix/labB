package ClimateMonitoring;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
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
    private static DatabaseHelper dbh = null;
    
    public ServerCM() throws RemoteException
    {
        super();
    }
    // esempio di parametri:
    // localhost/postgres ClimateMonitoring DatabaseCM 
    public static void main(String args[])
    {
        boolean reset = false;
        String [] input = args;
        if(input.length < 3)
        {
            input = richiediParametri();
        }
        dbh = new DatabaseHelper(input[0],input[1],input[2]);
        reset = input[3].equalsIgnoreCase("y");
        
        if(dbh.getConnection())
            try {
                if(reset) dbh.databaseInit();
                
                System.out.println("ServerCM : Database pronto.");
                
                Registry r = LocateRegistry.createRegistry(3003);
                r.rebind("ServerCM", new ServerCM());
                System.out.println("ServerCM : pronto per ricevere richieste.");
                
            } catch (RemoteException ex) 
            {ex.printStackTrace();}
        else{
            System.err.println("Sono richiesti i seguenti parametri: ");
            System.err.println("[url del database] [username] [password]");
            System.err.println("Esempio di utilizzo: localhost/postgres admin password123");
        }
    }
    
    private static String[] richiediParametri()
    {
        String [] parametri = new String[4];
        for(String e : parametri)
        {e = "";}
        Scanner keyboard = new Scanner (System.in);
        
        System.out.print("Inserisci l'indirizzo del database PostgreSQL (default: localhost/postgres): ");
        String input = keyboard.nextLine();
        if (input.equals("")) input = "localhost/postgres";
        parametri[0] = input;
        
        System.out.print("Inserisci il nome utente (default: ClimateMonitoring): ");
        input = keyboard.nextLine();
        if (input.equals("")) input = "ClimateMonitoring";
        parametri[1] = input;
        
        System.out.print("Inserisci la password per l'utente inserito: ");
        parametri[2] = keyboard.nextLine();
        
        System.out.print("Desideri reinizializzare il server (Sì = y / No = qualsiasi)? ");
        parametri[3] = keyboard.nextLine();
        
        return parametri;
    }

    @Override
    public ArrayList<String> effettuaLogin(String idUtente, String password) throws RemoteException{
        ResultSet rs;
        try {
            rs = dbh.getUtente(idUtente);
            ArrayList res = new ArrayList<String>();
            if (rs == null)
            {
                return null;
            }
            rs.next();
            if(password.equals(rs.getString(2)))
            {
                res.add(rs.getString(1));
                res.add(rs.getString(2));
                res.add(rs.getString(3));
                res.add(rs.getString(4));
                res.add(rs.getString(5));
                return res;
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
