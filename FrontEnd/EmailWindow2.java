/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Models.Email;
import Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import static Constants.MessageConstants.INVALID_COURSE_ID;
import static Constants.MessageConstants.MESSAGE_SENT;
import static Constants.MessageConstants.PASSWORD_REQUIRED;

/**
 * Creates a GUI to send emails.
 * @author Rylan
 * @version 2.0
 * @since April 12, 2018
 */
public class EmailWindow2 extends javax.swing.JDialog {
    /**
     * Client that communicates with the server
     */
    private Client client;
    /**
     * Email model that will be sent to the server
     */
    private Email email;
    /**
     * List of users who will receive the email
     */
    ArrayList<User> emailReceivers;
    /**
     * Creates new form EmailWindow2
     */
    public EmailWindow2(java.awt.Frame parent, boolean modal, Client client, ArrayList<User> emailReceivers) {
        super(parent, modal);
        this.client = client;
        email = new Email(client.getAuthenticatedUser().getEmail(), null);
        this.emailReceivers = emailReceivers;

        initComponents();
        setListeners();

        this.pack();
        this.setVisible(true);
    }

    /**
     * Adds a the listeners to the frame for the 2 JButtons on the frame
     */
    private void setListeners()
    {
        //Collects information from the text areas and sends an email through the database
        sendB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event)
            {
                if(String.valueOf(emailPWTA.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(getContentPane(), PASSWORD_REQUIRED, "",
                            JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    email.compose(textTA.getText(), textTA.getText());
                    email.setSenderPW(String.valueOf(emailPWTA.getPassword()));

                    for(int i = 0; i < emailReceivers.size(); i++)
                    {
                        email.addRecipient(emailReceivers.get(i).getEmail());
                    }

                    client.sendEmail(email);
                    JOptionPane.showMessageDialog(getContentPane(), MESSAGE_SENT, "",
                            JOptionPane.PLAIN_MESSAGE);

                    dispose();
                }
            }});

        //Closes the frame
        cancelB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subjectField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textTA = new javax.swing.JTextArea();
        emailPWTA = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        sendB = new javax.swing.JButton();
        cancelB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compose New Email");

        subjectField.setText("");

        jLabel1.setText("Subject Line:");
        jLabel1.setForeground(FOREGROUND_COLOUR);

        textTA.setColumns(20);
        textTA.setRows(5);
        textTA.setText("");
        jScrollPane1.setViewportView(textTA);

        jLabel2.setText("Your Email Password:");
        jLabel2.setForeground(FOREGROUND_COLOUR);

        emailPWTA.setText("");

        sendB.setText("Send");

        cancelB.setText("Discard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailPWTA, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sendB)
                                .addGap(18, 18, 18)
                                .addComponent(cancelB))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(subjectField, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subjectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailPWTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendB)
                    .addComponent(cancelB))
                .addContainerGap())
        );

        getContentPane().setBackground(LOGIN_BACKGROUND_COLOUR);
        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(EmailWindow2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmailWindow2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmailWindow2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmailWindow2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                EmailWindow2 dialog = new EmailWindow2(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelB;
    private javax.swing.JPasswordField emailPWTA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendB;
    private javax.swing.JTextField subjectField;
    private javax.swing.JTextArea textTA;
    // End of variables declaration//GEN-END:variables
}
