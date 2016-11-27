package mainClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args ) throws SQLException
    {
    	final String DB_URL = "jdbc:hsqldb:file:SoccerDB/Soccer";
    	
    	//Fields you'd like to extract from the data (Select columnNames)
    	//Table(s) that you'd like to extract the data from (From tableName)
    	//Optional Filter (where CONDITIONS)
    	//Optional Ordering (order by columnNames desc)
    	
    	String sqlStatement = "SELECT * FROM SoccerMatches ";
    	
    	Connection conn = DriverManager.getConnection(DB_URL);
    	
    	Statement stmt = conn.createStatement();
    	
    	ResultSet result = stmt.executeQuery(sqlStatement);
    	
    	while(result.next()){
    		System.out.println(result.getString("SoccerLeague") + " The Home Team:" +result.getString("HomeTeam") +" The Away Team:" + result.getString("AwayTeam") + " The Score was:"
    				+ result.getInt("HomeScore") + " - " + result.getInt("AwayScore"));
    	}
    	
    	conn.close();
    }
}


