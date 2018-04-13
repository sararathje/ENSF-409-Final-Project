
package FrontEnd;

import Models.Course;
import Models.User;

import static Constants.FontConstants.BUTTON_FONT;
import static Constants.MessageConstants.NO_RECIPIENTS;

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
    public ProfCoursePage(String courseName, Client client) {
        //Get set data fields from super
        super(courseName, client);
        enrolledStudents = client.getEnrolledStudents(panelName);
        addStudentSubtitle();
        studentList = new StudentListView(client, enrolledStudents, panelName);
        middle.add(studentList);
        
        
        //Set the title of the GUI
        String userName = client.getAuthenticatedUser().getFirstName() + " "
                + client.getAuthenticatedUser().getLastName();
        setTitle(userName + " Course Page (Professor)");
        addSearchlStudentButton();
        addAssignmentButton();
        addListeners();
        updateProfessorAssignmentList();
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
     * @param student student to add to panel
     * @param courseName course Name
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
     * Adds listeners to the professor course page.
     */
   private void addListeners() {
        addRefreshListener();
        addEmailListener();
   }

    /**
     * Adds refresh listener.
     */
   private void addRefreshListener() {
       refresh.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               updateProfessorAssignmentList();
               updateEnrolledStudentList();
           }
       });
   }

    /**
     * Add email listener.
     */
   private void addEmailListener() {
       email.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               ArrayList<User> students = client.getEnrolledStudents(panelName);

               if (!students.isEmpty()) {
                   emailWindow = new EmailWindow2(ProfCoursePage.this, false, client, students);
                   emailWindow.setVisible(true);
               } else {
                   JOptionPane.showMessageDialog(getContentPane(), NO_RECIPIENTS, ""
                           , JOptionPane.WARNING_MESSAGE);
               }
           }
       });
   }

    /**
     * Updates professor assignment list.
     */
    private void updateProfessorAssignmentList() {
       clearAssignmentList();
       Course c = getSelectedCourse(panelName);
       addAssignmentsToView(c);
    }

    /**
     * Adds assignments to the view.
     * @param c course that assignments belong to
     */
    private void addAssignmentsToView(Course c) {
        for(int i = 0; i < c.getAssignmentList().size(); i++) {
           addAssignment(c.getAssignmentList().get(i));
       }
    }

    /**
     * Updates the student list.
     */
    private void updateEnrolledStudentList() {
        // Get list of enrolled students
        enrolledStudents = client.getEnrolledStudents(panelName);

        if (enrolledStudents.isEmpty()) {
            refreshView();
        }

        // Empty the current student list
        studentList.getStudentList().clear();

        Iterator<User> iterator = enrolledStudents.iterator();
        while(iterator.hasNext()) {
            User student = iterator.next();
            addStudent(student, panelName);
        }
    }

    /**
     * Refreshes the view
     */
    private void refreshView() {
        studentList.getDisplayPanel().removeAll();
        studentList.getDisplayPanel().revalidate();
        studentList.getDisplayPanel().repaint();
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
//        
//        
//        JButton b = coursePage.getUnenrollButton(2);
//    }

    
}
