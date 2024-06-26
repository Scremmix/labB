/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClimateMonitoring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 * Form grafico che permette di creare una nuova rilevazione
 * per le località disponibili all'utente
 * @author Zucchi Luca
 * @author Scremin Alessandro
 */
public class NuovaRilevazionePopup extends javax.swing.JFrame {

    private ServerCMInterface server=null;
    
    /**
     * Crea un nuovo form di RilevazionePopup
     */
    public NuovaRilevazionePopup(ServerCMInterface server) {
        initComponents();
        this.server=server;
        ventoX.setText(""+ventoSlider.getValue());
        umiditaX.setText(""+umiditaSlider.getValue());
        pressioneX.setText(""+pressioneSlider.getValue());
        temperaturaX.setText(""+temperaturaSlider.getValue());
        precipitazioniX.setText(""+precipitazioniSlider.getValue());
        aGhiacciaiX.setText(""+aGhiacciaiSlider.getValue());
        mGhiacciaiX.setText(""+mGhiacciaiSlider.getValue());
        
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        ArrayList<String[]> coordinateTemp;
        try {
            coordinateTemp = server.cercaLocalitaAbbinate(Utente.getCentro());
            for(String[] parti : coordinateTemp)
            {
                ddtm.addRow(
                    new Object[] {parti[1],parti[2],parti[0],Double.valueOf(parti[3]),Double.valueOf(parti[4])});
            }
        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
    }
    
    javax.swing.table.DefaultTableModel ddtm=new javax.swing.table.DefaultTableModel(
        new Object [][] {},
        new String [] {
        "Località", "Stato", "ID", "Lat.", "Long."
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ventoSlider = new javax.swing.JSlider();
        ventoX = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabellaLocalita = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        annullaButton = new javax.swing.JButton();
        salvaRilevazioneButton = new javax.swing.JButton();
        nomeLocalita = new javax.swing.JLabel();
        umiditaSlider = new javax.swing.JSlider();
        umiditaX = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pressioneSlider = new javax.swing.JSlider();
        pressioneX = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        temperaturaSlider = new javax.swing.JSlider();
        temperaturaX = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        precipitazioniSlider = new javax.swing.JSlider();
        precipitazioniX = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        aGhiacciaiSlider = new javax.swing.JSlider();
        aGhiacciaiX = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mGhiacciaiSlider = new javax.swing.JSlider();
        mGhiacciaiX = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Nuova rilevazione");

        ventoSlider.setMaximum(5);
        ventoSlider.setMinimum(1);
        ventoSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ventoSliderStateChanged(evt);
            }
        });

        ventoX.setText("X");

        jLabel3.setText("Vento");

