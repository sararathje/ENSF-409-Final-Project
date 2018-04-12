
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

        emailWindow = new EmailWindow(StudentCoursePage.this, false, client, userList);
        emailWindow.setVisible(true);
    }
}
