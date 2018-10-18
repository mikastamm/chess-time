package com.mikastamm.chesstime.Game.Figures;

import android.graphics.Point;

import com.mikastamm.chesstime.R;

public class King extends Figure {
    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public int getDrawableResourceId() {
        if(isWhite)
        {
            return R.drawable.ic_w_king;
        }
        else
        {
            return R.drawable.ic_b_king;
        }
    }

    @Override
    public FigureMovePattern[] getValidMovePositions() {
        final int movePatternCount = 3;
        FigureMovePattern[] moves = new FigureMovePattern[movePatternCount];

        moves[0] = new FigureMovePattern(new Point(1,1), false, false, false);
        moves[0] = new FigureMovePattern(new Point(1,-1), false, false, false);
        moves[0] = new FigureMovePattern(new Point(-1,1), false, false, false);
        moves[0] = new FigureMovePattern(new Point(-1,-1), false, false, false);

        moves[0] = new FigureMovePattern(new Point(0,1), false, false, false);
        moves[0] = new FigureMovePattern(new Point(0,-1), false, false, false);
        moves[0] = new FigureMovePattern(new Point(-1,0), false, false, false);
        moves[0] = new FigureMovePattern(new Point(1,0), false, false, false);

        return moves;
    }
}
