package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.graphics.Point;
import android.util.Log;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.GUI.UserInterface.BoardView;
import com.mikastamm.chesstime.Game.Board.BoardStateChangeListener;
import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Board.HighlightedFieldType;
import com.mikastamm.chesstime.Game.Board.MoveValidator;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.GameStateChangeListener;
import com.mikastamm.chesstime.Game.Logic.GameplayManager;
import com.mikastamm.chesstime.Game.Logic.GameplayManagerFactory;
import com.mikastamm.chesstime.Game.UserManager;

import java.util.Map;

public class DefaultBoardPresenter implements BoardPresenter {
    private BoardView view;
    private Game game;
    private GameplayManager gameplayManager;

    public DefaultBoardPresenter(final BoardView view, final String gameId)
    {
        this.view = view;

        game = ChessTimeApplication.gamesManager.getGame(gameId);
        view.setGame(game);

        game.listeners.add(new GameStateChangeListener() {
            @Override
            public void onGameOver(boolean winner) {
                view.notifyGameOver(winner);
            }

            @Override
            public void onTurnChanged(boolean isWhitesTurn) {

            }
        });

        game.boardState.addBoardStateChangeListener(new BoardStateChangeListener() {
            @Override
            public void onBoardStateChanged() {
                view.setHighlightedFields(gameplayManager.getHighlightedFields(gameplayManager.getSelectedField(), ChessTimeApplication.userManager.isPlayerWhite(game)));
                view.notifyBoardStateChanged();
            }
        });
        gameplayManager = GameplayManagerFactory.makeGameplayManager(game);
    }

    @Override
    public void onFieldClicked(Point field) {
        Log.d("FieldClicked",field.x+","+field.y);
        if(ChessTimeApplication.userManager.getPlayer().equals(game.getPlayerWhoseTurnItIs())) //Only do anything if its the players turn
        {
            if (gameplayManager.getSelectedFigure() == null || !isFieldInMovePatternOfSelected(field, gameplayManager.getSelectedField())) {//Select field
                gameplayManager.setSelectedField(field);
                view.setHighlightedFields(gameplayManager.getHighlightedFields(field, ChessTimeApplication.userManager.isPlayerWhite(game)));
            } else {//Move
                Map<Point, HighlightedFieldType> highlightedFields = view.getHighlightedFields();
                if(highlightedFields != null) {
                    HighlightedFieldType fieldType = view.getHighlightedFields().get(field);

                    if(fieldType == HighlightedFieldType.CAPTURE || fieldType == HighlightedFieldType.MOVE)
                        gameplayManager.moveFigure(gameplayManager.getSelectedField(), field, ChessTimeApplication.userManager.getPlayer());
                }
            }
        }
    }

    private boolean isFieldInMovePatternOfSelected(Point field, Point selectedPosition)
    {
         return gameplayManager.getHighlightedFields(selectedPosition, ChessTimeApplication.userManager.isPlayerWhite(game)).containsKey(field);
    }
}
