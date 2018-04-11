
package FrontEnd;

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
    }
    
     public static void main(String[] args) {

    }
}
