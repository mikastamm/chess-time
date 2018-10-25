package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.R;

public class GamesAdapter extends BaseAdapter {
    Context context;
    Game[] data;
    private static LayoutInflater inflater = null;

    public GamesAdapter(Context context, Game[] games) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = games;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
//TODO: implement real data
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.item_game, null);


            TextView leftTextView = (TextView) vi.findViewById(R.id.txt_name_opponent);
            TextView rightTextView = (TextView) vi.findViewById(R.id.txt_elo_opponent);
            TextView centreTextView = (TextView) vi.findViewById(R.id.txt_game_time);

            if (leftTextView != null) {
                leftTextView.setText(data[position].playerBlack.name);
            }
            if (rightTextView != null) {
                rightTextView.setText("Elo: " +Integer.toString(data[position].playerBlack.elo));
            }
            if (centreTextView != null) {
                centreTextView.setText("1 Stunde, 2 Minuten");
            }


        return vi;
    }
}

