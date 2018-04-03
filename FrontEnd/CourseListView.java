
package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Provides data fields to add courses to a displayPanel.
 * @author Rylan
 */
public class CourseListView extends JScrollPane implements ColourSchemeConstants, FontConstants {
    /**
     * the list of JPanels representing courses
     */
    private ArrayList<JPanel> courseList;
    
    /**
     * The JPanel that holds the courses
     */
    private JPanel displayPanel;
    
    
    
    /**
     * Creates an object of CourseListView.
     */
    public CourseListView(){
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
    public void addCourse(String courseName){
        //creates a new JPanel for a course
        JPanel newCourse = new JPanel();
        newCourse.setLayout(new BoxLayout(newCourse, BoxLayout.LINE_AXIS));
        newCourse.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        newCourse.setMinimumSize(new Dimension(0, 50));
        newCourse.setBackground(LOGIN_BACKGROUND_COLOUR);
        newCourse.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        //creates the label for the course
        JLabel label = new JLabel(" " + courseName + " ");
        label.setFont(PANEL_TITLE_FONT);
        label.setForeground(FOREGROUND_COLOUR);
        newCourse.add(label);
        newCourse.add(Box.createHorizontalGlue());
        
        //creates the view button for the course
        JButton view = new JButton("View");
        view.setFont(BUTTON_FONT);
        newCourse.add(view);
        newCourse.add(Box.createRigidArea(new Dimension(20,0)));
        courseList.add(newCourse);
        
       
        //clears and adds all the new courses to displayPanel
        displayPanel.removeAll();
        for(int i = 0; i < courseList.size(); i++){
            displayPanel.add(courseList.get(i));
        }
    }

    
    

    /**
     * Gets course list ArrayList.
     * @return courseList
     */
    public ArrayList<JPanel> getViewButtons() {
        return courseList;
    }
    
    
}
