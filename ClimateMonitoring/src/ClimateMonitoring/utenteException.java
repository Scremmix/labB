/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package ClimateMonitoring;

/**
 * Eccezioni causate dagli errori relativi alle azioni utente/operatore
 * @author Riva Samuele
 */
public class utenteException extends Exception{

    /**
     * Crea una nuova istanza di <code>utenteException</code> senza messaggio
     */
    public utenteException() {
    }

    /**
     * Crea una nuova istanza di <code>utenteException</code>
     * con messaggio di dettagio
     *
     * @param msg messaggio di dettaglio.
     */
    public utenteException(String msg) {
        super(msg);
    }
}
