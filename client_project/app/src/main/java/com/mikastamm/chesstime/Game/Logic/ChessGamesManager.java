package com.mikastamm.chesstime.Game.Logic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.NetworkRequest;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.GUI.UserInterface.GameActivity;
import com.mikastamm.chesstime.GUI.UserInterface.MenuActivity;
import com.mikastamm.chesstime.Game.Board.BoardUtil;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.GameFactory;
import com.mikastamm.chesstime.Game.PersistenceManager;
import com.mikastamm.chesstime.Game.UserManager;
import com.mikastamm.chesstime.Networking.NetworkEvents.GameFoundData;
import com.mikastamm.chesstime.Networking.NetworkEvents.NetworkEventDispatcher;
import com.mikastamm.chesstime.Networking.NetworkEvents.NetworkEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChessGamesManager implements GamesManager {
    private Map<String, Game> games;
    private Context context;

    public ChessGamesManager(Context context){
        this.context = context;
        games = PersistenceManager.getGames(context);

        if(games == null)
            games = new HashMap<>();

        PersistenceManager.storeGames(games, context);
        setupNetworkEventListener();
    }

    private void setupNetworkEventListener(){
        NetworkEventDispatcher.getInstance().eventListeners.add(new NetworkEventListener() {
            @Override
            public void onMoveReceived(String from, String to, String gameId) {
                Game target = games.get(gameId);
                Point fromPoint = BoardUtil.getPointFromFieldName(from);
                Point toPoint = BoardUtil.getPointFromFieldName(to);
                GameplayManager gpm = new ChessGameplayManager();
                gpm.setGame(target);
                gpm.moveFigure(fromPoint, toPoint, ChessTimeApplication.userManager.isPlayerWhite(target) ? target.playerBlack : target.playerWhite);
            }

            @Override
            public void onGameFound(GameFoundData data) {
                addGame(GameFactory.newGame(data), context);

                Intent openGameActivityIntent = new Intent(context, GameActivity.class);
                openGameActivityIntent.putExtra("GameId", data.game_id);
                context.startActivity(openGameActivityIntent);
            }
        });
    }



    @Override
    public Game getGame(String gameId) {
        return games.get(gameId);
    }


    @Override
    public Game[] getAllGames() {
        Game[] gameArr = new Game[games.values().size()];
        return games.values().toArray(gameArr);
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

    @Override
    public void saveGames() {
        PersistenceManager.storeGames(games, context);
    }
}
