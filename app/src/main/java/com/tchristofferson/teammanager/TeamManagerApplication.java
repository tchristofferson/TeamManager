package com.tchristofferson.teammanager;

import android.app.Application;
import android.os.Bundle;

import com.tchristofferson.teammanager.models.Team;

import java.util.ArrayList;

public class TeamManagerApplication extends Application {

    private static Team team;

    @Override
    public void onCreate() {
        super.onCreate();
        team = new Team(new ArrayList<>());
    }

    public static void saveState(Bundle outState) {
        //TODO: implement save state
    }

    public static void restoreState(Bundle savedInstanceState) {
        //TODO: implement restore state
    }

    public static Team getTeam() {
        return team;
    }
}
