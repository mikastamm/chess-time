package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.PlayerInfo;
import com.mikastamm.chesstime.Game.UserInfo;
import com.mikastamm.chesstime.Game.UserManager;
import com.mikastamm.chesstime.R;

import java.util.Map;

public class GamesAdapter extends BaseAdapter {
    public Game[] games;
    private Context context;

    public GamesAdapter(Context context , Game[] games)
    {
        this.context = context;
        this.games = games;
    }

    @Override
    public int getCount() {
        return games.length;
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
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_game, parent, false);
        }


        UserInfo oppenent = ChessTimeApplication.userManager.getPlayer().equals(games[position].playerBlack) ? games[position].playerWhite : games[position].playerBlack;

        TextView leftTextView = (TextView) convertView.findViewById(R.id.txt_name_opponent);
        TextView rightTextView = (TextView) convertView.findViewById(R.id.txt_elo_opponent);
        TextView centreTextView = (TextView) convertView.findViewById(R.id.txt_game_time);

        if (leftTextView != null) {
            leftTextView.setText(oppenent.name);
        }
        if (rightTextView != null) {
            rightTextView.setText("Elo: " + oppenent.elo);
        }
        if (centreTextView != null) {
            centreTextView.setText("1 Stunde, 2 Minuten");
        }

        return convertView;
    }
}
