
package FrontEnd;

import Models.Course;
import Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *The Student Course home page.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 4, 2018
 */
public class StudentCoursePage extends CoursePage {
    /**
     * Creates new StudentCourse object.
     */
    public StudentCoursePage(String courseName, Client client) {
        //Get set data fields from super
        super(courseName, client);

        String userName = client.getAuthenticatedUser().getFirstName() + " "
                + client.getAuthenticatedUser().getLastName();
        setTitle(userName + " Course Page (Student)");
        
        updateAssignmentList();
        
        addListeners();
    }

    /**
     * Adds listeners to the student course page.
     */
    private void addListeners() {
        addRefreshListener();
        addEmailListener();
    }

    /**
     * Adds refresh listener
     */
    private void addRefreshListener() {
        refresh.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                updateAssignmentList();
            }
        });
    }

    /**
     * Add email listener.
     */
    private void addEmailListener() {
        email.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer id = getProfIdFromCourseName();

                if (!id.equals(null)) {
                    User professor = client.searchProfessor(id);
                    openEmailWindow(professor);
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "Something went very wrong", "",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * Gets the prof ID from the course name.
     * @return prof ID for given course
     */
    private Integer getProfIdFromCourseName() {
        ArrayList<Course> userCourses = client.getAuthenticatedUser().getCourses();
        Iterator<Course> iterator = userCourses.iterator();
        Integer profID = null;

        while(iterator.hasNext()) {
            Course course = iterator.next();
            String fullCourseName = course.getCourseName() + " " + course.getCourseNumber();

            if (fullCourseName.equals(panelName)) {
                return course.getProfID();
            }
        }

        return profID;
    }

    /**
     * Opens an email window.
     * @param professor professor to send the email to
     */
    private void openEmailWindow(User professor) {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(professor);

        emailWindow = new EmailWindow2(StudentCoursePage.this, false, client, userList);
        emailWindow.setVisible(true);
    }
    
    @Override
    protected void updateAssignmentList() {
        ArrayList<AssignmentPanel> newList = new ArrayList<>();
        // empty out the current course list.
        assignmentList.setAssignmentList(newList);
        initializeStudentAssignListView(panelName);
    }
    
     /**
    * Initializes the AssignmentListView on the page.
    * @param courseName 
    */
   private void initializeStudentAssignListView(String courseName) {
       StudentCoursePage.this.client.getAssignmentInfo(courseName, client.getAuthenticatedUser().getUserType());
       Course c = null;
       ArrayList<Course> courses = StudentCoursePage.this.client.getAuthenticatedUser().getCourses();
       for(int i = 0; i < courses.size(); i++){
            String info = courses.get(i).getCourseName() +" " + courses.get(i).getCourseNumber();
            if(courseName.equals(info)){
                c = courses.get(i);
            }
        }
       for(int i = 0; i < c.getAssignmentList().size(); i++){
           if(c.getAssignmentList().get(i).isActive()){
               System.out.println("blah  ");
                StudentCoursePage.this.addAssignment(c.getAssignmentList().get(i));
           }
       }
   }
}
