package server;

import java.util.ArrayList;

import model.Event;
import model.User;

public interface HangoutServer {

	public boolean joinEvent(int eventID, int userID);
	public boolean createUser (User user);
	public boolean setEvent (Event event);
	public ArrayList<Event> getEvent(double latitude, double longitude);
	public ArrayList<Event> getComments (int eventID);
}
