package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.graphics.Point;
import android.util.Log;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.GUI.UserInterface.BoardView;
import com.mikastamm.chesstime.Game.Board.BoardStateChangeListener;
import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Board.MoveValidator;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.Logic.GameplayManager;
import com.mikastamm.chesstime.Game.Logic.GameplayManagerFactory;
import com.mikastamm.chesstime.Game.UserManager;

public class DefaultBoardPresenter implements BoardPresenter {
    private BoardView view;
    private Game game;
    private GameplayManager gameplayManager;

    public DefaultBoardPresenter(final BoardView view, final String gameId)
    {
        this.view = view;
        game = ChessTimeApplication.gamesManager.getGame(gameId);
        view.setGame(game);
        game.boardState.addBoardStateChangeListener(new BoardStateChangeListener() {
            @Override
            public void onBoardStateChanged() {
                view.setHighlightedFields(gameplayManager.getHighlightedFields(gameplayManager.getSelectedField()));
                view.notifyBoardStateChanged();
            }
        });
        gameplayManager = GameplayManagerFactory.makeGameplayManager(game);
    }

    @Override
    public void onFieldClicked(Point field) {
        Log.d("FieldClicked",field.x+","+field.y);
        if(UserManager.getPlayer().equals(game.getPlayerWhoseTurnItIs())) //Only do anything if its the players turn
        {
            if (gameplayManager.getSelectedFigure() == null || !isFieldInMovePatternOfSelected(field, gameplayManager.getSelectedField())) {//Select field
                gameplayManager.setSelectedField(field);
                view.setHighlightedFields(gameplayManager.getHighlightedFields(field));
            } else {//Move
                Point from = gameplayManager.getSelectedField();

                gameplayManager.moveFigure(gameplayManager.getSelectedField(), field, UserManager.getPlayer());
            }
        }
    }

    private boolean isFieldInMovePatternOfSelected(Point field, Point selectedPosition)
    {
         return gameplayManager.getHighlightedFields(selectedPosition).containsKey(field);
    }
}
