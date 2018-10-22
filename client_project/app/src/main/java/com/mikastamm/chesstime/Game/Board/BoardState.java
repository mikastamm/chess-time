package com.mikastamm.chesstime.Game.Board;

import android.graphics.Point;

import com.mikastamm.chesstime.Game.Figures.Bishop;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Figures.King;
import com.mikastamm.chesstime.Game.Figures.Knight;
import com.mikastamm.chesstime.Game.Figures.Pawn;
import com.mikastamm.chesstime.Game.Figures.Queen;
import com.mikastamm.chesstime.Game.Figures.Rook;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return board[p.x][p.y];
    }

    public void addBoardStateChangeListener(BoardStateChangeListener listener)
    {
        listeners.add(listener);
    }

    //Calls all registered Listeners
    public void notifyBoardStateChanged()
    {
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
