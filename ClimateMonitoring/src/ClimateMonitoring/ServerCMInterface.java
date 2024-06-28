package ClimateMonitoring;

import java.rmi.*;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author alesc
 */
public interface ServerCMInterface extends Remote{
    
    public ArrayList<String> effettuaLogin (String idUtente, String password) throws RemoteException;
    public boolean registraUtente (String nome, String cognome, String codiceFiscale, 
            String email, String idUtente, String password, String idCentro) throws RemoteException;
    public boolean cambiaCentroUtente(String idUtente, String idCentro) throws RemoteException;
    
    public ArrayList<String[]> cercaCentri (String criterio) throws RemoteException;
    public boolean salvaCentro(String idCentro, String nomeCentro, String indirizzoCentro, 
            String capCentro, String cittCentro, String statoCentro, ArrayList<String> localitaAbbinate) throws RemoteException;
    
    public ArrayList<String[]> cercaLocalita (String criterioNome, String criterioStato) throws RemoteException;
    public ArrayList<String[]> cercaLocalitaCoordinate (String latitudine, String logitudine) throws RemoteException;
    
}
