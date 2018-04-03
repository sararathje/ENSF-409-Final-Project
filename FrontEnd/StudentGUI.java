
package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import Constants.LabelConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 *  Provides methods to create the GUI used by a student.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 2, 2018
 */
public class StudentGUI extends UserGUI implements ColourSchemeConstants, FontConstants, LabelConstants{                                        // this needs to be chaged to extend UserGui

    
     /**
     * Constructs an object of type StudentGUI.
     */
    public StudentGUI(){
        //Get set data firls from super
        super();
        
        // Set title
        setTitle("Student Home Page");
        
        //Set frame format
        setPreferredSize(new Dimension(1500, 1000));
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);

        
        //add 'My Courses' title to top
        addTitle();
        
        //add the course list to the center
        add("Center", courseList);
        
        //add the refresh button to the bottom
        addRefresh();
        
        //add blank space to the left and the right
        addBorders();
        pack();
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
    
    /**
     * Adds a title to the GUI.
     */
    private void addTitle(){
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(1000, 100));
        top.setBackground(LOGIN_BACKGROUND_COLOUR);
        JLabel title = new JLabel("My Courses");
        title.setFont(TITLE_FONT);
        title.setForeground(FOREGROUND_COLOUR);
        top.add(title);
        this.add("North", top);
    }
    
    /**
     * Adds a JPanel containing the refresh button to the bottom of the GUI.
     */
    private void addRefresh(){
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000, 100));
        bottom.setBackground(LOGIN_BACKGROUND_COLOUR);
        bottom.add(refresh);
        this.add("South", bottom);
    }
    
    /**
     * Adds blank space to the left and right sides of the GUI 
     * for aesthetic purposes.
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
     * Adds a course to the courseList
     * @param courseName the name of the course
     */
    public void addCourse(String courseName){
        courseList.addCourse(courseName);
        this.validate();
    }

    /**
     * Gets the courseList
     * @return the courseList
     */
    public CourseListView getCourseList() {
        return courseList;
    }
    
    
    
    // Placeholder for now just to test what it looks like
    public static void main(String[] args) {
        StudentGUI studentHome = new StudentGUI();
        studentHome.setVisible(true);
        studentHome.addCourse("TEST 123");
        studentHome.addCourse("TEST 456");
        studentHome.addCourse("TEST 789");
       
        System.out.println(studentHome.getCourseList().getViewButtons().size());
    }
}
