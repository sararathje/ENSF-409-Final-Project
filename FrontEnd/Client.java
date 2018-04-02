package FrontEnd;

import Constants.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

/**
 * Retrieves information from server to feed to front-end of learning platform application.
 */

public class Client implements ConnectionConstants {
    /**
     * Socket for establishing connection between client and server.
     *
     * @author Jack Glass, Rylan Kettles, Sara Rathje
     * @version 1.0
     * @since April 1, 2018
     */
    private Socket socket;

    /**
     * Object to write to the socket
     */
    private PrintWriter socketOut;

    /**
     * Object to read from the socket
     */
    private BufferedReader socketIn;

    /**
     * Constructs a Client object with specified values for serverName and portNumber.
     * The values of the data fields are supplied by the given parameters.
     */
    public Client() {
        try {
            // Establish socket connection
            socket = new Socket(HOSTNAME, PORT);
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketOut = new PrintWriter((socket.getOutputStream()), true);
        } catch (IOException e) {
            System.out.println("Error establishing socket connection on " + HOSTNAME + ": " + PORT);
            e.printStackTrace();
        }
    }
}
