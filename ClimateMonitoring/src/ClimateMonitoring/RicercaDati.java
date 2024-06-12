/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClimateMonitoring;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Classe utile alla ricerca di località tramite nomi o coordinate
 * e a mostrarne i relativi dat chiamando la classe DataDisplay
 * @author Scremin Alessandro
 * @author Riva Samuele
 */
public class RicercaDati extends javax.swing.JFrame {

    private ArrayList<datiStato> mondoNomi=null;
    private ArrayList<String[]> mondoCoord=null;
    
    /**
     * Metodo utile alla ricerca di stati tramite il relativo nome
     * @param nomeStato nome dello stato in questione
     * @return l'oggetto datiStato del relativo stato se esistente, null altrimenti
     */
    public datiStato cercaStato(String nomeStato)
    {
        if(mondoNomi!=null)
        {
            for(datiStato stato: mondoNomi)
                if(stato.daiNomeStato().equals(nomeStato))
                    return stato;
            return null;
        }
        else{return null;}
    }
    
    /**
     * Modifica il metodo di ricerca
     * @param mod: 1 per la ricerca nome/Stato, 2 per la ricerca coordinate
     */
    public void cambiaModalita(int mod)
    {
        if(this.modalita!=mod)
        {
            resetVarFile(this.modalita);
            this.modalita=mod;
            switch(this.modalita)
            {
                case 1 -> {
                    Campo1.setText("Nome località (inglese)");
                    Campo2.setText("Nome Stato (inglese)");
                    caricaPerNomi();
                }
                case 2 -> {
                    Campo1.setText("Latitudine (N/S)");
                    Campo2.setText("Longitudine (E/O)");
                    caricaPerCoord();
                }
            }
        }
    }

