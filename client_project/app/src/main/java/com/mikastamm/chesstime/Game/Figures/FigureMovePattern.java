package com.mikastamm.chesstime.Game.Figures;

import android.graphics.Point;

public class FigureMovePattern {
    //Wether target defines one field or a direction
    public boolean isInfinite;

    public boolean canJump;
    //Should the figure only be able to move to target if it captures an enemy figure with that move
    public boolean onlyOnCapture;
    //Position relative to the Figure
    public Point target;

    public FigureMovePattern(Point target,
            boolean isInfinite,
            boolean onlyOnCapture,
                             boolean canJump)
    {
        this.isInfinite = isInfinite;
        this.onlyOnCapture = onlyOnCapture;
        this.target = target;
        this.canJump = canJump;
    }
}
