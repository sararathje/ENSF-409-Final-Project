package FrontEnd;

import java.io.*;
import java.net.Socket;
import Models.*;
import Constants.*;

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
     * Message stream that sends strings to the client
     */
    BufferedReader stringIn;

    /**
     * Message stream that receives strings from the client
     */
    PrintWriter stringOut;

    /**
     * Indicates whether user session is in progress
     */
    boolean sessionInProgress;

    /**
     * User to log in
     */
    User authenticatedUser;

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
            stringIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stringOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("Error establishing socket connection on " + HOSTNAME + ": " + PORT);
            e.printStackTrace();
        }
    }

    /**
     * Runs the client.
     */
    public void runClient() {
        String serverOutput;

        try {
            while(true) {
                while(true) {
                    serverOutput = stringIn.readLine();
                    processServerOutput(serverOutput);
                }
            }
        } catch(IOException e) {
            System.out.println("Error reading from socket");
            e.printStackTrace();
        } finally {
            try {
                // Close input and output streams
                socketIn.close();
                socketOut.close();
                stringIn.close();
                stringOut.close();
            } catch (IOException e) {
                System.out.println("Error closing streams");
                e.printStackTrace();
            }
        }
    }

    /**
     * Processes the server otuput.
     * @param serverOutput server output string
     */
    private void processServerOutput(String serverOutput) {
        // If authentication has been performed
        // if (!serverOutput.equals(null) && serverOutput.equals(AUTHENTICATE)) {
            // try {
                // authenticatedUser = (User)socketIn.readObject();
            // } catch(IOException e) {
                // e.printStackTrace();
           //  } catch(ClassNotFoundException e) {
                // e.printStackTrace();
            // }
        // }
    }

    /**
     * Sends authentication information to the server.
     * @param login login information to be sent (username and password)
     */
    void sendAuthenticationInformation(Login login) {
        try {
            stringOut.println(AUTHENTICATE);
            socketOut.writeObject(login);
            socketOut.flush();
        } catch(IOException e) {
            System.out.println("Error sending login information to server...");
            e.printStackTrace();
        }
    }

    /**
     * Gets the authenticated user.
     * @return authenticated user
     */
    User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
