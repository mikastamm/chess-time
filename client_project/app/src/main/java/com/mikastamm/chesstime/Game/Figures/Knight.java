package com.mikastamm.chesstime.Game.Figures;

import android.graphics.Point;

import com.mikastamm.chesstime.R;

public class Knight extends Figure {
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public int getDrawableResourceId() {
        if(isWhite)
        {
            return R.drawable.ic_w_knight;
        }
        else
        {
            return R.drawable.ic_b_knight;
        }
    }

    @Override
    public FigureMovePattern[] getValidMovePositions() {
        final int movePatternCount = 8;
        FigureMovePattern[] moves = new FigureMovePattern[movePatternCount];

        moves[0] = new FigureMovePattern(new Point(1,2), false, false, true);
        moves[1] = new FigureMovePattern(new Point(-1,2), false, false, true);

        moves[2] = new FigureMovePattern(new Point(2,1), false, false, true);
        moves[3] = new FigureMovePattern(new Point(2,-1), false, false, true);

        moves[4] = new FigureMovePattern(new Point(1,-2), false, false, true);
        moves[5] = new FigureMovePattern(new Point(-1,-2), false, false, true);

        moves[6] = new FigureMovePattern(new Point(-2,1), false, false, true);
        moves[7] = new FigureMovePattern(new Point(-2,-1), false, false, true);


        return moves;
    }
}
