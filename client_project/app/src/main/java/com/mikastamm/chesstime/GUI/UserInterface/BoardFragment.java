package com.mikastamm.chesstime.GUI.UserInterface;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.GUI.PresentationLogic.BoardPresenter;
import com.mikastamm.chesstime.GUI.PresentationLogic.DefaultBoardPresenter;
import com.mikastamm.chesstime.GUI.PresentationLogic.BoardAdapter;
import com.mikastamm.chesstime.Game.Board.BoardState;
import com.mikastamm.chesstime.Game.Board.HighlightedField;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.Logic.GameplayManager;
import com.mikastamm.chesstime.Game.Logic.GameplayManagerFactory;
import com.mikastamm.chesstime.Game.Logic.GamesManager;
import com.mikastamm.chesstime.R;


public class BoardFragment extends Fragment implements BoardView {

    private Game game;
    private GameplayManager gameplayManager;
    private ImageButton[][] fields = new ImageButton[8][];
    private BoardPresenter boardPresenter;
    private BoardAdapter boardAdapter;

    public BoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void notifyBoardStateChanged() {
        boardAdapter.notifyDataSetChanged();
    }

    @Override
    public void setBoardEnabled(boolean enabled) {
        boardAdapter.buttonsEnabled = enabled;
        notifyBoardStateChanged();
    }

    @Override
    public void setHighlightedFields(HighlightedField[] highlightedFields){
        boardAdapter.setHighlightedFields(highlightedFields);
        boardAdapter.notifyDataSetChanged();
    }


    //Methods defined in Fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pass the gameId on to the Presenter
        String gameId = getArguments().getString(BoardView.GAME_ID_BUNDLE_KEY);
        boardPresenter = new DefaultBoardPresenter(this, gameId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        GridView boardview = view.findViewById(R.id.gvChessboard);

        //Click listener for every button on the Board
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Point btnPos = (Point) v.getTag();
                boardPresenter.onFieldClicked(btnPos);
            }
        };

        boardAdapter = new BoardAdapter(getContext(),BoardState.getStartingBoardState(), clickListener);
        boardview.setAdapter(boardAdapter);


        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

  
}
