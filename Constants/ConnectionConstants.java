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
    
    String QUIT = "QUIT";
    String AUTHENTICATE = "AUTHENTICATE";
    String NEW_COURSE = "NEW_COURSE";
    String UPDATE_COURSE_ACTIVE = "UPDATE_COURSE_ACTIVE";
    String SEARCH_FOR_STUDENT = "STUDENT_SEARCH";
	String SEND_STUDENT_RESULT = "SEND_STUDENT_RESULT";
    String REMOVE_STUDENT = "REMOVE_STUDENT";
    String ENROLL_STUDENT = "ENROLL_STUDENT";
    String UNENROLL_STUDENT = "UNENROLL_STUDENT";
    String UPLOAD_ASSIGNMENT = "UPLOAD_ASSIGNMENT";
    String SET_ASSIGNMENT_ACTIVE = "SET_ASSIGNMENT_ACTIVE";
    String SET_ASSIGNMENT_INACTIVE = "SET_A_INACTIVE";
    String GET_COURSE_INFO = "Get Course Info";
}
