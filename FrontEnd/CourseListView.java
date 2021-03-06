
package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


/**
 * Provides data fields to add courses to a displayPanel.
 * @author Rylan
 */
public class CourseListView extends JScrollPane implements ColourSchemeConstants, FontConstants {
    /**
     * the list of JPanels representing courses
     */
    private ArrayList<CoursePanel> courseList;
    
    /**
     * The JPanel that holds the courses
     */
    private JPanel displayPanel;
    
    private Client client;
    
    /**
     * Creates an object of CourseListView.
     */
    public CourseListView(Client client){
        this.client = client;
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setBackground(LOGIN_BACKGROUND_COLOUR);
        displayPanel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        this.getViewport().add(displayPanel);
        courseList = new ArrayList<>();
    }
    
    /**
     * Adds courses to the displayPanel.
     * @param courseName 
     */
    public void addCourse(String courseName, Client client) {
        // Creates a new JPanel for a course
        CoursePanel newCourse = new CoursePanel(courseName, client);
        courseList.add(newCourse);

        // Remove all previous courses from list
        displayPanel.removeAll();
        displayPanel.revalidate();
        displayPanel.repaint();

        // Display updated course list
        for(int i = 0; i < courseList.size(); i++){
            displayPanel.add(courseList.get(i));
        }
    }
    
    /**
     * Gets course list ArrayList.
     * @return courseList
     */
    public ArrayList<CoursePanel> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<CoursePanel> courseList) {
        this.courseList = courseList;
    }

    /**
     * Gets the display panel
     * @return display panel
     */
    public JPanel getDisplayPanel() {
        return displayPanel;
    }
}
