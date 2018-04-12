
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import Constants.LabelConstants;
import Models.Assignment;
import Models.Date;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class AssignmentPage extends JFrame implements ColourSchemeConstants, FontConstants, LabelConstants{
    
    protected Assignment assignment;
    
    protected String panelName;
    
    protected JPanel middle;
    
    protected JPanel infoBar;
   
    protected JTextArea assignmentFileArea;
    
    protected JPanel bottom;
        
    protected JButton refresh;
    
    protected Client client;
    
    private JLabel dueDate;
    
    
    public AssignmentPage(Assignment assignment, Client client){
       this.assignment = assignment;
       this.client = client;
       panelName = assignment.getName();
       
        //Set frame format
        setPreferredSize(new Dimension(1500, 1000));
        setLayout(new BorderLayout());
        setBackground(LOGIN_BACKGROUND_COLOUR);
        
        //add 'Course Home' title to top
        addTitle(panelName);
        
        //add text area to center
        addMiddle();
        createInfoBar();
        addAssignmentFile();
        
        //add buttons
        addBottomPanel();
        createRefreshButton();
                
        //add blank space to the left and the right
        addBorders();
        pack();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        
    }
    
    /**
     * Adds the name of the assignment to the top of the panel.
     * @param panelName 
     */
    private void addTitle(String panelName){
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(1000, 100));
        top.setBackground(LOGIN_BACKGROUND_COLOUR);
        JLabel title = new JLabel(panelName); 
        title.setFont(TITLE_FONT);
        title.setForeground(FOREGROUND_COLOUR);
        top.add(title);
        this.add("North", top);
    }
     
    /**
     * Adds the bottom panel to the south portion of the gui.
     */
    private void addBottomPanel(){
        bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(1000, 100));
        bottom.setBackground(LOGIN_BACKGROUND_COLOUR);
        this.add("South", bottom);
        
    }
    
    /**
     * Creates refresh button at the bottom of the GUI.
     */
    private void createRefreshButton(){
        refresh = new JButton("Refresh");
        refresh.setFont(BUTTON_FONT);
        refresh.setMinimumSize(new Dimension(0, 50));
        bottom.add(refresh);
        //TODO add action listener
    }
    
    /**
     * Adds a panel to he center of the frame.
     */
    private void addMiddle(){
        middle = new JPanel();
        middle.setBackground(LOGIN_BACKGROUND_COLOUR);
        middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
        this.add("Center", middle);
    }
    
    /**
     * creates the info bar below the title.
     */
     private void createInfoBar(){
        infoBar = new JPanel();
        infoBar.setLayout(new BoxLayout(infoBar, BoxLayout.LINE_AXIS));
        infoBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        infoBar.setMinimumSize(new Dimension(0, 50));
        infoBar.setBackground(LOGIN_BACKGROUND_COLOUR);
        infoBar.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        middle.add(infoBar);
        
        JLabel label1 = new JLabel(" Due Date: ");
        label1.setFont(PANEL_TITLE_FONT);
        label1.setForeground(FOREGROUND_COLOUR);
        infoBar.add(label1);
        
        dueDate = new JLabel(parseDate(assignment.getDueDate()));
        dueDate.setFont(PANEL_TITLE_FONT);
        dueDate.setForeground(FOREGROUND_COLOUR);
        infoBar.add(dueDate);
        
        //not sure what to do about grades......
        
        infoBar.add(Box.createRigidArea(new Dimension(20,50)));
     }
     
     private String parseDate(Date date){
        String data = date.toString();
        data = data.replace(',', '/');
        return data;
    }
    
    private void addAssignmentFile(){
        assignmentFileArea = new JTextArea(10, 20);
        assignmentFileArea.setEditable(false);
        JScrollPane scroller = new JScrollPane(assignmentFileArea);
        middle.add(scroller);
    }
    
    
    /**
     * Adds blank space to either side of the GUI for aesthetic purposes.
     */
    private void addBorders(){
        JPanel right = new JPanel();
        right.setPreferredSize(new Dimension(100, 800));
        right.setBackground(LOGIN_BACKGROUND_COLOUR);
        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(100, 800));
        left.setBackground(LOGIN_BACKGROUND_COLOUR);
        add("East", right);
        add("West", left);
    }
   
     
}
