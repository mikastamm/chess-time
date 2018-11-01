package chesstimeserver.database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import chesstimeserver.game.UserInfo;

public class MysqlApplicationDatabase implements ApplicationDatabase {

	@Override
	public void updateFirebaseToken(String userPasswordToken, String newFirebaseToken) {
		// TODO Auto-generated method stub
		DatabaseConnection con = new DatabaseConnection();
		if(con.connectToMysql("localhost","chesstime","root","chesstime") == false) {
			return;
		}
		Statement stmt = null;
		try {
			stmt = con.connection.createStatement();
			String updateString = "Update User SET firebase_token = "+newFirebaseToken+" WHERE password_token = "+userPasswordToken;
			stmt.executeUpdate(updateString);
			return;
		} catch (Exception ex) {
			return;
		}
	}

	@Override
	public UserInfo getUser(String passwordtoken) {
		// TODO Auto-generated method stub
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
			result = stmt.executeQuery("SELECT * FROM User WHERE password_token = "+passwordtoken);
			result.first(); // <- first entry of the result set
			Vector userInfo = new Vector();
			while(! result.isAfterLast()) {
				String name = result.getString("name");
				int elo = result.getInt("elo");
				String firebasetoken = result.getString("firebase_token");
		     
				UserInfo user  = new UserInfo(name, elo, passwordtoken, firebasetoken);
				userInfo.add(user);
				result.next(); // go to next line in the customer table
			} 
		} catch (Exception ex) {
			System.out.println("Error during access + n" + ex.getMessage());
			return null;
		}
	}

}
