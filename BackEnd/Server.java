package BackEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
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
     * Thread pool for client connections
     */
    private ExecutorService pool;

    /**
     * Constructs a Server object that creates a ServerSocket.
     */
    public Server() {
        try {
            // Establish socket connection
        	InetAddress address = InetAddress.getByName(ADDRESS);
            serverSocket = new ServerSocket(PORT, 50, address);
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
                ServerWorker session = new ServerWorker(socket);
                pool.execute(session);
                System.out.println("Connection established...");
            } catch(IOException e) {
                System.out.println("Error running server...");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.runServer();
    }
}
