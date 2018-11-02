package chesstimeserver.networking.services;

import com.google.gson.Gson;

import chesstimeserver.DatabaseContainer;
import chesstimeserver.game.Game;
import chesstimeserver.game.UserInfo;
import chesstimeserver.networking.FirebaseCommunicator;

public class SearchPlayerService {
	public void searchPlayer(final String passwordToken)
	{
		Game game = DatabaseContainer.getGameplayDatabase().findGame(passwordToken);
		if(game != null)
		{
			UserInfo player = DatabaseContainer.getApplicationDatabase().getUser(passwordToken);
			SearchPlayerResponse resp = new SearchPlayerResponse();
			resp.game_id = game.id;
			resp.is_opponent_white = game.playerBlack.passwordToken.equals(passwordToken);
			resp.kind = "new_game";
			resp.opponent_elo =(resp.is_opponent_white ? game.playerWhite.elo : game.playerBlack.elo)+"";
			resp.opponent_name = (resp.is_opponent_white ? game.playerWhite.name : game.playerBlack.name)+"";
			
			Gson gson = new Gson();
			String searcherJson = gson.toJson(resp, SearchPlayerResponse.class);

			resp.opponent_elo = player.elo+"";
			resp.opponent_name = player.name;
			resp.is_opponent_white = !resp.is_opponent_white;
			
			UserInfo searchee = (!resp.is_opponent_white ? game.playerWhite : game.playerBlack);
			String searcheeJson = gson.toJson(resp, SearchPlayerResponse.class);
			
			FirebaseCommunicator.sendStringFCM(searcherJson, player.firebaseToken);
			DatabaseContainer.getGameplayDatabase().removeFromSearchingUsers(player.passwordToken);

			FirebaseCommunicator.sendStringFCM(searcheeJson, searchee.firebaseToken); 			
			DatabaseContainer.getGameplayDatabase().removeFromSearchingUsers(searchee.passwordToken);

		}
		else {
			DatabaseContainer.getGameplayDatabase().addToSearchingUsers(passwordToken);
		}
	}
	
}
