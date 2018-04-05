
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class AssignmentListView extends JScrollPane implements ColourSchemeConstants, FontConstants{
    
    private ArrayList<JPanel> assignmentList;
    
    private JPanel displayPanel;
    
    
    
    public AssignmentListView(){
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setBackground(LOGIN_BACKGROUND_COLOUR);
        displayPanel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        this.getViewport().add(displayPanel);
        assignmentList = new ArrayList<>();
    }
    
    public void addAssignment(String assignmentName){
        JPanel newAssignment = new JPanel();
        newAssignment.setLayout(new BoxLayout(newAssignment, BoxLayout.LINE_AXIS));
        newAssignment.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        newAssignment.setMinimumSize(new Dimension(0, 50));
        newAssignment.setBackground(LOGIN_BACKGROUND_COLOUR);
        newAssignment.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        JLabel label = new JLabel(" " + assignmentName + " ");
        label.setFont(PANEL_TITLE_FONT);
        label.setForeground(FOREGROUND_COLOUR);
        newAssignment.add(label);
        newAssignment.add(Box.createHorizontalGlue());
        
        JButton view = new JButton("View");
        view.setFont(BUTTON_FONT);
        newAssignment.add(view);
        newAssignment.add(Box.createRigidArea(new Dimension(20,0)));
        assignmentList.add(newAssignment);
        
        displayPanel.removeAll();
        for(int i = 0; i < assignmentList.size(); i++){
            displayPanel.add(assignmentList.get(i));
        }
    }
    
     public ArrayList<JPanel> getAssignmentList() {
        return assignmentList;
    }
}
