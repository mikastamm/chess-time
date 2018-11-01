package chesstimeserver.networking.services;

import java.awt.Point;

import chesstimeserver.DatabaseContainer;
import chesstimeserver.game.Game;
import chesstimeserver.game.board.BoardUtil;

public class MoveService {
	public void move(String passwordToken,String gameId,String from,String to) {
		Game game = DatabaseContainer.gameplayDatabase.getGame(gameId);
		Point fromPoint, toPoint;
		fromPoint = BoardUtil.getPointFromFieldName(from);
		toPoint = BoardUtil.getPointFromFieldName(to);
	}
}
