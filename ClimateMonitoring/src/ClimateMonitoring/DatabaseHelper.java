/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClimateMonitoring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
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
    
    public synchronized boolean getConnection()
    {
        try{
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
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
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
        return true;
    }
    
    public synchronized boolean databaseInit()
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
            ex.printStackTrace();
            return false;
        }
    }
    
    private synchronized boolean popolaDatabase()
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
            ex.printStackTrace();
            return false;
        } catch (FileNotFoundException ex) {
            System.err.println("File csv non trovato.");
            ex.printStackTrace();
            return false;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
