
package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import Constants.LabelConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class StudentGUI extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants{                                        // this needs to be chaged to extend UserGui
    
    private CourseListView courseList;
    
     /**
     * Constructs an object of type StudentGUI.
     */
    public StudentGUI(){
        // Set title
        this.setPreferredSize(new Dimension(600, 600));
        setTitle("Student Home Page");
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);
        courseList = new CourseListView();

        
        addTitle();
        add("Center", courseList);
        pack();
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    }
    
    /**
     * Adds a title to the GUI
     */
    private void addTitle(){
        JPanel top = new JPanel();
        top.setBackground(LOGIN_BACKGROUND_COLOUR);
        JLabel title = new JLabel("My Courses");
        title.setFont(PANEL_TITLE_FONT);
        title.setForeground(FOREGROUND_COLOUR);
        top.add("North", title);
        this.add("North", top);
    }

    public CourseListView getCourseList() {
        return courseList;
    }
    
    
    
    // Placeholder for now just to test what it looks like
    public static void main(String[] args) {
        StudentGUI studentHome = new StudentGUI();
        studentHome.setVisible(true);
        studentHome.getCourseList().addCourse("TEST 123");
        studentHome.getCourseList().addCourse("TEST 456");
        studentHome.getCourseList().addCourse("TEST 789");
    }
}
