package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.example.supersaiyans.hangout.model.Comment;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import adapter.EventAdapter;

public class DefaultSocketServer extends Thread implements SocketServerInterface, SocketServerConstant {
	private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket socket;
    private String strHost;
    private int iPort;
    
    public DefaultSocketServer(String strHost, int iPort) {       
    	setiPort (iPort);
    	setStrHost (strHost);
    }
    
    public DefaultSocketServer(Socket socket) {       
    	this.socket=socket;
    }

    public String getStrHost() {
		return strHost;
	}

	public void setStrHost(String strHost) {
		this.strHost = strHost;
	}

	public int getiPort() {
		return iPort;
	}

	public void setiPort(int iPort) {
		this.iPort = iPort;
	}
	

	
	
	@Override
	public boolean openConnection(){System.out.println("in oc");
		   try {
			   this.writer = new ObjectOutputStream(this.socket.getOutputStream());
			   this.writer.flush();
			   this.reader = new ObjectInputStream(this.socket.getInputStream());
		   }
		  catch (Exception e){System.out.println(e);
		     return false;
		  }
		  return true;
	}


	@Override
	public void handleSession() {
		
		String inputLine, outputLine;
		outputLine = "Connection Established. Enter choice";
		boolean keepRunning = true;
		boolean receiveObject=false;
		boolean receiveEvent = false;
		boolean getJoinEventData=false;
		boolean receiveComment = false;
		boolean receiveUser = false;
		boolean getEventIDForComment=false;
		boolean getUserIDForEvent=false;
		boolean receiveUserIDToFetchUser= false;
		try {
			writer.writeUTF(outputLine);
			this.writer.flush();
			while(keepRunning){
				if(receiveObject){
					if(receiveEvent){
						Event e = (Event)this.reader.readObject();
						EventAdapter ea = new EventAdapter();
						ea.createEvent(e);
						//writer.writeUTF("Event Added in DB");
						//writer.flush();
						receiveEvent=false;
						keepRunning=false;
					}
					
					else if(receiveComment){
						Comment c = (Comment)this.reader.readObject();
						EventAdapter ea = new EventAdapter();
						ea.addComment(c);
						//writer.writeUTF("Event Added in DB");
						//writer.flush();
						receiveComment=false;
						keepRunning=false;
					}
					
					else if(receiveUser){
						User u = (User)this.reader.readObject();
						EventAdapter ea = new EventAdapter();
						ea.createUser(u);
						//writer.writeUTF("Event Added in DB");
						//writer.flush();
						receiveUser=false;
						keepRunning=false;
					}
					
					
					
					receiveObject=false;
				}
				else if (getJoinEventData){
					inputLine = this.reader.readUTF();
					System.out.println(inputLine);
					String[] splitData = inputLine.split(",");
					int eventID = Integer.parseInt(splitData[0]);
					int userID = Integer.parseInt(splitData[0]);
					System.out.println(eventID + " " + userID);
					EventAdapter ea = new EventAdapter();
					ea.joinEvent(eventID, userID);
					keepRunning=false;
				}
				
				else if (getEventIDForComment){
					inputLine = this.reader.readUTF();
					int eventID = Integer.parseInt(inputLine);
					System.out.println(inputLine);
					EventAdapter ea = new EventAdapter();
					ArrayList<Comment> commentList = ea.getComments(eventID);
					if(commentList!=null){
						writer.writeObject(commentList);
						writer.flush();
					}
					keepRunning=false;
				}
				
				else if (getUserIDForEvent){
					inputLine = this.reader.readUTF();
					int userID = Integer.parseInt(inputLine);
					System.out.println(inputLine);
					EventAdapter ea = new EventAdapter();
					ArrayList<Event> eventList = ea.getUserEvents(userID);
					if(eventList!=null){
						writer.writeObject(eventList);
						writer.flush();
					}
					keepRunning=false;
				}
				
				else if (receiveUserIDToFetchUser){
					inputLine = this.reader.readUTF();
					int userID = Integer.parseInt(inputLine);
					System.out.println(inputLine);
					EventAdapter ea = new EventAdapter();
					User u = ea.checkUser(userID);
					if(u!=null){
						writer.writeObject(u);
						writer.flush();
					}
					keepRunning=false;
				}
				
				else{
					inputLine = this.reader.readUTF();
					System.out.println(inputLine);
					if(inputLine.equalsIgnoreCase("1"))
					{
						writer.writeUTF("Send Event Object");
						writer.flush();
						receiveObject=true;
						receiveEvent=true;
					}
					else if(inputLine.equalsIgnoreCase("2"))
					{
						writer.writeUTF("Send join event data");
						writer.flush();
						getJoinEventData =true;
					}
					else if(inputLine.equalsIgnoreCase("3"))
					{
						writer.writeUTF("Send comment object");
						writer.flush();
						receiveObject=true;
						receiveComment=true;
					}
					else if(inputLine.equalsIgnoreCase("4"))
					{
						writer.writeUTF("Send user object");
						writer.flush();
						receiveObject=true;
						receiveUser=true;
					}
					else if(inputLine.equalsIgnoreCase("5"))
					{
						//
						EventAdapter ea = new EventAdapter();
						ArrayList<Event> listEvents = ea.getAllEvents();
						if(listEvents!=null){
							System.out.println(listEvents.iterator().next().getID());
							writer.writeObject(listEvents);
							writer.flush();
							keepRunning=false;
						}
						else{
							System.out.println("nulll");
						}
					}
					
					else if(inputLine.equalsIgnoreCase("6"))
					{
						writer.writeUTF("Send event id");
						writer.flush();
						getEventIDForComment=true;
					}
					

					else if(inputLine.equalsIgnoreCase("7"))
					{
						writer.writeUTF("Send user id");
						writer.flush();
						getUserIDForEvent=true;
					}
					
					else if(inputLine.equalsIgnoreCase("8"))
					{
						writer.writeUTF("Send user id");
						writer.flush();
						receiveUserIDToFetchUser=true;
					}
				}
			}
			
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void closeSession() {
		try {
	        this.writer = null;
	        this.reader = null;
	        this.socket.close();
	        }
	        catch (IOException e){
	        	e.printStackTrace();
	        }       
	 }
	
	public void run(){
		if (openConnection()){System.out.println("got connection from app");
			handleSession();
	        closeSession();
		}
	}
}
