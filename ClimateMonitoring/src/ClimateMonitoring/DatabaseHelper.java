/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClimateMonitoring;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ottengo la connesione a postgres e stampo se sono riuscito a connettermi o no.
 * per l'inizializzazione del database vengono scritte tutte le query e vengono create,
 * dopo viene popolato il database, che legge da file i contenuti e li traduce 
 * nei risultati di ogni tabella.
 * 
 * @author alesc
 */
public class DatabaseHelper 
{
    private String url="";
    private String user="";
    private String password="";
    private Connection connection=null;

    public DatabaseHelper(String url, String user, String password) {
        this.url=url;
        this.user=user;
        this.password=password;
    }
    
    public synchronized ResultSet getUtente(String idUtente) throws SQLException
    {
        PreparedStatement stmt = null;
        String query = "SELECT codiceOperatore, passwordOperatore, nomeOperatore, cognomeOperatore, centroOperatore "
                + "FROM OperatoriRegistrati WHERE codiceOperatore = '" + idUtente + "';";
        stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if( !rs.isBeforeFirst() ) return null;
        return rs;
    }
    
    public synchronized ResultSet cercaLocalita(String nome, String stato) throws SQLException
    {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM coordinateMonitoraggio "
                + "WHERE LOWER(nomeLocalita) LIKE '%"+nome.toLowerCase()+"%' AND LOWER(statoLocalita) LIKE '%"+stato.toLowerCase()+"%'"
                + "ORDER BY statoLocalita, nomeLocalita";
        stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if( !rs.isBeforeFirst() ) return null;
        return rs;
    }
    
    public synchronized ResultSet cercaLocalitaCoordinate(String latitudine, String longitudine) throws SQLException
    {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM coordinateMonitoraggio "
                + "WHERE latitudineLocalita BETWEEN "+(Double.parseDouble(latitudine)-0.5)+" AND "+(Double.parseDouble(latitudine)+0.5)+" "
                + "AND longitudineLocalita BETWEEN "+(Double.parseDouble(longitudine)-0.5)+" AND "+(Double.parseDouble(longitudine)+0.5)+" "
                + "ORDER BY nomeLocalita";
        stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if( !rs.isBeforeFirst() ) return null;
        return rs;
    }
    
    public synchronized ResultSet cercaCentri(String criterio) throws SQLException
    {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM centroMonitoraggio WHERE LOWER(nomeCentro) LIKE '%" + criterio.toLowerCase() + "%';";
        stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if( !rs.isBeforeFirst() ) return null;
        return rs;
    }
    
    public synchronized boolean cambiaCentrtoUtente(String idUtente, String idCentro) throws SQLException
    {
        PreparedStatement pStmt;
        pStmt = connection.prepareStatement("SELECT * FROM centroMonitoraggio WHERE idCentro = "+Integer.valueOf(idCentro)+";");
        if( !pStmt.executeQuery().isBeforeFirst() ) throw new SQLException("Non esiste nessun centro con questo codice");
        
        pStmt = connection.prepareStatement("SELECT * FROM OperatoriRegistrati WHERE codiceOperatore = "+ Integer.valueOf(idUtente) +";");
        if( !pStmt.executeQuery().isBeforeFirst() ) throw new SQLException("Non esise nessun operatore con questo ID");
        
        pStmt = connection.prepareStatement("UPDATE operatoriregistrati SET centroOperatore = "+Integer.valueOf(idCentro)
                +" WHERE codiceOperatore = "+ Integer.valueOf(idUtente) +";");
        pStmt.executeUpdate();
        pStmt.close();
        return true;
    }
    
    public synchronized ResultSet getRilevazioniLocalita(String idLocalita) throws SQLException
    {
        PreparedStatement stmt = null;
        String query = "SELECT * FROM parametriclimatici "
                + "WHERE localitaRegistrazione = " + Integer.valueOf(idLocalita) + " "
                + "ORDER BY idRegistrazione DESC;";
        stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        if( !rs.isBeforeFirst() ) return null;
        return rs;
    }
    
