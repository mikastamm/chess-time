package com.mikastamm.chesstime.Networking.ServerCommunication;

public class ServerCommunicator {
    private static IServerCommunicator instance;
    public static IServerCommunicator getInstance()
    {
        if(instance == null)
        {
            instance = new RestServerCommunicator();
        }
        return instance;
    }
}
