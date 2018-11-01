package com.mikastamm.chesstime.Networking.NetworkEvents;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mikastamm.chesstime.ChessTimeApplication;
import com.mikastamm.chesstime.Game.UserManager;

import java.util.ArrayList;
import java.util.List;

public class NetworkEventDispatcher {
    static final String KIND_RECEIVED_TURN = "oppenent_turn";
    static final String KIND_GAME_FOUND = "new_game";
    static final String KIND_REGISTRATION_SUCCESS = "registration_result";


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
       Log.i("NetworkEventDispatcher", "Deserializing:" + msg);
        JsonObject jsonObject = gson.fromJson( msg, JsonObject.class);
        String kind = jsonObject.get("kind").getAsString();
        if(kind.equals(KIND_RECEIVED_TURN))
        {
            MoveData move = gson.fromJson(msg, MoveData.class);
            Log.i("NetworkEventDispatcher","Received opponent turn!" + move.game_id);

            notifyMoveReceived(move.from, move.to, move.game_id);
        }
        else if(kind.equals(KIND_GAME_FOUND)) {
            GameFoundData data = gson.fromJson(msg, GameFoundData.class);
            Log.i("NetworkEventDispatcher", "Received new Game! " + data.game_id);

            notifyGameFound(data);
        }
        else if(kind.equals(KIND_REGISTRATION_SUCCESS))
        {
            RegisterData data = gson.fromJson(msg, RegisterData.class);
            Log.i("NetworkEventDispatcher","Received passwordtoken! " + data.password_token);
            if(data.password_token == null) {
                Log.i("NetworkEventDispatcher", "Username already in Use!! ");
                ChessTimeApplication.userManager.notifyRegistrationFailure();
            }
            else{
                ChessTimeApplication.userManager.notifyRegistrationSuccess(data.password_token);
            }

        }

    }


    private void notifyGameFound(GameFoundData data)
    {
        for(NetworkEventListener l : eventListeners)
        {
            l.onGameFound(data);
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
