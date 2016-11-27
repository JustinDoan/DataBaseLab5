package mainClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

	//Must contain at least:  League, HomeTeam, AwayTeam, HomeScore, AwayScore, Month, Day, Year

	//Pre-populate with at least 5 matches during create.

	//Write a program that allows a user to add new matches

	//Allow user to send in requests to database and return data.


public class CreateDatabase {

	public static void main(String[] args) {
		final String DB_URL = "jdbc:hsqldb:file:SoccerDB/Soccer";	//create database
		
		try {
			Connection conn = DriverManager.getConnection(DB_URL);	//connect to database

			dropSoccerTables(conn);	//remove table

			buildSoccerTables(conn);	//build table

			conn.close();	//close
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public static void dropSoccerTables (Connection conn){
			System.out.println("Checking for existing tables.");

			try {
				Statement stmt = conn.createStatement();	//get statement
				;

				try {
					stmt.execute("DROP TABLE SoccerMatches");		//drops the SoccerMatches Table
					System.out.println("SoccerMatches table dropped.");
				} catch (SQLException ex) {
				}
			} catch (SQLException ex) {
				System.out.println("ERROR: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	
	public static void buildSoccerTables (Connection conn) {
		try{
			Statement stmt = conn.createStatement();
			
			stmt.execute("CREATE TABLE SOCCERMATCHES (" + "SoccerLeague VARCHAR(70), " + "HomeTeam VARCHAR(25), " + "AwayTeam VARCHAR(25), "
						+ "HomeScore INT," + "AwayScore INT, " + "Month INT, " + "Day INT, " + "Year INT" + ")");
			
			
			//pre-pop with 5 different games
			
			String stmtString = "INSERT INTO SoccerMatches VALUES ('North American Soccer League','CAROLINA RAILHAWKS','MINNESOTA UNITED FC',2,1,4,2,2016)";
			
			
			stmt.executeUpdate(stmtString);		
			stmt.execute("INSERT INTO SoccerMatches VALUES ( " + "'North American Soccer League', " + "'FORT LAUDERDALE STRIKERS', " + "'MIAMI FC', " + "1, "
					+ "1," + "4," + "2," + "2016 " + ")");
			stmt.execute("INSERT INTO SoccerMatches VALUES ( " + "'North American Soccer League', " + "'TAMPA BAY ROWDIES', " + "'INDY ELEVEN', " + "0, "
					+ "0," + "4," + "2," + "2016 " + ")");
			stmt.execute("INSERT INTO SoccerMatches VALUES ( " + "'North American Soccer League', " + "'RAYO OKC', " + "'FC EDMONTON', " + "0, "
					+ "0," + "4," + "2," + "2016 " + ")");
			stmt.execute("INSERT INTO SoccerMatches VALUES ( " + "'North American Soccer League', " + "'NEW YORK COSMOS', " + "'OTTAWA FURY FC', " + "3, "
					+ "0," + "4," + "3," + "2016 " + ")");
			
			
			
			System.out.println("Your SoccerMatches table has been created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
		}
	}