    /**
     * l'inserimento di un nuovo c'entro subisce fondamentali controlli che hanno la funzione
     * di abbinare i centri di registrazione con i luoghi in cui si possono svolgere le 
     * registrazioni in questione. viene ritornato vero se l'operazione è andata a buon fine
     * @param idCentro
     * @param nomeCentro
     * @param indirizzoCentro
     * @param capCentro
     * @param cittaCentro
     * @param statoCentro
     * @param localitaAbbinate
     * @return
     * @throws SQLException
     */
    public synchronized boolean inserisciNuovoCentro(String idCentro, String nomeCentro, String indirizzoCentro, 
            String capCentro, String cittaCentro, String statoCentro, ArrayList<String> localitaAbbinate) throws SQLException
    {
        PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM centroMonitoraggio WHERE idCentro = "+Integer.valueOf(idCentro)+";");
        if( pStmt.executeQuery().isBeforeFirst() ) throw new SQLException("Esiste già un centro con lo stesso codice");
                
        pStmt = connection.prepareStatement("INSERT INTO centroMonitoraggio "
                    + "(idCentro, nomeCentro, indirizzoCentro, capCentro, cittaCentro, statoCentro) "
                    + "VALUES(?,?,?,?,?,?)");
        
        pStmt.setInt(1, Integer.parseInt(idCentro));
        pStmt.setString(2, nomeCentro);
        pStmt.setString(3, indirizzoCentro);
        pStmt.setInt(4, Integer.parseInt(capCentro));
        pStmt.setString(5, cittaCentro);
        pStmt.setString(6, statoCentro);
        
        pStmt.executeUpdate();
        
        for(String idLocalita : localitaAbbinate)
        {
            pStmt = connection.prepareStatement("INSERT INTO abbinamentiCentriLocalita "
                    + "(centroAbbinamento, localitaAbbinamento) "
                    + "VALUES(?,?)");
        
            pStmt.setInt(1, Integer.parseInt(idCentro));
            pStmt.setInt(2, Integer.parseInt(idLocalita));
        
            pStmt.executeUpdate();
        }
        pStmt.close();
        return true;
    }
    
    public synchronized boolean inserisciNuovoUtente(String nome, String cognome, String codiceFiscale, 
            String email, String idUtente, String password, String idCentro) throws SQLException
    {
        
        PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM OperatoriRegistrati WHERE LOWER(cfOperatore) = '"+codiceFiscale.toLowerCase()+"';");
        if( pStmt.executeQuery().isBeforeFirst() ) throw new SQLException("Esiste già un operatore con lo stesso codice fiscale");
        
        pStmt = connection.prepareStatement("SELECT * FROM OperatoriRegistrati WHERE codiceOperatore = "+ Integer.valueOf(idUtente) +";");
        if( pStmt.executeQuery().isBeforeFirst() ) throw new SQLException("Esiste già un operatore con lo stesso ID");
        
        pStmt = connection.prepareStatement("SELECT * FROM OperatoriRegistrati WHERE LOWER(mailOperatore) = '"+ email.toLowerCase() +"';");
        if( pStmt.executeQuery().isBeforeFirst() ) throw new SQLException("Email già in uso da un operatore");
                
        pStmt = connection.prepareStatement("INSERT INTO OperatoriRegistrati "
                    + "(nomeOperatore, cognomeOperatore, cfOperatore, mailOperatore, codiceOperatore, passwordOperatore, centroOperatore) "
                    + "VALUES(?,?,?,?,?,?,?)");
      
        pStmt.setString(1, nome);
        pStmt.setString(2, cognome);
        pStmt.setString(3, codiceFiscale);
        pStmt.setString(4, email);
        pStmt.setInt(5, Integer.parseInt(idUtente));
        pStmt.setString(6, password);
        pStmt.setInt(7, Integer.parseInt(idCentro));
        pStmt.executeUpdate();
        pStmt.close();
        
        return true;
    }
    
