package resource;

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
}
