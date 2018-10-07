package com.mikastamm.chesstime.GUI.PresentationLogic;

import com.mikastamm.chesstime.GUI.UserInterface.MenuView;

public interface IMenuPresenter {
    void setView(MenuView view);
    void onResume();
    void onCreate();
    void onDestroy();
    void onStop();
    void onStart();
    void findGame();
}
