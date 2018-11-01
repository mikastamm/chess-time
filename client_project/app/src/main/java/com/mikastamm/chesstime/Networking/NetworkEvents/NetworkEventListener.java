package com.mikastamm.chesstime.Networking.NetworkEvents;

public interface NetworkEventListener {
    void onMoveReceived(String from, String to, String gameId);
    void onGameFound(GameFoundData data);
    void onRegisterResponse(String password_token);
}
