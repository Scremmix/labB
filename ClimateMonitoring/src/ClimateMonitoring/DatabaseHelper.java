/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ClimateMonitoring;

import java.sql.*;

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
    
    public boolean getConnection()
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
    
    public boolean databaseInit()
    {
        try{
            Statement stmt = connection.createStatement();
            String currentQuery  = "DROP TABLE CentroMonitoraggio IF EXISTS;"
                        + "DROP TABLE CoordinateMonitoraggio IF EXISTS"
                        + "DROP TABLE OperatoriRegistrati IF EXISTS"
                        + "DROP TABLE ParametriClimatici IF EXISTS"
                        + "DROP TABLE abbinamentiCentriLocalita IF EXISTS;";
                stmt.executeUpdate(currentQuery);
            
            
            currentQuery =  "CREATE TABLE CentroMonitoraggio ( "
                    + "idCentro INTEGER PRIMARY KEY, "
                    + "nomeCentro TEXT, "
                    + "indirizzoCentro TEXT, "
                    + "capCentro INTEGER, "
                    + "cittaCentro TEXT, "
                    + "statoCentro TEXT );";
            stmt.executeUpdate(currentQuery);
            
            currentQuery = "CREATE TABLE CoordinateMonitoraggio("
                    + "idLocalita INTEGER PRIMARY KEY, "
                    + "nomeLocalita TEXT, "
                    + "statoLocalita TEXT, "
                    + "latitudineLocalita NUMERIC, "
                    + "longitudineLocalita NUMERIC);";
            stmt.executeUpdate(currentQuery);
            
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
            
            currentQuery = "CREATE TABLE ParametriClimatici("
                    + "idRegistrazione SERIAL PRIMARY KEY, "
                    + "centroRegistrazione INTEGER, "
                    + "localitaRegistrazione INTEGER, "
                    + "ventoParametro INTEGER, "
                    + "umiditaParametro INTEGER, "
                    + "precatmParametro INTEGER, "
                    + "tempParametro INTEGER, "
                    + "precipParametro INTEGER, "
                    + "altghiParametro INTEGER, "
                    + "massaghiParametro INTEGER, "
                    + "noteRegistrazione TEXT, "
                    + "FOREIGN KEY (centroRegistrazione) REFERENCES CentroMonitoraggio(idCentro) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (localitaRegistrazone) REFERENCES CoordinateMonitoraggio(idLocalita) ON DELETE CASCADE ON UPDATE CASCADE);";
            stmt.executeUpdate(currentQuery);
            
            currentQuery = "CREATE TABLE abbinamentiCentriLocalita("
                    + "idAbbinamento SERIAL PRIMARY KEY, "
                    + "centroAbbinamento INTEGER, "
                    + "localitaAbbinamento INTEGER, "
                    + "FOREIGN KEY (centroAbbinamento) REFERENCES CentroMonitoraggio(idCentro) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "FOREIGN KEY (localitaAbbinamento) REFERENCES CoordinateMonitoraggio(idLocalita) ON DELETE CASCADE ON UPDATE CASCADE);";
            stmt.executeUpdate(currentQuery);
            
            stmt.close();
            return true;
        } catch (SQLException ex) {
            System.err.println("Errore imprevisto nella connessione al database");
            return false;
        }
    }
}
