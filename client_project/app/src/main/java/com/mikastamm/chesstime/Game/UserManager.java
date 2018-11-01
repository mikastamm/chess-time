package com.mikastamm.chesstime.Game;

import android.content.Context;

public class UserManager {
    private Context context;

    public UserManager(Context context){
        this.context = context;
    }
    //Return the PlayerInfo of the user using the app
    public static PlayerInfo getPlayer() {
        //TODO: Replace with real player management
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.name = "player";
        playerInfo.elo = "222";
        playerInfo.passwordToken = "";
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

    public static boolean isPlayerWhite(Game game) {
        return game.playerWhite.equals(getPlayer());
    }

    public void registerUser(Context context, String playerName){

        PersistenceManager.storeUserName(context,playerName);
    }
}
