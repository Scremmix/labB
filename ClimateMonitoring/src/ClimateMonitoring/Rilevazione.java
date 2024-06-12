package ClimateMonitoring;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Salva le nuove rilevazioni su file e gestisce le eccezioni del form
 * @author Riva Samuele
 * @author Scremin Alessandro
 * @author Zucchi Luca
 */
public class Rilevazione {
    private String centro; //esempio: Insubria
    private Long area=Long.valueOf(0); //esempio: 3178229 (ID per l'area di Como, IT)
    private String valori; //esempio: 3=Temperatura, 4 valore
    private String nota;
    private String data, ora;
    
    /**
     * Costruttore utile ad ottenere i dati di una particolare rilevazione
     * @param centro particolare centro di monitoraggio
     * @param area area di interesse su cui sviluppare la rilevazione
     * @param valori la stringa contenente i valori dei sette dati
     * separati da una @
     * "Vento" -> [0];
     * "Umidità" -> [1];
     * "Pressione" -> [2];
     * "Temperatura" -> [3];
     * "Precipitazioni" -> [4];
     * "Altezza dei ghiacciai" -> [5];
     * "Massa dei ghiacciai" -> [6];
     * @param nota  informazioni riguardanti il dato
     * @throws rilevazioneException evidenzia eventuali errori relativi ai parametri
     */
    public Rilevazione(String centro, Long area, String valori, String nota) throws rilevazioneException
    {
        this.centro=centro;
        this.area=area;
        if(this.area==null||this.area<0)
            throw new rilevazioneException("Nessuna area selezionata.");
        this.valori=valori;
        this.nota=nota;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.data=dtf.format(now);
        dtf = DateTimeFormatter.ofPattern("HH:mm");  
        this.ora=dtf.format(now);
    }
    
    /**
     * Costruttore utile ad inizializzare i dati con valori di default
     */
    public Rilevazione()
    {
        centro="";
        area=Long.valueOf(0);
        valori="";
        nota="";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.data=dtf.format(now);
        dtf = DateTimeFormatter.ofPattern("HH:mm");  
        this.ora=dtf.format(now);
    }
    
    /**
     * Salva i dati della rilevazione su file
     * @throws rilevazioneException evidenzia errori nel corso del salvataggio
     */
    public void salvaRilevazione() throws rilevazioneException{
        try (FileWriter writer = new FileWriter("data/ParametriClimatici.csv",true)) {
            writer.write(
                    "\n"+centro+"#"+area.toString()+"#"+data+"#"+ora+"#"+valori+"#"+nota
                );
            writer.close();
        }
        catch(IOException e){
             throw new rilevazioneException("Errore durante il salvataggio dei dati climatici.");
        }
    }
}