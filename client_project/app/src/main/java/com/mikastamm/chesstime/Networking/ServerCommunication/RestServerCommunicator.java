package com.mikastamm.chesstime.Networking.ServerCommunication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mikastamm.chesstime.Networking.NetworkConstants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

class RestServerCommunicator implements IServerCommunicator{
    @Override
    public void findGame(final String passwordToken) {
        //Networking cannot be done on UI Thread, so start a new Thread for the Request
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                sendFindGameRequest(passwordToken);
            }
        });
    }

    public void register(final String userName) {
        //Networking cannot be done on UI Thread, so start a new Thread for the Request
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                sendRegisterUserRequest(userName);
            }
        });
    }
    private void sendRegisterUserRequest(String userName) {
        URL requestUrl;
        try{
            requestUrl = new URL(NetworkConstants.ServerBaseUrl + NetworkConstants.RegisterUserRestPath);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("name", userName);
            connection.setRequestProperty("firebase_instance_id",  FirebaseInstanceId.getInstance().getToken());

            int responseCode = connection.getResponseCode();
            connection.disconnect();
            if(responseCode == 200)
            {
                //successfully started search
            }
            else
            {
                //search failed
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e)
        {
            //TODO: Handle expeption
            e.printStackTrace();
        }
    }

    @Override
    public void updateFirebaseToken(final String passwordToken, final String firebaseToken) {
        //Networking cannot be done on UI Thread, so start a new Thread for the Request
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                sendUpdateFireBaseToken(passwordToken, firebaseToken);
            }
        });

    }

    @Override
    public void sendMove(final String passwordToken,final String gameId,final String fromField,final String toField) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                doSendMove(passwordToken, gameId, fromField, toField);
            }
        });
    }

    public void doSendMove(final String passwordToken, final String gameId, final String fromField, final String toField) {
        URL requestUrl;
        try{
            requestUrl = new URL(NetworkConstants.ServerBaseUrl + NetworkConstants.SetFirebaseTokenRestPath);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("password_token", passwordToken);
            connection.setRequestProperty("game_id", gameId);
            connection.setRequestProperty("to", toField);
            connection.setRequestProperty("from", fromField);

            Map<String, List<String>> x = connection.getRequestProperties();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            if(responseCode == 200)
            {
                //successfully started search
            }
            else
            {
                //search failed
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e)
        {
            //TODO: Handle expeption
            e.printStackTrace();
        }
    }

    private void sendUpdateFireBaseToken(final String passwordToken, final String firebaseToken) {
        URL requestUrl;
        try{
            requestUrl = new URL(NetworkConstants.ServerBaseUrl + NetworkConstants.SetFirebaseTokenRestPath);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("password_token", passwordToken);
            connection.setRequestProperty("firebase_token", firebaseToken);
            Map<String, List<String>> x = connection.getRequestProperties();
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            if(responseCode == 200)
            {
                //successfully started search
            }
            else
            {
                //search failed
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e)
        {
            //TODO: Handle expeption
            e.printStackTrace();
        }


    }

    private void sendFindGameRequest(final String passwordToken){
        URL requestUrl;
        Log.i("feck","Find game request sent");
        try{
            requestUrl = new URL(NetworkConstants.ServerBaseUrl + NetworkConstants.FindGameRestPath);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("password_token", passwordToken);

            int responseCode = connection.getResponseCode();
            connection.disconnect();
            if(responseCode == 200)
            {
                //successfully started search
            }
            else
            {
                //search failed
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e)
        {
            //TODO: Handle expeption
            e.printStackTrace();
        }


    }
}
