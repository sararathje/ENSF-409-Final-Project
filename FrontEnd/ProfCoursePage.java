
package FrontEnd;

import static Constants.FontConstants.BUTTON_FONT;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Rylan
 */
public class ProfCoursePage extends CoursePage{
    
    private JOptionPane StudentList;
    private NewAssignment newAssignment;
    private JButton enrollStudents;
    private JButton getStudentList;
    private JButton addAssignment;
    
    public ProfCoursePage(){
        //Get set data fields from super
        super();
        
        //Set the title of the GUI
        setTitle("Professor Course Page");
        addEnrollStudentButton();
        addGetStudentListButton();
        addAssignmentButton();
        
    }
    
    private void addEnrollStudentButton(){
        enrollStudents = new JButton("Enroll Students");
        enrollStudents.setFont(BUTTON_FONT);
        enrollStudents.setMinimumSize(new Dimension(0, 50));
        bottom.add(enrollStudents);
    }
    
    private void addGetStudentListButton(){
        getStudentList = new JButton("Student List");
        getStudentList.setFont(BUTTON_FONT);
        getStudentList.setMinimumSize(new Dimension(0, 50));
        bottom.add(getStudentList);
    }
    
    private void addAssignmentButton(){
        addAssignment = new JButton("Add Assignment");
        addAssignment.setFont(BUTTON_FONT);
        addAssignment.setMinimumSize(new Dimension(0, 50));
        bottom.add(addAssignment);
    }
    
    public void addAssignment(){
        newAssignment = new NewAssignment(this, true);
        //some stuff that greates a new assinment
        
    }
    
    
    public static void main(String[] args) {
        ProfCoursePage coursePage = new ProfCoursePage();
        coursePage.setVisible(true);
        coursePage.addAssignment();
        
        System.out.println(coursePage.getAssignmentList().getAssignmentList().size());
    }

    
}
