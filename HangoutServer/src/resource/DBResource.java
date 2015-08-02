package resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBResource {
	//Static variables
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String user = "casteel";
	public static final String password = "tiger";
	public static final String oracleDriverClassName = "oracle.jdbc.driver.OracleDriver";
	public static final String oracleConnectionString = "dbc:oracle:thin:@localhost/XE?user=casteel&password=tiger";
	public static final String displayAllEvents = "select * from Event;";
	public static final String displayAllAppUsers = "select * from AppUser;";
	public static final String displayAllAppComment = "select * from AppComment;";
	public static final String displayAllEventCommentMap = "select * from EventCommentMap ;";
	
	public static String insertIntoEventQuery(int eventID, String eventName, int organizer, double latitude, double longitude,
			String time, String description){
		String query = "insert into Event (EventID, Name,latlocation,longlocation,time,userid,description)"+
			"values("+ eventID + ",'" + eventName + "'," + latitude + "," + longitude + "," + "'" + time + "',"
			+ organizer + "," +  "'" + description + "')";
		return query;
	}
	
	public static String checkUserQuery(int userID){
		String query = "select userid from appuser where userid = "+userID;
		return query;
	}

	public static String checkEventQuery(int eventID) {
		String query = "select eventid from appuser where userid = "+eventID;
		return query;
	}

	/*public static String addParticipantQuery(int eventID) {
		String query = 
	}*/

	public static String addUserEventMapQuery(int eventID, int userID) {
		String query = "insert into EventUserMap(Eventid,userid) values (" + eventID + "," + userID + ")";
		return query;
	}

	public static String insertIntoAppUserQuery(int userID, String userName, double latitude, double longitude) {
		String query = "insert into AppUser (userID, Name,latlocation,longlocation)"+
				"values("+ userID + ",'" + userName + "'," + latitude + "," + longitude +")";
			return query;
	}

	public static String insertIntoAppCommentQuery(int commentID, int eventID, int userID, String commentText) {
		String query = "insert into AppComment (commentID,userID,eventID,text) values (" + commentID + "," +
				eventID + "," + userID + ",'" + commentText + "')";
		return query;
	}

	public static String getCommentQuery(int eventID) {
		String query = "select commentID, userID, text from AppComment where eventID=" + eventID;
		return query;
	}

	public static String getAllEventsQuery() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String date = dateFormat.format(new Date());
		String query = "select eventID, name, latlocation,longlocation,time,userID,description from Event where time >=" + "'" + date + "'" ;
		return query;
	}

	public static String getUserEventsQuery(int userID) {
		//DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		//String date = dateFormat.format(new Date());
		String query = "select eventID, name, latlocation,longlocation,time,userID,description "
				+ "from Event where eventID IN (select eventID from EventUserMap where userID ="  + userID ;
		return query;
	}

	public static String getUserQuery(int userID) {
		String query = "select userid,name,latlocation,longlocation,userloginid,userloginpwd from appuser where userid = "+userID;
		return query;
	}
}
