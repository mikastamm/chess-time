package com.mikastamm.chesstime.GUI.UserInterface;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.R;

public class GameActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String gameId;

        ////Adds a test game to the gamesmanager
        //ChessTimeApplication.gamesManager.addGame(Game.getTestGame(), this);

        //Get the GameId of the Game this GameActivity represents, which was passed as a parameter in a Bundle when the Activity was created
        Bundle passedParameters = getIntent().getExtras();
        if(passedParameters != null)
        {
            gameId = passedParameters.getString("GameId");
        }
        //TODO: Remove
        //For debug only because we dont have a menu yet
        else{
            gameId = "testgame";
        }

        //Replace the Placeholder view with a new Instance of the BoardFragment
        //This is done to be able to pass the GameplayManager as a Parameter to the Fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //Pass the GameId as a Parameter to the Created Fragment
        BoardFragment fragment = new BoardFragment();
        Bundle boardFragmentParameters = new Bundle();
        boardFragmentParameters.putString(BoardView.GAME_ID_BUNDLE_KEY,gameId);
        fragment.setArguments(boardFragmentParameters);

        //Replace the Placeholder View with our new BoradFragment
        ft.replace(R.id.boardFragmentPlaceholder, fragment);
        ft.commit();
    }
}
