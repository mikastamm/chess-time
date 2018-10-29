package com.mikastamm.chesstime.Networking.NetworkEvents;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class NetworkEventDispatcher {
    public static final String KIND_RECEIVED_TURN = "oppenent_turn";
    private static NetworkEventDispatcher instance;
    public static NetworkEventDispatcher getInstance(){
        if(instance == null)
            instance = new NetworkEventDispatcher();

        return instance;
    }

    private NetworkEventDispatcher(){}

    public List<NetworkEventListener> eventListeners = new ArrayList<>();
    public void notifyEventReceived(String msg){
       Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson( msg, JsonObject.class);
        String kind = jsonObject.get("kind").getAsString();
        if(kind.equals(KIND_RECEIVED_TURN))
        {
            ReceivedMove move = gson.fromJson(msg, ReceivedMove.class);
            notifyMoveReceived(move.from, move.to, move.game_id);
        }
    }

    private void notifyMoveReceived(String from, String to, String gameId)
    {
        for(NetworkEventListener l : eventListeners)
        {
            l.onMoveReceived(from, to, gameId);
        }
    }

}
