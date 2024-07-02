/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ClimateMonitoring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lenovo
 */
public class ServerCMTest {
    
    public ServerCMTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class ServerCM.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        try {
            ServerCM.main(args);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerCMTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of effettuaLogin method, of class ServerCM.
     */
    @Test
    public void testEffettuaLogin() throws Exception {
        System.out.println("effettuaLogin");
        String idUtente = "";
        String password = "";
        ServerCM instance = new ServerCM();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.effettuaLogin(idUtente, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registraUtente method, of class ServerCM.
     */
    @Test
    public void testRegistraUtente() throws Exception {
        System.out.println("registraUtente");
        String nome = "";
        String cognome = "";
        String codiceFiscale = "";
        String email = "";
        String idUtente = "";
        String password = "";
        String idCentro = "";
        ServerCM instance = new ServerCM();
        boolean expResult = false;
        boolean result = instance.registraUtente(nome, cognome, codiceFiscale, email, idUtente, password, idCentro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cambiaCentroUtente method, of class ServerCM.
     */
    @Test
    public void testCambiaCentroUtente() throws Exception {
        System.out.println("cambiaCentroUtente");
        String idUtente = "";
        String idCentro = "";
        ServerCM instance = new ServerCM();
        boolean expResult = false;
        boolean result = instance.cambiaCentroUtente(idUtente, idCentro);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaCentri method, of class ServerCM.
     */
    @Test
    public void testCercaCentri() throws Exception {
        System.out.println("cercaCentri");
        String criterio = "";
        ServerCM instance = new ServerCM();
        ArrayList expResult = null;
        ArrayList result = instance.cercaCentri(criterio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvaCentrto method, of class ServerCM.
     */
    @Test
    public void testSalvaCentrto() throws Exception {
        System.out.println("salvaCentrto");
        String idCentro = "";
        String nomeCentro = "";
        String indirizzoCentro = "";
        String capCentro = "";
        String cittaCentro = "";
        String statoCentro = "";
        ArrayList<String> localitaAbbinate = null;
        ServerCM instance = new ServerCM();
        boolean expResult = false;
        boolean result = instance.salvaCentro(idCentro, nomeCentro, indirizzoCentro, capCentro, cittaCentro, statoCentro, localitaAbbinate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaLocalita method, of class ServerCM.
     */
    @Test
    public void testCercaLocalita() throws Exception {
        System.out.println("cercaLocalita");
        String criterioNome = "";
        String criterioStato = "";
        ServerCM instance = new ServerCM();
        ArrayList expResult = null;
        ArrayList result = instance.cercaLocalita(criterioNome, criterioStato);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cercaLocalitaCoordinate method, of class ServerCM.
     */
    @Test
    public void testCercaLocalitaCoordinate() throws Exception {
        System.out.println("cercaLocalitaCoordinate");
        String latitudine = "";
        String logitudine = "";
        ServerCM instance = new ServerCM();
        ArrayList expResult = null;
        ArrayList result = instance.cercaLocalitaCoordinate(latitudine, logitudine);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
