
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import Models.Submission;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class SubmissionPanel extends JPanel implements ColourSchemeConstants, FontConstants{
   
    private Submission submission;
    
    private Client client;
    
    private JButton gradeButton;
    
    public SubmissionPanel(Submission submission, Client client){
        this.client = client;
        this.submission = submission;
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        setMinimumSize(new Dimension(100, 100));
        setBackground(LOGIN_BACKGROUND_COLOUR);
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        addSubmissionInfo();
        addAssignGradeButton();
    }
    
    private void addSubmissionInfo(){
        String name, studentID, timeStamp, grade;
        name = submission.getTitle();
        studentID = Integer.toString(submission.getStudentID());
        timeStamp = submission.getTimeStamp();
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
    
    private void addAssignGradeButton(){
        gradeButton = new JButton("Assign Grade");
        gradeButton.setFont(BUTTON_FONT);
        add(gradeButton);
        add(Box.createRigidArea(new Dimension(20,50)));
        //add action listener
        
    }
    
    
    
}
