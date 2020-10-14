package com.tchristofferson.teammanager;

import com.tchristofferson.teammanager.models.AtBat;
import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestObjectUtil {

    static AtBat getAtBat() {
        return new AtBat(1, 2, AtBat.Result.DOUBLE);
    }

    static Player getPlayer() {
        return new Player("Trent", "0123456789", Arrays.asList(getDifferentAtBats()));
    }

    static Team getTeam() {
        return new Team(Arrays.asList(getDifferentPlayers()));
    }

    static AtBat[] getSameAtBats() {
        int strikes = 1;
        int balls = 2;
        AtBat.Result result = AtBat.Result.DOUBLE;

        return new AtBat[]{
                new AtBat(strikes, balls, result),
                new AtBat(strikes, balls, result)
        };
    }

    static AtBat[] getDifferentAtBats() {
        return new AtBat[]{
                new AtBat(0, 1, AtBat.Result.DOUBLE),
                new AtBat(1, 0, AtBat.Result.SINGLE)
        };
    }

    static Player[] getSamePlayers() {
        String name = "Trent";
        String phone = "0123456789";
        List<AtBat> atBats = Arrays.asList(getDifferentAtBats());

        return new Player[]{
                new Player(name, phone, atBats),
                new Player(name, phone, atBats)
        };
    }

    static Player[] getDifferentPlayers() {
        return new Player[]{
                new Player("Trent", "0123456789", Collections.emptyList()),
                new Player("Bobby", "0023456789", Arrays.asList(getDifferentAtBats()))
        };
    }

    static Team[] getSameTeams() {
        List<Player> players = Arrays.asList(getDifferentPlayers());

        return new Team[]{
                new Team(players),
                new Team(players)
        };
    }

    static Team[] getDifferentTeams() {
        return new Team[]{
                new Team(Arrays.asList(getDifferentPlayers())),
                new Team(Arrays.asList(getSamePlayers()))
        };
    }

}
