package com.tchristofferson.teammanager;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tchristofferson.teammanager.json.AtBatAdapter;
import com.tchristofferson.teammanager.json.PlayerAdapter;
import com.tchristofferson.teammanager.json.TeamAdapter;
import com.tchristofferson.teammanager.models.AtBat;
import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * Class extending Application to maintain global state, in this case for managing the team
 * Had to add that this class will be used as the application in the manifest
 */
public class TeamManagerApplication extends Application {

    private static final String TAG = TeamManagerApplication.class.getSimpleName();

    //The global state used for all activities and fragments
    private static final String FILENAME = "team.json";
    private static Team team;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(new TypeToken<AtBat>(){}.getType(), new AtBatAdapter())
                .registerTypeAdapter(new TypeToken<Player>(){}.getType(), new PlayerAdapter())
                .registerTypeAdapter(new TypeToken<Team>(){}.getType(), new TeamAdapter())
                .create();

        loadTeam();
    }

    public static Team getTeam() {
        return team;
    }

    public static Gson getGson() {
        return gson;
    }

    public static void saveTeam(Context context) {
        String json = gson.toJson(team);
        Log.d(TAG, "JSON: \n" + json);
        File file = getFile(context);
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            Log.e(TAG, "Failed to save data", e);
        }
    }

    private void loadTeam() {
        Context context = getApplicationContext();
        File file = getFile(context);

        if (!file.exists()) {
            team = new Team(new ArrayList<>());
            return;
        }

        try (FileInputStream inputStream = context.openFileInput(FILENAME)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder(bufferedReader.readLine());
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append('\n').append(line);
            }

            team = gson.fromJson(stringBuilder.toString(), new TypeToken<Team>(){}.getType());
        } catch (IOException e) {
            Log.e(TAG, "Failed to load data", e);
        }
    }

    private static File getFile(Context context) {
        return new File(context.getFilesDir(), FILENAME);
    }
}
