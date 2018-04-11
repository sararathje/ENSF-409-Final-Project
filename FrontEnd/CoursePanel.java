

package FrontEnd;

import Constants.ColourSchemeConstants;
import Constants.FontConstants;
import static Constants.FontConstants.BUTTON_FONT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class CoursePanel extends JPanel implements ColourSchemeConstants, FontConstants{
    
    private String CourseName;
    
    private Client client;
    
    private JButton view;

    public CoursePanel(String CourseName, Client client) {
        this.CourseName = CourseName;
        this.client = client;
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setMinimumSize(new Dimension(0, 50));
        setBackground(LOGIN_BACKGROUND_COLOUR);
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        addLabel(CourseName);
        addViewButton(client);
        add(Box.createRigidArea(new Dimension(20,0)));
    }
    
    
    private void addViewButton(Client client){
        view = new JButton("View");
        view.setFont(BUTTON_FONT);
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(client.getAuthenticatedUser().getUserType() == 'P' && e.getSource() == view){
                    ProfCoursePage profCoursePage = new ProfCoursePage(CoursePanel.this.getCourseName(), client);
                    profCoursePage.setVisible(true);
                }
                else if(client.getAuthenticatedUser().getUserType() == 'S' && e.getSource() == view){
                    StudentCoursePage studCoursePage = new StudentCoursePage(CoursePanel.this.getCourseName(), client);
                    studCoursePage.setVisible(true);
                }
            }
    
                
        });
        add(view);
    }

    public String getCourseName() {
        return CourseName;
    }

    
    
    private void addLabel(String courseName){
        JLabel label = new JLabel(" " + courseName + " ");
        label.setFont(PANEL_TITLE_FONT);
        label.setForeground(FOREGROUND_COLOUR);
        add(label);
        add(Box.createHorizontalGlue());
    }
}
