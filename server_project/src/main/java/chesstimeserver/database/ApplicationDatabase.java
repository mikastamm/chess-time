package chesstimeserver.database;

import chesstimeserver.game.UserInfo;

public interface ApplicationDatabase {
	void updateFirebaseToken(String userPasswordToken, String newFirebaseToken);
	UserInfo getUser(String passwordtoken);
}
