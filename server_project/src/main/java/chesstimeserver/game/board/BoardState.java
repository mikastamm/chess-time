package chesstimeserver.game.board;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import chesstimeserver.game.figures.*;

public class BoardState {
    public Figure[][] board;
    private List<BoardStateChangeListener> listeners = new ArrayList<>();
    
    public BoardState(Figure[][] board){
        this.board = board;
    }

    public Figure getFigure(String fieldName)
    {
        Point p = BoardUtil.getPointFromFieldName(fieldName);
        return getFigure(p);
    }

    public Figure getFigure(Point p)
    {
        return board[p.y][p.x];
    }

    public void addBoardStateChangeListener(BoardStateChangeListener listener)
    {
        listeners.add(listener);
    }

    //Calls all registered Listeners
    public void notifyBoardStateChanged()
    {
    	System.out.println("BoardState Changed");
        for (BoardStateChangeListener l:listeners) {
            l.onBoardStateChanged();
        }
    }

    public static BoardState getStartingBoardState()
    {
        Figure[][] board = new Figure[8][];
        Figure[] whiteBackrow = { new Rook(true), new Knight(true), new Bishop(true), new King(true), new Queen(true), new Bishop(true), new Knight(true), new Rook(true)};
        Figure[] whitePawnRow = new Figure[8];
        for (int i = 0; i < 8; i++) {
            whitePawnRow[i] = new Pawn(true);
        }

        Figure[] blackPawnRow = new Figure[8];
        for (int i = 0; i < 8; i++) {
            blackPawnRow[i] = new Pawn(false);
        }
        Figure[] blackBackrow = { new Rook(false), new Knight(false), new Bishop(false), new King(false), new Queen(false), new Bishop(false), new Knight(false), new Rook(false)};

        board[6] = whiteBackrow;
        board[7] = whitePawnRow;
        for (int i = 2; i < 6; i++) {
            board[i] = new Figure[8];
        }
        board[1] = blackPawnRow;
        board[0] = blackBackrow;

        return new BoardState(board);
    }
    



}
