package com.mikastamm.chesstime.Game.Logic;

import android.graphics.Point;

import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Board.HighlightedFieldType;
import com.mikastamm.chesstime.Game.Board.MoveValidator;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Game;

import java.util.List;

public class ChessGameplayManager implements GameplayManager {
    private Game game;
    private Point selectedField;

    @Override
    public void setSelectedField(Point field) {
        selectedField = field;
    }

    @Override
    public void clearSelectedField() {
        selectedField = null;
    }

    @Override
    public HighlightedField[] getHighlightedFields(Point forField) {
        MoveValidator val = new MoveValidator(game);

        List<Point> movables = val.getMoveLocationsOfFigure(getSelectedFigure(), new Point(selectedField));
        HighlightedField[] highlightedFields = new HighlightedField[movables.size()];
        for (int i = 0; i < movables.size(); i++) {
            highlightedFields[i] = new HighlightedField();
            highlightedFields[i].field = movables.get(i);
            highlightedFields[i].type = HighlightedFieldType.MOVE;
        }

        return highlightedFields;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Figure getSelectedFigure() {
        if(selectedField == null)
            return null;

        return game.boardState.board[selectedField.y][selectedField.x];
    }

    @Override
    public Point getSelectedField() {
        return new Point(selectedField);
    }


    @Override
    public void moveFigure(Point from, Point to) {

    }

    @Override
    public void requestRemis() {

    }

}
