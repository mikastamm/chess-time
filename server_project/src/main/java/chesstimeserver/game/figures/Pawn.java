package chesstimeserver.game.figures;

import java.awt.Point;
public class Pawn extends Figure {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }


    @Override
    public FigureMovePattern[] getValidMovePositions() {
        final int movePatternCount = 3;
        FigureMovePattern[] moves = new FigureMovePattern[movePatternCount];

        if(isWhite)
        {
            moves[0] = new FigureMovePattern(new Point(0,-1), false, false, false);
            moves[1] = new FigureMovePattern(new Point(1,-1), false, true, false);
            moves[2] = new FigureMovePattern(new Point(-1,-1), false, true, false);
        }
        else{
            moves[0] = new FigureMovePattern(new Point(0,1), false, false, false);
            moves[1] = new FigureMovePattern(new Point(1,1), false, true, false);
            moves[2] = new FigureMovePattern(new Point(-1,1), false, true, false);
        }



        return moves;
    }
}
