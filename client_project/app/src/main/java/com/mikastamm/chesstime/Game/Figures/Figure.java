package com.mikastamm.chesstime.Game.Figures;

public abstract class Figure {

    public Figure(boolean isWhite)
    {
        this.isWhite = isWhite;
    }

    public boolean isWhite;

    public abstract int getDrawableResourceId();

    public abstract FigureMovePattern[] getValidMovePositions();
}
