package chesstimeserver.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import chesstimeserver.game.Game;

import java.sql.Date;

public class DatabaseConnection {
	Connection connection;
	
	public DatabaseConnection() {
		 
	}
	
	public boolean connectToMysql(String host, String database, String user, String passwd){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?user="+user+"&password="+passwd;
			connection = DriverManager.getConnection(connectionCommand);
			return true;
		}catch (Exception ex){
			return false;
		}
	}
	
	public boolean saveGame(Game game){
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String query = "INSERT INTO Game idGame ,player_white, player_black) ";
			query = query + "VALUES( "+game.id+", " +game.playerWhite.passwordToken + ", "+game.playerWhite.passwordToken+")";
			stmt.executeUpdate(query);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
