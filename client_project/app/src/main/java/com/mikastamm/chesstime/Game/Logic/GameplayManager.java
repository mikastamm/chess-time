package com.mikastamm.chesstime.Game.Logic;

import android.graphics.Point;

import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Game;

public interface GameplayManager {
    void setGame(Game game);
    void requestRemis();
    Figure getSelectedFigure();
    Point getSelectedField();
    void moveFigure(Point from, Point to);
    void setSelectedField(Point field);
    void clearSelectedField();
    HighlightedField[] getHighlightedFields(Point forField);

}
