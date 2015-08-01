package db;

import java.sql.SQLException;

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
	

}
