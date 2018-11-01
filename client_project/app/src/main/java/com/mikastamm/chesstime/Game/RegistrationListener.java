package com.mikastamm.chesstime.Game;

public interface RegistrationListener {
    void onRegistrationSuccess(String pwtoken);
    void onRegistrationFailure();
}
