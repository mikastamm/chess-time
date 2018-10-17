package com.mikastamm.chesstime.Game.Entities;

import android.graphics.Point;

import com.mikastamm.chesstime.R;

public class Queen extends Figure {

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public int getDrawableResourceId() {
        if(isWhite)
        {
            return R.drawable.ic_w_queen;
        }
        else
        {
            return R.drawable.ic_b_queen;
        }
    }

    @Override
    public FigureMovePattern[] getValidMovePositions() {
        final int movePatternCount = 8;
        FigureMovePattern[] moves = new FigureMovePattern[movePatternCount];
        for (int i = 0; i < movePatternCount; i++) {
            moves[i] = new FigureMovePattern(
                    new Point((int)Math.ceil(Math.cos(i*Math.toRadians(45))), (int)Math.ceil(Math.sin(i*Math.toRadians(45)))),
                    true,
                    false
            );
        }
        return moves;
    }
}
