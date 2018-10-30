package com.mikastamm.chesstime.Game;

public class UserManager {

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
