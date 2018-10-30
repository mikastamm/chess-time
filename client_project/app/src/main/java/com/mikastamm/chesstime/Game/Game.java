package com.mikastamm.chesstime.Game;

import com.mikastamm.chesstime.Game.Board.BoardState;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public String id;
    public UserInfo playerWhite;
    public UserInfo playerBlack;
    public BoardState boardState;
    public boolean isWhitesTurn = true;
    public List<GameStateChangeListener> listeners = new ArrayList<>();

    public void notifyGameOver(boolean isPlayerWinner)
    {
        for (GameStateChangeListener l: listeners
             ) {
            l.onGameOver(isPlayerWinner);
        }
    }

    public void notifyTurnChanged(boolean isWhitesTurn)
    {
        for (GameStateChangeListener l: listeners) {
            l.onTurnChanged(isWhitesTurn);
        }
    }

    public UserInfo getPlayerWhoseTurnItIs()
    {
        if(isWhitesTurn)
            return playerWhite;
        else
            return playerBlack;
    }


    //TODO: Remove later
    public static Game getTestGame(){
        Game game = new Game();
        game.boardState = BoardState.getStartingBoardState();
        game.id = "testgame";
        UserInfo userInfo = new UserInfo();
        PlayerInfo playerInfo = new PlayerInfo();
        userInfo.name = "opponent";
        userInfo.elo = "112";

        playerInfo.name = "player";
        playerInfo.elo = "222";
        playerInfo.passwordToken = "";

        game.playerBlack = userInfo;
        game.playerWhite = playerInfo;
        return game;
    }
}
