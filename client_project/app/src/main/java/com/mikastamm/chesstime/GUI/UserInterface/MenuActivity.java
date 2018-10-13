package com.mikastamm.chesstime.GUI.UserInterface;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mikastamm.chesstime.GUI.PresentationLogic.DefaultMenuPresenter;
import com.mikastamm.chesstime.GUI.PresentationLogic.IMenuPresenter;
import com.mikastamm.chesstime.R;

public class MenuActivity extends AppCompatActivity implements MenuView {

    private IMenuPresenter presenter = new DefaultMenuPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        presenter.setView(this);

        presenter.onCreate();
        //Bind Search button to presenter
        Button searchButton = findViewById(R.id.btnMenuActivitySearchGame);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.findGame();
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onStop(){
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.onStart();
    }



    @Override
    public Context getContext() {
        return this;
    }
}
