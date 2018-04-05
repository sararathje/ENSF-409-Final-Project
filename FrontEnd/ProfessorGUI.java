
package FrontEnd;

import static Constants.FontConstants.BUTTON_FONT;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *  Provides methods to create the GUI used by a Professor.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 4, 2018
 */
public class ProfessorGUI extends UserGUI {
    
    private JButton createCourse;
    
    
    public ProfessorGUI(){
        //Get set data fields from super
        super();
        
        // Set title
        setTitle("Professor Home Page");
        
        //add create course button
        addCreateCourseButton();
    }
    
    /**
     * Adds the create course button to the bottom JPanel.
     */
    private void addCreateCourseButton(){
        createCourse = new JButton("Create Course");
        createCourse.setFont(BUTTON_FONT);
        createCourse.setPreferredSize(new Dimension(200, 50));
        bottom.add(createCourse);
    }
    
     public static void main(String[] args) {
        ProfessorGUI ProfHome = new ProfessorGUI();
        ProfHome.setVisible(true);
        ProfHome.addCourse("TEST 123");
        ProfHome.addCourse("TEST 456");
        ProfHome.addCourse("TEST 789");
        
    }
}
