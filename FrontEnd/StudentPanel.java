
package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import Models.User;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class StudentPanel extends JPanel implements ColourSchemeConstants, FontConstants{
    
    private User student;
    
    private Client client;
    
    private String courseName;
    
    private JButton enrollButton;
    
    private JButton unenrollButton;

    public StudentPanel(User student, Client client, String courseName, boolean enroll) {
        this.courseName = courseName;
        this.student = student;
        this.client = client;
        
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setMinimumSize(new Dimension(100, 100));
        setBackground(LOGIN_BACKGROUND_COLOUR);
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        addStudentInfo();
        if(enroll){
        addEnrollButton();
        }
        else{
            addUnenrollButton();
        }
    }
    
        private void addStudentInfo(){
           String id, email, firstName, lastName; 
            id = Integer.toString(student.getID());
            email = student.getEmail();
            firstName = student.getFirstName();
            lastName = student.getLastName();
            
            String toAdd = "Student ID: " + id + "\n " + "First Name: " + firstName + "\n " + "Last Name: " +
                    lastName + "\n " + "Email: " + email;
            
            JPanel info = new JPanel();
            info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
            info.setBackground(LOGIN_BACKGROUND_COLOUR);
            
            JLabel idLabel = new JLabel("Student ID: " + id);
            idLabel.setFont(PANEL_TITLE_FONT);
            idLabel.setForeground(FOREGROUND_COLOUR);
            info.add(idLabel);
            
            JLabel nameLabel = new JLabel("Name: " + lastName+ ", "+ firstName);
            nameLabel.setFont(PANEL_TITLE_FONT);
            nameLabel.setForeground(FOREGROUND_COLOUR);
            info.add(nameLabel);
            
            JLabel emailLabel = new JLabel("Email: " + email);
            emailLabel.setFont(PANEL_TITLE_FONT);
            emailLabel.setForeground(FOREGROUND_COLOUR);
            info.add(emailLabel);
            
            add(info);
            add(Box.createHorizontalGlue());
        }
    
    private void addEnrollButton(){
        enrollButton = new JButton("Enroll");
        enrollButton.setFont(BUTTON_FONT);
        enrollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {


                client.enrollStudent(student, courseName);
                enrollButton.setText("Enrolled!");
                enrollButton.setEnabled(false);
            }
        });

        add(enrollButton);
    }

    private void addUnenrollButton(){
        unenrollButton = new JButton("Unenroll");
        unenrollButton.setFont(BUTTON_FONT);
        unenrollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                client.unenrollStudent(student, courseName);
                unenrollButton.setText("Unenrolled!");
                unenrollButton.setEnabled(false);
            }
        });

        add(unenrollButton);
    }
}
