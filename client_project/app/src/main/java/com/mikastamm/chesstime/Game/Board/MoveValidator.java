package com.mikastamm.chesstime.Game.Board;

import android.graphics.Point;

import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Figures.FigureMovePattern;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator {

    private Game game;

    public MoveValidator(Game game)
    {
        this.game = game;
    }

    public boolean isMoveValid(String fromField, String toField, UserInfo issueingUser){
        boolean valid = true;
        Figure fromFigure = game.boardState.getFigure(fromField);
        Figure toFigure = game.boardState.getFigure(toField);

        //Is there a figure on the from-field?
        valid &= fromFigure != null;

        //Was the move Issued by the Player whose turn it is?
        valid &= game.getPlayerWhoseTurnItIs().equals(issueingUser);

        //Does the fromFigure belong to the player whose turn it is?
        valid &= game.isWhitesTurn == fromFigure.isWhite;

        //Is the toField empty or an enemy figure?
        boolean isTargetEnemyFigure = toFigure.isWhite != fromFigure.isWhite;
        valid &= (toFigure == null || isTargetEnemyFigure);



        return valid;
    }

    public List<Point> getMoveLocationsOfFigure(Figure figure, Point position)
    {
        if(figure == null)
            return new ArrayList<>();

        List<Point> points = new ArrayList<>();
        FigureMovePattern[] movePatterns = figure.getValidMovePositions();
        for (FigureMovePattern p: movePatterns) {
            points.addAll(getMovePositionsFromMovePattern(p, position));
        }
        return points;
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
