
package FrontEnd;

import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Models.Assignment;
import Models.Date;
import Models.Submission;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
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

        dropBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show file selector
                FileSelector fileSelector = new FileSelector();

                String fullPath = fileSelector.getAbsoluteFilePath();

                Path filePathWithName = Paths.get(fullPath).getFileName();
                String fileName = filePathWithName.toString();
                String extension = "." + fileName.split("\\.")[1];

                if (!fullPath.equals("")) {
                    int assignmentID = assignment.getID();
                    int studentID = client.getAuthenticatedUser().getID();
                    String title = panelName + " " + client.getAuthenticatedUser().getFirstName() +
                            client.getAuthenticatedUser().getLastName();

                    Submission submission = new Submission(assignmentID, studentID, serverDirPath, title);
                    String submissionFileName = String.valueOf(submission.getAssignmentID()) + "_"
                            + String.valueOf(submission.getStudentID());
                    client.uploadFile(fullPath, submissionFileName, extension);
                }
            }
        });
        
        bottom.add(dropBox);
    }
    
    private void addGradeToInfoBar(){
        
        String gr;
        int grade = getGradeFromServer();
        if (grade != -1) {
            gr = Integer.toString(grade) + "  ";
        }
        else{
            gr = "Ungraded  ";
        }
        gradeLabel = new JLabel("Grade: " + gr+ "%  ");
        gradeLabel.setFont(PANEL_TITLE_FONT);
        gradeLabel.setForeground(FOREGROUND_COLOUR);
        infoBar.add(Box.createHorizontalGlue());
        infoBar.add(gradeLabel);
    }
    
    //todo add listener to refresh button that will add update grade
    
    
    public int getGradeFromServer(){
        int grade = client.getGrade(assignment.getID(), client.getAuthenticatedUser().getID());
        return grade;
    }
    
    public static void main(String[] args) {
        Client test = new Client();
        Assignment a = new Assignment("name", new Date(1,2,3,4, 5), 12345, 33333, true );
        StudentAssignmentPage testPage = new StudentAssignmentPage(a, test);
        testPage.setVisible(true);
    }
}
