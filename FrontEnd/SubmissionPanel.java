
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import Models.Submission;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class SubmissionPanel extends JPanel implements ColourSchemeConstants, FontConstants{
   
    
    
    private Submission submission;
    
    private Client client;
    
    private JButton gradeButton;
    
    private JButton viewFile;
    
    public SubmissionPanel(Submission submission, Client client){
        this.client = client;
        this.submission = submission;
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setMinimumSize(new Dimension(100, 100));
        setBackground(LOGIN_BACKGROUND_COLOUR);
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        addSubmissionInfo();
        addViewFileButton();
        addAssignGradeButton();
    }
    
    private void addSubmissionInfo(){
        String name, studentID, timeStamp, grade;
        name = submission.getTitle();
        studentID = Integer.toString(submission.getStudentID());
        timeStamp = submission.getTimeStamp();
        updateGrade();
        grade = Integer.toString(submission.getGrade()) + "%";
        
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
        info.setBackground(LOGIN_BACKGROUND_COLOUR);
        
        JLabel submissionName = new JLabel("Submission Name: " + name);
        submissionName.setFont(PANEL_TITLE_FONT);
        submissionName.setForeground(FOREGROUND_COLOUR);
        info.add(submissionName);
        
        JLabel ID = new JLabel("Student ID: " + studentID);
        ID.setFont(PANEL_TITLE_FONT);
        ID.setForeground(FOREGROUND_COLOUR);
        info.add(ID);
        
        JLabel time = new JLabel("Time Submitted: " + timeStamp);
        time.setFont(PANEL_TITLE_FONT);
        time.setForeground(FOREGROUND_COLOUR);
        info.add(time);
        
        JLabel currentGrade = new JLabel("Current Grade: " + grade);
        currentGrade.setFont(PANEL_TITLE_FONT);
        currentGrade.setForeground(FOREGROUND_COLOUR);
        info.add(currentGrade);
        
        add(info);
        add(Box.createHorizontalGlue());
    }
    
    private void updateGrade(){
        int grade = client.getGrade(submission.getAssignmentID(), submission.getStudentID());
        submission.setGrade(grade);
}
    
    private void addViewFileButton(){
        viewFile = new JButton("View File");
        viewFile.setFont(BUTTON_FONT);
        add(viewFile);
        viewFile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //TODO do something here
            }
    
                
        });
    }
    
    private void addAssignGradeButton(){
        gradeButton = new JButton("Assign Grade");
        gradeButton.setFont(BUTTON_FONT);
        add(gradeButton);
        add(Box.createRigidArea(new Dimension(20,50)));
        gradeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SetGradePanel setGrades = new SetGradePanel((DropBoxView)SubmissionPanel.this.getTopLevelAncestor(),
                        true, submission,  client);
                setGrades.setVisible(true);
            }
    
                
        });
        
    }
    
    
    
}
