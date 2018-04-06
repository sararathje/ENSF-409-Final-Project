package BackEnd;

import Constants.ConnectionConstants;
import java.net.Socket;
import java.io.*;

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
	
	public ServerWorker(Socket socket)
	{
            dbHelper = new DatabaseHelper();
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
        try{
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

				else if(input instanceof Course){
					System.out.println("trying to make new course");
					dbHelper.addCourse((Course)input);
				}
				else if(input instanceof String)
				{
					if(input.equals(GET_COURSE_INFO)){
						objOut.writeObject("Sending Course List");
						objOut.flush();
						objOut.writeObject(dbHelper.getCourseList());
						objOut.flush();
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
					else if (input.equals(UNENROLL_STUDENT))
					{
						Object userTemp = objIn.readObject();
						input = objIn.readObject();
						dbHelper.unenrollStudent(((User)userTemp).getID(), ((Course)input).getCourseNumber());
					}
					else if(input.equals(ENROLL_STUDENT))
					{
						Object userTemp = objIn.readObject();
						input = objIn.readObject();
						dbHelper.enrollStudent(((User)userTemp).getID(), ((Course)input).getCourseNumber());
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
}
