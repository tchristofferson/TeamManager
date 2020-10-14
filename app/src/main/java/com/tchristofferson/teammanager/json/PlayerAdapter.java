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
import com.tchristofferson.teammanager.models.AtBat;
import com.tchristofferson.teammanager.models.Player;

import java.lang.reflect.Type;
import java.util.List;

public class PlayerAdapter implements JsonSerializer<Player>, JsonDeserializer<Player> {
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String AT_BATS = "atBats";

    @Override
    public JsonElement serialize(Player src, Type typeOfSrc, JsonSerializationContext context) {
        Gson gson = TeamManagerApplication.getGson();
        JsonObject obj = new JsonObject();
        obj.addProperty(NAME, src.getName());
        obj.addProperty(PHONE, src.getPhone());

        Type type = new TypeToken<List<AtBat>>(){}.getType();
        obj.addProperty(AT_BATS, gson.toJson(src.getAtBats(), type));

        return obj;
    }

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = TeamManagerApplication.getGson();
        JsonObject obj = json.getAsJsonObject();
        String name = obj.get(NAME).getAsString();
        String phone = obj.get(PHONE).getAsString();
        String atBatsString = obj.get(AT_BATS).getAsString();

        Type type = new TypeToken<List<AtBat>>(){}.getType();
        List<AtBat> atBats = gson.fromJson(atBatsString, type);

        return new Player(name, phone, atBats);
    }
}
