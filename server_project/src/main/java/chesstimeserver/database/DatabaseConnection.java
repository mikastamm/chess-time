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
	
	public boolean connectToMysql(){
		try{
			String database = "chesstime";
			String host = "localhost";
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());		
			String connectionCommand = "jdbc:mysql://"+host+"/"+database+"?serverTimezone=Europe/Amsterdam";
			connection = DriverManager.getConnection(connectionCommand, "root", "root");
			return true;
		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	
}
