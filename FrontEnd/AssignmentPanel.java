
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import static Constants.FontConstants.BUTTON_FONT;
import Models.Assignment;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Provides data fields and methods to create and use an assignment panel.
 * @author Rylan
 */
public class AssignmentPanel extends JPanel implements ColourSchemeConstants, FontConstants {
    
    private Assignment assignment;
    
    private String assignmentName;
    
    private Client client;
    
    private JButton view;
    
    public AssignmentPanel(Assignment assignment, Client client){
        this.assignment = assignment;
        assignmentName = assignment.getName();
        this.client = client;
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setMinimumSize(new Dimension(0, 50));
        setBackground(LOGIN_BACKGROUND_COLOUR);
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        addLabel(assignmentName);
        addViewButton(client);
        add(Box.createRigidArea(new Dimension(20,50)));
    }
    
    private void addViewButton(Client client){
        view = new JButton("View");
        view.setFont(BUTTON_FONT);
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(client.getAuthenticatedUser().getUserType() == 'P' && e.getSource() == view){
                    ProfAssignmentPage profAssignPage = new ProfAssignmentPage(AssignmentPanel.this.assignment, AssignmentPanel.this.client);
                    profAssignPage.setVisible(true);
                }
                else if(client.getAuthenticatedUser().getUserType() == 'S' && e.getSource() == view){
                    StudentAssignmentPage studentAssignPage = new StudentAssignmentPage(AssignmentPanel.this.assignment, AssignmentPanel.this.client);
                    studentAssignPage.setVisible(true);
                }
            }
    
                
        });
        add(view);
    }

    public String getAssignmentName() {
        return assignmentName;
    }
    
    private void addLabel(String assignmentName){
        JLabel label = new JLabel(" " + assignmentName + " ");
        label.setFont(PANEL_TITLE_FONT);
        label.setForeground(FOREGROUND_COLOUR);
        add(label);
        add(Box.createHorizontalGlue());
    }
    
}
