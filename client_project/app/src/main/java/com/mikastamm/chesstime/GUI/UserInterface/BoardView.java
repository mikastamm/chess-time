package com.mikastamm.chesstime.GUI.UserInterface;

import android.graphics.Point;

import com.mikastamm.chesstime.Game.Board.BoardState;
import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Board.HighlightedFieldType;
import com.mikastamm.chesstime.Game.Game;

import java.util.Map;

public interface BoardView {
    String GAME_ID_BUNDLE_KEY = "GameId";
    void notifyBoardStateChanged();
    void setHighlightedFields(Map<Point, HighlightedFieldType> highlightedFields);
    void setGame(Game game);
}
