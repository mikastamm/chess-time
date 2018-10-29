package com.mikastamm.chesstime.GUI.UserInterface;

import com.mikastamm.chesstime.Game.Game;

public interface UserInfoView {
    String IS_WHITE_BUNDLE_KEY = "IS_WHITE";
    String GAME_ID_BUNDLE_KEY = "GameId";
    void setGame(Game game);

}
