package com.mikastamm.chesstime.Game;

public interface GameStateChangeListener {
    void onGameOver(boolean winner);
    void onTurnChanged(boolean isWhitesTurn);
}
