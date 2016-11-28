package mainClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
    	Scanner keyboard = new Scanner(System.in);
    	
    	while(true){
    		System.out.println("Soccer Database");
    		System.out.println("--------------------------");
    		System.out.println("1. Reset Database");
    		
    		System.out.println("2. Insert Match");
    		System.out.println("3. Delete Match");
    		System.out.println("4. Search for a Match");
    		System.out.println("5. Exit the Program");
    		System.out.println("Enter your selection: ");
    		int selection = keyboard.nextInt();
    		
    		
    		
    		
    		switch (selection){
    			case 1: {
    						CreateDatabase.main(args);
    						break;
    					}
    			
    			case 2: {
    						InsertDelete.insert();
    						break;
    					}
    			
    			case 3: {
    						InsertDelete.delete();
    						break;
    					}
    			
    			case 4: {
	    				System.out.println("What Team? ");
	    				keyboard.nextLine();
	    				String Team = keyboard.nextLine();
	    				Team = Team.toUpperCase();
	    				System.out.println("What year? ");
	    				int year = keyboard.nextInt();
	    				
	    				
	    				
	    				Connection conn = DriverManager.getConnection(DB_URL);
	    				
	    			
	    				String sqlStmt = "SELECT * FROM SoccerMatches "
	    						+ "WHERE HomeTeam = '" + Team + "' "
	    								+ "OR AwayTeam = '" + Team + "'";
	    							
	    				Statement stmt = conn.createStatement();
	    				
	    		    	
	    		    	ResultSet result = stmt.executeQuery(sqlStmt);
	    		    	
	    		    	while(result.next()){
	    		    		System.out.println(result.getString("SoccerLeague") + "\n The Date was: " + result.getInt("Day")+ "/" + result.getInt("Month") + "/" + result.getInt("Year") + "\n The Home Team: " +result.getString("HomeTeam") +"\n The Away Team: " + result.getString("AwayTeam") + "\n The Score was: "
	    		    				+ result.getInt("HomeScore") + " - " + result.getInt("AwayScore"));
	    		    	}
	    		    	
	    		    	conn.close();
	    		    	break;
	    			}
    			case 5:{System.exit(0);}
    			default: {
    				System.out.println("That was not a valid choice. Please Try Again");
    				break;
    				
    			}
    		}	
    	}
    	
    	
    	
    	
    	
    	
    	
    	
    	
		
    }
}


