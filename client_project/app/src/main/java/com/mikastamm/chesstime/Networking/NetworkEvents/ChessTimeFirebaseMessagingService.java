package com.mikastamm.chesstime.Networking.NetworkEvents;

import com.mikastamm.chesstime.Networking.ServerCommunication.ServerCommunicator;

public class ChessTimeFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onNewToken(String token){
        super.onNewToken(token);
        ServerCommunicator.getInstance().updateFirebaseToken("", token);
    }
}
