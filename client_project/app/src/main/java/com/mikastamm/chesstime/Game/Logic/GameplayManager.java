package com.mikastamm.chesstime.Game.Logic;

import android.graphics.Point;

import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Board.HighlightedFieldType;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.UserInfo;

import java.util.Map;

public interface GameplayManager {
    void setGame(Game game);
    void requestRemis();
    Figure getSelectedFigure();
    Point getSelectedField();
    void moveFigure(Point from, Point to, UserInfo issueingUser);
    void setSelectedField(Point field);
    void clearSelectedField();
    Map<Point, HighlightedFieldType> getHighlightedFields(Point forField, boolean isIssueingPlayerWhite);

}
