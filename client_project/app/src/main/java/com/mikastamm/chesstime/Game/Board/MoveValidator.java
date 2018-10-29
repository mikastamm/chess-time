package com.mikastamm.chesstime.Game.Board;

import android.graphics.Point;

import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Figures.FigureMovePattern;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveValidator {

    private Game game;

    public MoveValidator(Game game)
    {
        this.game = game;
    }

    public boolean isMoveValid(Point fromField, Point toField, UserInfo issueingUser){
        boolean valid = true;
        return true; //TODO: Remove. This is just for testing
//        Figure fromFigure = game.boardState.getFigure(fromField);
//        Figure toFigure = game.boardState.getFigure(toField);
//
//        //Is there a figure on the from-field?
//        valid &= fromFigure != null;
//
//        //Was the move Issued by the Player whose turn it is?
//        valid &= game.getPlayerWhoseTurnItIs().equals(issueingUser);
//
//        //Does the fromFigure belong to the player whose turn it is?
//        if(valid)
//        valid &= game.isWhitesTurn == fromFigure.isWhite;
//
//        //Is the toField empty or an enemy figure?
//        boolean isTargetEnemyFigure = toFigure.isWhite != fromFigure.isWhite;
//        valid &= (toFigure == null || isTargetEnemyFigure);
//
//
//
//        return valid;
    }

    public List<Point> getMoveLocationsOfFigure(Figure figure, Point position)
    {
        if(figure == null)
            return new ArrayList<>();

        List<Point> points = new ArrayList<>();
        FigureMovePattern[] movePatterns = figure.getValidMovePositions();
        for (FigureMovePattern p: movePatterns) {
            points.addAll(getMovePositionsFromMovePattern(p, position));

            boolean hasToJump = false;
            for(Point point : getMovePositionsFromMovePattern(p, position))
            {

                Figure figureAtPoint = game.boardState.board[point.y][point.x];

                if((figureAtPoint == null || figureAtPoint.isWhite != figure.isWhite)
                        && (!hasToJump || p.canJump))
                {
                    points.add(point);
                }

                if(figureAtPoint != null){
                    hasToJump = true;
                }
            }
        }
        return points;
    }

    public Map<Point, HighlightedFieldType> getHighlightedFieldsOfFigure(Figure figure, Point position)
    {
        if(figure == null)
            return new HashMap<>();

        Map<Point, HighlightedFieldType> fields = new HashMap<>();
        FigureMovePattern[] movePatterns = figure.getValidMovePositions();
        for (FigureMovePattern p: movePatterns) {
            boolean hasToJump = false;
            for(Point point : getMovePositionsFromMovePattern(p, position))
            {
                Figure figureAtPoint = game.boardState.board[point.y][point.x];
                HighlightedField result = null;

                if(!hasToJump || p.canJump) {
                    if (figureAtPoint == null) {
                        result = new HighlightedField(point, HighlightedFieldType.MOVE);
                    }

                    if(figureAtPoint != null && figureAtPoint.isWhite != figure.isWhite)
                    {
                        result = new HighlightedField(point, HighlightedFieldType.CAPTURE);
                    }

                    if (figureAtPoint != null) {
                        hasToJump = true;
                    }
                }

                if(result!=null)
                    fields.put(result.field, result.type);
                else
                    fields.put(point, HighlightedFieldType.CANT_MOVE);
            }
        }
        return fields;
    }

    private List<Point> getMovePositionsFromMovePattern(FigureMovePattern pattern, Point position){
        position = new Point(position);
        List<Point> points = new ArrayList<>();
        if(!pattern.isInfinite)
        {
            if(position.x + pattern.target.x < 8 && position.x + pattern.target.x >= 0 && position.y + pattern.target.y < 8 && position.y + pattern.target.y >= 0)
                points.add(new Point(pattern.target.x + position.x, pattern.target.y + position.y));
        }
        else{
            while (true)
            {
                position.x += pattern.target.x;
                position.y += pattern.target.y;

                if(position.x < 8 && position.x >= 0 && position.y < 8 && position.y >= 0)
                    points.add(new Point(position));
                else
                    break;
            }
        }
        return points;
    }



}
