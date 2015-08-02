package adapter;

import java.util.ArrayList;

import com.example.supersaiyans.hangout.model.Comment;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import db.DBUtil;
import resource.DBResource;

public abstract class ProxyAppAdapter {
	DBUtil dbUtil = new DBUtil();
	
	public void createEvent(Event event) {
		int eventID = event.getID();
		String eventName = event.getName();
		Double[] location = event.getLocation();
		double latitude = location[0];
		double longitude = location[1];
		String time = event.getTime();
		String description = event.getDescription();
		if(description==null){
			description="";
		}
		int organizer = event.getOrganizer();
		//System.out.println(time+organizer+description);
		dbUtil.createEvent(eventID,eventName,organizer,latitude,longitude,time,description);
		
	}

	public void joinEvent(int eventID, int userID) {
		dbUtil.joinEvent(eventID,userID);
	}
	
	public void addComment (Comment comment){
		int commentID= comment.getCommentID();
		int eventID = comment.getEventID();
		int userID = comment.getUserID();
		String commentText = comment.getCommentText();
		dbUtil.addComment(commentID,eventID,userID,commentText);
	}

	public ArrayList<Event> getAllEvents() {
		return dbUtil.getAllEvents();
	}
	

	public void createUser(User user) {
		int userID = user.getID();
		String name = user.getName();
		Double[] location = user.getLocation();
		double latitude = location[0];
		double longitude = location[1];
		dbUtil.createUser(userID, name, latitude, longitude);
	}

	public void updateUser(User user) {
		
	}
	
	
	
	public ArrayList<Comment> getComments(int eventID) {
		return dbUtil.getAllComments(eventID);
	}

	public ArrayList<Event> getUserEvents(int userID) {
		return dbUtil.getUserEvents(userID);
	}

	public User checkUser(int userID) {
		return dbUtil.checkUser(userID);
	}
}
