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
     * Creates a new instance of <code>utenteException</code> without detail
     * message.
     */
    public utenteException() {
    }

    /**
     * Constructs an instance of <code>utenteException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public utenteException(String msg) {
        super(msg);
    }
}
