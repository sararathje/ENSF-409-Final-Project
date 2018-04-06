package FrontEnd;

import java.io.*;
import java.net.Socket;
import Models.*;
import Constants.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * Retrieves information from server to feed to front-end of learning platform application.
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 1, 2018
 */

public class Client implements ConnectionConstants, MessageConstants {
    /**
     * Socket for establishing connection between client and server.
     */
    private Socket socket;

    /**
     * Object to write to the socket
     */
    private ObjectOutputStream socketOut;

    /**
     * Object to read from the socket
     */
    private ObjectInputStream socketIn;

    /**
     * Authenticated user to log in
     */
    private User authenticatedUser;

    /**
     * Login window
     */
    LoginWindow loginWindow;
    

    /**
     * Constructs a Client object with specified values for serverName and portNumber.
     * The values of the data fields are supplied by the given parameters.
     */
    public Client() {
        try {
            // Establish socket connection
            socket = new Socket(HOSTNAME, PORT);
            socketOut = new ObjectOutputStream(socket.getOutputStream());
            socketIn = new ObjectInputStream(socket.getInputStream());
            
        } catch (IOException e) {
            System.out.println("Error establishing socket connection on " + HOSTNAME + ": " + PORT);
            e.printStackTrace();
        }
    }

    /**
     * Runs the client.
     */
    public void runClient() {
        loginWindow = new LoginWindow(socketIn, socketOut);
        loginWindow.setVisible(true);

        while (true) {
            try {
                if (socketIn.readObject().equals(AUTHENTICATE)) {
                    authenticatedUser = (User) socketIn.readObject();
                }

                if (authenticatedUser != null) {
                    loginWindow.dispose();
                    System.out.println("authenticated");
                    if (authenticatedUser.getUserType() == 'P') {
                        ProfessorGUI profGUI = new ProfessorGUI(this);
                        profGUI.setVisible(true);
                    } else if (authenticatedUser.getUserType() == 'S') {
                        StudentGUI stuGUI = new StudentGUI(this);
                        stuGUI.setVisible(true);
                    }

                    break;
                } else {
                    JOptionPane.showMessageDialog(loginWindow, NO_USER_FOUND, "", JOptionPane.WARNING_MESSAGE);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

        public User getAuthenticatedUser() {
            return authenticatedUser;
        }
        
        /**
         * Gets the Courses from the Database
         */
        public void getCourseInfo(){
            try {
                String instruction = "Get Course Info";
                socketOut.writeObject(instruction);

                Object input = socketIn.readObject();

                if(input instanceof String && input.equals("Sending Course List")) {
                        ArrayList<Course> list = (ArrayList<Course>)socketIn.readObject();
                        this.authenticatedUser.setCourses(list);
                    }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    /**
     * Sends new course to server.
     * @param course course to send to server
     */
    void createNewCourse(Course course) {
//        // TODO: This should be attached to the listener to create course in GUI.
        try {
            socketOut.writeObject(course);
            socketOut.flush();
        } catch(IOException e) {
            System.out.println("Error sending new course to server");
            e.printStackTrace();
        }
    }
    //}

    /**
     * Sends course to server to update active status.
     * @param course course to update active status
     */
//    void setCourseActive(Course course) {
////        // TODO: This should be attached to listener to set course active.
////        try {
////            //stringOut.println(UPDATE_COURSE_ACTIVE);
////            socketOut.writeObject(course);
////            socketOut.flush();
////        } catch(IOException e) {
////            System.out.println("Error sending new course to server");
////            e.printStackTrace();
////        }
//    }

    /**
     * Sends search request to server by for student by last name and ID.
     * Note that a search is only performed on non-empty fields.
     * @param lastName student last name
     * @param id student ID
     */
//    void searchForStudent(String lastName, String id) {
////        // TODO: This should be attached to listener to search for student by last name.
////        try {
////            String studentLastName = lastName == "" ? null : lastName,
////                    studentID = id == ""? null : id;
////
////            stringOut.println(SEARCH_FOR_STUDENT);
////            socketOut.writeObject(studentLastName);
////            socketOut.flush();
////            socketOut.writeObject(studentID);
////            socketOut.flush();
////        } catch(IOException e) {
////            System.out.println("Error sending student search to server.");
////            e.printStackTrace();
////        }
//    }

//    /**
//     * Sends request to server to unenroll student from a course.
//     * @param student student to un-enroll
//     * @param course course to un-enroll student from
//     */
////    void unenrollStudent(User student, Course course) {
////        // TODO: This should be attached to the listener for removing a student from a course.
////        try {
////            stringOut.println(REMOVE_STUDENT);
////            socketOut.writeObject(student);
////            socketOut.flush();
////            socketOut.writeObject(course);
////            socketOut.flush();
////        } catch(IOException e) {
////            System.out.println("Error sending server request to un-enroll student");
////            e.printStackTrace();
////        }
//    }

//    /**
//     * Sends request to server to upload assignment to course.
//     * @param assignment assignment to upload
//     * @param course course to upload assignment to
//     */
////    void uploadAssignment(Assignment assignment, Course course) {
////        // TODO: This should be attached to the listener for uploading an assignment.
////        try {
////            socketOut.writeObject(assignment);
////            socketOut.flush();
////            socketOut.writeObject(course);
////            socketOut.flush();
////        } catch(IOException e) {
////            System.out.println("Error sending server request to un-enroll student");
////            e.printStackTrace();
////        }
//    }

    /**
     * Sends request to server to set assignment as active.
     * @param assignment assignment to set active
     */
//    void setAssignmentActive(Assignment assignment) {
//        // TODO: This should be attached to the listener for setting an assignment as active.
////        try {
////            socketOut.writeObject(assignment);
////            socketOut.flush();
////        } catch(IOException e) {
////            System.out.println("Error sending request to set assignment as active");
////            e.printStackTrace();
////        }
//    }

    /**
     * Gets the authenticated user.
     * @return authenticated user
     */
//    User getAuthenticatedUser() {
//        return authenticatedUser;
//    }
//}
    
 }
