package com.mikastamm.chesstime.Networking.ServerCommunication;

import android.os.AsyncTask;

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
