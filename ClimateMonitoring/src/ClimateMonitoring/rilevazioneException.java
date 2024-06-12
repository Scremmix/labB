/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package ClimateMonitoring;

/**
 * Eccezioni causate dagli errori relativi alle
 * nuove rilevazioni da salvare
 * @author Scremin Alessandro
 */
public class rilevazioneException extends Exception{

    /**
     * Creates a new instance of <code>rilevazioneException</code> without
     * detail message.
     */
    public rilevazioneException() {}

    /**
     * Constructs an instance of <code>rilevazioneException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public rilevazioneException(String msg) {
        super(msg);
    }
}
