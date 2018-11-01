package chesstimeserver;

import chesstimeserver.database.ApplicationDatabase;
import chesstimeserver.database.GameplayDatabase;
import chesstimeserver.database.MysqlApplicationDatabase;
import chesstimeserver.database.MysqlGameplayDatabase;

public class DatabaseContainer {

	public static ApplicationDatabase applicationDatabase;
	public static GameplayDatabase gameplayDatabase;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		applicationDatabase = new MysqlApplicationDatabase();
		gameplayDatabase = new MysqlGameplayDatabase();
	}

}
