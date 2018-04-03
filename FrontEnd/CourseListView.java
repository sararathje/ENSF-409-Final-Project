
package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Provides data fields to add courses to a panel.
 * @author Rylan
 */
public class CourseListView extends JPanel implements ColourSchemeConstants, FontConstants {
    private ArrayList<JButton> viewButtons;
    
    
    /**
     * Creates an object of CourseListView.
     */
    public CourseListView(){
        this.setLayout(new BoxLayout(this , 1));
        viewButtons = new ArrayList<>();
        
    }
    
    
    // I don't like this and im not sure how the courses are gonna
    // be added to the GUI.
    
    /**
     * Adds courses to the panel.
     * @param courseName 
     */
    public void addCourse(String courseName){
        JButton newButton = new JButton("View");
        newButton.setFont(BUTTON_FONT);
        viewButtons.add(newButton);
        JPanel course = new JPanel();
        course.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label = new JLabel(courseName+ "                              "
                + "                                     ");
        label.setFont(PANEL_TITLE_FONT);
        label.setForeground(FOREGROUND_COLOUR);
        course.add(label);
        course.add(viewButtons.get(viewButtons.size()-1));
        course.setBackground(LOGIN_BACKGROUND_COLOUR);
        this.add(course);
    }
    
}
