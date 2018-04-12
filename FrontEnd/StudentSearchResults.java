
package FrontEnd ;

import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Models.User;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;


/**
 *
 * @author Rylan
 */
public class StudentSearchResults extends JFrame{
    
    /**
     * Client
     */
    private Client client;
    
    /**
     * List of students to display
     */
    private ArrayList<User> students;
    
    /**
     * Course name
     */
    private String courseName;
    
    /**
     * 
     */
    private JScrollPane view;

    private JPanel displayPanel;
    
    
    public StudentSearchResults(Client client, ArrayList<User> students, String courseName) {
        this.client = client;
        this.students = students;
        this.courseName = courseName;
        setPreferredSize(new Dimension(800, 300));
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setBackground(LOGIN_BACKGROUND_COLOUR);
        displayPanel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        view = new JScrollPane(displayPanel);
        
        add(view);
        
        showResults();
        
        pack();
        setTitle("Search Results");
    }
    
    
    private void showResults(){
        for(int i = 0; i < students.size(); i++){
            displayPanel.add(new StudentPanel(students.get(i), client, courseName, true));
        }
    }
    
    
}
