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
    String HOSTNAMEREMOTE = "DESKTOP-A3JUSUB";
    String IPADDRESS = "136.159.49.113";
    byte[] ipAddress = new byte[] {(byte)136,(byte)159,(byte)49,(byte)113};
    int PORT = 9090;
    
    String CLIENTTEMPPATH = "C:\\temp\\"; //Jack's path: C:\\temp\\
    String serverDirPath = "C:\\ENSF_409\\"; //Jack's path: C:\\ENSF_409\\
    // String serverDirPath = "/Users/sararathje/Desktop/";
    
    //List of commands that the client can send to the server
    String QUIT = "QUIT";
    String AUTHENTICATE = "AUTHENTICATE";
    
    String NEW_COURSE = "NEW_COURSE";
    String GET_COURSE_INFO = "Get Course Info";
    String GET_ASSIGNMENT_INFO = "Get Assignment Info";
    String COURSE_LIST_STUDENT = "GET COURSE LIST STUDENT";
    String COURSE_LIST_PROF = "GET COURSE LIST PROF";
    String SET_COURSE_ACTIVE = "UPDATE_COURSE_ACTIVE";
    String SET_COURSE_INACTIVE = "SET_COURSE_INACTIVE";
    String SEND_COURSE_LIST = "Sending Course List";
    
    String SEARCH_FOR_STUDENT = "STUDENT_SEARCH";
    String ENROLL_STUDENT = "ENROLL_STUDENT";
    String UNENROLL_STUDENT = "UNENROLL_STUDENT";
    String GET_ENROLLED_STUDENTS = "GET_ENROLLED_STUDENTS";
    String SEND_STUDENT_RESULT = "SEND_STUDENT_RESULT";
    
    String SET_ASSIGNMENT_ACTIVE = "SET_ASSIGNMENT_ACTIVE";
    String SET_ASSIGNMENT_INACTIVE = "SET_A_INACTIVE";
    String NEW_ASSIGNMENT = "NEW_ASSIGNMENT";
    String ASSIGNMENT_LIST_STUDENT = "GET ASSIGNMENT LIST STUDENT";
    String ASSIGNMENT_LIST_PROF = "Get assignment list prof";
    String SENDING_ASSIGNMENT_LIST = "Sending Assignment List";
    
    String SUBMIT_ASSIGNMENT = "SUBMIT ASSIGNMENT";
    String DOWNLOAD_SUBMISSION = "download submission";
    String GRADE_SUBMISSION = "GRADE SUBMISSION";
    String GET_GRADE = "GET GRADE";
    String GET_SUBMISSIONS = "GET SUBMISSIONS";
    String UPLOAD_FILE = "UPLOAD FILE";
    String DOWNLOAD_FILE = "DOWNLOAD FILE";
    String TXT = ".txt";
    String PDF = ".pdf";
    
    String SEND_EMAIL = "send email";

    String SEARCH_FOR_PROF = "SEARCH_FOR_PROF";
}
