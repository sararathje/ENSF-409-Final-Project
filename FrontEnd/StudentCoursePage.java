
package FrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public StudentCoursePage(String courseName, Client client){
        //Get set data fields from super
        super(courseName, client);
        
        setTitle("Student Course Page");
        addRefreshListener();
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
    
     public static void main(String[] args) {
//        StudentCoursePage coursePage = new StudentCoursePage();
//        coursePage.setVisible(true);
//        coursePage.addAssignment("TESTAssign1");
//        coursePage.addAssignment("TESTAssign2");
//        coursePage.addAssignment("TESTAssign3");
//        System.out.println(coursePage.getAssignmentList().getAssignmentList().size());
    }
}
