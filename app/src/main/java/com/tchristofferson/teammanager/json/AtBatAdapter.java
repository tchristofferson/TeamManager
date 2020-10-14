package com.tchristofferson.teammanager.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tchristofferson.teammanager.models.AtBat;

import java.lang.reflect.Type;

public class AtBatAdapter implements JsonSerializer<AtBat>, JsonDeserializer<AtBat> {
    //Keys used in json object
    private static final String STRIKES = "strikes";
    private static final String BALLS = "balls";
    private static final String RESULT = "result";

    @Override
    public JsonElement serialize(AtBat src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty(STRIKES, src.getStrikes());
        obj.addProperty(BALLS, src.getBalls());
        obj.addProperty(RESULT, src.getResult().name());

        return obj;
    }

    @Override
    public AtBat deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        int strikes = obj.get(STRIKES).getAsInt();
        int balls = obj.get(BALLS).getAsInt();
        String resultString = obj.get(RESULT).getAsString();

        return new AtBat(strikes, balls, AtBat.Result.valueOf(resultString));
    }
}
