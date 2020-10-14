package com.tchristofferson.teammanager.json;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.tchristofferson.teammanager.TeamManagerApplication;
import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import java.lang.reflect.Type;
import java.util.List;

public class TeamAdapter implements JsonSerializer<Team>, JsonDeserializer<Team> {
    private static final String PLAYERS = "players";

    @Override
    public JsonElement serialize(Team src, Type typeOfSrc, JsonSerializationContext context) {
        Gson gson = TeamManagerApplication.getGson();
        JsonObject obj = new JsonObject();

        Type type = new TypeToken<List<Player>>(){}.getType();
        obj.addProperty(PLAYERS, gson.toJson(src.getPlayers(), type));

        return obj;
    }

    @Override
    public Team deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = TeamManagerApplication.getGson();
        JsonObject obj = json.getAsJsonObject();
        String playersString = obj.get(PLAYERS).getAsString();

        Type type = new TypeToken<List<Player>>(){}.getType();
        List<Player> players = gson.fromJson(playersString, type);

        return new Team(players);
    }
}
