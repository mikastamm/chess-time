package com.mikastamm.chesstime.GUI.UserInterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikastamm.chesstime.Game.RegistrationListener;
import com.mikastamm.chesstime.Networking.NetworkEvents.GameFoundData;
import com.mikastamm.chesstime.Networking.NetworkEvents.NetworkEventDispatcher;
import com.mikastamm.chesstime.Networking.NetworkEvents.NetworkEventListener;
import com.mikastamm.chesstime.Networking.ServerCommunication.ServerCommunicator;
import com.mikastamm.chesstime.R;

import static com.mikastamm.chesstime.ChessTimeApplication.userManager;

public class RegistrationActivity extends AppCompatActivity {
    boolean locked = true; //TODO: for testing purposes false!
    String playerName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final Button button = findViewById(R.id.btn_register);
        setupNetworkEventListener();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        EditText playerNameEdit = findViewById(R.id.editText_registration_name);
        playerName = playerNameEdit.getText().toString();
        ServerCommunicator.getInstance().register(playerName);
    }
    private void setupNetworkEventListener(){
        userManager.addListener(new RegistrationListener() {
            @Override
            public void onRegistrationSuccess(String pwtoken) {
                locked = false;
                userManager.setPlayerToken(pwtoken);
                userManager.saveUser(RegistrationActivity.this,playerName);

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onRegistrationFailure() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(RegistrationActivity.this, "Name bereits vergeben", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

            }
        });
    }
}
