package com.mikastamm.chesstime.Game.Logic;

import android.content.Context;

import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.PersistenceManager;

import java.util.HashMap;
import java.util.Map;

public class ChessGamesManager implements GamesManager {
    private Map<String, Game> games;

    public ChessGamesManager(Context context){
        games = PersistenceManager.getGames(context);

        if(games == null)
            games = new HashMap<>();

        PersistenceManager.storeGames(games, context);
    }

    @Override
    public Game getGame(String gameId) {
        return games.get(gameId);
    }


    @Override
    public Game[] getAllGames() {
        return (Game[])games.values().toArray();
    }
    //TODO: remove later
    public Game[] getAllTestGames() {
        Game[] testGames = new Game[5];
        for (int i = 0; i < 5 ; i++){
            testGames[i] = Game.getTestGame();
        }
        return testGames;
    }

    @Override
    public void addGame(Game game, Context context) {
            games.put(game.id, game);
            PersistenceManager.storeGames(games, context);

    }

    @Override
    public void deleteGame(String gameId, Context context) {
        games.remove(gameId);
        PersistenceManager.storeGames(games, context);

    }
}
