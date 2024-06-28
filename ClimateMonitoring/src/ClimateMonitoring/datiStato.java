/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClimateMonitoring;

import static java.lang.Math.min;
import java.util.ArrayList;

/**
 * @deprecated 
 * Classe riguardante i dati dello Stato, ossia le righe del 
 * file contenente le località accomunate dallo stesso Stato
 * @author Riva Samuele
 * @author Scremin Alessandro
 * @author Zucchi Luca
 */
@Deprecated
public class datiStato 
{
    private String nomeStato="";
    private ArrayList<String[]> elencoDati;
    
    /**
     * Metodo utile alla definizione del nome dello stato di interesse
     * @param nomeStato : nome dello stato in questione
     */
    public datiStato(String nomeStato)
    {
        this.nomeStato=nomeStato;
        elencoDati= new ArrayList<>();
    }
    
    /**
     * Metodo utile all'inserimento della località di interesse
     * @param dato relativo alla località di interesse
     * @throws datiStatoException eccezione che stabilisce se i dati inseriti
     * siano o meno in un formato invalido
     */
    public void inserireLocalita(String[] dato) throws datiStatoException
    {
        if(dato.length==6)
            elencoDati.add(dato);
        else
            throw new datiStatoException("Formato dati non valido.");
    }
    
    /**
     * Metodo utile ad ottenere il nome dello stato in analisi
     * @return nome dello stato
     */
    public String daiNomeStato()
    {return this.nomeStato;}
    
    /**
     * Metodo utile alla ricerca di una località per nome ascii
     * @param nomeAscii nome ascii dello stato
     * @return risultato/i della ricerca
     */
    public ArrayList<String[]> cerca(String nomeAscii)
    {
        ArrayList<String[]> risultato= new ArrayList<>();
        int primoEl= ricercaPrecisa(nomeAscii, 0, elencoDati.size()-1);
        if(primoEl==-1)
            return null;
        int tolleranza=0;
        try{
            while(elencoDati.get(primoEl-tolleranza)[2].
                       substring(0, 
                               min(nomeAscii.length(),elencoDati.get(primoEl-tolleranza)[2].length())
                       ).
                       compareToIgnoreCase(nomeAscii)
                  ==0)
            {
                risultato.add(0, elencoDati.get(primoEl-tolleranza));
                tolleranza++;
            }
        }catch(IndexOutOfBoundsException e){} 
        tolleranza=1;
        try{
            while(elencoDati.get(primoEl+tolleranza)[2].
                       substring(0, 
                               min(nomeAscii.length(),elencoDati.get(primoEl-tolleranza)[2].length())
                       ).
                       compareToIgnoreCase(nomeAscii)
                  ==0)
            {
                risultato.add(elencoDati.get(primoEl+tolleranza));
                tolleranza++;
            }
        }catch(IndexOutOfBoundsException e){} 
        return risultato;
    }
    
    /**
     * Effettua una ricerca binaria ricorsiva  della località (in nome ascii)
     * tra la riga "min" e la riga "max"
     * @param nome ascii della località
     * @param min : riga "min"
     * @param max : riga "max"
     * @return risultato della ricerca
     */
    public int ricercaPrecisa(String nome,int min, int max)
    {
        int media=(max+min)/2;
        
        String nomeInPos =elencoDati.get(media)[2];
        int risCompare=nomeInPos.substring(0, min(nomeInPos.length(),nome.length())).compareToIgnoreCase(nome);
        
        if(risCompare<0)
        {
            if(media==min)
                return -1;
            else
                return ricercaPrecisa(nome, media, max);
        }
        else if(risCompare>0)
        {
            if(media==max)
                return -1;
            else
                return ricercaPrecisa(nome, min, media);
        }
        else return media;
    }
}
