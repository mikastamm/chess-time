package chesstimeserver.database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.xml.bind.DatatypeConverter;

import chesstimeserver.game.UserInfo;

public class MysqlApplicationDatabase implements ApplicationDatabase {

	@Override
	public void updateFirebaseToken(String userPasswordToken, String newFirebaseToken) {
		// TODO Auto-generated method stub
		DatabaseConnection con = new DatabaseConnection();
		if(con.connectToMysql() == false) {
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
		if(con.connectToMysql() == false) {
			return null;
		}
		
		//SQL-Abfrage passwordtoken_w
		try {
			stmt = con.connection.createStatement();
			result = stmt.executeQuery("SELECT * FROM User WHERE password_token = "+passwordtoken);
			result.first(); // moves the cursor to the first entry of the result set
			List<UserInfo> userInfo = new ArrayList<UserInfo>();
			while(! result.isAfterLast()) {
				String name = result.getString("name");
				int elo = result.getInt("elo");
				String firebasetoken = result.getString("firebase_token");
		     
				UserInfo user  = new UserInfo();
				user.elo = elo;
				user.name = name;
				user.passwordToken = passwordtoken;
				user.firebaseToken = firebasetoken;
				userInfo.add(user);
				result.next(); // go to next line in the customer table
			} 
			
			if(userInfo.size() != 0)
				return userInfo.get(0);
			else 
				return null;
		} catch (Exception ex) {
			System.out.println("Error during access + n" + ex.getMessage());
			return null;
		}
	}

	@Override
	public String registerUser(String username) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		Statement stmt = null;
		//Datenbank verbindung erstellen
		DatabaseConnection con = new DatabaseConnection();
		if(con.connectToMysql() == false) {
			return null;
		}
		
		try {
			stmt = con.connection.createStatement();
			ResultSet result = stmt.executeQuery("SELECT count(*) FROM user WHERE name = \""+username+"\"");
			result.first();
			String pwtoken = generatePasswordToken();
			if(result.getInt(1) == 0) {
				con.connection.createStatement().executeUpdate("INSERT INTO user (password_token, name) VALUES (\""+pwtoken+"\",\""+username+"\")");
			}
			return pwtoken;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}

	private String generatePasswordToken() {
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
}
