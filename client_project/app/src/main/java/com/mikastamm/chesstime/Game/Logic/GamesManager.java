package com.mikastamm.chesstime.Game.Logic;

import android.content.Context;

import com.mikastamm.chesstime.Game.Game;

public interface GamesManager {
    Game getGame(String gameId);
    Game[] getAllGames();
    void addGame(Game game, Context context);
    void deleteGame(String gameId, Context context);
    void saveGames();
}
