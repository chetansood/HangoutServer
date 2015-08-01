package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

ServerSocket serverSocket = null;
	
	//Create Socket
	public void createServerSocket(){
        try {
            serverSocket = new ServerSocket(8001);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
	}
	
	//Process client request
	public void processClientRequest(){
        try {
            Socket clientSocket = serverSocket.accept();
            DefaultSocketServer dSocket = new DefaultSocketServer(clientSocket);
            dSocket.start();
        } catch (IOException e) {
            System.err.println("Accept Failed");
            System.exit(1);
        }
	}	
}
