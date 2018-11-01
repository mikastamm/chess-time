package chesstimeserver.networking.services;

import com.google.gson.Gson;

import chesstimeserver.DatabaseContainer;
import chesstimeserver.game.Game;
import chesstimeserver.game.UserInfo;
import chesstimeserver.networking.FirebaseCommunicator;

public class SearchPlayerService {
	public void searchPlayer(final String passwordToken)
	{
		new Thread(new Runnable() {

			@Override
			public void run() {
				doSearchPlayer(passwordToken);
			}});
	}
	
	private void doSearchPlayer(String passwordToken) {
		Game game = DatabaseContainer.gameplayDatabase.findGame(passwordToken);
		if(game != null)
		{
			UserInfo player = DatabaseContainer.applicationDatabase.getUser(passwordToken);
			SearchPlayerResponse resp = new SearchPlayerResponse();
			resp.gameId = game.id;
			resp.is_opponent_white = game.playerBlack.passwordToken.equals(passwordToken);
			resp.kind = "new_game";
			resp.opponent_elo =(resp.is_opponent_white ? game.playerWhite.elo : game.playerBlack.elo)+"";
			resp.opponent_name = (resp.is_opponent_white ? game.playerWhite.name : game.playerBlack.name)+"";
			
			Gson gson = new Gson();
			String json = gson.toJson(resp, UserInfo.class);

			FirebaseCommunicator.sendStringFCM(json, player.firebaseToken);
		}
		else {
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doSearchPlayer(passwordToken);
		}
	}
}
