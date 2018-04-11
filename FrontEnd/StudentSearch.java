/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FrontEnd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Constants.*;
import Models.User;
import com.sun.codemodel.internal.JOp;

/**
 * Creates a Student Search form.
 * @author Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 5, 2018
 */
public class StudentSearch extends javax.swing.JDialog implements MessageConstants, ColourSchemeConstants {
    private Client client;
    private String courseName;
    private java.awt.Frame parent;

    /** Creates new form StudentSearch */
    public StudentSearch(java.awt.Frame parent, boolean modal, Client client, String courseName) {
        super(parent, modal);
        this.client = client;
        this.courseName = courseName;
        this.parent = parent;
        initComponents();
        addListeners();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lastNameSearch = new javax.swing.JLabel();
        IDSearch = new javax.swing.JLabel();
        IDField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Search");

        lastNameSearch.setText("Student Last Name:");
        lastNameSearch.setForeground(FOREGROUND_COLOUR);

        IDSearch.setText("Student ID:");
        IDSearch.setForeground(FOREGROUND_COLOUR);

        searchButton.setText("Search");

        cancelButton.setText("Cancel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(searchButton)
                .addGap(27, 27, 27)
                .addComponent(cancelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lastNameSearch)
                    .addComponent(IDSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameSearch)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDSearch)
                    .addComponent(IDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(cancelButton))
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
            java.util.logging.Logger.getLogger(StudentSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StudentSearch dialog = new StudentSearch(new javax.swing.JFrame(), true, new Client(), "Blah");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDField;
    private javax.swing.JLabel IDSearch;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JLabel lastNameSearch;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables

    /**
     * Adds listeners to search panel.
     */
    private void addListeners() {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String lastName = lastNameField.getText(),
                        id = IDField.getText();

                if (lastName.equals("") && id.equals("")) {
                    JOptionPane.showMessageDialog(null, EMPTY_SEARCH, "",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    clearInputFields();
                    dispose();

                    ArrayList<User> matchedStudents = client.searchForStudent(lastName, id, courseName);
                    showResults(matchedStudents);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                clearInputFields();
                dispose();
            }
        });
    }

    /**
     * Clears input fields on submit and close.
     */
    private void clearInputFields() {
        lastNameField.setText("");
        IDField.setText("");
    }

    /**
     * Gets the course name.
     * @return course name
     */
    public String getCourseName() {
        return courseName;
    }

    private void showResults(ArrayList<User> matchedStudents) {
        if (!matchedStudents.isEmpty()) {
            StudentSearchResults studentResults = new StudentSearchResults(parent, true, client,
                    matchedStudents, courseName);
            studentResults.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, NO_MATCHES_FOUND, "",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
