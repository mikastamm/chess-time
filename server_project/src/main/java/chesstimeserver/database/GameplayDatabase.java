package chesstimeserver.database;

import chesstimeserver.game.Game;
import chesstimeserver.game.UserInfo;

public interface GameplayDatabase {
	//Searches for a game and returns the found opponent; Returns null if none is found
	Game findGame(String searcherPasswordToken);
	Game getGame(String gameId);
	boolean saveGame(String gameid, String whiteToken, String blackToken);
	void addToSearchingUsers(String pwtoken);
	void removeFromSearchingUsers(String pwtoken);
}
