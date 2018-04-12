
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
    private JLabel gradeLabel;
    //private String grade;
    
    public StudentAssignmentPage(Assignment assignment, Client client) {
        //Get set data fields from super
        super(assignment, client);
        
        
        
        createDropBoxButton();
        addGradeToInfoBar();
        setTitle("Student Assignment Page");
        
    }
 
    
    private void createDropBoxButton(){
        dropBox = new JButton("Submit File To DropBox");
        //TODO add action listener
        
        bottom.add(dropBox);
    }
    
    private void addGradeToInfoBar(){
        
        String gr;
        int grade = getGradeFromServer();
        if (grade != -1){
            gr = Integer.toString(grade) + "  ";
        }
        else{
            gr = "Ungraded  ";
        }
        gradeLabel = new JLabel("Grade: " + gr);
        gradeLabel.setFont(PANEL_TITLE_FONT);
        gradeLabel.setForeground(FOREGROUND_COLOUR);
        infoBar.add(Box.createHorizontalGlue());
        infoBar.add(gradeLabel);
    }
    
    //todo add listener to refresh button that will add update grade
    
    
    public int getGradeFromServer(){
        int grade = client.getGrade(assignment.getID(), client.getAuthenticatedUser().getID(), assignment.getCourseID() );
        return grade;
    }
    
    public static void main(String[] args) {
        Client test = new Client();
        Assignment a = new Assignment("name", new Date(1,2,3,4, 5), 12345, 33333, true );
        StudentAssignmentPage testPage = new StudentAssignmentPage(a, test);
        testPage.setVisible(true);
    }
}
