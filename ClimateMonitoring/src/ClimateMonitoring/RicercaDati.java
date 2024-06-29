/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClimateMonitoring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Classe utile alla ricerca di località tramite nomi o coordinate
 * e a mostrarne i relativi dat chiamando la classe DataDisplay
 * @author Scremin Alessandro
 * @author Riva Samuele
 */
public class RicercaDati extends javax.swing.JFrame {

    private ArrayList dati = new ArrayList<String[]>();
    
    
    /**
     * Modifica il metodo di ricerca
     * @param mod: 1 per la ricerca nome/Stato, 2 per la ricerca coordinate
     */
    public void cambiaModalita(int mod)
    {
        if(this.modalita!=mod)
        {
            dati = new ArrayList<String[]>();
            this.modalita=mod;
            switch(this.modalita)
            {
                case 1 -> {
                    Campo1.setText("Nome località (inglese)");
                    Campo2.setText("Nome Stato (inglese)");
                }
                case 2 -> {
                    Campo1.setText("Latitudine (N/S)");
                    Campo2.setText("Longitudine (E/O)");
                }
            }
        }
    }

    /**
     * Costruttore di default
     */
    public RicercaDati(ServerCMInterface server) {
        initComponents();
        this.server=server;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        RicercaDati.this.revalidate();
        RicercaDati.this.repaint();
    }
    private int modalita;
    //1= stato e nome
    //2= coordinate
    
    private ServerCMInterface server;
    
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
                    try {
                        dati = server.cercaLocalita(Valore1.getText(), Valore2.getText());
                    } 
                    catch (RemoteException ex) { JOptionPane.showMessageDialog(rootPane, ex.getMessage()); }
                }
                case 2 -> {
                    try
                    {
                        dati = server.cercaLocalitaCoordinate(Valore1.getText(), Valore2.getText());
                    }
                    catch(NumberFormatException e) { JOptionPane.showMessageDialog(rootPane, "Formato coordinate invalido, usa ',' o '.' per separare."); }
                    catch (RemoteException ex) { JOptionPane.showMessageDialog(rootPane, ex.getMessage()); }
                }
            }
        }
        mostraInTabella(dati);
    }//GEN-LAST:event_cercaLocalitaButtonActionPerformed

    /**
     * Mostra in tabella i dati delle località passati per parametro
     * @param righe di dati da mostrare
     */
    public void mostraInTabella(ArrayList<String[]> righe)
    {
        ddtm.setRowCount(0);
        for(String[] parti : righe)
        {
            ddtm.addRow
                (new Object[] {parti[1],parti[2],Long.valueOf(parti[0]),Double.valueOf(parti[3]),Double.valueOf(parti[4])});
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
            DataDisplay showData = new DataDisplay(Long.valueOf(ddtm.getValueAt(dataToDisplayTable.getSelectedRow(), 2).toString()),this.server);
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

    @Override
    public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
    }

    @Override
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