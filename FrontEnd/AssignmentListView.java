
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import Models.Assignment;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The list of Assignments to be displayed on the course page.
 * @author Rylan
 */
public class AssignmentListView extends JScrollPane implements ColourSchemeConstants, FontConstants{
    
    /**
     * List of assignment panels
     */
    private ArrayList<AssignmentPanel> assignmentList;
    
    /**
     * Panel to display the assignments
     */
    private JPanel displayPanel;

    /**
     * Client
     */
    private Client client;
    
    /**
     * Creates an object of type AssignmentList View.
     */
    public AssignmentListView(Client client){
        this.client = client;
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setBackground(LOGIN_BACKGROUND_COLOUR);
        displayPanel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        this.getViewport().add(displayPanel);
        assignmentList = new ArrayList<>();
    }
    
    /**
     * Adds panels that represent assignments to the display panel.
     * @param assignment 
     */
    public void addAssignmentToView(Assignment assignment){
        //creates a new JPanel for an assignment
        AssignmentPanel newAssignment = new AssignmentPanel(assignment, client);
        
        assignmentList.add(newAssignment);
        
        displayPanel.removeAll();

        for(int i = 0; i < assignmentList.size(); i++){
            displayPanel.add(assignmentList.get(i));
        }
    }
    
    /**
     * Gets the assignmentList.
     * @return assignment list
     */
     public ArrayList<AssignmentPanel> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(ArrayList<AssignmentPanel> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public JPanel getDisplayPanel() {
        return displayPanel;
    }
    
    
}
