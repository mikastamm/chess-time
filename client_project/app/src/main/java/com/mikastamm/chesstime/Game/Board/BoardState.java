package com.mikastamm.chesstime.Game.Board;

import com.mikastamm.chesstime.Game.Figures.Bishop;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Figures.King;
import com.mikastamm.chesstime.Game.Figures.Knight;
import com.mikastamm.chesstime.Game.Figures.Pawn;
import com.mikastamm.chesstime.Game.Figures.Queen;
import com.mikastamm.chesstime.Game.Figures.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardState {
    public Map<String, Map<String, Figure>> board;
    private List<BoardStateChangeListener> listeners = new ArrayList<>();
    
    public BoardState(Map<String, Map<String, Figure>> board){
        this.board = board;
    }

    public Figure getFigure(String Field)
    {
        char[] c = Field.toCharArray();
        return board.get(c[1]+"").get(c[0]+"");
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
        Map<String, Map<String, Figure>> board = new HashMap<>();
        String[] rows = {"a","b","c","d","e","f","g","h"};

        Map<String, Figure> row1 = new HashMap<>();
        row1.put("a", new Rook(true));
        row1.put("b", new Knight(true));
        row1.put("c", new Bishop(true));
        row1.put("d", new King(true));
        row1.put("e", new Queen(true));
        row1.put("f", new Bishop(true));
        row1.put("g", new Knight(true));
        row1.put("h", new Rook(true));

        Map<String, Figure> row2 = new HashMap<>();
        for (String s:rows) {
            row2.put(s, new Pawn(true));
        }

        Map<String, Figure> row7 = new HashMap<>();
        for (String s:rows) {
            row2.put(s, new Pawn(false));
        }

        Map<String, Figure> row8 = new HashMap<>();
        row8.put("a", new Rook(  false));
        row8.put("b", new Knight(false));
        row8.put("c", new Bishop(false));
        row8.put("d", new King(  false));
        row8.put("e", new Queen( false));
        row8.put("f", new Bishop(false));
        row8.put("g", new Knight(false));
        row8.put("h", new Rook(  false));

       board.put("1", row1);
       board.put("2", row2);
        for (int i = 3; i < 7; i++) {
            board.put(i+"", new HashMap<String, Figure>());
        }
        board.put("7", row7);
        board.put("8", row8);

        return new BoardState(board);
    }


}
