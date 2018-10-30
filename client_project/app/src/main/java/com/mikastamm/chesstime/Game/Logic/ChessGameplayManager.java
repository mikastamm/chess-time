package com.mikastamm.chesstime.Game.Logic;

import android.graphics.Point;
import android.util.ArrayMap;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Board.HighlightedFieldType;
import com.mikastamm.chesstime.Game.Board.MoveValidator;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Figures.King;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.UserInfo;
import com.mikastamm.chesstime.Game.UserManager;
import java.util.List;
import java.util.Map;

public class ChessGameplayManager implements GameplayManager {
    private Game game;
    private Point selectedField;

    @Override
    public void setSelectedField(Point field) {
        selectedField = field;
    }

    @Override
    public void clearSelectedField() {
        selectedField = null;
    }

    @Override
    public Map<Point, HighlightedFieldType> getHighlightedFields(Point forField, boolean isIssueingPlayerWhite) {
        MoveValidator val = new MoveValidator(game);

        Map<Point, HighlightedFieldType> highlightedFields = new ArrayMap<>();
        Figure figure = game.boardState.board[forField.y][forField.x];
        if(isIssueingPlayerWhite == game.isWhitesTurn && figure != null && figure.isWhite == isIssueingPlayerWhite)//Only highlight any fields if its the players turn and only for his figures
        {
            return val.getHighlightedFieldsOfFigure(figure, forField);
        }
        return highlightedFields;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Figure getSelectedFigure() {
        if(selectedField == null)
            return null;

        return game.boardState.board[selectedField.y][selectedField.x];
    }

    @Override
    public Point getSelectedField() {
        return new Point(selectedField);
    }


    @Override
    public void moveFigure(Point from, Point to, UserInfo issuingUser) {
        Figure toFigure = game.boardState.getFigure(to);
        game.boardState.board[to.y][to.x] = game.boardState.getFigure(from);
        game.boardState.board[from.y][from.x] = null;
        game.isWhitesTurn = !game.isWhitesTurn;

        //TODO: Remove when multiplayer works. This just ends the non player users turn after some time
        if(!game.isWhitesTurn)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(1500);
                    }
                    catch (Exception ignored){}
                    game.isWhitesTurn = true;
                }
            }).start();
        }

        //Check for win & notify the Change listener
        if(toFigure.getClass() == King.class)
            game.notifyGameOver(issuingUser.equals(UserManager.getPlayer()));

        game.boardState.notifyBoardStateChanged();
        ChessTimeApplication.gamesManager.saveGames();
    }

    @Override
    public void requestRemis() {

    }

}
