
package FrontEnd;

import Models.Assignment;
import Models.Date;
import Models.Submission;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class ProfAssignmentPage extends AssignmentPage{
    
    private JButton uploadFile;
    private JButton viewDropBox;
    private JButton activeButton;
    
    public ProfAssignmentPage(Assignment assignment, Client client) {
        super(assignment, client);
        
        initializeDropboxSubmissionList();
        
        createUploadFileButton();
        
        createViewDropBoxButton();
        
        createSetActiveButton(assignment);
        
        setTitle("Professor Assignment Page");
    }
    
    
    
    /**
     * adds the upload file button to the bottom of the gui.
     */
    private void createUploadFileButton(){
        uploadFile = new JButton("Upload Assignment File");
        bottom.add(uploadFile);

        uploadFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileSelector fileSelector = new FileSelector();
                String fullPath = fileSelector.getAbsoluteFilePath();

                Path filePathWithName = Paths.get(fullPath).getFileName();
                String fileName = filePathWithName.toString();
                String extension = "." + fileName.split("\\.")[1];

                if (!fullPath.equals("")) {
                    client.uploadFile(fullPath, fileName, extension);
                }
            }
        });
                       
    }

    /**
     * Creates View Dropbox button.
     */
    private void createViewDropBoxButton(){
        viewDropBox = new JButton("View Drop Box");
        bottom.add(viewDropBox);
        
        viewDropBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               DropBoxView dropboxView = new DropBoxView(assignment.getDropbox(), client);
               dropboxView.setVisible(true);
                
            }
        });
        
        
    }
    
    private void createSetActiveButton(Assignment assign){
        if (assign.isActive()){
            activeButton = new JButton("Deactivate");
        }
        else{
            activeButton = new JButton("Activate");
        }
        
        activeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               if(activeButton.getText().equals("Activate")){
                   client.setAssignmentActive(assignment);
                   activeButton.setText("Deactivate");
               }
               else if(activeButton.getText().equals("Deactivate")){
                   client.setAssignmentInactive(assignment);
                   activeButton.setText("Activate");
               }
            }
        });
        bottom.add(activeButton);
    }
            
    private void initializeDropboxSubmissionList(){
      ArrayList<Submission> submissions = client.getSubmissionList(assignment.getID());
      assignment.getDropbox().setSubmissions(submissions);
   }
    
    public static void main(String[] args) {
        Client test = new Client();
        Assignment a = new Assignment("name", new Date(1,2,3,4, 5), 12345, 33333, true );
        ProfAssignmentPage testPage = new ProfAssignmentPage(a, test);
        testPage.setVisible(true);
    }
    
    
    
}
