package com.mikastamm.chesstime.GUI.UserInterface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.UserManager;
import com.mikastamm.chesstime.R;

public class RegistrationActivity extends AppCompatActivity {
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final Button button = findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerUser();
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser() {
        EditText playerNameEdit = findViewById(R.id.editText_registration_name);
        String playerName = playerNameEdit.getText().toString();
        UserManager userManager = ChessTimeApplication.userManager;
        userManager.registerUser(this,playerName);
    }
}
