package Constants;
/**
 * Defines the connection constants for socket communication.
 *
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 1, 2018
 */
public interface ConnectionConstants {
    String HOSTNAME = "localhost";
    int PORT = 9090;
    
    String AUTHENTICATE = "AUTHENTICATE";
    String NEW_COURSE = "NEW_COURSE";
    String UPDATE_COURSE_ACTIVE = "UPDATE_COURSE_ACTIVE";
    String SEARCH_FOR_STUDENT = "STUDENT_SEARCH";
    String REMOVE_STUDENT = "REMOVE_STUDENT";
    String ENROLL_STUDENT = "ENROLL_STUDENT";
    String UNENROLL_STUDENT = "UNENROLL_STUDENT";
    String UPLOAD_ASSIGNMENT = "UPLOAD_ASSIGNMENT";
    String SET_ASSIGNMENT_ACTIVE = "SET_ASSIGNMENT_ACTIVE";
    String SET_ASSIGNMENT_NOT_ACTIVE = "SET_A_NOT_ACTIVE";
}
