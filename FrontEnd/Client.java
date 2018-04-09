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
     * Professor GUI
     */
    ProfessorGUI profGUI;

    /**
     * Student GUI
     */
    StudentGUI stuGUI;


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
        while (true) {
        	LoginWindow loginWindow = new LoginWindow(socketIn, socketOut);
            loginWindow.setVisible(true);
            try {
                if (socketIn.readObject().equals(AUTHENTICATE)) {
                    authenticatedUser = (User) socketIn.readObject();
                }

                if (authenticatedUser != null) {
                    loginWindow.dispose();
                    System.out.println("authenticated");
                    if (authenticatedUser.getUserType() == 'P') {
                        profGUI = new ProfessorGUI(this);
                        profGUI.setVisible(true);
                    } else if (authenticatedUser.getUserType() == 'S') {
                        stuGUI = new StudentGUI(this);
                        stuGUI.setVisible(true);
                    }

                    break;
                } else {
                	loginWindow.dispose();
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
    void getCourseInfo(){
        try {
            sendObject(GET_COURSE_INFO);
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
     * Gets course list for the user from database
     * @return
     */
    void getCourseList()
    {
		ArrayList<Course> courses = new ArrayList<Course>();
    	try
    	{
	    	if(authenticatedUser.getUserType() == 'P')
	    	{
	    		sendObject(COURSE_LIST_PROF);
	    		courses = (ArrayList<Course>)socketIn.readObject();
	    		
	    	}
	    	else if(authenticatedUser.getUserType() == 'S')
	    	{
	    		sendObject(COURSE_LIST_STUDENT);
	    		courses = (ArrayList<Course>)socketIn.readObject();
	    	}
    	}
    	catch(IOException e)
    	{
    		System.err.println("Error in getting course list");
    	}
    	catch(ClassNotFoundException f)
    	{
    		System.err.println("Error in getting course list");
    	}
		this.authenticatedUser.setCourses(courses);
    }
    
    /**
     * Sends new course to server.
     * @param course course to send to server
     */
    void createNewCourse(Course course) {
        try {
        	sendObject(NEW_COURSE);
            sendObject(course);
        } catch(IOException e) {
            System.out.println("Error sending new course to server");
            e.printStackTrace();
        }
    }

    /**
     * Sends course to server to update active status to true.
     * @param course course to update active status
     */
    void setCourseActive(Course course) {
        try {
            sendObject(SET_COURSE_ACTIVE);
            sendObject(course);
            
        } catch(IOException e) {
            System.out.println("Error sending new course to server");
            e.printStackTrace();
        }
    }
    
    /**
     * Sends course to server to update active status to false.
     * @param course course to update active status
     */
    void setCourseInactive(Course course) {
        try {
            sendObject(SET_COURSE_INACTIVE);
            sendObject(course);
            
        } catch(IOException e) {
            System.out.println("Error sending new course to server");
            e.printStackTrace();
        }
    }

    /**
     * Sends search request to server by for student by last name and ID.
     * Note that a search is only performed on non-empty fields.
     * @param lastName student last name
     * @param id student ID
     * @param courseName course name
     */
    void searchForStudent(String lastName, String id, String courseName) {
        try {
            sendObject(SEARCH_FOR_STUDENT);
            sendObject(lastName);
            sendObject(id);

            Object input = socketIn.readObject();

            if (input instanceof String && input.equals(SEND_STUDENT_RESULT)) {
                // Read in matching student object and then show the Student GUI?
                ArrayList<User> matchedStudents = (ArrayList<User>) socketIn.readObject();
                if (!matchedStudents.isEmpty()) {
                    StudentSearchResults studentResults = new StudentSearchResults(profGUI, true, this, matchedStudents, courseName);
                    studentResults.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No matches found", "",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch(IOException e) {
            System.out.println("Error sending student search to server.");
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends request to server to unenroll student from a course.
     * @param student student to un-enroll
     * @param courseName course to un-enroll student from
     */
    void unenrollStudent(User student, String courseName) {
        try {
            sendObject(UNENROLL_STUDENT);
            sendObject(student);
            sendObject(courseName);
            
        } catch(IOException e) {
            System.out.println("Error sending server request to un-enroll student");
            e.printStackTrace();
        }
    }
    
    /**
     * Sends request to server to enroll student from a course.
     * @param student student to un-enroll
     * @param courseName course to un-enroll student from
     */
    void enrollStudent(User student, String courseName) {
        try {
            sendObject(ENROLL_STUDENT);
            sendObject(student);
            sendObject(courseName);
        } catch(IOException e) {
            System.out.println("Error sending server request to un-enroll student");
            e.printStackTrace();
        }
    }

    /**
     * Sends request to server to upload assignment to course.
     * @param assignment assignment to upload
     */
    void uploadAssignment(Assignment assignment) {
        try {
        	sendObject(NEW_ASSIGNMENT);
            sendObject(assignment);
        } catch(IOException e) {
            System.out.println("Error sending server request to un-enroll student");
            e.printStackTrace();
        }
    }

    /**
     * Sends request to server to set assignment as active.
     * @param assignment assignment to set active
     */
    void setAssignmentActive(Assignment assignment) {
        try {
        	sendObject(SET_ASSIGNMENT_ACTIVE);
            sendObject(assignment);
            
        } catch(IOException e) {
            System.out.println("Error sending request to set assignment as active");
            e.printStackTrace();
        }
    }
    
    /**
     * Gets assignment list for a course
     */
    void getAssignmentList(String course)
    {
    	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    	try
    	{
	    	if(authenticatedUser.getUserType() == 'P')
	    	{
	    		sendObject(ASSIGNMENT_LIST_PROF);
	    		sendObject(course);
	    		assignments = (ArrayList<Assignment>)socketIn.readObject();
	    		
	    	}
	    	else if(authenticatedUser.getUserType() == 'S')
	    	{
	    		sendObject(ASSIGNMENT_LIST_STUDENT);
	    		assignments = (ArrayList<Assignment>)socketIn.readObject();
	    	}
    	}
    	catch(IOException e)
    	{
    		System.err.println("Error in getting course list");
    	}
    	catch(ClassNotFoundException f)
    	{
    		System.err.println("Error in getting course list");
    	}
		//TODO make the assignment list visible on the GUI
    }
    
    /**
     * Sends a message to server to stop session
     */
    void quit()
    {
    	try
    	{
    		sendObject(QUIT);	
    	}
    	catch(IOException e)
    	{
    		System.err.println("Mwahahahahahahahahah");
    	}
    	
    }
    
    /**
     * Uploads a file to the server
     */
    void uploadFile()
    {
    	//TODO
    }
    
    /**
     * Downloads a file from the server
     */
    void downloadFile()
    {
    	//TODO
    }
    
    /**
     * Sends an email object to the server which will be sent to all recipients in the email list
     * @param email
     */
    void sendEmail(Email email)
    {
    	try
    	{
    		sendObject(SEND_EMAIL);
    		sendObject(email);
    	}
    	catch(IOException e)
    	{
    		System.err.println("Error sending the email");
    	}
    }
    
    

    /**
     * Helper function that sends objects to the server and flushes the output stream
     * @param obj
     * @throws IOException
     */
    private void sendObject(Object obj) throws IOException
    {
        socketOut.writeObject(obj);
        socketOut.flush();
    }
    
 }

