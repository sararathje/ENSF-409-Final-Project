
package FrontEnd;

import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Models.Assignment;
import Models.Date;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class StudentAssignmentPage extends AssignmentPage {

    private JButton dropBox;
    private JLabel dueDate;
    private JLabel submissionNumber;
    private JLabel grade;
    
    public StudentAssignmentPage(Assignment assignment, Client client) {
        //Get set data fields from super
        super(assignment, client);
        
        createDropBoxButton();
        setTitle("Student Assignment Page");
        
    }
 
    
    private void createDropBoxButton(){
        dropBox = new JButton("Submit File To DropBox");
        //TODO add action listener
        
        bottom.add(dropBox);
    }
    
    
    
    public static void main(String[] args) {
        Client test = new Client();
        Assignment a = new Assignment("name", new Date(1,2,3,4, 5), 12345, 33333, true );
        StudentAssignmentPage testPage = new StudentAssignmentPage(a, test);
        testPage.setVisible(true);
    }
}
