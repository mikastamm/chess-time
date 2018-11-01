package com.mikastamm.chesstime.GUI.UserInterface;

import android.support.v4.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.GUI.PresentationLogic.BoardAdapter;
import com.mikastamm.chesstime.GUI.PresentationLogic.DefaultBoardPresenter;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.R;

public class UserInfoFragment extends Fragment implements UserInfoView {
    private Game game;
    private boolean isWhite;

    @Override
    public void setGame(Game game) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pass the gameId on to the Presenter
        String gameId = getArguments().getString(UserInfoView.GAME_ID_BUNDLE_KEY);
        game = ChessTimeApplication.gamesManager.getGame(gameId);
        //Is this the UserInfoFragment for the white or black player (top or botton)
        isWhite = getArguments().getBoolean(UserInfoView.IS_WHITE_BUNDLE_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        TextView txtName = view.findViewById(R.id.txt_player_name);
        TextView txtElo = view.findViewById(R.id.txt_player_elo);

        txtName.setText(isWhite ? game.playerWhite.name : game.playerBlack.name);
        txtElo.setText(isWhite ? game.playerWhite.elo : game.playerBlack.elo);

        return view;
    }
}
