
package FrontEnd;

/**
 *
 * @author Rylan
 */
public class StudentCoursePage extends CoursePage {
    public StudentCoursePage(){
        //Get set data fields from super
        super();
        
        setTitle("Student Course Page");
    }
    
     public static void main(String[] args) {
        StudentCoursePage coursePage = new StudentCoursePage();
        coursePage.setVisible(true);
        coursePage.addAssignment("TESTAssign1");
        coursePage.addAssignment("TESTAssign2");
        coursePage.addAssignment("TESTAssign3");
        System.out.println(coursePage.getAssignmentList().getAssignmentList().size());
    }
}
