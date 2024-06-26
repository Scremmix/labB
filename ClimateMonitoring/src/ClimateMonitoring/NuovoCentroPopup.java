/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClimateMonitoring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Form grafico che permette di creare un nuovo centro di rilevazione
 * @author Scremin Alessandro
 * @author Riva Samuele
 */
public class NuovoCentroPopup extends javax.swing.JFrame {

    private ServerCMInterface server=null;
    
    /**
     * Crea un nuovo form NuovoCentroPopup
     */
    public NuovoCentroPopup(ServerCMInterface server) {
        initComponents();
        this.server=server;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        localitaAggiungere.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idBox = new javax.swing.JTextField();
        nomeCentroBox = new javax.swing.JTextField();
        capBox = new javax.swing.JTextField();
        indirizzoBox = new javax.swing.JTextField();
        provinciaBox = new javax.swing.JTextField();
        comuneBox = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        localitaTable = new javax.swing.JTable();
        localitaCerca = new javax.swing.JTextField();
        statoCerca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cercaLocalitaButton = new javax.swing.JButton();
        salvaRilevazioneButton = new javax.swing.JButton();
        annullaButton = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        statoBox = new javax.swing.JTextField();
        aggiungiLocalitaButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        localitaSelezionata = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        localitaAggiungere = new javax.swing.JTextArea();
        cambiaCentroOp = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Nuovo centro di rilevazioni");

        jLabel2.setText("Nome del centro:");

        jLabel3.setText("ID centro:");

        jLabel4.setText("Indirizzo (via, n° civico)");

        jLabel5.setText("CAP");

        jLabel6.setText("Comune");

        jLabel7.setText("Provincia");

        localitaTable.setModel(ddtm);
        localitaTable.setColumnSelectionAllowed(true);
        localitaTable.getTableHeader().setReorderingAllowed(false);
        localitaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                localitaTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(localitaTable);
        localitaTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel8.setText("Località:");

        jLabel9.setText("Cerca:");

        jLabel10.setText("Località da abbinare al centro:");

        jLabel11.setText("Stato:");

        cercaLocalitaButton.setText("Cerca");
        cercaLocalitaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cercaLocalitaButtonActionPerformed(evt);
            }
        });

        salvaRilevazioneButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        salvaRilevazioneButton.setText("Salva");
        salvaRilevazioneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaRilevazioneButtonActionPerformed(evt);
            }
        });

        annullaButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        annullaButton.setText("Annulla");
        annullaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaButtonActionPerformed(evt);
            }
        });

        jLabel12.setText("Stato");

        statoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statoBoxActionPerformed(evt);
            }
        });

        aggiungiLocalitaButton.setText("Aggiungi");
        aggiungiLocalitaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggiungiLocalitaButtonActionPerformed(evt);
            }
        });

        jLabel13.setText("Località selezionata:");

        localitaAggiungere.setEditable(false);
        localitaAggiungere.setColumns(20);
        localitaAggiungere.setRows(10);
        localitaAggiungere.setMaximumSize(new java.awt.Dimension(369, 34567));
        jScrollPane3.setViewportView(localitaAggiungere);

        cambiaCentroOp.setText("Abbina l'operatore attuale al centro appena creato");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(245, 245, 245))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(salvaRilevazioneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(annullaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel10)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addGap(37, 37, 37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nomeCentroBox, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(capBox, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                            .addComponent(idBox)
                                            .addComponent(indirizzoBox)
                                            .addComponent(provinciaBox)
                                            .addComponent(comuneBox)
                                            .addComponent(statoBox)))))
                            .addComponent(jScrollPane3)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cambiaCentroOp, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(localitaSelezionata)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(localitaCerca))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(statoCerca, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cercaLocalitaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(aggiungiLocalitaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))))
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(nomeCentroBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cercaLocalitaButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(localitaCerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statoCerca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aggiungiLocalitaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(localitaSelezionata))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(indirizzoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(capBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(comuneBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(provinciaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(statoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cambiaCentroOp)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salvaRilevazioneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(annullaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void statoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statoBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statoBoxActionPerformed
    
    /**
     * Metodo che apporta modifiche alla tabella in base ai risultati della 
     * ricerca per nome oppure stato e attiva popup in caso di errori
     * @param evt 
     */
    private void cercaLocalitaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cercaLocalitaButtonActionPerformed
        // TODO add your handling code here:
        if(localitaCerca.getText().isBlank()||localitaCerca.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Il campo per il nome del paese è vuoto.");
        }
        else if(statoCerca.getText().isBlank()||statoCerca.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane, "Il campo per il nome dello stato è vuoto."); 
        }
        else{
            try {
                datiLocalita = server.cercaLocalita(localitaCerca.getText(), statoCerca.getText());
                mostraInTabella(datiLocalita);
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
            }
        }
    }//GEN-LAST:event_cercaLocalitaButtonActionPerformed
    
    /**
     * Fa controlli sugli input e salva il nuovo centro su file se ogni input è utilizzabile
     * @param evt 
     */
    private void salvaRilevazioneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaRilevazioneButtonActionPerformed
        // TODO add your handling code here:
        
        if(nomeCentroBox.getText().isBlank() ||  nomeCentroBox.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Campo del nome invalido");
        else if(idBox.getText().isBlank() ||  idBox.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Campo dell'ID invalido");
        else if(indirizzoBox.getText().isBlank() ||  indirizzoBox.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Campo dell'indirizzo invalido");
        else if(capBox.getText().length() != 5)
            JOptionPane.showMessageDialog(rootPane, "Campo del cap non valido");
        else if(comuneBox.getText().isBlank() ||  comuneBox.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Campo del comune invalido");
        else if(provinciaBox.getText().length()!=2)
            JOptionPane.showMessageDialog(rootPane, "Campo della provincia invalido");
        else if(statoBox.getText().isBlank() || statoBox.getText().isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Campo dello stato vuoto");
        else if(idDaAbbinare.isEmpty())
            JOptionPane.showMessageDialog(rootPane, "Nessuna località selezionata");  
        else{
            ArrayList listaIdToSend = new ArrayList<String>();
            for(Long id : idDaAbbinare)
                listaIdToSend.add(id.toString());
            
            try {
                if(server.salvaCentro(idBox.getText(), nomeCentroBox.getText(),
                        indirizzoBox.getText(), capBox.getText(),
                        comuneBox.getText()+", "+provinciaBox.getText(), statoBox.getText(),
                        listaIdToSend))
                    JOptionPane.showMessageDialog(rootPane, "Centro registrato.");  
                
                if(cambiaCentroOp.isSelected())
                {
                    if(server.cambiaCentroUtente(Utente.getIDUtente(), idBox.getText()))
                    {
                        Utente.setCentro(idBox.getText());
                        JOptionPane.showMessageDialog(rootPane, "Utente attualmentte loggatto abbinato al cenro appena creato"); 
                    }
                }
                this.dispose();
            } catch (RemoteException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage()); 
            }
        }
    }//GEN-LAST:event_salvaRilevazioneButtonActionPerformed

    private void localitaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_localitaTableMouseClicked
        // TODO add your handling code here
        int [] righeSelez=localitaTable.getSelectedRows();
        for(int singolaRiga: righeSelez)
        {
            String testoInPiu=ddtm.getValueAt(singolaRiga, 0).toString();
            localitaSelezionata.setText(testoInPiu);
        }        
    }//GEN-LAST:event_localitaTableMouseClicked

    private void annullaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annullaButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_annullaButtonActionPerformed

    private ArrayList<Long> idDaAbbinare= new ArrayList<>();
    
    /**
     * Aggiunge l'id della riga selezionata della tabella agli id da abbinare
     * @param evt 
     */
    private void aggiungiLocalitaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggiungiLocalitaButtonActionPerformed
        // TODO add your handling code here:
        int [] righeSelez=localitaTable.getSelectedRows();
        for(int singolaRiga: righeSelez)
            if(!idDaAbbinare.contains(Long.valueOf(ddtm.getValueAt(singolaRiga, 2).toString())))
            {
                String testoInPiu=" - "+ddtm.getValueAt(singolaRiga, 0).toString();
                if(((testoInPiu.length()+localitaAggiungere.getText().length())*8)/369
                    >(localitaAggiungere.getText().length()*8)/369)
                    testoInPiu="\n"+testoInPiu;
                localitaAggiungere.setText(localitaAggiungere.getText()+testoInPiu);
                idDaAbbinare.add(Long.valueOf(ddtm.getValueAt(singolaRiga, 2).toString()));
            }
    }//GEN-LAST:event_aggiungiLocalitaButtonActionPerformed
    
    /**
     * Mostra i risultati della ricerca nome/Stato in tabella
     * @param righe di dati da mostrare in tabella
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
        
    public ArrayList<String[]> datiLocalita=null;
    
    
    javax.swing.table.DefaultTableModel ddtm = new javax.swing.table.DefaultTableModel(
    new Object [][] {},
    new String [] {
        "Località", "Stato", "ID", "Lat.", "Long."
    }
) {
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
    }
};

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aggiungiLocalitaButton;
    private javax.swing.JButton annullaButton;
    private javax.swing.JCheckBox cambiaCentroOp;
    private javax.swing.JTextField capBox;
    private javax.swing.JButton cercaLocalitaButton;
    private javax.swing.JTextField comuneBox;
    private javax.swing.JTextField idBox;
    private javax.swing.JTextField indirizzoBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea localitaAggiungere;
    private javax.swing.JTextField localitaCerca;
    private javax.swing.JLabel localitaSelezionata;
    private javax.swing.JTable localitaTable;
    private javax.swing.JTextField nomeCentroBox;
    private javax.swing.JTextField provinciaBox;
    private javax.swing.JButton salvaRilevazioneButton;
    private javax.swing.JTextField statoBox;
    private javax.swing.JTextField statoCerca;
    // End of variables declaration//GEN-END:variables
}
