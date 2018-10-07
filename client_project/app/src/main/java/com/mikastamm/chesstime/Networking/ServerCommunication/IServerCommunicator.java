package com.mikastamm.chesstime.Networking.ServerCommunication;

public interface IServerCommunicator {
    void findGame(String passwordToken);
    void updateFirebaseToken(String passwordToken, String firebaseToken);
}
