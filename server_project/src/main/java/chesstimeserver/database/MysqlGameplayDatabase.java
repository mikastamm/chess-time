package chesstimeserver.database;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import chesstimeserver.DatabaseContainer;
import chesstimeserver.game.Game;
import chesstimeserver.game.UserInfo;

public class MysqlGameplayDatabase implements GameplayDatabase {

	@Override
	public Game findGame(String searcherPasswordToken) {
		// TODO Auto-generated method stub
		//Datenbank verbindung erstellen
		DatabaseConnection con = new DatabaseConnection();
		if(con.connectToMysql() == false) {
			return null;
		}
		//SQL-Abfrage passwordtoken_w
		try {
			Statement stmt = con.connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM searchingusers ");
			result.first(); // <- first entry of the result set
			String passwordToken = null;
			if(!result.isAfterLast())
				passwordToken = result.getString("password_token"); 
			if (passwordToken != null) {
				String gameid =createGame(searcherPasswordToken,passwordToken);
				return getGame(gameid);
			} else 
				return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Game getGame(String gameId) {
		// TODO Auto-generated method stub
		String passwordtoken_w, passwordtoken_b;
		Statement stmt = null;
		ResultSet result = null;
		//Datenbank verbindung erstellen
		DatabaseConnection con = new DatabaseConnection();
		if(con.connectToMysql() == false) {
			return null;
		}
		//SQL-Abfrage passwordtoken_w
		try {
		stmt = con.connection.createStatement();
		result = stmt.executeQuery("SELECT * FROM game WHERE idGame = \""+gameId+"\"");
		result.first(); // <- first entry of the result set
		passwordtoken_w = result.getString("player_white");  
		passwordtoken_b = result.getString("player_black");
		} catch (Exception ex) {
		ex.printStackTrace();
		return null;
		}
		
		
		Game game = new Game();
	    game.playerBlack = DatabaseContainer.getApplicationDatabase().getUser(passwordtoken_b);
	    game.playerWhite = DatabaseContainer.getApplicationDatabase().getUser(passwordtoken_w);
		game.id = gameId;
		return game;
	}


	@Override
	public boolean saveGame(String gameid, String whiteToken, String blackToken){
		DatabaseConnection con = new DatabaseConnection();
		con.connectToMysql();
		
		Statement stmt = null;
		try {
			stmt = con.connection.createStatement();
			String query = "INSERT INTO game (idGame ,player_white, player_black) ";
			query += "VALUES ( \""+gameid+"\", \"" +whiteToken+ "\", \""+blackToken+"\")";
			stmt.executeUpdate(query);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	private String createGame(String passwordtoken_w, String passwordtoken_b) {
		DatabaseConnection con = new DatabaseConnection();

		if(con.connectToMysql() == false){
			return null;
		}
		
		String gameid = generateGameId();
		saveGame (gameid,passwordtoken_w, passwordtoken_b);
		return gameid;
	}
	
	private String generateGameId() {
		Random r = new Random();
		byte[] bytes = new byte[8];
		r.nextBytes(bytes);
		
		  MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		  
	}

	@Override
	public void addToSearchingUsers(String pwtoken) {
		DatabaseConnection con = new DatabaseConnection();
		con.connectToMysql();
		try {
			Statement statement = con.connection.createStatement();
			statement.executeUpdate("INSERT INTO searchingusers VALUES(\""+pwtoken+"\")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
	}

	@Override
	public void removeFromSearchingUsers(String pwtoken) {
		DatabaseConnection con = new DatabaseConnection();
		con.connectToMysql();
		try {
			Statement statement = con.connection.createStatement();
			statement.executeUpdate("DELETE FROM searchingusers WHERE password_token=\""+pwtoken+"\"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
	}
}
