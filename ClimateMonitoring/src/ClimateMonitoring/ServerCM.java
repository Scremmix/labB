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
    
    /**
     * Vengono richiesti i parametri necessari alla connessione al db e se si desidera
     * effettuarne il reset: questo avviene solamente se viene passata una "y" in input a tale richiesta.
     * Inoltre viene creato un Registry sulla porta 3003 su cui viene fatto bind e rebind 
     * dell'istanza del server ed esplicitato a video lo stato del server e del database
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
     * @return true in caso di successo
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
    
    /**
     * Override del metodo in DatabaseHelper utile a cambiare il centro di monitoraggio relativo
     * add un id utente, con l'aggiunta del lancio di una RemoteException in caso di rilevazione 
     * di SQLException
     * @param idUtente
     * @param idCentro
     * @return
     * @throws RemoteException
     */
    @Override
    public boolean cambiaCentroUtente(String idUtente, String idCentro) throws RemoteException
    {
        try {
            return dbh.cambiaCentroUtente(idUtente, idCentro);
        } catch (SQLException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
    }
    
    /**
     * Serve per cercare le rilevazioni relative ad una località tramite il suo id
     * @param idLocalita in questione
     * @return
     * @throws RemoteException
     */
    @Override
    public ArrayList<Rilevazione> cercaRilevazioneLocalita (String idLocalita) throws RemoteException
    {
        ArrayList result = new ArrayList<Rilevazione>();
        try {
            ResultSet r = dbh.getRilevazioniLocalita(idLocalita);
            if(r != null)
            while (r.next())
            {
                ArrayList valoriTemp = new ArrayList<Integer>();
                for(int i = 5; i <= 11; i++)
                {
                    valoriTemp.add(r.getInt(i));
                }
                Rilevazione temp = new Rilevazione(Integer.toString(r.getInt(2)),
                        Long.valueOf(idLocalita), 
                        valoriTemp, 
                        r.getString(12));
                String[] tempData = r.getString(4).split(" ");
                temp.setData(tempData[0]);
                temp.setOra(tempData[1]);
                result.add(temp);
            }
        } catch (SQLException | rilevazioneException ex) {
            throw new RemoteException(ex.getLocalizedMessage());
        }
        return result;
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
    
    /**
     * Override del metodo presente in DatabaseHelper utile al salvataggio di un
     * centro nel database con il controllo di SQLException e il possibile lancio
     * di RemoteException
     * @param idCentro
     * @param nomeCentro
     * @param indirizzoCentro
     * @param capCentro
     * @param cittaCentro
     * @param statoCentro
     * @param localitaAbbinate
     * @return
     * @throws RemoteException
     */
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
    
    /**
     * Override del metodo presente in DatabaseHelper utile alla ricerca di una
     * località sulla base del suo nome e della località in cui si trova
     * @param criterioNome
     * @param criterioStato
     * @return
     * @throws RemoteException
     */
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
    
    /**
     * Override del metodo presente in DatabaseHelper utile alla ricerca di una 
     * località per coordinate con il controllo di SQLException e il possibile lancio
     * di RemoteException
     * @param latitudine
     * @param logitudine
     * @return
     * @throws RemoteException
     */
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
    
    /**
     * Override del metodo presente in DatabaseHelper che dall'ID del centro ricava 
     * i dati di tutte le località ad esso abbinate
     * @param idCentro
     * @return
     * @throws RemoteException
     */
    @Override
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
    
    /**
     * Override del metodo presente in DatabaseHelper utile al salvataggio di una
     * rilevazione nel database
     * @param r contiene i dati relativi alla rilevazione in questione
     * @return
     * @throws RemoteException
     */
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
