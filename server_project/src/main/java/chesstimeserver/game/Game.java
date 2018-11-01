package chesstimeserver.game;

import chesstimeserver.game.board.BoardState;

public class Game {
    public String id;
    public UserInfo playerWhite;
    public UserInfo playerBlack;
    public boolean isWhitesTurn = true;
    public UserInfo getPlayerWhoseTurnItIs()
    {
        if(isWhitesTurn)
            return playerWhite;
        else
            return playerBlack;
    }

   
}
