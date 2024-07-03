/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package ClimateMonitoring;

/**
 * Eccezioni causate dagli errori relativi alle rilevazioni
 * @author Scremin Alessandro
 */
public class rilevazioneException extends Exception{

    /**
     * Crea una nuova istanza di <code>rilevazioneException</code> senza messaggio.
     */
    public rilevazioneException() {}

    /**
     * Crea una nuova istanza di <code>rilevazioneException</code> 
     * con un messaggio di dettaglio
     *
     * @param msg messaggio di dettaglio.
     */
    public rilevazioneException(String msg) {
        super(msg);
    }
}
