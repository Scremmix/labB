/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClimateMonitoring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

/**
 * Il main di tutto il programma.
 * Permette di eseguire le operazioni utente e operatore
 * @author Scremin Alessandro
 */
public class ClientCM extends javax.swing.JFrame {

    /**
     * Crea un nuovo form Main
     */
    public ClientCM() {
        initComponents();
        sceltaRicerca.add(perNomeStato);
        sceltaRicerca.add(perCoordinate);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sceltaRicerca = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        registraButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        perNomeStato = new javax.swing.JRadioButton();
        perCoordinate = new javax.swing.JRadioButton();
        effettuaRicerca = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        nuovoCentro = new javax.swing.JButton();
        nuovaRilevazione = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        serverIPField = new javax.swing.JTextField();
        serverConnectButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("Benvenuto!");

        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registraButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        registraButton.setText("Registrati");
        registraButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registraButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Effettua ricerca per:");

        perNomeStato.setText("Nome paese e Stato");

        perCoordinate.setText("Coordinate geografiche");

        effettuaRicerca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        effettuaRicerca.setText("Vai");
        effettuaRicerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                effettuaRicercaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Oppure, se loggato:");

        nuovoCentro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nuovoCentro.setText("<html>\n<p style=\"text-align: center\">Crea nuovo <br/>centro</p>");
        nuovoCentro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuovoCentro.setMinimumSize(new java.awt.Dimension(113, 57));
        nuovoCentro.setPreferredSize(new java.awt.Dimension(119, 57));
        nuovoCentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuovoCentroActionPerformed(evt);
            }
        });

        nuovaRilevazione.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nuovaRilevazione.setText("<html>\n<p style=\"text-align: center\">Crea nuova <br/>rilevazione</p>");
        nuovaRilevazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuovaRilevazioneActionPerformed(evt);
            }
        });

        jLabel4.setText("<html>Inserisci IP<br/>del server:</html>");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setText("<html>Per poter effettuare qualsasi operazione indicata,<br>è necessario connettersi ad un nostro server remoto</html>");

        serverIPField.setText("127.0.0.1");

        serverConnectButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        serverConnectButton.setText("<html>Connetti<br/>al server</html>");
        serverConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverConnectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nuovaRilevazione, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(effettuaRicerca)
                                    .addComponent(perCoordinate)
                                    .addComponent(perNomeStato)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nuovoCentro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(registraButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(serverIPField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addComponent(serverConnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel1)))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(perNomeStato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(perCoordinate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(effettuaRicerca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuovaRilevazione, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuovoCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(serverIPField)
                    .addComponent(serverConnectButton))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registraButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registraButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registraButtonActionPerformed
        // TODO add your handling code here:
        if(remoteServerCM == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Client non collegato al server.");
            return;
        }
        if(!Utente.loggato())
        {
            RegisterPopup registerForm = new RegisterPopup(remoteServerCM);
            registerForm.setVisible(true);
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Login già eseguito come: "+Utente.getUsername()+".");
    }//GEN-LAST:event_registraButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        // TODO add your handling code here:
        if(remoteServerCM == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Client non collegato al server.");
            return;
        }
        if(!Utente.loggato())
        {
            LoginPopup loginForm = new LoginPopup(remoteServerCM);
            loginForm.setVisible(true);
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Login già eseguito come: "+Utente.getUsername()+".");
    }//GEN-LAST:event_loginButtonActionPerformed

    private void effettuaRicercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_effettuaRicercaActionPerformed
        // TODO add your handling code here:
        if(remoteServerCM == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Client non collegato al server.");
            return;
        }
        if(perCoordinate.isSelected())
        {
            RicercaDati formCerca = new RicercaDati(remoteServerCM);
            formCerca.cambiaModalita(2);
            formCerca.setVisible(true);
        }
        else if(perNomeStato.isSelected())
        {
            RicercaDati formCerca = new RicercaDati(remoteServerCM);
            formCerca.cambiaModalita(1);
            formCerca.setVisible(true);
        }
    }//GEN-LAST:event_effettuaRicercaActionPerformed

    private void nuovaRilevazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuovaRilevazioneActionPerformed
        // TODO add your handling code here:if(!Utente.loggato())
        if(remoteServerCM == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Client non collegato al server.");
            return;
        }
        if(Utente.loggato())
        {
            NuovaRilevazionePopup nuovaRilevazioneForm = new NuovaRilevazionePopup(remoteServerCM);
            nuovaRilevazioneForm.setVisible(true);
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Operazione limitata agli utenti loggati.");
    }//GEN-LAST:event_nuovaRilevazioneActionPerformed

    private void nuovoCentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuovoCentroActionPerformed
        // TODO add your handling code here:if(Utente.loggato())
        if(remoteServerCM == null)
        {
            JOptionPane.showMessageDialog(rootPane, "Client non collegato al server.");
            return;
        }
        if(Utente.loggato())
        {
            NuovoCentroPopup nuovoCentroForm = new NuovoCentroPopup(remoteServerCM);
            nuovoCentroForm.setVisible(true);
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Operazione limitata agli utenti loggati.");
    }//GEN-LAST:event_nuovoCentroActionPerformed

    private void serverConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverConnectButtonActionPerformed
        // TODO add your handling code here:
        String ipInserito = serverIPField.getText();
        if(remoteServerCM != null)
            JOptionPane.showMessageDialog(rootPane, "L'applicazione è già connessa ad un server. Chiudere l'applicazione e riprovare.");
        else if(ipInserito.equalsIgnoreCase("localhost") || ipValido(ipInserito))
        {
            connectToRemoteServerCM(ipInserito);
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Indirizzo IP non valido");
        }
    }//GEN-LAST:event_serverConnectButtonActionPerformed

    private boolean connectToRemoteServerCM(String ip)
    {
        try {
            Registry r = LocateRegistry.getRegistry(ip, 3003);
            remoteServerCM = (ServerCMInterface) r.lookup("ServerCM"); 
            JOptionPane.showMessageDialog(rootPane, "Connesso al server "+ip+", pronto.");
            return true;
        } 
        catch (NotBoundException | RemoteException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Impossibile connettersi al server all'IP "+ip);
            return false;
        }
    }
    
    private boolean ipValido(String ip)
    {
        String ipv4Pattern = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + 
                             "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + 
                             "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." + 
                             "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern pattern = Pattern.compile(ipv4Pattern); 
  
        // Create a matcher with the input IP address 
        Matcher matcher = pattern.matcher(ip); 
  
        // Check if the matcher finds a match 
        return matcher.matches(); 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientCM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientCM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientCM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientCM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientCM().setVisible(true);
            }
        });
    }

    private ServerCMInterface remoteServerCM = null;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton effettuaRicerca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton nuovaRilevazione;
    private javax.swing.JButton nuovoCentro;
    private javax.swing.JRadioButton perCoordinate;
    private javax.swing.JRadioButton perNomeStato;
    private javax.swing.JButton registraButton;
    private javax.swing.ButtonGroup sceltaRicerca;
    private javax.swing.JButton serverConnectButton;
    private javax.swing.JTextField serverIPField;
    // End of variables declaration//GEN-END:variables
}
