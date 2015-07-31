package driver;

import db.DBUtil;
import server.DefaultSocketServer;
import server.Server;

public class Driver {
	public static void main (String[] args){
		System.out.println("Starting server");
		Server s = new Server();
		s.createServerSocket();
		s.processClientRequest();
		//new DBUtil();
	}
}
