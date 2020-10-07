package com.tchristofferson.teammanager;

import android.app.Application;

import com.tchristofferson.teammanager.models.Team;

import java.util.ArrayList;

/*
 * Class extending Application to maintain global state, in this case for managing the team
 * Had to add that this class will be used as the application in the manifest
 */
public class TeamManagerApplication extends Application {

    //The global state used for all activities and fragments
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
