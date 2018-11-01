package com.mikastamm.chesstime.Game;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.mikastamm.chesstime.Game.Figures.Bishop;
import com.mikastamm.chesstime.Game.Figures.Figure;
import com.mikastamm.chesstime.Game.Figures.King;
import com.mikastamm.chesstime.Game.Figures.Knight;
import com.mikastamm.chesstime.Game.Figures.Pawn;
import com.mikastamm.chesstime.Game.Figures.Queen;
import com.mikastamm.chesstime.Game.Figures.Rook;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;

public class PersistenceManager {

    private static final String firebaseIdKey ="firebase_id";
    private static final String gamesKey ="games";
    private static final String playerNameKey = "playerName";


    public static void storeFirebaseId(String id, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(firebaseIdKey, id);
        editor.apply();
    }

    public static String getFirebaseId(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(firebaseIdKey, null);
    }

    public static void storeGames(Map<String, Game> games, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        Object[] objArray = games.values().toArray();
        Game[] gamesArray = Arrays.copyOf(objArray, objArray.length, Game[].class);

        //Custom serialization for Figures to Serialize its Type aswell (eg. Pawn instead of Figure)
        JsonSerializer<Figure> figureSerializer = new JsonSerializer<Figure>() {
            @Override
            public JsonElement serialize(Figure src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject json =new JsonObject();
                json.addProperty("isWhite", src.isWhite);
                json.addProperty("FigureType", src.getClass().getSimpleName());
                return json;
            }
        };

        Gson gson = new GsonBuilder().registerTypeAdapter(Figure.class, figureSerializer ).create();
        editor.putString(gamesKey, gson.toJson(gamesArray));

        editor.apply();
    }

    public static Map<String, Game> getGames(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        JsonDeserializer<Figure> figureDeserializer = new JsonDeserializer<Figure>() {
            @Override
            public Figure deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Figure figure;
                JsonObject object = json.getAsJsonObject();
                String FigureName = object.get("FigureType").getAsString();
                boolean isWhite = object.get("isWhite").getAsBoolean();

                if(FigureName.equals(Pawn.class.getSimpleName()))
                {
                    figure = new Pawn(isWhite);
                }
                else if(FigureName.equals(Bishop.class.getSimpleName()))
                {
                    figure = new Bishop(isWhite);
                }
                else if(FigureName.equals(King.class.getSimpleName()))
                {
                    figure = new King(isWhite);
                }
                else if(FigureName.equals(Knight.class.getSimpleName()))
                {
                    figure = new Knight(isWhite);
                }
                else if(FigureName.equals(Queen.class.getSimpleName()))
                {
                    figure = new Queen(isWhite);
                }
                else if(FigureName.equals(Rook.class.getSimpleName()))
                {
                    figure = new Rook(isWhite);
                }
                else
                    figure = null;

                return figure;
            }
        };

        Gson gson = new GsonBuilder().registerTypeAdapter(Figure.class, figureDeserializer).create();
        String json = preferences.getString(gamesKey, null);
        Type type = new TypeToken<Game[]>(){}.getType();
        if(json != null) {
            Game[] res = gson.fromJson(json, Game[].class);
            Map<String, Game> mapRes = new HashMap<>();
            for (Game g: res
                 ) {
                mapRes.put(g.id, g);
            }
            return mapRes;
        }
        else
            return null;
    }

    public static void storeUserName(Context context, String playerName) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(playerNameKey, playerName);
        editor.apply();
    }

    //Currently unused
//    public static String serializeGame(Game game){
//        Gson gson = new Gson();
//        return gson.toJson(game);
//    }
//
//    public static Game deserializeGame(String game){
//        Gson gson = new Gson();
//        return gson.fromJson(game, Game.class);
//    }
}
