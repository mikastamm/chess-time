package com.mikastamm.chesstime.Networking.NetworkEvents;

public class GameOverData {
    public GameOverReason reason;
    public enum GameOverReason{
        remis_consent,
        remis_stalemate,
        fifty_turns,
        repition,
        invalid_move
    }
}
