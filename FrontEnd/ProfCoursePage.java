
package FrontEnd;

import static Constants.FontConstants.BUTTON_FONT;
import java.awt.Dimension;
import javax.swing.*;

/**
 * The Professor Course home page.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 4, 2018
 */
public class ProfCoursePage extends CoursePage{
    
    /**
     * The list of students JscrollPane
     */
    private StudentListView studentList;
    
    /**
     * The searchStudent button
     */
    private JButton searchStudent;
    
    /**
     * The addAssignment button
     */
    private JButton addAssignment;
    
    /**
     * Creates an object of ProfCoursePage.
     */
    public ProfCoursePage(){
        //Get set data fields from super
        super();
        
        addStudentSubtitle();
        studentList = new StudentListView();
        middle.add(studentList);
        
        
        //Set the title of the GUI
        setTitle("Professor Course Page");
        addSearchlStudentButton();
        addAssignmentButton();
        
    }
    
    /**
     * adds 'Enrolled Students' to the assignments to the middle panel.
     */
   private void addStudentSubtitle(){
       JLabel subTitle = new JLabel("Enrolled Students");
       subTitle.setFont(PANEL_TITLE_FONT);
       subTitle.setForeground(FOREGROUND_COLOUR);
       middle.add(subTitle);
   } 
    
   /**
    *  Adds the search student button.
    */
    private void addSearchlStudentButton(){
        searchStudent = new JButton("Search Students");
        searchStudent.setFont(BUTTON_FONT);
        searchStudent.setMinimumSize(new Dimension(0, 50));
        bottom.add(searchStudent);
    }
    
    
    /**
     * Adds the add assignment button.
     */
    private void addAssignmentButton(){
        addAssignment = new JButton("Add Assignment");
        addAssignment.setFont(BUTTON_FONT);
        addAssignment.setMinimumSize(new Dimension(0, 50));
        bottom.add(addAssignment);
    }
    
    /**
     * Adds a panel representing a student to the JScrollPane.
     * @param firstName
     * @param lastName
     * @param ID 
     */
    public void addStudent(String firstName, String lastName, int ID){
       studentList.addStudentToView(firstName, lastName, ID);
       validate();
    }  
    
    /**
     * Gets the Unenroll button from the student panel.
     * @param index
     * @return 
     */
    public JButton getUnenrollButton(int index){
       JPanel temp = studentList.getStudentList().get(index);
       System.out.println(temp.getComponentCount());
       return (JButton)temp.getComponent(2);
       
   }
    
    public static void main(String[] args) {
        ProfCoursePage coursePage = new ProfCoursePage();
        coursePage.setVisible(true);
        coursePage.addAssignment("TEST_ASSIGNMENT 1");
        coursePage.addAssignment("TEST_ASSIGNMENT 2");
        coursePage.addAssignment("TEST_ASSIGNMENT 3");
        coursePage.addAssignment("TEST_ASSIGNMENT 4");
        coursePage.addAssignment("TEST_ASSIGNMENT 5");
        coursePage.addAssignment("TEST_ASSIGNMENT 6");
        coursePage.addAssignment("TEST_ASSIGNMENT 7");
        coursePage.addAssignment("TEST_ASSIGNMENT 8");
        coursePage.addAssignment("TEST_ASSIGNMENT 9");
        coursePage.addAssignment("TEST_ASSIGNMENT 10");
        coursePage.addAssignment("TEST_ASSIGNMENT 11");
        coursePage.addAssignment("TEST_ASSIGNMENT 12");
        coursePage.addAssignment("TEST_ASSIGNMENT 13");
        
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        coursePage.addStudent("Jane", "Doe", 69696969);
        
        
        
        System.out.println(coursePage.getAssignmentList().getAssignmentList().size());
        
        
        JButton b = coursePage.getUnenrollButton(2);
    }

    
}
