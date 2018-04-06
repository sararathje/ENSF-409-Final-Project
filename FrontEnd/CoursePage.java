
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
 * The Course home page.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 4, 2018
 */
public class CoursePage extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants {
    protected String name;
    protected AssignmentListView assignmentList;
    protected JButton refresh;
    protected JButton email;
    protected JPanel middle;
    protected JPanel bottom; 
    
    public CoursePage(String courseName){
       
        name = courseName;
        //Set frame format
        setPreferredSize(new Dimension(1500, 1000));
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);
        
        addMiddle();
        
        //create assignment list
        assignmentList = new AssignmentListView();
        middle.add(assignmentList);
        
        
        //add 'Course Home' title to top
        addTitle(courseName);
        
        //add buttons
        addBottomPanel();
        createRefreshButton();
        createEmailButton();
        
        //add blank space to the left and the right
        addBorders();
        pack();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
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
     * Adds a panel to he center of the frame.
     */
    private void addMiddle(){
        middle = new JPanel();
        middle.setBackground(LOGIN_BACKGROUND_COLOUR);
        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        JLabel subTitle = new JLabel("Assignments:");
        subTitle.setFont(PANEL_TITLE_FONT);
        subTitle.setForeground(FOREGROUND_COLOUR);
        middle.add(subTitle);
        this.add("Center", middle);
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
    private void addTitle(String courseName){
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(1000, 100));
        top.setBackground(LOGIN_BACKGROUND_COLOUR);
        JLabel title = new JLabel(courseName + " Home Page"); 
        title.setFont(TITLE_FONT);
        title.setForeground(FOREGROUND_COLOUR);
        top.add(title);
        this.add("North", top);
    }

    
    /**
     * Gets the AssignmentList.
     * @return 
     */
    public AssignmentListView getAssignmentList() {
        return assignmentList;
    }
    
    
    
   /**
    * Adds an assignment to the assignment list.
    * @param AssignmentName the name of the assignment to be added.
    */
   public void addAssignment(String AssignmentName){
       assignmentList.addAssignmentTOView(AssignmentName);
       validate();
    }  
   
   /**
    * Returns the 'View' button of the assignment.
    * @param index
    * @return View button.
    */
   public JButton getAssignmentViewButton(int index){
       JPanel temp = assignmentList.getAssignmentList().get(index);
       System.out.println(temp.getComponentCount());
       return (JButton)temp.getComponent(2);
       
   }
   
}
