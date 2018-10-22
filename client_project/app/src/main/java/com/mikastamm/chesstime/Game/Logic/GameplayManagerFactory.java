package com.mikastamm.chesstime.Game.Logic;

import android.content.Context;

import com.mikastamm.chesstime.Game.Game;

public class GameplayManagerFactory {
    public static GameplayManager makeGameplayManager(Game game)
    {
        GameplayManager mgr = new ChessGameplayManager();
        mgr.setGame(game);
        return mgr;
    }

}
