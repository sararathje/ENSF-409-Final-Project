package BackEnd;

import Constants.ConnectionConstants;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

import Models.*;


public class ServerWorker implements Runnable, ConnectionConstants
{
	/**
	 * Socket that communicates with the client
	 */
	Socket aSocket;
	
	/**
	 * Object that sends objects to the client
	 */
	ObjectInputStream objIn;
	
	/**
	 * Object that receives objects from client
	 */
	ObjectOutputStream objOut;
        
    private DatabaseHelper dbHelper;
    
    private FileHelper fHelper;
    
    private EmailHelper eHelper;
	
	public ServerWorker(Socket socket)
	{
            dbHelper = new DatabaseHelper();
            fHelper = new FileHelper();
            eHelper = new EmailHelper();
            try {
               this.aSocket = socket;
                objOut = new ObjectOutputStream(aSocket.getOutputStream()); 
                objIn = new ObjectInputStream(aSocket.getInputStream());
            } catch (IOException e) {
                System.out.println("Error with socket streams");
                e.printStackTrace();
            } 
	}

	/**
	 * Sends an authenticated user back to the client.
	 * @param user authenticated user
	 */
    private void sendAuthenticatedUser(User user){
        try {
            objOut.writeObject(AUTHENTICATE);
            
            objOut.writeObject(user);
            objOut.flush();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

	/**
	 * Implements the run method in Runnable interface.
	 */
	@Override
	public void run()
	{
		while(true){
			try {
				Object input = objIn.readObject();
				if (input instanceof Login) {
					User user = dbHelper.authenticate((Login)input);
					sendAuthenticatedUser(user);
				}
				else if(input instanceof String)
				{
					if(input.equals(GET_COURSE_INFO)) {
					    User user = (User)objIn.readObject();

						sendObject(SEND_COURSE_LIST);
						// Pass in courses
						sendObject(dbHelper.getCourseList(user));
					}
					else if (input.equals(NEW_COURSE))
					{
						dbHelper.addCourse((Course)objIn.readObject());
					}
					else if (input.equals(SET_COURSE_ACTIVE))
					{
						//TODO update the database on the course's activeness
					}
					else if (input.equals(SET_COURSE_INACTIVE))
					{
						//TODO update the database on the course's activeness
					}
                    else if(input.equals(GET_ASSIGNMENT_INFO))
                    {
                        int courseID = getCourseIDFromName();
                        char userType = (char) objIn.readObject();
                        ArrayList<Assignment> assignments = dbHelper.getAssignmentList(courseID, userType);

                        sendObject(assignments);
                    }
					else if(input.equals(SET_ASSIGNMENT_ACTIVE))
					{
						input = objIn.readObject();
						dbHelper.setAssignmentActive(((Assignment) input).getID());
					}
					else if(input.equals(SET_ASSIGNMENT_INACTIVE))
					{
						input = objIn.readObject();
						dbHelper.setAssignmentInactive(((Assignment) input).getID());
					}
					else if(input.equals(NEW_ASSIGNMENT))
					{
						input = objIn.readObject();
                                                dbHelper.addAssignment((Assignment)input);
					}
					else if (input.equals(ASSIGNMENT_LIST_PROF))
					{
						//TODO gets the assignment list for a specific course and send it to the client
					}
					else if (input.equals(ASSIGNMENT_LIST_STUDENT))
					{
						//TODO gets the assignment list for a specific course and send it to the client
					}
					else if (input.equals(UNENROLL_STUDENT))
					{
						Object userTemp = objIn.readObject();
						int courseID = getCourseIDFromName();

						dbHelper.unenrollStudent(((User)userTemp).getID(), courseID);
					}
					else if(input.equals(ENROLL_STUDENT))
					{
						Object userTemp = objIn.readObject();
						int courseID = getCourseIDFromName();

						dbHelper.enrollStudent(((User)userTemp).getID(), courseID);

						// Next line gets list of all students enrolled in the course with ID courseID
						// ArrayList<Integer> studID = dbHelper.getEnrolledStudents(courseID);
					}
					else if(input.equals(GET_ENROLLED_STUDENTS)) 
					{
						 sendObject(dbHelper.getEnrolledStudents(getCourseIDFromName()));
					}
					else if(input.equals(SEARCH_FOR_STUDENT)) 
					{
						String lastName = (String)objIn.readObject();
						String id = (String)objIn.readObject();

						ArrayList<User> matchedStudents = dbHelper.searchForStudent(lastName, id);
						sendObject(SEND_STUDENT_RESULT);
						sendObject(matchedStudents);
					}
					else if (input.equals(SUBMIT_ASSIGNMENT))
					{
						Submission s = (Submission) objIn.readObject();
						//TODO
					}
					else if (input.equals(DOWNLOAD_SUBMISSION))
					{
						//TODO
					}
					else if (input.equals(GRADE_SUBMISSION))
					{
						//TODO
					}
					else if (input.equals(SEND_EMAIL))
					{
						Email email = (Email)objIn.readObject();
						eHelper.sendEmail(email);
					}
					else if (input.equals(UPLOAD_FILE))
					{
						String name = (String) objIn.readObject();
						byte[] content = (byte[])objIn.readObject();
						String extension = (String) objIn.readObject();
						fHelper.saveFile(name, content, extension);
					}
					else if (input.equals(DOWNLOAD_FILE))
					{
						String name = (String) objIn.readObject();
						String extension = (String) objIn.readObject();
						byte[] content = fHelper.getFile(name, extension);
						sendObject(content);
					}
                    else if(input.equals(GET_GRADE)){
                        int assignmentID = (int) objIn.readObject();
                        int studentID = (int) objIn.readObject();
                        int courseID = (int) objIn.readObject();
                        int grade = dbHelper.getGrade(assignmentID, studentID, courseID);
                        sendObject(grade);
                    } else if(input.equals(SEARCH_FOR_PROF)) {
					    User professor = dbHelper.searchForProfessor((int)objIn.readObject());
					    sendObject(professor);
                    }
					else if(input.equals(QUIT))
					{
						break;
					}
				}
				
			} catch(IOException e) {
				e.printStackTrace();
				break;
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
		}
		try
		{
		objIn.close();
		objOut.close();
		aSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("error closing streams");
		}
	}

	/**
	 * Writes object to the object output stream
	 * @param obj object to write to the stream
	 * @throws IOException when writing the object to the stream
	 */
	private void sendObject(Object obj) throws IOException
	{
		objOut.writeObject(obj);
		objOut.flush();
	}

	/**
	 * Gets the Course ID from the name, which is made up of course name + " " + courseID
	 * @return course ID
	 * @throws IOException when reading from object input stream
	 * @throws ClassNotFoundException when reading from object input stream
	 */
	private int getCourseIDFromName() throws IOException, ClassNotFoundException {
		String courseName = (String)objIn.readObject();
		String[] splitString = courseName.split(" ");

		return Integer.parseInt(splitString[1]);
	}
}


