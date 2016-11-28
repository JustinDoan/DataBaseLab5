package mainClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InsertDelete {

	public static void insert() throws SQLException{
		
		try {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("What League was this game played in? ");
			String league = keyboard.nextLine();
			league = league.toUpperCase();
			System.out.println("What Team was the Home Team? ");
			String homeTeam = keyboard.nextLine();
			homeTeam = homeTeam.toUpperCase();
			System.out.println("What Team was the Away Team? ");
			String awayTeam = keyboard.nextLine();
			awayTeam = awayTeam.toUpperCase();
			System.out.println("What was the score for the Home Team? ");
			int homeScore = keyboard.nextInt();
			System.out.println("What was the score for the Away Team? ");
			int awayScore = keyboard.nextInt();
			keyboard.nextLine();
			System.out.println("What was the date? (day/month/year)");
			String date = keyboard.nextLine();
			
			String[] datesplit = date.split(Pattern.quote("/"));

			final String DB_URL = "jdbc:hsqldb:file:SoccerDB/Soccer";
			
			Connection conn = DriverManager.getConnection(DB_URL);
			

			String sql = "INSERT INTO SoccerMatches"
						 + " (SoccerLeague,HomeTeam,AwayTeam,HomeScore,AwayScore,Month,Day,Year)"
						 + " VALUES "
						 + " (?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, league);
			stmt.setString(2, homeTeam);
			stmt.setString(3, awayTeam);
			stmt.setInt(4, homeScore);
			stmt.setInt(5, awayScore);
			stmt.setInt(6, Integer.parseInt(datesplit[1]));
			stmt.setInt(7, Integer.parseInt(datesplit[0]));
			stmt.setInt(8, Integer.parseInt(datesplit[2]));
			
			
			stmt.executeUpdate();
			
			conn.close();
			
			System.out.println("Match was successfully inserted.");
		} catch (NumberFormatException e) {
			System.out.println("There was an error processing your request to insert a match, Please try again. Double check all of your inputs.");
		} catch (InputMismatchException e){
			System.out.println("There was an error processing your request to insert a match, Please try again. Double check all of your inputs.");
		}
	}
	
	public static void delete() throws SQLException{
		
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What Team? ");
		String team = keyboard.nextLine();
		team = team.toUpperCase();
			
		System.out.println("What date? (day/month/year)");
		String date = keyboard.nextLine();
		
		String[] datesplit = date.split(Pattern.quote("/"));
		
		final String DB_URL = "jdbc:hsqldb:file:SoccerDB/Soccer";
		
		Connection conn = DriverManager.getConnection(DB_URL);
		
	
		String sql = "DELETE FROM SoccerMatches WHERE HomeTeam='" + team + "' OR AwayTeam='" + team + "' AND Day="+ datesplit[0] + "AND Month="+ datesplit[1]+ "AND Year="+ datesplit[2]+"";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		
		
		stmt.executeUpdate();
		
		conn.close();
		
		System.out.println("Match was successfully deleted.");
		
		
		
	}

}