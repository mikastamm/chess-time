package com.mikastamm.chesstime.Game;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private Context context;


    private List<RegistrationListener> listeners = new ArrayList<>();
    public void addListener(RegistrationListener listener)
    {
        listeners.add(listener);
    }

    public void notifyRegistrationSuccess(String pwtoken){
        for (RegistrationListener l:
             listeners) {
            l.onRegistrationSuccess(pwtoken);
        }
    }

    public void notifyRegistrationFailure(){
        for (RegistrationListener l:
                listeners) {
            l.onRegistrationFailure();
        }
    }

    public UserManager(Context context){
        this.context = context;
    }
    //Return the PlayerInfo of the user using the app
    public PlayerInfo getPlayer() {
        //TODO: Replace with real player management
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.name = PersistenceManager.getPlayerName(context);
        playerInfo.elo = "1100";
        playerInfo.passwordToken = PersistenceManager.getPlayerToken(context);;
        return playerInfo;
    }

    public String getPlayerName(){
        return PersistenceManager.getPlayerName(context);
    }
    public String getPlayerToken(){
        return PersistenceManager.getPlayerToken(context);
    }
    public void setPlayerToken(String playerToken){
        PersistenceManager.storePlayerToken(context,playerToken);
    }

    public boolean isPlayerWhite(Game game) {
        return game.playerWhite.equals(getPlayer());
    }

    public void saveUser(Context context, String playerName){

        PersistenceManager.storeUserName(context,playerName);
    }
}
