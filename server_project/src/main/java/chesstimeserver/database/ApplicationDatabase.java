package chesstimeserver.database;

import chesstimeserver.game.UserInfo;

public interface ApplicationDatabase {
	void updateFirebaseToken(String userPasswordToken, String newFirebaseToken);
	UserInfo getUser(String passwordtoken);
	//Registers the User by creating the UserInfo object in the database
	//Returns null if the username is already in use
	String registerUser(String username);
}
