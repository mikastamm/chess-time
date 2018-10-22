package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.graphics.Point;
import android.util.Log;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.GUI.UserInterface.BoardView;
import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.Logic.GameplayManager;
import com.mikastamm.chesstime.Game.Logic.GameplayManagerFactory;

public class DefaultBoardPresenter implements BoardPresenter {
    private BoardView view;
    private Game game;
    private GameplayManager gameplayManager;

    public DefaultBoardPresenter(BoardView view, String gameId)
    {
        this.view = view;
        game = ChessTimeApplication.gamesManager.getGame(gameId);
        gameplayManager = GameplayManagerFactory.makeGameplayManager(game);
    }

    @Override
    public void onFieldClicked(Point field) {
        Log.d("FieldClicked",field.x+","+field.y);
        if(gameplayManager.getSelectedFigure() == null || !isFieldInMovePatternOfSelected(field,gameplayManager.getSelectedField() ))
        {//Select field
            gameplayManager.setSelectedField(field);
            view.setHighlightedFields(gameplayManager.getHighlightedFields(field));
        }
        else
        {//Move

        }
    }

    private boolean isFieldInMovePatternOfSelected(Point field, Point selectedPosition)
    {
       HighlightedField[] highlightedFields = gameplayManager.getHighlightedFields(selectedPosition);
        for (HighlightedField highlightedField : highlightedFields) {
            if (highlightedField.field.equals(field)) {
                return true;
            }
        }
        return false;
    }
}
