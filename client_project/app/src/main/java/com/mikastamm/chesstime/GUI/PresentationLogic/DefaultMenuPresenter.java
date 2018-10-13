package com.mikastamm.chesstime.GUI.PresentationLogic;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.mikastamm.chesstime.GUI.UserInterface.MenuView;
import com.mikastamm.chesstime.Game.PersistenceManager;
import com.mikastamm.chesstime.Networking.ServerCommunication.ServerCommunicator;

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

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                    String newToken = instanceIdResult.getToken();
                    Log.e("newToken", newToken);
                    PersistenceManager.storeFirebaseId(newToken, viewContext);
            }
        });

        String firebaseToken = PersistenceManager.getFirebaseId(viewContext);
        ServerCommunicator.getInstance().updateFirebaseToken("", FirebaseInstanceId.getInstance().getToken());
        Log.i("ChessTime", "FirebaseToken:"+firebaseToken);
    }
}
