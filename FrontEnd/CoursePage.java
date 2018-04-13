
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import static Constants.FontConstants.BUTTON_FONT;
import Constants.LabelConstants;
import Models.Assignment;
import Models.Course;
import Models.User;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The Course home page.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 4, 2018
 */
public class CoursePage extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants {
    /**
     * The name of the course and the number in the following format:
     * "course 123"
     */
    protected String panelName;
    
    protected AssignmentListView assignmentList;
    
    /**
     * The refresh button on the bottom of the page
     */
    protected JButton refresh;
    
    /**
     * The email button on the bottom of the page
     */
    protected JButton email;
    
    /**
     * The panel in the middle of the page
     */
    protected JPanel middle;
    
    /**
     * The panel at the bottom of the page
     */
    protected JPanel bottom;
    
    /**
     * The client using this page
     */
    protected Client client;

    /**
     * Email window
     */
    protected EmailWindow2 emailWindow;
    
    /**
     * Creates an object of type CoursePage.
     * @param courseName
     * @param client 
     */
    public CoursePage(String courseName, Client client){
       this.client = client;
        panelName = courseName;
        //Set frame format
        setPreferredSize(new Dimension(1500, 1000));
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);
        
        addMiddle();
        
        //create assignment list
        assignmentList = new AssignmentListView(client);
        initializeAssignListView(panelName);

        middle.add(assignmentList);
        
        //add 'Course Home' title to top
        addTitle(panelName);
        
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
     * Updates the Assignment List in the course view.
     */
    protected void updateAssignmentList() {
        ArrayList<AssignmentPanel> newList = new ArrayList<>();
        // empty out the current course list.
        assignmentList.setAssignmentList(newList);
        initializeAssignListView(panelName);
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
    * @param assignment the panelName of the assignment to be added.
    */
   public void addAssignment(Assignment assignment){
       assignmentList.addAssignmentToView(assignment);
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
   
   
   /**
    * Initializes the AssignmentListView on the page.
    * @param courseName 
    */
   private void initializeAssignListView(String courseName) {
       CoursePage.this.client.getAssignmentInfo(courseName, client.getAuthenticatedUser().getUserType());
       Course c = null;
       ArrayList<Course> courses = CoursePage.this.client.getAuthenticatedUser().getCourses();
       for(int i = 0; i < courses.size(); i++){
            String info = courses.get(i).getCourseName() +" " + courses.get(i).getCourseNumber();
            if(courseName.equals(info)){
                c = courses.get(i);
            }
        }

       for(int i = 0; i < c.getAssignmentList().size(); i++){
           CoursePage.this.addAssignment(c.getAssignmentList().get(i));
       }
   }
}
