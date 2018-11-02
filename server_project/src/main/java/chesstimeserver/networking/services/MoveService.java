package chesstimeserver.networking.services;

import java.awt.Point;

import com.google.gson.Gson;

import chesstimeserver.DatabaseContainer;
import chesstimeserver.game.Game;
import chesstimeserver.game.UserInfo;
import chesstimeserver.game.board.BoardUtil;
import chesstimeserver.networking.FirebaseCommunicator;

public class MoveService {
	public void move(String passwordToken,String gameId,String from,String to) {
		Game game = DatabaseContainer.getGameplayDatabase().getGame(gameId);
		UserInfo requester, opponent;
		requester = (game.playerBlack.passwordToken.equals(passwordToken) ? game.playerBlack : game.playerWhite);
		opponent = (game.playerBlack.passwordToken.equals(passwordToken) ? game.playerWhite : game.playerBlack);

		MoveData data = new MoveData();
		data.from = from;
		data.to = to;
		data.game_id = game.id;
		
		String json;
		Gson gson = new Gson();
		json = gson.toJson(data, MoveData.class);
		System.out.println("sending"+json);
		FirebaseCommunicator.sendStringFCM(json, opponent.firebaseToken);
	}
}