        tabellaLocalita.setModel(ddtm);
        tabellaLocalita.setColumnSelectionAllowed(true);
        tabellaLocalita.getTableHeader().setReorderingAllowed(false);
        tabellaLocalita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabellaLocalitaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabellaLocalita);
        tabellaLocalita.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tabellaLocalita.getColumnModel().getColumnCount() > 0) {
            tabellaLocalita.getColumnModel().getColumn(0).setMinWidth(100);
            tabellaLocalita.getColumnModel().getColumn(0).setPreferredWidth(170);
            tabellaLocalita.getColumnModel().getColumn(0).setMaxWidth(250);
            tabellaLocalita.getColumnModel().getColumn(1).setMinWidth(100);
            tabellaLocalita.getColumnModel().getColumn(1).setPreferredWidth(170);
            tabellaLocalita.getColumnModel().getColumn(1).setMaxWidth(250);
            tabellaLocalita.getColumnModel().getColumn(2).setResizable(false);
            tabellaLocalita.getColumnModel().getColumn(2).setPreferredWidth(70);
            tabellaLocalita.getColumnModel().getColumn(3).setResizable(false);
            tabellaLocalita.getColumnModel().getColumn(3).setPreferredWidth(70);
        }

        jLabel4.setText("Inserisci qui le tue note:");

        jScrollPane2.setViewportView(jTextPane1);

        jLabel5.setText("Seleziona la località per cui effettuare la rilevazione:");

        annullaButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        annullaButton.setText("Annulla");
        annullaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaButtonActionPerformed(evt);
            }
        });

        salvaRilevazioneButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        salvaRilevazioneButton.setText("Invia");
        salvaRilevazioneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaRilevazioneButtonActionPerformed(evt);
            }
        });

        umiditaSlider.setMaximum(5);
        umiditaSlider.setMinimum(1);
        umiditaSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                umiditaSliderStateChanged(evt);
            }
        });

        umiditaX.setText("X");

        jLabel6.setText("Umidità");

        pressioneSlider.setMaximum(5);
        pressioneSlider.setMinimum(1);
        pressioneSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pressioneSliderStateChanged(evt);
            }
        });

        pressioneX.setText("X");

        jLabel7.setText("Pressione");

        temperaturaSlider.setMaximum(5);
        temperaturaSlider.setMinimum(1);
        temperaturaSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                temperaturaSliderStateChanged(evt);
            }
        });

        temperaturaX.setText("X");

        jLabel8.setText("Temperatura");

        precipitazioniSlider.setMaximum(5);
        precipitazioniSlider.setMinimum(1);
        precipitazioniSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                precipitazioniSliderStateChanged(evt);
            }
        });

        precipitazioniX.setText("X");

        jLabel9.setText("Precipitazioni");

        aGhiacciaiSlider.setMaximum(5);
        aGhiacciaiSlider.setMinimum(1);
        aGhiacciaiSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                aGhiacciaiSliderStateChanged(evt);
            }
        });

        aGhiacciaiX.setText("X");

        jLabel10.setText("Alt. dei ghiacciai");

        mGhiacciaiSlider.setMaximum(5);
        mGhiacciaiSlider.setMinimum(1);
        mGhiacciaiSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mGhiacciaiSliderStateChanged(evt);
            }
        });

        mGhiacciaiX.setText("X");

        jLabel11.setText("Massa dei ghiacciai");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ventoSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(temperaturaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pressioneSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(mGhiacciaiSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(precipitazioniSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(aGhiacciaiSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ventoX, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pressioneX, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(temperaturaX, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(aGhiacciaiX, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(mGhiacciaiX, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(precipitazioniX, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(umiditaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(umiditaX)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(salvaRilevazioneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(annullaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomeLocalita))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(356, 356, 356))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(nomeLocalita)
                    .addComponent(ventoX)
                    .addComponent(jLabel3)
                    .addComponent(ventoSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(umiditaX)
                            .addComponent(jLabel6)
                            .addComponent(umiditaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pressioneX)
                            .addComponent(jLabel7)
                            .addComponent(pressioneSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(temperaturaX)
                            .addComponent(jLabel8)
                            .addComponent(temperaturaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(precipitazioniX)
                            .addComponent(precipitazioniSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aGhiacciaiX)
                            .addComponent(jLabel10)
                            .addComponent(aGhiacciaiSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mGhiacciaiX)
                            .addComponent(jLabel11)
                            .addComponent(mGhiacciaiSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(annullaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salvaRilevazioneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ventoSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ventoSliderStateChanged
        // TODO add your handling code here:
        ventoX.setText(""+ventoSlider.getValue());
    }//GEN-LAST:event_ventoSliderStateChanged

    private void tabellaLocalitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabellaLocalitaMouseClicked
        // TODO add your handling code here:
        nomeLocalita.setText(tabellaLocalita.getValueAt(tabellaLocalita.getSelectedRow(), 0)
                +", "+tabellaLocalita.getValueAt(tabellaLocalita.getSelectedRow(), 1));
    }//GEN-LAST:event_tabellaLocalitaMouseClicked

    private void annullaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annullaButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_annullaButtonActionPerformed

    /**
     * Restituisce la stringa del bottone radio selezionato al momento
     * @param buttonGroup gruppo di bottoni in questione
     * @return testo del RadioButton selezionato
     */
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
    
    /**
     * Effettua e eventualmente conferma il salvataggio su file tramite un messaggio
     * @param evt 
     */
    
    private void salvaRilevazioneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaRilevazioneButtonActionPerformed
        // TODO add your handling code here:
        ArrayList valori = new ArrayList<Integer>();
        valori.add(ventoSlider.getValue());
        valori.add(umiditaSlider.getValue());
        valori.add(pressioneSlider.getValue());
        valori.add(temperaturaSlider.getValue());
        valori.add(precipitazioniSlider.getValue());
        valori.add(aGhiacciaiSlider.getValue());
        valori.add(mGhiacciaiSlider.getValue());
        try {
            Rilevazione r =new Rilevazione(
                    Utente.getCentro(),
                    Long.valueOf(tabellaLocalita.getValueAt(tabellaLocalita.getSelectedRow(), 2).toString()),
                    valori,
                    jTextPane1.getText().replace("\n", "- ")
            );
            if(server.salvaRilevazione(r))
            {
                JOptionPane.showMessageDialog(rootPane, "Registrazione salvata con successo.");
                this.dispose();
            }
        }
        catch (rilevazioneException | RemoteException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        catch(NullPointerException ex){
            JOptionPane.showMessageDialog(rootPane, "Nessuna area selezionata.");
        }
    }//GEN-LAST:event_salvaRilevazioneButtonActionPerformed

    private void umiditaSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_umiditaSliderStateChanged
        // TODO add your handling code here:
        umiditaX.setText(""+umiditaSlider.getValue());
    }//GEN-LAST:event_umiditaSliderStateChanged

    private void pressioneSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pressioneSliderStateChanged
        // TODO add your handling code here:
        pressioneX.setText(""+pressioneSlider.getValue());
    }//GEN-LAST:event_pressioneSliderStateChanged

    private void temperaturaSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_temperaturaSliderStateChanged
        // TODO add your handling code here:
        temperaturaX.setText(""+temperaturaSlider.getValue());
    }//GEN-LAST:event_temperaturaSliderStateChanged

    private void precipitazioniSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_precipitazioniSliderStateChanged
        // TODO add your handling code here:
        precipitazioniX.setText(""+precipitazioniSlider.getValue());
    }//GEN-LAST:event_precipitazioniSliderStateChanged

    private void aGhiacciaiSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_aGhiacciaiSliderStateChanged
        // TODO add your handling code here:
        aGhiacciaiX.setText(""+aGhiacciaiSlider.getValue());
    }//GEN-LAST:event_aGhiacciaiSliderStateChanged

    private void mGhiacciaiSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mGhiacciaiSliderStateChanged
        // TODO add your handling code here:
        mGhiacciaiX.setText(""+mGhiacciaiSlider.getValue());
    }//GEN-LAST:event_mGhiacciaiSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider aGhiacciaiSlider;
    private javax.swing.JLabel aGhiacciaiX;
    private javax.swing.JButton annullaButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JSlider mGhiacciaiSlider;
    private javax.swing.JLabel mGhiacciaiX;
    private javax.swing.JLabel nomeLocalita;
    private javax.swing.JSlider precipitazioniSlider;
    private javax.swing.JLabel precipitazioniX;
    private javax.swing.JSlider pressioneSlider;
    private javax.swing.JLabel pressioneX;
    private javax.swing.JButton salvaRilevazioneButton;
    private javax.swing.JTable tabellaLocalita;
    private javax.swing.JSlider temperaturaSlider;
    private javax.swing.JLabel temperaturaX;
    private javax.swing.JSlider umiditaSlider;
    private javax.swing.JLabel umiditaX;
    private javax.swing.JSlider ventoSlider;
    private javax.swing.JLabel ventoX;
    // End of variables declaration//GEN-END:variables
}
