package com.mikastamm.chesstime.GUI.UserInterface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
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
import com.mikastamm.chesstime.Game.Board.HighlightedFieldType;
import com.mikastamm.chesstime.Game.Game;
import com.mikastamm.chesstime.Game.Logic.GameplayManager;
import com.mikastamm.chesstime.Game.Logic.GameplayManagerFactory;
import com.mikastamm.chesstime.Game.Logic.GamesManager;
import com.mikastamm.chesstime.R;

import java.util.Map;


public class BoardFragment extends Fragment implements BoardView {

    private BoardPresenter boardPresenter;
    private BoardAdapter boardAdapter;
    private Game game;

    @Override
    public void notifyBoardStateChanged() {
        boardAdapter.notifyDataSetChanged();
    }


    @Override
    public void setHighlightedFields(Map<Point, HighlightedFieldType> highlightedFields){
        boardAdapter.setHighlightedFields(highlightedFields);
        boardAdapter.notifyDataSetChanged();
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void notifyGameOver(boolean winner) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("You " + (winner ? "Won!" : "Lost!"))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       Intent intent = new Intent(getContext(), MenuActivity.class);
                       startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public Map<Point, HighlightedFieldType> getHighlightedFields() {
        return boardAdapter.getHighlightedFields();
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

        boardAdapter = new BoardAdapter(getContext(),game, clickListener);
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
