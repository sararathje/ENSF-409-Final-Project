
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import static Constants.FontConstants.BUTTON_FONT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Provides data fields and methods to create and use an assignment panel.
 * @author Rylan
 */
public class AssignmentPanel extends JPanel implements ColourSchemeConstants, FontConstants {
    
    private String assignmentName;
    
    private Client client;
    
    private JButton view;
    
    public AssignmentPanel(String assignmentName, Client client){
        this.assignmentName = assignmentName;
        this.client = client;
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        setMinimumSize(new Dimension(0, 50));
        setBackground(LOGIN_BACKGROUND_COLOUR);
        setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        addLabel(assignmentName);
        addViewButton(client);
        add(Box.createRigidArea(new Dimension(20,0)));
    }
    
    private void addViewButton(Client client){
        view = new JButton("View");
        view.setFont(BUTTON_FONT);
        view.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(client.getAuthenticatedUser().getUserType() == 'P' && e.getSource() == view){
                    //todo:  create an assignment page displaying assignment info.
                    System.out.println("Finish this");
                }
                else if(client.getAuthenticatedUser().getUserType() == 'S' && e.getSource() == view){
                     //todo:  create an assignment page displaying assignment info.
                    System.out.println("Finish this");
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
