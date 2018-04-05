
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import static Constants.FontConstants.BUTTON_FONT;
import static Constants.FontConstants.PANEL_TITLE_FONT;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;


public class StudentListView extends JScrollPane implements ColourSchemeConstants, FontConstants{
    private ArrayList<JPanel> studentList;
    
    private JPanel displayPanel;
    
    public StudentListView(){
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setBackground(LOGIN_BACKGROUND_COLOUR);
        displayPanel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        this.getViewport().add(displayPanel);
        studentList = new ArrayList<>();
    }
    
    public void addStudentToView(String firstName, String lastName, int ID){
        JPanel newStudent = new JPanel();
        newStudent.setLayout(new BoxLayout(newStudent, BoxLayout.LINE_AXIS));
        newStudent.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        newStudent.setMinimumSize(new Dimension(0, 50));
        newStudent.setBackground(LOGIN_BACKGROUND_COLOUR);
        newStudent.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        
        JLabel label = new JLabel(" " + lastName.toUpperCase() + ", " + firstName +" " + ID);
        label.setFont(PANEL_TITLE_FONT);
        label.setForeground(FOREGROUND_COLOUR);
        newStudent.add(label);
        newStudent.add(Box.createHorizontalGlue());
        
        JButton Unenroll = new JButton("Unenroll");
        Unenroll.setFont(BUTTON_FONT);
        newStudent.add(Unenroll);
        newStudent.add(Box.createRigidArea(new Dimension(20,0)));
        studentList.add(newStudent);
        
        displayPanel.removeAll();

        for(int i = 0; i < studentList.size(); i++){
            displayPanel.add(studentList.get(i));
        }
        this.validate();
    }

    public ArrayList<JPanel> getStudentList() {
        return studentList;
    }
    
    
    
}
