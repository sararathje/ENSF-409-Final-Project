package FrontEnd;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import Models.*;
import Constants.*;
import java.util.ArrayList;


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
        	
        	//HOSTNAME for local connection
        	//HOSTNAMEREMOTE for remote connection
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
     * Gets the Courses from the Database.
     */
    void getCourseInfo() {
        try {
            // Get courses that have the professor ID
            // NOTE: This only works for professors right now
            sendObject(GET_COURSE_INFO);
            sendObject(authenticatedUser);

            Object input = socketIn.readObject();

            // This gets a list of all courses instead of just courses the user has
            if(input instanceof String && input.equals(SEND_COURSE_LIST)) {
                ArrayList<Course> list = (ArrayList<Course>)socketIn.readObject();
                authenticatedUser.setCourses(list);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Gets list of assignments (assignment info) for a course based on userType
     * @param courseName course name
     * @param userType user type
     */
    void getAssignmentInfo(String courseName, char userType) {
        try {
            sendObject(GET_ASSIGNMENT_INFO);
            sendObject(courseName);
            sendObject(userType);

            ArrayList<Assignment> list = (ArrayList<Assignment>)socketIn.readObject();
            ArrayList<Course> courses = this.authenticatedUser.getCourses();

            for(int i = 0; i < courses.size(); i++){
                String info = courses.get(i).getCourseName() +" " + courses.get(i).getCourseNumber();
                if(courseName.equals(info)) {
                    this.authenticatedUser.getCourses().get(i).setAssignments(list);
                }
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
		Object input;
    	try
    	{
	    	if(authenticatedUser.getUserType() == 'P')
	    	{
	    		sendObject(COURSE_LIST_PROF);
	    		input = socketIn.readObject();
	    		
	    		if(input instanceof ArrayList<?>)
		    	{
		    		courses = (ArrayList<Course>)input;
		    	}
	    		
	    	}
	    	else if(authenticatedUser.getUserType() == 'S')
	    	{
	    		sendObject(COURSE_LIST_STUDENT);
	    		input = socketIn.readObject();
	    		
	    		if(input instanceof ArrayList<?>)
		    	{
		    		courses = (ArrayList<Course>)input;
		    	}
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

            // Add course to professor course list
            authenticatedUser.addCourse(course);
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
     * @return student search result
     */
	 ArrayList<User> searchForStudent(String lastName, String id, String courseName) {
	     ArrayList<User> matchedStudents = new ArrayList();
        try {
            sendObject(SEARCH_FOR_STUDENT);
            sendObject(lastName);
            sendObject(id);

            Object input = socketIn.readObject();

            if (input instanceof String && input.equals(SEND_STUDENT_RESULT)) {
                // Read in matching student object and then show the Student GUI?
            	input = socketIn.readObject();
            	matchedStudents = (ArrayList<User>)input;

            	if(input instanceof ArrayList<?>)
            	{
            		matchedStudents = (ArrayList<User>)input;
            	}
            }
        } catch(IOException e) {
            System.out.println("Error sending student search to server.");
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        return matchedStudents;
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
     * Gets list of students enrolled in course
     * @param courseName name of course to get list of enrolled students in
     * @return list of enrolled students
     */
    ArrayList<User> getEnrolledStudents(String courseName) {
        ArrayList<User> enrolledStudents = new ArrayList<>();

        try {
            sendObject(GET_ENROLLED_STUDENTS);
            sendObject(courseName);

            // Get list of enrolled students
            enrolledStudents = (ArrayList<User>)socketIn.readObject();
        } catch(IOException e) {
            System.out.println("Error sending server request to get enrolled students");
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        return enrolledStudents;
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
    @SuppressWarnings("unchecked")
	void getAssignmentList(String course)
    {
    	ArrayList<Assignment> assignments = new ArrayList<Assignment>();
    	try
    	{
	    	if(authenticatedUser.getUserType() == 'P')
	    	{
	    		sendObject(ASSIGNMENT_LIST_PROF);
	    		sendObject(course);
	    		Object input = socketIn.readObject();
	    		if(input instanceof ArrayList<?>)
	    		{
	    			assignments = (ArrayList<Assignment>)input;
	    		}
	    		
	    	}
	    	else if(authenticatedUser.getUserType() == 'S')
	    	{
	    		sendObject(ASSIGNMENT_LIST_STUDENT);
	    		sendObject(course);
	    		Object input = socketIn.readObject();
	    		if(input instanceof ArrayList<?>)
	    		{
	    			assignments = (ArrayList<Assignment>)input;
	    		}
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
		
    }
    
     int getGrade(int assignmentID, int studentID){
         try
    	{
            int grade;
            sendObject(GET_GRADE);
            sendObject(assignmentID);
            sendObject(studentID);
            Object input = socketIn.readObject();

            if(input instanceof Integer){
                grade = (int)input;
            }
            else{
                grade = -1;
            }

            return grade;
    	}
    	catch(IOException e)
    	{
    		System.err.println("Error Recieving Grades");
                e.printStackTrace();
    	}
         catch(ClassNotFoundException f)
    	{
    		System.err.println("Error Recieving Grades");
                f.printStackTrace();
    	}
         return -2;
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
    void uploadFile(String path, String name, String ext)
    {
    	if(ext.equals(TXT) || ext.equals(PDF))
    	{
    		File selectedFile = new File(path);
        	long length = selectedFile.length();
        	byte[] content = new byte[(int) length]; // Converting Long to Int
        	try {
        	FileInputStream fis = new FileInputStream(selectedFile);
        	BufferedInputStream bos = new BufferedInputStream(fis);
        	bos.read(content, 0, (int)length);
        	sendObject(UPLOAD_FILE);
        	sendObject(name);
        	sendObject(content);
        	sendObject(ext);
        	bos.close();
        	} 
        	catch (FileNotFoundException e) 
        	{
        	e.printStackTrace();
        	} 
        	catch(IOException e)
        	{
        	e.printStackTrace();
        	}
    	} else
    	{
    		System.err.println("Invalid extension");
    		return;
    	}
    	
    }
    
    /**
     * Downloads a file from the server
     */
    void viewFile(String name, String ext, JTextArea theArea)
    {
    	try
    	{
    	sendObject(DOWNLOAD_FILE);
    	sendObject(name);
    	sendObject(ext);
    	
    	byte[] content = (byte[])socketIn.readObject();
    	
    	File newFile = new File(CLIENTTEMPPATH + name + ext);
		if(!newFile.exists())
		newFile.createNewFile();
		FileOutputStream writer = new FileOutputStream(newFile);
		BufferedOutputStream bos = new BufferedOutputStream(writer);
		bos.write(content);
		bos.close();
		
		FileReader fis = new FileReader(newFile);
    	BufferedReader bis = new BufferedReader(fis);
    	
    	String line = bis.readLine();
    	while(line != null)
    	{
    		theArea.append(line);
    		line = bis.readLine();
    	}
    	bis.close();
    	newFile.delete();
    	}
    	catch(IOException e)
    	{
    		System.err.println("Error downloading file.");
    	}
    	catch(ClassNotFoundException f)
    	{
    		System.err.println("Class not found");
    	}
    }

    /**
     * Sends request to server to search for Professor with id provided by the given parameter.
     * @param id professor ID
     * @return Professor matching the professor ID
     */
    User searchProfessor(int id) {
        User user = null;

        try {
            sendObject(SEARCH_FOR_PROF);
            sendObject(id);

            user =  (User)socketIn.readObject();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
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

    void submitAssignment(Submission submission, String extension)
    {
    	uploadFile(submission.getPath(), String.valueOf(submission.getAssignmentID()) + "_" 
    				+ String.valueOf(submission.getStudentID()), extension);
    	submission.setPath(serverDirPath);
    	
    	try
    	{
    		sendObject(SUBMIT_ASSIGNMENT);
    		sendObject(submission);
    	}
    	catch(IOException r)
    	{
    		System.err.println("Error sending the submission...");
    	}
    }
    
    ArrayList<Submission> getSubmissionList(int assignmentID){
        try{
            sendObject(GET_SUBMISSIONS);
            sendObject(assignmentID);
            ArrayList<Submission> submissions = (ArrayList<Submission>)socketIn.readObject();
           return submissions;
        }
        catch(IOException ex){
            System.out.println("Error getting submission list");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error getting submission list");
        }
        
        return null;
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

