package com.mikastamm.chesstime.Game;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.Board.BoardState;
import com.mikastamm.chesstime.Networking.NetworkEvents.GameFoundData;

public class GameFactory {
    public static Game newGame(GameFoundData data)
    {
        Game game = new Game();
        UserInfo opponent = new UserInfo();
        opponent.elo = data.opponent_elo;
        opponent.name = data.opponent_name;


        if(data.is_opponent_white)
        {
            game.playerWhite = opponent;
            game.playerBlack = ChessTimeApplication.userManager.getPlayer();
        }
        else{
            game.playerWhite = ChessTimeApplication.userManager.getPlayer();
            game.playerBlack = opponent;
        }

        game.isWhitesTurn = true;
        game.id = data.game_id;
        game.boardState = BoardState.getStartingBoardState();

        return game;
    }
}
