package chesstimeserver.game.board;

import java.awt.Point;

public class BoardUtil {
    public static Point getPointFromFieldName(String fieldName)
    {
        String x, y;
        x = fieldName.substring(0, fieldName.indexOf(","));
        y = fieldName.substring(fieldName.indexOf(",")+1);
        Point p = new Point(Integer.parseInt(x), Integer.parseInt(y));
        return p;
    }

    public static String getFieldNameFromPoint(Point point){
        String result = point.x + "," +point.y;
        return result;   
       }

    public static int charToAlphabetPosition(char c){
        c = Character.toLowerCase(c);
        return ((int)c) - ((int)'a') + 1;
    }

    public static char numberToCharAtAlphabetPosition(int pos){
        return ((char)((int)'a'+pos-1));
    }
}