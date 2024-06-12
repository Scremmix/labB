/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package ClimateMonitoring;

/**
 * Eccezioni causate dagli errori relativi alla 
 * classe datiException (quindi al caricamento del file
 * delle località di rilevamento)
 * @author Riva Samuele
 */
public class datiStatoException extends Exception{

    /**
     * Creates a new instance of <code>datiStatoException</code> without detail
     * message.
     */
    public datiStatoException() {
    }

    /**
     * Constructs an instance of <code>datiStatoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public datiStatoException(String msg) {
        super(msg);
    }
}
