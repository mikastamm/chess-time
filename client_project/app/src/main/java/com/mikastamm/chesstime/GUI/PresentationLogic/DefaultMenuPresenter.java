package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.content.Context;

import com.mikastamm.chesstime.GUI.UserInterface.MenuView;

public class DefaultMenuPresenter implements IMenuPresenter {

    Context viewContext;
    MenuView view;

    //Move to Model
  /*  BroadcastReceiver serverCommunicatorReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           String messageType = intent.getStringExtra(NetworkConstants.BroadcastReceiverMessageTypeExtraId);
           switch (messageType)
           {
               case NetworkConstants.BroadcastReceiverMessageTypeSearchStarted:
                   //display search started
                   break;
               case NetworkConstants.BroadcastReceiverMessageTypeSearchFailed:
                   //display search failed
                   break;
           }
        }
    };*/

    @Override
    public void setView(MenuView view) {
    this.view = view;
    viewContext = view.getContext();
    }

    @Override
    public void onStart() {
        //Move to model
        //viewContext.registerReceiver(serverCommunicatorReceiver, new IntentFilter(NetworkConstants.BroadcastReceiverFilter));
    }


    @Override
    public void onResume() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop() {
        //Move to model
        //viewContext.unregisterReceiver(serverCommunicatorReceiver);
    }

    @Override
    public void findGame() {
    }
}
