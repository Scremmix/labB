/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClimateMonitoring;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Riguarda i processi di login e registrazione degli operatori
 * @author Riva Samuele
 * @author Zucchi Luca
 */
public class Utente {
    
    private static String nomeUtente, password, nome, cognome,idCentro;
    
    /**
     * Imposta i parametri della classe Utente a valori di default (null)
     */
    public Utente()
    {
        nomeUtente= null;
        password= null;
        nome= null;
        cognome= null;
        idCentro=null;
    }
    
    /**
     * Il metodo richiede all'utente i dati utili al login
     * @param server su cui effettuare il login
     * @param idUtente ID dell'utente da loggare
     * @param password password dell'utente da loggare
     * @return true in caso di successo
     * @throws utenteException errori ottenuti dal server
     */
    public static boolean login(ServerCMInterface server, String idUtente, String password) throws utenteException
    {
        if(!loggato())
        {
            ArrayList rigaUtente;
            try {
                rigaUtente = server.effettuaLogin(idUtente, password);
                if(rigaUtente != null){
                    Utente.nomeUtente=idUtente;
                    Utente.password=password;
                    Utente.nome=rigaUtente.get(2).toString();
                    Utente.cognome=rigaUtente.get(3).toString();
                    Utente.idCentro=rigaUtente.get(4).toString();
                    return true;
                }
            } catch (RemoteException ex) {
                throw new utenteException(ex.getLocalizedMessage());
            }
            return false;
        }else return true;
    // nomeUtente qui è lo userID ndicato nei commenti sottostanti
    // per esempio, Francesco Totti effettua il login con 7930
    }
    
    /**
     * Metodo utile a ottenere l'ID dell'operatore attuale
     * @return userID
     */
    public static String getIDUtente()
    {return Utente.nomeUtente;}
    
    
    /**
     * Metodo utile a ottenere i dati anagrafici dell'operatore attuale
     * @return "[nome] [cognome]"
     */
    public static String getUsername()
    {return Utente.nome+" "+Utente.cognome;}
    
    /**
     * Utile a richiamare il centro di monitoraggio cui è abbinato l'operatore attuale
     * @return id del centro
     */
    public static String getCentro()
    {return Utente.idCentro;}
    
    /**
     * Utile a impostare il centro di monitoraggio cui è abbinato l'operatore attuale
     * Nota: necessita di una chiamata per modificare a DB il valore
     * 
     * @param centro l'ID del nuovo centro
     */
    public static void setCentro(String centro)
    { Utente.idCentro = centro; }
    
    /**
     * Metodo utile a controllare se un operatore è attualmente autenticato o meno
     * @return valore booleano corrispondente
     */
    public static boolean loggato()
    {
        return !(nomeUtente==null)&&!(password==null);
    }
    
    /**
     * Metodo utile alla registrazione di nuovi utenti
     * @param server su cui effettuare la registrazione
     * @param nome del nuovo utente
     * @param cognome del nuovo utente
     * @param password1 : primo inserimento di una password
     * @param password2 : secondo inserimento della password di cui verrà
     * controllata la corrispondenza con la prima
     * @param email del nuovo utente
     * @param userID del nuovo utente 
     * @param codiceFiscale del nuovo utente
     * @param idCentro di interesse, utile a sviluppare rilevazioni mirate
     * @return valore booleano che definisce il successo o il fallimento della 
     * registrazione
     * @throws utenteException eventuali errori in fase di registrazione da server
     */
    public static boolean register(ServerCMInterface server, String nome, String cognome, String password1, String password2, 
            String email, String userID, String codiceFiscale, String idCentro) 
            throws utenteException
    {
        if(!password1.equals(password2))
            throw new utenteException("Le password non corrispondono.");
        
        if(!Pattern.compile(
                "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"+"[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"
        ).matcher(email).matches())
            throw new utenteException("La mail inserita non è valida.");
        
        if(codiceFiscale.length()!=16)
            throw new utenteException("Il codice fiscale non è valido.");
        
        try {
            return server.registraUtente(nome, cognome, codiceFiscale, email, userID, password1, idCentro);
        } catch (RemoteException ex) {
            throw new utenteException(ex.getLocalizedMessage());
        }
    }
    
    /**
     * @deprecated 
     * Metodo utile a verificare se alcuni parametri della registrazione
     * sono in uso da un utente già registrato
     * DEPRECATO in quanto gli errori di idoneità vengono gestiti a livello server
     * @param idUtente del nuovo utente
     * @param codiceFiscale del nuovo utente
     * @param email del nuovo utente
     * @return true se i parametri inseriti sono validi per una
     * nuova registrazione, false altrimenti
     * @throws utenteException eventuali errori specifici per le 
     * incongruenze dei parametri (es email già in uso) o di lettura file
     */
    @Deprecated
    public static boolean verificaIdoneita(String idUtente, String codiceFiscale, String email) 
            throws utenteException
    {
        try {
            FileReader read = new FileReader("data/OperatoriRegistrati.csv");
            Scanner input = new Scanner(read);
            while(input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split("#");
                    if(parts[4].equals(idUtente))
                        throw new utenteException("Esiste già un operatore con lo stesso ID");
                    if(parts[2].equalsIgnoreCase(codiceFiscale))
                        throw new utenteException("Esiste già un operatore con lo stesso codice fiscale");
                    if(parts[3].equalsIgnoreCase(email))
                        throw new utenteException("Email già in uso da un operatore");
                }
        }catch(FileNotFoundException ex){
            throw new utenteException("Impossibile trovare il file contenente gli utenti.");
        }
        return true;
    }
    
    /**
     * @deprecated 
     * metodo utile alla ricerca di un utente registrato su file
     * DEPRECATO in seguito all'implementazione del database, in quanto estrapola dati da file
     * @param idUtente da ricercare
     * @param pw da ricercare
     * @return stabilisce il successo o il fallimento della ricerca
     * @throws utenteException errore nella ricerca del file contenente gli 
     * utenti
     */
    @Deprecated
    public static String[] cerca(String idUtente, String pw)throws utenteException{
        try {
            FileReader read = new FileReader("data/OperatoriRegistrati.csv");
            Scanner input = new Scanner(read);
            while(input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split("#");
                    if (parts[4].contains(idUtente) && parts[5].contains(pw))
                        return parts;
                }
        }catch(FileNotFoundException ex){
            throw new utenteException("Impossibile trovare il file contenente gli utenti.");
        }
        return null;
    }
}