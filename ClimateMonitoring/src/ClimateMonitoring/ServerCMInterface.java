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
    
}
