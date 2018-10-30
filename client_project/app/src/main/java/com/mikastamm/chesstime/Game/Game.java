package com.mikastamm.chesstime.Game;

import com.mikastamm.chesstime.Game.Board.BoardState;

public class Game {
    public String id;
    public UserInfo playerWhite;
    public UserInfo playerBlack;
    public BoardState boardState;
    public boolean isWhitesTurn = true;
    public GameStateChangeListener listener;

    public void notifyGameOver(boolean isPlayerWinner)
    {
        if(listener != null)
            listener.onGameOver(isPlayerWinner);
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
