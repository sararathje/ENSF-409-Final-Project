
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import static Constants.FontConstants.BUTTON_FONT;
import Constants.LabelConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class CoursePage extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants {
    protected AssignmentListView assignmentList;
    protected JButton refresh;
    protected JButton email;
    protected JPanel bottom; 
    
    public CoursePage(){
       
        //Set frame format
        setPreferredSize(new Dimension(1500, 1000));
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);
        
        //create assignment list
        assignmentList = new AssignmentListView();
        add("Center", assignmentList);
        
        //add 'Course Home' title to top
        addTitle();
        
        //add buttons
        addBottomPanel();
        createRefreshButton();
        createEmailButton();
        
        //add blank space to the left and the right
        addBorders();
        pack();
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
    }
    
    /**
     * Adds the bottom panel to the bottom of the frame.
     */
    private void addBottomPanel(){
        bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000, 100));
        bottom.setBackground(LOGIN_BACKGROUND_COLOUR);
        this.add("South", bottom);
        
    }
    
    /**
     * Creates refresh button at the bottom of the GUI.
     */
    private void createRefreshButton(){
        refresh = new JButton("Refresh");
        refresh.setFont(BUTTON_FONT);
        refresh.setMinimumSize(new Dimension(0, 50));
        bottom.add(refresh);
    }
    
    /**
     * Creates the email button at the bottom of the GUI.
     */
    private void createEmailButton(){
        email = new JButton("Email");
        email.setFont(BUTTON_FONT);
        email.setMinimumSize(new Dimension(0, 50));
        bottom.add(email);
    }
    
    /**
     * Adds blank space to either side of the GUI for aesthetic purposes.
     */
    private void addBorders(){
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(100, 800));
        right.setBackground(LOGIN_BACKGROUND_COLOUR);
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(100, 800));
        left.setBackground(LOGIN_BACKGROUND_COLOUR);
        add("East", right);
        add("West", left);
    }
     
    /**
     * Adds a title to the GUI.
     */
    private void addTitle(){
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(1000, 100));
        top.setBackground(LOGIN_BACKGROUND_COLOUR);
        JLabel title = new JLabel("Course Home Page"); //change this to get the name of the course
        title.setFont(TITLE_FONT);
        title.setForeground(FOREGROUND_COLOUR);
        top.add(title);
        this.add("North", top);
    }

    public AssignmentListView getAssignmentList() {
        return assignmentList;
    }
    
    
    
   /**
    * Adds an assignmrnt to the assignment list.
    * @param AssignmentName the name of the assignment to be added.
    */
   public void addAssignment(String AssignmentName){
       assignmentList.addAssignmentTOView(AssignmentName);
        this.validate();
    }  
   
   
}
