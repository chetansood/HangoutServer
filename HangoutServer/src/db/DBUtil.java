package db;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.supersaiyans.hangout.model.Comment;
import com.example.supersaiyans.hangout.model.Event;
import com.example.supersaiyans.hangout.model.User;

import resource.DBResource;

public class DBUtil {
	
	private JDBCAdapter adapter = new JDBCAdapter(DBResource.url,DBResource.oracleDriverClassName, DBResource.user, DBResource.password);

	public void createEvent(int eventID, String eventName, int organizer, double latitude, double longitude,
			String time, String description) {
		String query = DBResource.insertIntoEventQuery(eventID, eventName, organizer, latitude, longitude, time, description);
		System.out.println(query);
		adapter.executeUpdate(query);
		/*try {
			adapter.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Added");*/
	}

	public void joinEvent(int eventID, int userID) {
		String checkUserQuery = DBResource.checkUserQuery(userID);
		String checkEventQuery = DBResource.checkEventQuery(eventID);
		boolean eventExists=false;
		boolean userExists=false;
		
		adapter.rows=null;
		adapter.executeQuery(checkUserQuery);
		if(adapter.rows!=null && adapter.getRowCount()>0){
			userExists=true;
		}
		adapter.rows=null;
		adapter.executeQuery(checkEventQuery);
		if(adapter.rows!=null && adapter.getRowCount()>0){
			eventExists=true;
		}
		
		if(eventExists&&userExists){
			//String addParticipantQuery = DBResource.addParticipantQuery(eventID);
			String addUserEventMapQuery = DBResource.addUserEventMapQuery(eventID,userID);
			adapter.executeUpdate(addUserEventMapQuery);
		}
	}
	
	public void createUser (int userID, String userName, double latitude, double longitude){
		String createUserQuery = DBResource.insertIntoAppUserQuery(userID, userName, latitude, longitude);
		adapter.executeUpdate(createUserQuery);
	}

	public void addComment(int commentID, int eventID, int userID, String commentText) {
		String checkUserQuery = DBResource.checkUserQuery(userID);
		String checkEventQuery = DBResource.checkEventQuery(eventID);
		boolean eventExists=false;
		boolean userExists=false;
		
		adapter.rows=null;
		adapter.executeQuery(checkUserQuery);
		if(adapter.rows!=null && adapter.getRowCount()>0){
			userExists=true;
		}
		adapter.rows=null;
		adapter.executeQuery(checkEventQuery);
		if(adapter.rows!=null && adapter.getRowCount()>0){
			eventExists=true;
		}
		
		if(eventExists&&userExists){
			String addCommentQuery = DBResource.insertIntoAppCommentQuery(commentID,eventID,userID,commentText);
			adapter.executeUpdate(addCommentQuery);
		}
	}

	public ArrayList<Comment> getAllComments(int eventID) {
		String checkEventQuery = DBResource.checkEventQuery(eventID);
		boolean eventExists=false;
		adapter.rows=null;
		adapter.executeQuery(checkEventQuery);
		if(adapter.rows!=null && adapter.getRowCount()>0){
			eventExists=true;
		}
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		if(eventExists)
		{
			String getCommentQuery = DBResource.getCommentQuery(eventID);
			adapter.rows=null;
			adapter.columnNames=null;
			adapter.executeQuery(getCommentQuery);
			if(adapter.rows!=null){
				int commentID=0;
				String text=null;
				int userID=0;
				int rowCount = adapter.getRowCount();
				//int colCount = adapter.getColumnCount();
				for (int i=0; i<adapter.getRowCount();i++){
					commentID=(int)adapter.getValueAt(i, 0);
					userID = (int)adapter.getValueAt(i, 1);
					text= (String)adapter.getValueAt(i, 2);
					if(commentID!=0 && userID!=0 && text!=null){
						commentList.add(new Comment(commentID, userID, eventID, text));
					}
					commentID=0;
					userID=0;
					text=null;
				}
				
			}
		}
		return commentList;
	}
	
