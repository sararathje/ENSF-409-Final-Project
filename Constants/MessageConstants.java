package Constants;

/**
 * Defines the message constants for learning platform GUI.
 *
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 3, 2018
 */
public interface MessageConstants {
    String EMPTY_LOGIN = "Whoops! Either username or password has not been filled in.";
    String NO_USER_FOUND = "No account was found for this username and password";
    String EMPTY_SEARCH = "Whoops! No search fields have been entered";
    String NO_MATCHES_FOUND = "No matches found.";
    String INVALID_COURSE_ID = "A course ID can only contain digits. Letters are not permitted.";
    String EMPTY_NEW_COURSE_FIELDS = "Whoops! All fields must be filled in to create a course";
    String MESSAGE_SENT = "Email sent!";
    String NO_RECIPIENTS = "There are no students enrolled in this class! Cannot send email";
}
