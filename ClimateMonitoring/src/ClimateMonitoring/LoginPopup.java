/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ClimateMonitoring;

import javax.swing.JOptionPane;

/**
 * Classe utile alla crezione del popup di Login.
 * Per il login, chiede l'ID utente e la relativa password
 * @author Scremin Alessandro
 */
public class LoginPopup extends javax.swing.JFrame {

    /**
     * Crea un nuovo LoginPopup
     */
    public LoginPopup() {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        faiLogin = new javax.swing.JButton();
        anullaButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(248, 248, 248));

        faiLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        faiLogin.setText("Login");
        faiLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faiLoginActionPerformed(evt);
            }
        });

        anullaButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        anullaButton.setText("Annulla");
        anullaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anullaButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login");

        jLabel2.setText("ID utente:");

        jLabel3.setText("Password:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(faiLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addComponent(anullaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(username)
                    .addComponent(password))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(faiLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anullaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void anullaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anullaButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_anullaButtonActionPerformed

    private void faiLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faiLoginActionPerformed
        // TODO add your handling code here:
        try{
            if(!Utente.login(username.getText(), new String(password.getPassword())))
                JOptionPane.showMessageDialog(rootPane, "Combinazione utente password non valida.");
            else
            {
                JOptionPane.showMessageDialog(rootPane, "Login eseguito con successo.");
                this.dispose();
            }
        }catch(utenteException e)
        {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
    }//GEN-LAST:event_faiLoginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anullaButton;
    private javax.swing.JButton faiLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