    /**
     * Caricamento su variabile del file relativo alle località di monitoraggio
     * pronto per la ricerca per nome/Stato
     */
    public void caricaPerNomi()
    {
        mondoNomi=new ArrayList<>();
        try {
                FileReader read = new FileReader("data/CoordinateMonitoraggio.csv");
                Scanner input = new Scanner(read);
                datiStato temp=null;
                while(input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] parts = line.split("#");
                    temp=cercaStato(parts[4]);
                    if(temp==null)
                    {
                        temp=new datiStato(parts[4]);
                        mondoNomi.add(temp);
                    }
                    try{
                        temp.inserireLocalita(parts);
                    } catch (datiStatoException ex) {
                        JOptionPane.showMessageDialog(rootPane, "Errore critico: impossibile caricare la riga con ID: "+parts[0]+".");
                    }
                }
            }
        catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(rootPane, "Errore critico: impossibile trovare il file contenente le stazioni di monitoraggio.");
        }
    }

    /**
     * Caricamento su variabile del file relativo alle località di monitoraggio
     * pronto per la ricerca per coordinate
     */
    public void caricaPerCoord()
    {
        mondoCoord=new ArrayList<>();
        try {
                FileReader read = new FileReader("data/CoordinateMonitoraggio.csv");
                Scanner input = new Scanner(read);
                while(input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] parts = line.split("#");
                    mondoCoord.add(parts);
                }
            }
        catch(FileNotFoundException ex){
                JOptionPane.showMessageDialog(rootPane, "Errore critico: impossibile trovare il file contenente le stazioni di monitoraggio.");
        }
    }
    
    /**
     * Resetta le variabili contenenti la copia del file delle località
     * @param tipo 1: resetta nome/Stato, 2: resetta coordinate
     */
    public void resetVarFile(int tipo)
    {
        switch(tipo)
        {
            case 1 -> {
                mondoNomi=null;
            }
            case 2 -> {
                mondoCoord=null;
            }
        }
    }

    /**
     * Costruttore di default
     */
    public RicercaDati() {
        initComponents();
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        RicercaDati.this.revalidate();
        RicercaDati.this.repaint();
    }
    private int modalita;
    //1= stato e nome
    //2= coordinate
    
    /**
     * Metodo utile a richiamare la modalità di ricerca attualmente attiva
     * @return modalità attiva
     */
    public int modalitaAttiva()
    {return this.modalita;}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Valore1 = new javax.swing.JTextField();
        Valore2 = new javax.swing.JTextField();
        cercaLocalitaButton = new javax.swing.JButton();
        mostraRilevazioniButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataToDisplayTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        areaToDisplay = new javax.swing.JLabel();
        Campo1 = new javax.swing.JLabel();
        Campo2 = new javax.swing.JLabel();
        annullaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Valore1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Valore1ActionPerformed(evt);
            }
        });

        cercaLocalitaButton.setText("Cerca");
        cercaLocalitaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cercaLocalitaButtonActionPerformed(evt);
            }
        });

        mostraRilevazioniButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        mostraRilevazioniButton.setText("Mostra dati");
        mostraRilevazioniButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostraRilevazioniButtonActionPerformed(evt);
            }
        });

        dataToDisplayTable.setModel(ddtm);
        dataToDisplayTable.setColumnSelectionAllowed(true);
        dataToDisplayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dataToDisplayTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dataToDisplayTable);
        dataToDisplayTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Area selezionata:");

        areaToDisplay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Campo1.setText("Campo 1");

        Campo2.setText("Campo2");

        annullaButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        annullaButton.setText("Annulla");
        annullaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(annullaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(areaToDisplay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mostraRilevazioniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Valore1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Campo2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Valore2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cercaLocalitaButton)
                                .addContainerGap(117, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Campo1)
                    .addComponent(Campo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Valore1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Valore2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cercaLocalitaButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(annullaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(areaToDisplay)
                        .addComponent(mostraRilevazioniButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        Valore1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Valore1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Valore1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Valore1ActionPerformed

    /**
     * Se i parametri sono validi, effettua la ricerca in base alla modalità
     * attiva e ne mostra i dati in tabella
     * @param evt 
     */
    private void cercaLocalitaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cercaLocalitaButtonActionPerformed
        // TODO add your handling code here:
        if(Valore1.getText().isBlank()||Valore1.getText().isEmpty())
        {
            switch(modalita)
            {
                case 1 -> {
                    JOptionPane.showMessageDialog(rootPane, "Il campo per il nome del paese è vuoto.");
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(rootPane, "Il campo per la latitudine è vuoto.");
                }
            }
        }
        else if(Valore2.getText().isBlank()||Valore2.getText().isEmpty())
        {
            switch(modalita)
            {
                case 1 -> {
                    JOptionPane.showMessageDialog(rootPane, "Il campo per il nome dello stato è vuoto.");
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(rootPane, "Il campo per la longitudine è vuoto.");
                }
            }
        }
        else{
            switch(modalita)
            {
                case 1 -> {
                    datiStato statoDiRicerca=cercaStato(Valore2.getText());
                    if(statoDiRicerca==null)
                        JOptionPane.showMessageDialog(rootPane, "Stato non trovato");
                    else
                    {
                        ArrayList<String[]> elencoLocalita=statoDiRicerca.cerca(Valore1.getText());
                        if(elencoLocalita==null)
                            JOptionPane.showMessageDialog(rootPane, "Combinazione località-Stato non trovata.");
                        else
                            mostraInTabella(elencoLocalita);
                    }
                }
                case 2 -> {
                    ArrayList<String[]> elencoLocalita=new ArrayList<>();
                    try{for(String[] riga: mondoCoord)
                    {
                        String[] coordString=riga[5].split(", ");
                        Double[] coordDouble={Double.valueOf(coordString[0]),Double.valueOf(coordString[1])};
                        Double[] coordInsDouble={Double.valueOf(Valore1.getText().replace(",", ".")),Double.valueOf(Valore2.getText().replace(",", "."))};
                        if(
                                (coordDouble[0]-coordInsDouble[0])<=0.4 && (coordDouble[0]-coordInsDouble[0])>=-0.4
                                &&
                                (coordDouble[1]-coordInsDouble[1])<=0.4 && (coordDouble[1]-coordInsDouble[1])>=-0.4
                          )
                            elencoLocalita.add(riga);
                    }
                    if(elencoLocalita.isEmpty())
                        JOptionPane.showMessageDialog(rootPane, "Nessuna località trovata nelle vicinanze.");
                    else
                        mostraInTabella(elencoLocalita);
                    }catch(NumberFormatException e)
                    {JOptionPane.showMessageDialog(rootPane, "Formato coordinate invalido, usa ',' o '.' per separare.");}
                }
            }
        }
    }//GEN-LAST:event_cercaLocalitaButtonActionPerformed

    /**
     * Mostra in tabella i dati delle località passati per parametro
     * @param righe di dati da mostrare
     */
    public void mostraInTabella(ArrayList<String[]> righe)
    {
        ddtm.setRowCount(0);
        String[] coordinateTemp;
        for(String[] parti : righe)
        {
            coordinateTemp=parti[5].split(",");
            ddtm.addRow
                (new Object[] {parti[2],parti[4],Long.valueOf(parti[0]),Double.valueOf(coordinateTemp[0]),Double.valueOf(coordinateTemp[1])});
        }
    }
    private void dataToDisplayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataToDisplayTableMouseClicked
        // TODO add your handling code here:
        String selectedArea = ddtm.getValueAt(dataToDisplayTable.getSelectedRow(), 0).toString();
        areaToDisplay.setText(selectedArea);
    }//GEN-LAST:event_dataToDisplayTableMouseClicked
    
    /**
     * Se è stata selezionata una località ne mostra le rilevazioni
     * @param evt 
     */
    private void mostraRilevazioniButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostraRilevazioniButtonActionPerformed
        // TODO add your handling code here:
        try{
            DataDisplay showData = new DataDisplay(Long.valueOf(ddtm.getValueAt(dataToDisplayTable.getSelectedRow(), 2).toString()));
            showData.setVisible(true);
            showData.impostaLocalita(
                ddtm.getValueAt(dataToDisplayTable.getSelectedRow(), 0).toString()
                + ", " + ddtm.getValueAt(dataToDisplayTable.getSelectedRow(), 1).toString());
        }catch(ArrayIndexOutOfBoundsException e)
        {JOptionPane.showMessageDialog(rootPane, "Nessuna area di interesse selezionata.");}
    }//GEN-LAST:event_mostraRilevazioniButtonActionPerformed

    private void annullaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annullaButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_annullaButtonActionPerformed

    javax.swing.table.DefaultTableModel ddtm = new javax.swing.table.DefaultTableModel(
    new Object [][] {},tableHeader) {
    Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Double.class, java.lang.Double.class
    };
    boolean[] canEdit = new boolean [] {
        false, false, false, false, false
    };

    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }};
    
    private static String[] tableHeader = new String [] {"Località", "Stato", "ID", "Latitudine", "Longitudine"};
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Campo1;
    private javax.swing.JLabel Campo2;
    private javax.swing.JTextField Valore1;
    private javax.swing.JTextField Valore2;
    private javax.swing.JButton annullaButton;
    private javax.swing.JLabel areaToDisplay;
    private javax.swing.JButton cercaLocalitaButton;
    private javax.swing.JTable dataToDisplayTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mostraRilevazioniButton;
    // End of variables declaration//GEN-END:variables
}