    public synchronized ResultSet cercaLocalitaAbbinate (String idCentro) throws SQLException
    {
        PreparedStatement pStmt;
        pStmt = connection.prepareStatement("SELECT * FROM centroMonitoraggio WHERE idCentro = "+Integer.valueOf(idCentro)+";");
        if( !pStmt.executeQuery().isBeforeFirst() ) throw new SQLException("Non esiste nessun centro con questo codice");
        
        pStmt = connection.prepareStatement("SELECT idlocalita, nomelocalita, statolocalita, latitudinelocalita, longitudinelocalita "
                + "FROM abbinamenticentrilocalita JOIN coordinatemonitoraggio ON localitaabbinamento = idlocalita "
                + "WHERE centroabbinamento = "+Integer.valueOf(idCentro)+";");
        ResultSet rs = pStmt.executeQuery();
        if( !rs.isBeforeFirst() ) return null;
        return rs;
    }
    
    public synchronized boolean salvaRilevazione(Rilevazione r) throws SQLException
    {
        PreparedStatement pStmt = connection.prepareStatement("INSERT INTO parametriclimatici "
                + "(centroRegistrazione, localitaRegistrazione, dataRegistrazione, "
                + "ventoParametro, umiditaParametro, precatmParametro, tempParametro, "
                + "precipParametro, altghiParametro, massaghiParametro, noteRegistrazione) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                            
        pStmt.setInt(1,Integer.parseInt(r.getCentro()));     
        pStmt.setInt(2,r.getArea().intValue());
        pStmt.setString(3,r.getData()+" "+r.getOra());     
        pStmt.setInt(4,r.getValori().get(0));     
        pStmt.setInt(5,r.getValori().get(1));     
        pStmt.setInt(6,r.getValori().get(2));     
        pStmt.setInt(7,r.getValori().get(3));     
        pStmt.setInt(8,r.getValori().get(4));     
        pStmt.setInt(9,r.getValori().get(5));     
        pStmt.setInt(10,r.getValori().get(6));
        pStmt.setString(11, r.getNota());
        
        pStmt.executeUpdate();
        pStmt.close();
        return true;
    }
    
    /**
     * connessione al database sql con stampa di un messaggio di avvenuta connessione
     * @return true se la connessione al db viene effettuata con successo, false altrimenti
     * @throws SQLException in caso di errori con la connessione al db
     */
    public synchronized boolean getConnection() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:postgresql://"+url,user,password);
        if(connection!=null)
        {
            System.out.println("Connessione al database PostgreSQL effettuata con successo.");
            return true;
        }
        else
        {
            System.err.println("Connessione al database PostgreSQL fallita.");
            return false;
        }
    }
    
