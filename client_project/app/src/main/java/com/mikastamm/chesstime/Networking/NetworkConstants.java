package com.mikastamm.chesstime.Networking;

public class NetworkConstants {
    public static final String ServerBaseUrl = "http://127.0.0.1:8080";
    public static final String FindGameRestPath = "/findGame";
    public static final String SetFirebaseTokenRestPath = "/setFirebaseToken";

    //Broadcast filters
    public static final String BroadcastReceiverFilter = "server_communicator_broadcast_filter";
    public static final String BroadcastReceiverMessageTypeExtraId = "message_type";
    public static final String BroadcastReceiverMessageTypeSearchStarted = "search_started";
    public static final String BroadcastReceiverMessageTypeSearchFailed = "search_failed";



}
