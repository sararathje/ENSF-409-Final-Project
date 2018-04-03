package BackEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Constants.*;

/**
 * Retrieves information from database communicator and sends information to front-end of learning platform application.
 *
 * @author Jack Glass, Rylan Kettles, Sara Rathje
 * @version 1.0
 * @since April 1, 2018
 */
public class Server implements ConnectionConstants {
    /**
     * Server-side socket connection
     */
    private ServerSocket serverSocket;
    /**
     * Socket for establishing connection between client and server
     */
    private Socket socket;
    /**
     * Object to write to the socket
     */
    private PrintWriter out;
    /**
     * Object to read from the socket
     */
    private BufferedReader in;

    /**
     * Thread pool for games
     */
    private ExecutorService pool;

    /**
     * Constructs a Server object that creates a ServerSocket.
     */
    public Server() {
        try {
            // Establish socket connection
            serverSocket = new ServerSocket(PORT);
            pool = Executors.newCachedThreadPool();
        } catch(IOException e) {
            System.out.println("Error in creating new socket");
            e.printStackTrace();
        }

        // Server running message
        System.out.println("Server is running...");
    }

    public void runServer() {
        while(true) {
            try {
                // NOTE: the in and out should probably be moved to what actually runs (ie., Worker)
                socket = serverSocket.accept();
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch(IOException e) {
                System.out.println("Error running server...");
                e.printStackTrace();
            }
        }
    }

    // SARA: Placeholder for now
    public static void main(String[] args) throws IOException {
        Server server = new Server();

        // TODO: Run server
        server.runServer();
    }
}
