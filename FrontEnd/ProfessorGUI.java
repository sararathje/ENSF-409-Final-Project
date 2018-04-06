
package FrontEnd;

import static Constants.FontConstants.BUTTON_FONT;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *  Provides methods to create the GUI used by a Professor.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 4, 2018
 */
public class ProfessorGUI extends UserGUI {
    
    /**
     * Button to create a new course
     */
    private JButton createCourse;
    
    /**
     * new course JOptionPane
     */
    private NewCourse newCourse;
    
    public ProfessorGUI(Client client){
        //Get set data fields from super
        super(client);
        
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
        
        createCourse.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(e.getSource()== createCourse){
                newCourse = new NewCourse(ProfessorGUI.this, true,ProfessorGUI.this.getClient());
                newCourse.setVisible(true);
                }
            }
        });
    }

    
    
    public Client getClient() {
        return client;
    }
    
    
    
     public static void main(String[] args) {
         Client c = new Client();
//        ProfessorGUI ProfHome = new ProfessorGUI(c);
//        ProfHome.setVisible(true);
//        ProfHome.addCourse("TEST 123");
//        ProfHome.addCourse("TEST 456");
//        ProfHome.addCourse("TEST 789");
//        JButton b = ProfHome.getCourseViewButton(1);
        
    }
}
