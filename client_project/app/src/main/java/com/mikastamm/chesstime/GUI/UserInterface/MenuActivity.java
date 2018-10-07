package com.mikastamm.chesstime.GUI.UserInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikastamm.chesstime.R;

public class MenuActivity extends AppCompatActivity implements MenuView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
}
