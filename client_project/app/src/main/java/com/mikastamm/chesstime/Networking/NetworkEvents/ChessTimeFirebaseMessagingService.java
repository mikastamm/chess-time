package com.mikastamm.chesstime.Networking.NetworkEvents;

import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.mikastamm.chesstime.Game.PersistenceManager;
import com.mikastamm.chesstime.Networking.ServerCommunication.ServerCommunicator;

public class ChessTimeFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onNewToken(String token){
        super.onNewToken(token);
        PersistenceManager.storeFirebaseId(token, this);
        ServerCommunicator.getInstance().updateFirebaseToken("", token);
        Log.i("ChessTime", "New FirebaseToken:"+token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
    Log.d("ChessTime", "FCM Received");

    }
}
