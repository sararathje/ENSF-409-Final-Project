
package FrontEnd;

import Models.Assignment;
import Models.Date;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class ProfAssignmentPage extends AssignmentPage{
    
    private JButton uploadFile;
    private JButton assignGrade;
    private JButton activeButton;
    
    public ProfAssignmentPage(Assignment assignment, Client client) {
        super(assignment, client);
        
        
        createUploadFileButton();
        
        createAssignGradeButton();
        
        createSetActiveButton(assignment);
        
        setTitle("Professor Assignment Page");
    }
    
    
    
    /**
     * adds the upload file button to the bottom of the gui.
     */
    private void createUploadFileButton(){
        uploadFile = new JButton("Upload Assignment File");
        bottom.add(uploadFile);

        //todo: add action listener
                       
    }
    
    private void createAssignGradeButton(){
        assignGrade = new JButton("Assign Grade");
        bottom.add(assignGrade);
        
        //todo: add action listener
        
    }
    
    private void createSetActiveButton(Assignment assign){
        if (assign.isActive()){
            activeButton = new JButton("Deacivate");
        }
        else{
            activeButton = new JButton("Activate");
        }
        
        //todo add listeners;
        bottom.add(activeButton);
    }
            
    public static void main(String[] args) {
        Client test = new Client();
        Assignment a = new Assignment("name", new Date(1,2,3,4, 5), 12345, 33333, true );
        ProfAssignmentPage testPage = new ProfAssignmentPage(a, test);
        testPage.setVisible(true);
    }
    
}
