package driver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DBUtil;
import server.DefaultSocketServer;
import server.Server;

public class Driver {
	public static void main (String[] args){
		System.out.println("Starting server");
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String date = dateFormat.format(new Date());
		System.out.println(date);
		Server s = new Server();
		s.createServerSocket();
		s.processClientRequest();
		//new DBUtil();
	}
}
