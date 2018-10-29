package com.mikastamm.chesstime.Networking.NetworkEvents;

public interface NetworkEventListener {
    void onMoveReceived(String from, String to, String gameId);
}
