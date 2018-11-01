package com.mikastamm.chesstime;

import android.app.Application;

import com.mikastamm.chesstime.Game.Logic.ChessGamesManager;
import com.mikastamm.chesstime.Game.Logic.GamesManager;
import com.mikastamm.chesstime.Game.UserManager;

public class ChessTimeApplication extends Application {
    public static ChessGamesManager gamesManager;
    public static UserManager userManager;

    @Override
    public void onCreate() {
        super.onCreate();
        gamesManager = new ChessGamesManager(this);
        userManager = new UserManager(this);
    }
}
