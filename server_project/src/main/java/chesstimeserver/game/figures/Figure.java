package chesstimeserver.game.figures;

public abstract class Figure {

    public Figure(boolean isWhite)
    {
        this.isWhite = isWhite;
    }

    public boolean isWhite;

    public abstract FigureMovePattern[] getValidMovePositions();
}
