package com.mikastamm.chesstime.Game.Board;

import android.graphics.Point;

public class BoardUtil {
    public static Point getPointFromFieldName(String fieldName)
    {
        char[] c = fieldName.toCharArray();
        Point p = new Point(Integer.parseInt(c[1]+""), charToAlphabetPosition(c[0]));
        return p;
    }

    public static String getFieldNameFromPoint(Point point){
        return numberToCharAtAlphabetPosition(point.y) + point.x + "";
    }

    public static int charToAlphabetPosition(char c){
        c = Character.toLowerCase(c);
        return ((int)c) - ((int)'a') + 1;
    }

    public static char numberToCharAtAlphabetPosition(int pos){
        return ((char)((int)'a'+pos-1));
    }
}