	public ArrayList<Event> getAllEvents(){
		ArrayList<Event> eventList = new ArrayList<Event>();
		String query = DBResource.getAllEventsQuery();
		adapter.rows=null;
		adapter.columnNames=null;
		adapter.executeQuery(query);
		if(adapter.rows!=null){
			int eventID=0;
			String name=null;
			double latLocation = -1.0d;
			double longLocation = -1.0d;
			String time = null;
			int userID = 0;
			String description=null;
			int rowCount = adapter.getRowCount();
			//int colCount = adapter.getColumnCount();
			for (int i=0; i<adapter.getRowCount();i++){
				eventID=((BigDecimal)adapter.getValueAt(i, 0)).intValue();
				name= (String) adapter.getValueAt(i, 1);
				latLocation = ((BigDecimal) adapter.getValueAt(i, 2)).doubleValue();
				longLocation = ((BigDecimal) adapter.getValueAt(i, 3)).doubleValue();
				time= ((java.sql.Date)adapter.getValueAt(i, 4)).toString();
				userID = ((BigDecimal)adapter.getValueAt(i, 5)).intValue();
				description= (String)adapter.getValueAt(i, 6);
				if(eventID!=0 && userID!=0 && name!=null && latLocation!=-1.0 && longLocation!=-1.0d && time!=null){
					Double[] location = new Double[2];
					location[0]=latLocation;
					location[1]=longLocation;
					Event e = new Event(name, eventID, location, userID, time);
					if(description!=null){
						e.setDescription(description);
					}
					System.out.println("adding here");
					eventList.add(e);
				}
				eventID=0;
				userID=0;
				latLocation= -1.0d;
				longLocation = -1.0d;
				time=null;
				description=null;
			}
		}
		return eventList;
		
	}

	public ArrayList<Event> getUserEvents(int userID) {
		
		ArrayList<Event> eventList = new ArrayList<Event>();
		String query = DBResource.getUserEventsQuery(userID);
		adapter.rows=null;
		adapter.columnNames=null;
		adapter.executeQuery(query);
		if(adapter.rows!=null){
			int eventID=0;
			String name=null;
			double latLocation = -1.0d;
			double longLocation = -1.0d;
			String time = null;
			int uID = 0;
			String description=null;
			int rowCount = adapter.getRowCount();
			//int colCount = adapter.getColumnCount();
			for (int i=0; i<adapter.getRowCount();i++){
				eventID=((BigDecimal)adapter.getValueAt(i, 0)).intValue();
				name= (String) adapter.getValueAt(i, 1);
				latLocation = ((BigDecimal) adapter.getValueAt(i, 2)).doubleValue();
				longLocation = ((BigDecimal) adapter.getValueAt(i, 3)).doubleValue();
				time= ((java.sql.Date)adapter.getValueAt(i, 4)).toString();
				uID = ((BigDecimal)adapter.getValueAt(i, 5)).intValue();
				description= (String)adapter.getValueAt(i, 6);
				if(eventID!=0 && uID!=0 && name!=null && latLocation!=-1.0 && longLocation!=-1.0d && time!=null){
					Double[] location = new Double[2];
					location[0]=latLocation;
					location[1]=longLocation;
					Event e = new Event(name, eventID, location, uID, time);
					if(description!=null){
						e.setDescription(description);
					}
					System.out.println("adding here");
					eventList.add(e);
				}
				eventID=0;
				uID=0;
				latLocation= -1.0d;
				longLocation = -1.0d;
				time=null;
				description=null;
			}
		}
		return eventList;
	}

	public User checkUser(int userID) {
		String checkUserQuery = DBResource.checkUserQuery(userID);
		boolean userExists=false;
		adapter.rows=null;
		adapter.executeQuery(checkUserQuery);
		if(adapter.rows!=null && adapter.getRowCount()>0){
			userExists=true;
		}
		if(userExists){
			String getUserQuery = DBResource.getUserQuery(userID);
			adapter.rows=null;
			adapter.columnNames=null;
			adapter.executeQuery(getUserQuery);
			int uID=((BigDecimal)adapter.getValueAt(0, 0)).intValue();
			String name= (String) adapter.getValueAt(0, 1);
			double latLocation = -1.0d;
			latLocation = 	((BigDecimal) adapter.getValueAt(0, 2)).doubleValue();
			double longLocation = -1.0d;
			longLocation=((BigDecimal) adapter.getValueAt(0, 3)).doubleValue();
			String fbLoginID= (String) adapter.getValueAt(0, 4);
			String fbPwd = (String) adapter.getValueAt(0, 5);
			if( uID!=0 && name!=null && latLocation!=-1.0d && longLocation!=-1.0d){
				Double[] location = new Double[2];
				location[0]=latLocation;
				location[1]=longLocation;
				User u = new User(uID, name, location);
				return u;
			}
			else{
				return null;
			}
			
		}
		else{
			return null;
		}
	}
}
