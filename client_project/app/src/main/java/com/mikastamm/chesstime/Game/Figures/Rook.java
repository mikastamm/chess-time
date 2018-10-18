package com.mikastamm.chesstime.Game.Figures;

import android.graphics.Point;

import com.mikastamm.chesstime.R;

public class Rook extends Figure {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public int getDrawableResourceId() {
        if(isWhite)
        {
            return R.drawable.ic_w_rook;
        }
        else
        {
            return R.drawable.ic_b_rook;
        }
    }

    @Override
    public FigureMovePattern[] getValidMovePositions() {
        final int movePatternCount = 4;
        FigureMovePattern[] moves = new FigureMovePattern[movePatternCount];

        moves[0] = new FigureMovePattern(new Point(0,1), true, false , false);
        moves[1] = new FigureMovePattern(new Point(1,0), true, false , false);
        moves[2] = new FigureMovePattern(new Point(0,-1), true, false, false);
        moves[3] = new FigureMovePattern(new Point(-1,0), true, false, false);


        return moves;
    }
}
