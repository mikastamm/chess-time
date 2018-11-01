package com.mikastamm.chesstime.Game;

import android.content.Context;

public class UserManager {
    Context context;

    public UserManager(Context context){
        context = context;
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

    public static boolean isPlayerWhite(Game game) {
        return game.playerWhite.equals(getPlayer());
    }

    public void registerUser(Context context, String playerName){
        PersistenceManager.storeUserName(context,playerName);
    }
}
