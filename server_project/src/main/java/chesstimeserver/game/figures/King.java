package chesstimeserver.game.figures;

import java.awt.Point;

public class King extends Figure {
    public King(boolean isWhite) {
        super(isWhite);
    }


    @Override
    public FigureMovePattern[] getValidMovePositions() {
        final int movePatternCount = 8;
        FigureMovePattern[] moves = new FigureMovePattern[movePatternCount];

        moves[0] = new FigureMovePattern(new Point(1,1), false, false, false);
        moves[1] = new FigureMovePattern(new Point(1,-1), false, false, false);
        moves[2] = new FigureMovePattern(new Point(-1,1), false, false, false);
        moves[3] = new FigureMovePattern(new Point(-1,-1), false, false, false);

        moves[4] = new FigureMovePattern(new Point(0,1), false, false, false);
        moves[5] = new FigureMovePattern(new Point(0,-1), false, false, false);
        moves[6] = new FigureMovePattern(new Point(-1,0), false, false, false);
        moves[7] = new FigureMovePattern(new Point(1,0), false, false, false);

        return moves;
    }
}
