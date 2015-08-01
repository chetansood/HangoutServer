package adapter;

import java.util.ArrayList;

import com.example.supersaiyans.hangout.model.Event;

public interface CRUDOnEvent {

	public void createEvent(Event event);
	public void joinEvent(int eventID, int userID);
	public ArrayList<Event> getAllEvents(double latitude, double longitude);
}
