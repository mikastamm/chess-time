package com.mikastamm.chesstime.Game;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static List<RegistrationListener> listeners = new ArrayList<>();
    public static void addListener(RegistrationListener listener)
    {
        listeners.add(listener);
    }

    public static void notifyRegistrationSuccess(String pwtoken){
        for (RegistrationListener l:
             listeners) {
            l.onRegistrationSuccess(pwtoken);
        }
    }

    public static void notifyRegistrationFailure(){
        for (RegistrationListener l:
                listeners) {
            l.onRegistrationFailure();
        }
    }

    //Return the PlayerInfo of the user using the app
    public static PlayerInfo getPlayer()
    {
        //TODO: Replace with real player management
        PlayerInfo playerInfo =  new PlayerInfo();
        playerInfo.name = "player";
        playerInfo.elo = "222";
        playerInfo.passwordToken = "";
        return playerInfo;
    }



    public static boolean isPlayerWhite(Game game)
    {
        return game.playerWhite.equals(getPlayer());
    }
}
