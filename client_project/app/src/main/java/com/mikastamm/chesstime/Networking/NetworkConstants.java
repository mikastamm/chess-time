package com.mikastamm.chesstime.Networking;

public class NetworkConstants {
    public static final String ServerBaseUrl = "http://192.168.0.122:8080/chesstime-server/";
    public static final String FindGameRestPath = "/findGame";
    public static final String SetFirebaseTokenRestPath = "/updateFirebaseToken";
    public static final String RegisterUserRestPath = "/register";

    //Broadcast filters
    public static final String BroadcastReceiverFilter = "server_communicator_broadcast_filter";
    public static final String BroadcastReceiverMessageTypeExtraId = "message_type";
    public static final String BroadcastReceiverMessageTypeSearchStarted = "search_started";
    public static final String BroadcastReceiverMessageTypeSearchFailed = "search_failed";



}
