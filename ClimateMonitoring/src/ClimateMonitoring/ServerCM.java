package ClimateMonitoring;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    /**
     * qui può avvenire un possibile reset che serve per reinizializzare il server
     * questo avviene solamente se viene passata una "y" in input a tale richiesta.
     * qui viene inoltre creato un registro sulla porta 3003 su cui viene fatto bind e rebind 
     * del server e esplicitato a video lo stato del server e del database
     * @param args [0]:databaseurl | [1]: databaseusername | [2]: password | [3]: "y" per reset database se desiderato
     * @throws RemoteException in caso di errori nella registrazione sul Registry
     */
    public static void main(String args[]) throws RemoteException
    {
        try {
            boolean reset = false;
            String [] input = args;
            if(input.length < 3)
            {
                input = richiediParametri();
            }
            dbh = new DatabaseHelper(input[0],input[1],input[2]);
            reset = input[3].equalsIgnoreCase("y");
            
            if(dbh.getConnection())
            {
                if(reset) dbh.databaseInit();
                {
                    System.out.println("ServerCM : Database pronto.");
                    
                    Registry r = LocateRegistry.createRegistry(3003);
                    r.rebind("ServerCM", new ServerCM());
                    System.out.println("ServerCM : pronto per ricevere richieste.");
                }
            }
            else{
                System.err.println("Sono richiesti i seguenti parametri: ");
                System.err.println("[url del database] [username] [password]");
                System.err.println("Esempio di utilizzo: localhost/postgres admin password123");
            }
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
    }
    
    /**
     * Richiede i parametri all'amministtraore che inizializza il server
     * @return parametri inseriti
     */
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

    /**
     * Metodo utile ad effettuare una richiesta di login da parte di un operatore
     * @param idUtente id dell'utente che desidera di effettuare il login
     * @param password password dell'utente di cui viene controllata l'esistenza
     * @return dati relativi all'utente richiesto qualora esista, null altrimenti
     * @throws RemoteException da eventuali errori nella procedura
     */
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
                rs.close();
                return res;
            }
            rs.close();
            return null;
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
    }

    /**
     * Viene inserito un nuovo utente nel database sulla base dei seguenti dati:
     * @param nome
     * @param cognome
     * @param codiceFiscale
     * @param email
     * @param idUtente
     * @param password
     * @param idCentro
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean registraUtente(String nome, String cognome, String codiceFiscale, 
            String email, String idUtente, String password, String idCentro) throws RemoteException {
        try {
            return dbh.inserisciNuovoUtente(nome, cognome, codiceFiscale, email, idUtente, password, idCentro);
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
    }
    
    @Override
    public boolean cambiaCentroUtente(String idUtente, String idCentro) throws RemoteException
    {
        try {
            return dbh.cambiaCentrtoUtente(idUtente, idCentro);
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
    }
    
    /**
     * Viene effettuata una ricerca su database per il centro
     * @param criterio testo che deve essere contenuto nel nome del centro da cercare
     * @return lista dei centri corrispondenti al criterio (arraylist vuota in assenza di riscontri)
     * @throws RemoteException eventuali errori da database o criteri non rispettati
     */
    @Override
    public ArrayList<String[]> cercaCentri (String criterio) throws RemoteException
    {
        ArrayList result = new ArrayList<String>();
        try {
            ResultSet r = dbh.cercaCentri(criterio);
            if(r != null)
            {
                while (r.next()){
                    String temp[] = new String[6];
                    temp[0] = Integer.toString(r.getInt(1));
                    temp[1] = r.getString(2);
                    temp[2] = r.getString(3);
                    temp[3] = Integer.toString(r.getInt(4));
                    temp[4] = r.getString(5);
                    temp[5] = r.getString(6);
                    result.add(temp);
                }
                r.close();
            }
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
        return result;
    }
    
    @Override
    public boolean salvaCentro(String idCentro, String nomeCentro, String indirizzoCentro, 
            String capCentro, String cittaCentro, String statoCentro, ArrayList<String> localitaAbbinate) throws RemoteException
    {
        try {
            return dbh.inserisciNuovoCentro(idCentro, nomeCentro, indirizzoCentro, capCentro, cittaCentro, statoCentro, localitaAbbinate);
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
    }
    
    @Override
    public ArrayList<String[]> cercaLocalita (String criterioNome, String criterioStato) throws RemoteException
    {
        ArrayList result = new ArrayList<String[]>();
        try {
            ResultSet r = dbh.cercaLocalita(criterioNome, criterioStato);
            while (r.next())
            {
                String[] content = new String[5];
                
                content[0] = Integer.toString(r.getInt(1));
                content[1] = r.getString(2);
                content[2] = r.getString(3);
                content[3] = Double.toString(r.getDouble(4));
                content[4] = Double.toString(r.getDouble(5));
                        
                result.add(content);
            }
            r.close();
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
        return result;
    }
    
    @Override
    public ArrayList<String[]> cercaLocalitaCoordinate (String latitudine, String logitudine) throws RemoteException
    {
        ArrayList result = new ArrayList<String[]>();
        try {
            ResultSet r = dbh.cercaLocalitaCoordinate(latitudine, logitudine);
            while (r.next())
            {
                String[] content = new String[5];
                
                content[0] = Integer.toString(r.getInt(1));
                content[1] = r.getString(2);
                content[2] = r.getString(3);
                content[3] = Double.toString(r.getDouble(4));
                content[4] = Double.toString(r.getDouble(5));
                        
                result.add(content);
            }
            r.close();
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
        return result;
    } 
    
    public ArrayList<String[]> cercaLocalitaAbbinate (String idCentro) throws RemoteException
    {
        ArrayList result = new ArrayList<String[]>();
        
        try {
            ResultSet r = dbh.cercaLocalitaAbbinate(idCentro);
            while(r.next())
            {
                String[] content = new String[5];
                
                content[0] = Integer.toString(r.getInt(1));
                content[1] = r.getString(2);
                content[2] = r.getString(3);
                content[3] = Double.toString(r.getDouble(4));
                content[4] = Double.toString(r.getDouble(5));
                        
                result.add(content);
            }
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
        
        return result;
    }
    
    @Override
    public boolean salvaRilevazione(Rilevazione r) throws RemoteException
    {
        try {
            return dbh.salvaRilevazione(r);
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
    }
}
