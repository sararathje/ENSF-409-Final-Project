package FrontEnd;


/**
 *  Provides methods to create the GUI used by a student.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 2, 2018
 */
public class StudentGUI extends UserGUI {
    /**
     * Constructs an object of type StudentGUI with the value of client specified by the given parameter.
     * @param client client
     */
    public StudentGUI(Client client){
        //Get set data fields from super
        super(client);
        
        // Set title
        String userName = client.getAuthenticatedUser().getFirstName() + " "
                + client.getAuthenticatedUser().getLastName();
        setTitle(userName + " Home Page (Student)");
    }
   
    // Placeholder for now just to test what it looks like
    public static void main(String[] args) {
//        StudentGUI studentHome = new StudentGUI();
//        studentHome.setVisible(true);
//        studentHome.addCourse("TEST 123");
//        studentHome.addCourse("TEST 456");
//        studentHome.addCourse("TEST 789");
//        
    }
}
