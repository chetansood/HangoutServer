package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import resource.ServerResource;

public class Server {

ServerSocket serverSocket = null;
	
	//Create Socket
	public void createServerSocket(){
        try {
            serverSocket = new ServerSocket(ServerResource.port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
	}
	
	//Process client request
	public void processClientRequest(){
        try {
        	while(true){
        		 Socket clientSocket = serverSocket.accept();
                 DefaultSocketServer dSocket = new DefaultSocketServer(clientSocket);
                 dSocket.start();
        	}
        } catch (IOException e) {
            System.err.println("Accept Failed");
            try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            System.exit(1);
        }
	}	
}
