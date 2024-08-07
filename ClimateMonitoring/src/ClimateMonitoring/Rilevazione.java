package ClimateMonitoring;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Costituisce una rilevazione e ne incapsula i dati
 * @author Riva Samuele
 * @author Scremin Alessandro
 * @author Zucchi Luca
 */
public class Rilevazione implements Serializable{
    private String centro; //esempio: Insubria
    private Long area=Long.valueOf(0); //esempio: 3178229 (ID per l'area di Como, IT)
    private ArrayList<Integer> valori;
    private String nota;
    private String data, ora;
    
    /**
     * Costruttore utile ad ottenere i dati di una particolare rilevazione
     * @param centro di monitoraggio che ha effettuato la rilevazione
     * @param area di interesse su cui è stata effettuata la rilevazione
     * @param valori ArrayList contenente i valori dei sette dati
     * "Vento" -> [0];
     * "Umidità" -> [1];
     * "Pressione" -> [2];
     * "Temperatura" -> [3];
     * "Precipitazioni" -> [4];
     * "Altezza dei ghiacciai" -> [5];
     * "Massa dei ghiacciai" -> [6];
     * @param nota della rilevazione
     * @throws rilevazioneException evidenzia eventuali errori relativi ai parametri
     */
    public Rilevazione(String centro, Long area, ArrayList<Integer> valori, String nota) throws rilevazioneException
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
        valori=new ArrayList<>();
        nota="";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        this.data=dtf.format(now);
        dtf = DateTimeFormatter.ofPattern("HH:mm");  
        this.ora=dtf.format(now);
    }

    public String getCentro() {
        return centro;
    }

    public Long getArea() {
        return area;
    }

    public ArrayList<Integer> getValori() {
        return valori;
    }

    public String getNota() {
        return nota;
    }

    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }
}