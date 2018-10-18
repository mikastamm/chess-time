package com.mikastamm.chesstime.Game.Board;

import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.UserInfo;

public class MoveValidator {
    public boolean isMoveValid(String fromField, String toField, Game game, UserInfo issueingUser){
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

}
