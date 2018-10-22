package com.mikastamm.chesstime.GUI.UserInterface;

import com.mikastamm.chesstime.Game.Board.BoardState;
import com.mikastamm.chesstime.Game.Board.HighlightedField;

public interface BoardView {
    String GAME_ID_BUNDLE_KEY = "GameId";
    void notifyBoardStateChanged();
    void setBoardEnabled(boolean enabled);
    void setHighlightedFields(HighlightedField[] highlightedFields);
}
