package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mikastamm.chesstime.GUI.UserInterface.SquareImageButton;
import com.mikastamm.chesstime.Game.Board.BoardState;
import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Board.HighlightedFieldType;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.R;

import java.util.HashMap;
import java.util.Map;

public class BoardAdapter extends BaseAdapter {
    private Game game;
    private Context context;
    private View.OnClickListener clickListener;
    private Map<Point, HighlightedFieldType> highlightedFields = new HashMap<>();

    public BoardAdapter(Context context, Game game, View.OnClickListener clickListener)
    {
        this.game = game;
        this.context = context;
        this.clickListener = clickListener;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }
    public void setHighlightedFields(Map<Point, HighlightedFieldType> highlightedFields)
    {
        this.highlightedFields = highlightedFields;
    }

    @Override
    public int getCount() {
        return 64;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SquareImageButton btn;
        int y = (int) Math.floor(position / 8);
        int x = position % 8;

        if(convertView == null) {
            btn = new SquareImageButton(parent.getContext());
            btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
            int padding = 15;
            btn.setPadding(padding, padding, padding, padding);
        }
        else{
            btn = (SquareImageButton)convertView;
        }

        //Write the buttons Position on the board to the button
        btn.setTag(new Point(x, y));
        btn.setOnClickListener(clickListener);

        //Set Figure Drawable
        if(game.boardState.board[y][x] != null)
            btn.setImageResource(game.boardState.board[y][x].getDrawableResourceId());
        else
            btn.setImageResource(android.R.color.transparent);

        HighlightedFieldType highlightedFieldType = highlightedFields.get(new Point(x,y));
        //Set field Background
        if((x + y % 2) % 2 == 0)
        {
            if(highlightedFieldType == null)
                btn.setBackgroundResource(R.color.colorFieldWhite);
            else {

                switch (highlightedFieldType) {
                    case MOVE:
                        btn.setBackgroundResource(R.drawable.field_w_move);
                        break;
                    case CAPTURE:
                        btn.setBackgroundResource(R.drawable.field_w_capture);
                        break;
                    case SELECTED:
                        btn.setBackgroundResource(R.drawable.field_w_selected);
                        break;
                    case CANT_MOVE:
                        btn.setBackgroundResource(R.drawable.field_w_cant_move);
                        break;
                }
            }
        }
        else
        {
            if(highlightedFieldType == null)
                btn.setBackgroundResource(R.color.colorFieldBlack);
            else {
                switch (highlightedFieldType) {
                    case MOVE:
                        btn.setBackgroundResource(R.drawable.field_b_move);
                        break;
                    case CAPTURE:
                        btn.setBackgroundResource(R.drawable.field_b_capture);
                        break;
                    case SELECTED:
                        btn.setBackgroundResource(R.drawable.field_b_selected);
                        break;
                    case CANT_MOVE:
                        btn.setBackgroundResource(R.drawable.field_b_cant_move);
                        break;
                }
            }

        }

        return btn;
    }



}
