package com.mikastamm.chesstime.GUI.UserInterface;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.UserInfo;
import com.mikastamm.chesstime.R;

public class GameActivity extends AppCompatActivity {
    private Game game;
    private UserInfoFragment whiteUserInfoFragment;
    private UserInfoFragment blackUserInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String gameId;

        ////Adds a test game to the gamesmanager
        //ChessTimeApplication.gamesManager.addGame(Game.getTestGame(), this);

        //Get the GameId of the Game this GameActivity represents, which was passed as a parameter in a Bundle when the Activity was created
        Bundle passedParameters = getIntent().getExtras();

        gameId = passedParameters.getString(BoardView.GAME_ID_BUNDLE_KEY);
        initBoardFragment(gameId);

        whiteUserInfoFragment = initUserInfoFragment(gameId, R.id.blackUserInfoPlaceholder, true);
        blackUserInfoFragment = initUserInfoFragment(gameId, R.id.whiteUserInfoPlaceholder, false);

    }

    private void initBoardFragment(String gameId){
        //Replace the Placeholder view with a new Instance of the BoardFragment
        //This is done to be able to pass the GameplayManager as a Parameter to the Fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //Pass the GameId as a Parameter to the Created Fragment
        BoardFragment fragment = new BoardFragment();
        Bundle boardFragmentParameters = new Bundle();
        boardFragmentParameters.putString(BoardView.GAME_ID_BUNDLE_KEY, gameId);
        fragment.setArguments(boardFragmentParameters);

        //Replace the Placeholder View with our new BoradFragment
        ft.replace(R.id.boardFragmentPlaceholder, fragment);
        ft.commit();
    }

    private UserInfoFragment initUserInfoFragment(String gameId, int placeholderId, boolean isWhite){
        //Replace the Placeholder view with a new Instance of the BoardFragment
        //This is done to be able to pass the GameplayManager as a Parameter to the Fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //Pass the GameId as a Parameter to the Created Fragment
        UserInfoFragment fragment = new UserInfoFragment();
        Bundle userInfoParameters = new Bundle();
        userInfoParameters.putString(UserInfoView.GAME_ID_BUNDLE_KEY, gameId);
        userInfoParameters.putBoolean(UserInfoView.IS_WHITE_BUNDLE_KEY, isWhite);
        fragment.setArguments(userInfoParameters);

        //Replace the Placeholder View with our new BoradFragment
        ft.replace(placeholderId, fragment);
        ft.commit();

        return fragment;
    }
}
