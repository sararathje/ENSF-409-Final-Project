
package FrontEnd;

import Constants.ColourSchemeConstants;
import static Constants.ColourSchemeConstants.FOREGROUND_COLOUR;
import static Constants.ColourSchemeConstants.LOGIN_BACKGROUND_COLOUR;
import Constants.FontConstants;
import static Constants.FontConstants.BUTTON_FONT;
import static Constants.FontConstants.PANEL_TITLE_FONT;
import Models.User;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;

//Rylan, this is a mess...make it its own class.
public class StudentListView extends JScrollPane implements ColourSchemeConstants, FontConstants{
    
    private ArrayList<StudentPanel> studentList;
    
    private JPanel displayPanel;
    
    private Client client;
    
    private ArrayList<User> students;
    
    public StudentListView(Client client, ArrayList<User> students, String courseName){
        this.client = client;
        this.students = students;
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.PAGE_AXIS));
        displayPanel.setBackground(LOGIN_BACKGROUND_COLOUR);
        displayPanel.setBorder(BorderFactory.createLineBorder(FOREGROUND_COLOUR));
        this.getViewport().add(displayPanel);
        studentList = new ArrayList<>();
    }
    
    public void addStudentToView(User student, String courseName){
        StudentPanel newStudent = new StudentPanel(student, client, courseName, false);
        studentList.add(newStudent);

        refreshView();

        // Update current students
        for(int i = 0; i < studentList.size(); i++){
            displayPanel.add(studentList.get(i));
        }
    }

    /**
     * Sets the student list
     * @param studentPanelList array of panels to set the list to
     */
    public void setStudentList(ArrayList<StudentPanel> studentPanelList) {
        studentList = studentPanelList;
    }

    /**
     * Gets the student list
     * @return student list
     */
    public ArrayList<StudentPanel> getStudentList() {
        return studentList;
    }

    /**
     * Gets the display panel.
     * @return display panel
     */
    public JPanel getDisplayPanel() {
        return displayPanel;
    }

    /**
     * Remove all panels, revalidate and repaint view.
     */
    private void refreshView() {
        displayPanel.removeAll();
        displayPanel.revalidate();
        displayPanel.repaint();
    }
}