    /**
     * qui viene reinizzializzato il database e sostituito con le componenti di interesse
     * @return true se l'operazione ha avuto successo
     * @throws SQLException in caso di errori con il db
     */
    public synchronized boolean databaseInit() throws SQLException
    {
        System.out.println("Reinizializzazione del database in corso...");
        try{
            Statement stmt = connection.createStatement();
            String currentQuery  = "DROP TABLE IF EXISTS OperatoriRegistrati;"
                        + "DROP TABLE IF EXISTS ParametriClimatici;"
                        + "DROP TABLE IF EXISTS abbinamentiCentriLocalita;"
                        + "DROP TABLE IF EXISTS CoordinateMonitoraggio;"
                        + "DROP TABLE IF EXISTS CentroMonitoraggio;";
            stmt.executeUpdate(currentQuery);
            stmt.close();
            
            stmt = connection.createStatement();
            currentQuery =  "CREATE TABLE CentroMonitoraggio ( "
                    + "idCentro INTEGER PRIMARY KEY, "
                    + "nomeCentro TEXT, "
                    + "indirizzoCentro TEXT, "
                    + "capCentro INTEGER, "
                    + "cittaCentro TEXT, "
                    + "statoCentro TEXT );";
            stmt.executeUpdate(currentQuery);
            stmt.close();
            
            stmt = connection.createStatement();
            currentQuery = "CREATE TABLE CoordinateMonitoraggio("
                    + "idLocalita INTEGER PRIMARY KEY, "
                    + "nomeLocalita TEXT, "
                    + "statoLocalita TEXT, "
                    + "latitudineLocalita NUMERIC, "
                    + "longitudineLocalita NUMERIC);";
            stmt.executeUpdate(currentQuery);
            stmt.close();
            
            stmt = connection.createStatement();
            currentQuery = "CREATE TABLE OperatoriRegistrati("
                    + "nomeOperatore TEXT, "
                    + "cognomeOperatore TEXT, "
                    + "cfOperatore TEXT, "
                    + "mailOperatore TEXT, "
                    + "codiceOperatore INTEGER PRIMARY KEY, "
                    + "passwordOperatore TEXT NOT NULL, "
                    + "centroOperatore INTEGER, "
                    + "FOREIGN KEY (centroOperatore) REFERENCES CentroMonitoraggio(idCentro) ON DELETE CASCADE ON UPDATE CASCADE);";
            stmt.executeUpdate(currentQuery);
            stmt.close();
            
            stmt = connection.createStatement();
            currentQuery = "CREATE TABLE ParametriClimatici("
                    + "idRegistrazione SERIAL PRIMARY KEY, "
                    + "centroRegistrazione INTEGER, "
                    + "localitaRegistrazione INTEGER, "
                    + "dataRegistrazione TEXT, "
                    + "ventoParametro INTEGER, "
                    + "umiditaParametro INTEGER, "
                    + "precatmParametro INTEGER, "
                    + "tempParametro INTEGER, "
                    + "precipParametro INTEGER, "
                    + "altghiParametro INTEGER, "
                    + "massaghiParametro INTEGER, "
                    + "noteRegistrazione TEXT, "
                    + "FOREIGN KEY (centroRegistrazione) REFERENCES CentroMonitoraggio(idCentro) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (localitaRegistrazione) REFERENCES CoordinateMonitoraggio(idLocalita) ON DELETE CASCADE ON UPDATE CASCADE);";
            stmt.executeUpdate(currentQuery);
            stmt.close();
            
            stmt = connection.createStatement();
            currentQuery = "CREATE TABLE abbinamentiCentriLocalita("
                    + "idAbbinamento SERIAL PRIMARY KEY, "
                    + "centroAbbinamento INTEGER, "
                    + "localitaAbbinamento INTEGER, "
                    + "FOREIGN KEY (centroAbbinamento) REFERENCES CentroMonitoraggio(idCentro) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (localitaAbbinamento) REFERENCES CoordinateMonitoraggio(idLocalita) ON DELETE CASCADE ON UPDATE CASCADE);";
            stmt.executeUpdate(currentQuery);
            stmt.close();
            
            System.out.println("Database resettato.");
            System.out.println("Popolazione del database in corso...");
            popolaDatabase();
            System.out.println("Database popolato.");
            
            return true;
        } catch (SQLException ex) {
            System.err.println("Errore imprevisto nella connessione al database");
            throw ex;
        }
    }
    
