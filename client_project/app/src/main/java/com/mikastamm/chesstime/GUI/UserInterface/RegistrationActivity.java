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
import android.widget.Toast;

import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.UserManager;
import com.mikastamm.chesstime.Networking.NetworkEvents.GameFoundData;
import com.mikastamm.chesstime.Networking.NetworkEvents.NetworkEventDispatcher;
import com.mikastamm.chesstime.Networking.NetworkEvents.NetworkEventListener;
import com.mikastamm.chesstime.R;

public class RegistrationActivity extends AppCompatActivity {
    Context context;
    boolean locked = false; //TODO: for testing purposes false!
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final Button button = findViewById(R.id.btn_register);
        setupNetworkEventListener();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!locked){
                    registerUser();
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Toast toast = Toast.makeText(RegistrationActivity.this, "Name bereits vergeben", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private void registerUser() {
        EditText playerNameEdit = findViewById(R.id.editText_registration_name);
        String playerName = playerNameEdit.getText().toString();
        UserManager userManager = ChessTimeApplication.userManager;
        userManager.registerUser(this,playerName);
    }
    private void setupNetworkEventListener(){
        NetworkEventDispatcher.getInstance().eventListeners.add(new NetworkEventListener() {

            @Override
            public void onRegisterResponse(String token) {
                if(!token.isEmpty()){
                    locked = false;
                    ChessTimeApplication.userManager.setPlayerToken(token);
                }
            }
            @Override
            public void onMoveReceived(String from, String to, String gameId) {

            }

            @Override
            public void onGameFound(GameFoundData data) {

            }

        });
    }
}
