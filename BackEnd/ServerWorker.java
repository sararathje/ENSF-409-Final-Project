package BackEnd;

import java.net.Socket;
import java.io.*;


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
	
	/**
	 * Message stream that sends strings to the client
	 */
	BufferedReader stringIn;
	
	/**
	 * Message stream that receives strings from the client
	 */
	PrintWriter stringOut;
	
	
	public ServerWorker(Socket aSocket)
	{
		this.aSocket = aSocket;
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
