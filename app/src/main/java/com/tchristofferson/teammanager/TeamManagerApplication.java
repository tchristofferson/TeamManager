package com.tchristofferson.teammanager;

import android.app.Application;

import com.tchristofferson.teammanager.models.Team;

import java.util.ArrayList;

public class TeamManagerApplication extends Application {

    private static Team team;

    @Override
    public void onCreate() {
        super.onCreate();
        team = new Team(new ArrayList<>());
    }

    public static Team getTeam() {
        return team;
    }
}
