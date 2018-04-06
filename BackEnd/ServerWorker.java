package BackEnd;

import static Constants.ConnectionConstants.AUTHENTICATE;
import java.net.Socket;
import java.io.*;

import Models.*;


public class ServerWorker implements Runnable
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
        
	@Override
	public void run()
	{
            try {
                Object input = objIn.readObject();
                if (input instanceof Login) {
                    User user = dbHelper.authenticate((Login)input);
                    sendAuthenticatedUser(user);
                }
            } catch(IOException e) {
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            }
     
	
	}
}
