
package FrontEnd;

import Models.User;

import static Constants.FontConstants.BUTTON_FONT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 * The Professor Course home page.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 4, 2018
 */
public class ProfCoursePage extends CoursePage {
    
    /**
     * The list of students JscrollPane
     */
    private StudentListView studentList;
    
    private ArrayList<User> enrolledStudents;
    
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
    public ProfCoursePage(String courseName, Client client){
        //Get set data fields from super
        super(courseName, client);
        enrolledStudents = client.getEnrolledStudents(panelName);
        addStudentSubtitle();
        studentList = new StudentListView(client, enrolledStudents, panelName);
        middle.add(studentList);
        
        
        //Set the title of the GUI
        setTitle("Professor Course Page");
        addSearchlStudentButton();
        addAssignmentButton();
        addRefreshListener();
        updateEnrolledStudentList();
    }
    
    /**
     * adds 'Enrolled Students' to the assignments to the middle panel.
     */
   private void addStudentSubtitle(){
       JLabel subTitle = new JLabel("Enrolled Students:");
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

        searchStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentSearch studentSearch = new StudentSearch(ProfCoursePage.this, true, client,
                        ProfCoursePage.this.panelName);
                studentSearch.setVisible(true);
            }
        });

        bottom.add(searchStudent);
    }

    /**
     * Adds the add assignment button.
     */
    private void addAssignmentButton(){
        addAssignment = new JButton("Add Assignment");
        addAssignment.setFont(BUTTON_FONT);
        addAssignment.setMinimumSize(new Dimension(0, 50));
        
        addAssignment.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event)
                {
                    String[] course = ProfCoursePage.this.panelName.split(" ");

                    NewAssignment newView = new NewAssignment(ProfCoursePage.this, true, ProfCoursePage.this.client,
                                    Integer.parseInt(course[1]));
                    newView.setVisible(true);
                }
            });
        bottom.add(addAssignment);
        //TODO incorporate the courseID in the assignment instructor
    }
    
    /**
     * Adds a panel representing a student to the JScrollPane.
     * @param firstName student first panelName
     * @param lastName student last panelName
     * @param ID student ID
     */
    public void addStudent(User student, String courseName){
       studentList.addStudentToView(student, courseName);
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

    /**
     * Adds refresh listener
     */
   private void addRefreshListener() {
       refresh.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               updateAssignmentList();
               updateEnrolledStudentList();
           }
       });
   }

    /**
     * Updates the student list.
     */
    private void updateEnrolledStudentList() {
        // Empty the current student list
        studentList.setStudentList(new ArrayList<StudentPanel>());

        // Get list of enrolled students
        enrolledStudents = client.getEnrolledStudents(ProfCoursePage.this.panelName);

        Iterator<User> iterator = enrolledStudents.iterator();
        while(iterator.hasNext()) {
            User student = iterator.next();
            addStudent(student, panelName);
        }
        validate();
    }
    
//    public static void main(String[] args) {
//        ProfCoursePage coursePage = new ProfCoursePage("someString", new Client());
//        coursePage.setVisible(true);
//        coursePage.addAssignment("TEST_ASSIGNMENT 1");
//        coursePage.addAssignment("TEST_ASSIGNMENT 2");
//        coursePage.addAssignment("TEST_ASSIGNMENT 3");
//        coursePage.addAssignment("TEST_ASSIGNMENT 4");
//        coursePage.addAssignment("TEST_ASSIGNMENT 5");
//        coursePage.addAssignment("TEST_ASSIGNMENT 6");
//        coursePage.addAssignment("TEST_ASSIGNMENT 7");
//        coursePage.addAssignment("TEST_ASSIGNMENT 8");
//        coursePage.addAssignment("TEST_ASSIGNMENT 9");
//        coursePage.addAssignment("TEST_ASSIGNMENT 10");
//        coursePage.addAssignment("TEST_ASSIGNMENT 11");
//        coursePage.addAssignment("TEST_ASSIGNMENT 12");
//        coursePage.addAssignment("TEST_ASSIGNMENT 13");
//        
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        coursePage.addStudent("Jane", "Doe", 69696969);
//        
//        
//        
//        System.out.println(coursePage.getAssignmentList().getAssignmentList().size());
//        
//        
//        JButton b = coursePage.getUnenrollButton(2);
//    }

    
}
