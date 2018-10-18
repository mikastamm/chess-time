package com.mikastamm.chesstime.Game;

import com.mikastamm.chesstime.Game.Board.BoardState;

public class Game {
    public UserInfo playerWhite;
    public UserInfo playerBlack;
    public BoardState boardState;
    public boolean isWhitesTurn = true;
    public UserInfo getPlayerWhoseTurnItIs()
    {
        if(isWhitesTurn)
            return playerWhite;
        else
            return playerBlack;
    }
}