    /**
     * popolazione del database tramite inserimento dei dati forniti
     * @return true se l'operazione ha avuto successo
     * @throws SQLException in caso di errori con il db
     */
    private synchronized boolean popolaDatabase() throws SQLException
    {
        try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO CentroMonitoraggio "
                    + "(idCentro, nomeCentro, indirizzoCentro, capCentro, cittaCentro, statoCentro) "
                    + "VALUES(?,?,?,?,?,?)");
            FileReader read = new FileReader("data/CentroMonitoraggio.csv");
            Scanner input = new Scanner(read);
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                String[] parts = line.split("#");
                if (parts.length>1)
                {
                    pStmt.setInt(1, Integer.parseInt(parts[0]));
                    pStmt.setString(2, parts[1]);
                    pStmt.setString(3, parts[2]);
                    pStmt.setInt(4, Integer.parseInt(parts[3]));
                    pStmt.setString(5, parts[4]);
                    pStmt.setString(6, parts[5]);

                    pStmt.executeUpdate();
                }
            }
            input.close();
            read.close();
            
            pStmt = connection.prepareStatement("INSERT INTO CoordinateMonitoraggio "
                    + "(idLocalita, nomeLocalita, statoLocalita, latitudineLocalita, longitudineLocalita) "
                    + "VALUES(?,?,?,?,?)");
            read = new FileReader("data/CoordinateMonitoraggio.csv");
            input = new Scanner(read);
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                String[] parts = line.split("#");
                String[] coordinate = parts[5].split(", ");
                if (parts.length>1)
                {
                    pStmt.setInt(1, Integer.parseInt(parts[0]));
                    pStmt.setString(2, parts[2]);
                    pStmt.setString(3, parts[4]);
                    pStmt.setObject(4, Double.valueOf(coordinate[0]),java.sql.Types.NUMERIC);
                    pStmt.setObject(5, Double.valueOf(coordinate[1]),java.sql.Types.NUMERIC);

                    pStmt.executeUpdate();
                }
            }
            input.close();
            read.close();
            
            pStmt = connection.prepareStatement("INSERT INTO OperatoriRegistrati "
                    + "(nomeOperatore, cognomeOperatore, cfOperatore, mailOperatore, codiceOperatore, passwordOperatore, centroOperatore) "
                    + "VALUES(?,?,?,?,?,?,?)");
            read = new FileReader("data/OperatoriRegistrati.csv");
            input = new Scanner(read);
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                String[] parts = line.split("#");
                if (parts.length>1)
                {
                    pStmt.setString(1, parts[0]);
                    pStmt.setString(2, parts[1]);
                    pStmt.setString(3, parts[2]);
                    pStmt.setString(4, parts[3]);
                    pStmt.setInt(5, Integer.parseInt(parts[4]));
                    pStmt.setString(6, parts[5]);
                    pStmt.setInt(7, Integer.parseInt(parts[6]));

                    pStmt.executeUpdate();
                }
            }
            input.close();
            read.close();
            
            pStmt = connection.prepareStatement("INSERT INTO ParametriClimatici "
                    + "(centroRegistrazione, localitaRegistrazione, dataRegistrazione, "
                    + "ventoParametro, umiditaParametro, precatmParametro, tempParametro, precipParametro, altghiParametro, massaghiParametro, "
                    + "noteRegistrazione) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            read = new FileReader("data/ParametriClimatici.csv");
            input = new Scanner(read);
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                String[] parts = line.split("#");
                String[] valori = parts[4].split("@");
                if (parts.length>1)
                {
                    pStmt.setInt(1, Integer.parseInt(parts[0]));
                    pStmt.setInt(2, Integer.parseInt(parts[1]));
                    pStmt.setString(3, parts[2] + " " + parts[3]);
                    
                    pStmt.setInt(4, Integer.parseInt(valori[0]));
                    pStmt.setInt(5, Integer.parseInt(valori[1]));
                    pStmt.setInt(6, Integer.parseInt(valori[2]));
                    pStmt.setInt(7, Integer.parseInt(valori[3]));
                    pStmt.setInt(8, Integer.parseInt(valori[4]));
                    pStmt.setInt(9, Integer.parseInt(valori[5]));
                    pStmt.setInt(10, Integer.parseInt(valori[6]));
                    
                    if(parts[5].equals(""))
                        pStmt.setNull(11, java.sql.Types.BLOB);
                    else
                        pStmt.setString(11, parts[5]);

                    pStmt.executeUpdate();
                }
            }
            input.close();
            read.close();
            
            pStmt = connection.prepareStatement("INSERT INTO abbinamentiCentriLocalita "
                    + "(centroAbbinamento, localitaAbbinamento) "
                    + "VALUES(?,?)");
            read = new FileReader("data/CentroMonitoraggio.csv");
            input = new Scanner(read);
            while (input.hasNextLine()) 
            {
                String line = input.nextLine();
                String[] parts = line.split("#");
                String[] listaLocalita = parts[6].split("@");
                if (parts.length>1)
                {
                    for(String localita : listaLocalita)
                    {
                        pStmt.setInt(1, Integer.parseInt(parts[0]));
                        pStmt.setInt(2, Integer.parseInt(localita));
                        pStmt.executeUpdate();
                    }
                }
            }
            input.close();
            read.close();
            
            pStmt.close();
            return true;
        } catch (SQLException ex) {
            System.err.println("Errore imprevisto nella connessione al database");
            throw ex;
        } catch (FileNotFoundException ex) {
            System.err.println("File csv non trovato.");
            return false;
        } catch (IOException ex) {
            System.err.println("Errore con la gestione file.");
            return false;
        }
    }
}
