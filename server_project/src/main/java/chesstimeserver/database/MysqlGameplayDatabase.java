package chesstimeserver.database;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import chesstimeserver.DatabaseContainer;
import chesstimeserver.game.Game;
import chesstimeserver.game.UserInfo;

public class MysqlGameplayDatabase implements GameplayDatabase {

	@Override
	public Game findGame(String searcherPasswordToken) {
		// TODO Auto-generated method stub
		String passwordToken;
		Statement stmt = null;
		ResultSet result = null;
		//Datenbank verbindung erstellen
		DatabaseConnection con = new DatabaseConnection();
		if(con.connectToMysql("localhost","chesstime","root","chesstime") == false) {
			return null;
		}
		//SQL-Abfrage passwordtoken_w
		try {
			stmt = con.connection.createStatement();
			result = stmt.executeQuery("SELECT * FROM SearchingPlayers ");
			result.first(); // <- first entry of the result set
			passwordToken = result.getString("player_white"); 
			if (result != null) {
				createGame(searcherPasswordToken,passwordToken);
				Game newgame = new Game();
				newgame = getGame("gameId");
				return newgame;
			} else return null;
		} catch (Exception ex) {
			System.out.println("Error during access + n" + ex.getMessage());
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
		if(con.connectToMysql("localhost","chesstime","root","chesstime") == false) {
			return null;
		}
		//SQL-Abfrage passwordtoken_w
		try {
		stmt = con.connection.createStatement();
		result = stmt.executeQuery("SELECT player_white FROM Game WHERE idGame = "+gameId);
		result.first(); // <- first entry of the result set
		passwordtoken_w = result.getString("player_white");  
		} catch (Exception ex) {
		System.out.println("Error during access + n" + ex.getMessage());
		return null;
		}
		// SQL-Abfrage passwordtoken_b
		try {
			stmt = con.connection.createStatement();
			result = stmt.executeQuery("SELECT player_black FROM Game WHERE idGame = "+gameId);
			result.first(); // <- first entry of the result set
			passwordtoken_b = result.getString("player_white");  
		} catch (Exception ex) {
			System.out.println("Error during access + n" + ex.getMessage());
			return null;
		}
		Game game = new Game();
	    game.playerBlack = DatabaseContainer.applicationDatabase.getUser(passwordtoken_b);
	    game.playerWhite = DatabaseContainer.applicationDatabase.getUser(passwordtoken_w);
		game.id = gameId;
		return game;
	}

	@Override
	public void writeGame(Game game) {
		// TODO Auto-generated method stub

	}
	
	private String createGame(String passwordtoken_w, String passwordtoken_b) {
		DatabaseConnection con = new DatabaseConnection();
		Game newGame = new Game(id fehlt, passwordtoken_w, passwordtoken_b);
		
		if(con.connectToMysql("Localhost", "chesstime", "root", "chesstime") == false){
			return null;
		}
		con.saveGame (passwordtoken_w, passwordtoken_b);

	}

}
