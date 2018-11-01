package chesstimeserver;

import chesstimeserver.database.ApplicationDatabase;
import chesstimeserver.database.GameplayDatabase;
import chesstimeserver.database.MysqlApplicationDatabase;
import chesstimeserver.database.MysqlGameplayDatabase;

public class DatabaseContainer {

	private static ApplicationDatabase applicationDatabase;
	private static GameplayDatabase gameplayDatabase;
	
	public static ApplicationDatabase getApplicationDatabase() {
		if(applicationDatabase == null)
			applicationDatabase = new MysqlApplicationDatabase();
		
		return applicationDatabase;
	}
	
	public static GameplayDatabase getGameplayDatabase() {
		if(gameplayDatabase == null)
			gameplayDatabase = new MysqlGameplayDatabase();
		
		return gameplayDatabase;
	}
	
}
