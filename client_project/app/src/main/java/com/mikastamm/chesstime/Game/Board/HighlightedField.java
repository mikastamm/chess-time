package com.mikastamm.chesstime.Game.Board;

import android.graphics.Point;

public class HighlightedField {
    public Point field;
    public HighlightedFieldType type;

    public HighlightedField(){}
    public HighlightedField(Point field, HighlightedFieldType type)
    {
        this.field = field;
        this.type = type;
    }
}
