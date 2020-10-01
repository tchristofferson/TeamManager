package com.tchristofferson.teammanager;

import android.content.Intent;

import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void totalPlayersCountIsCorrect() {
        Team team = Team.getInstance();
        team.getPlayers().clear();
        team.addPlayer(new Player("", ""));
        team.addPlayer(new Player("", ""));
        assertEquals(team.getPlayers().size(), team.getTotalPlayers());
    }

    @Test
    public void addPlayerToTeamIsCorrect() {
        Team team = Team.getInstance();
        team.getPlayers().clear();
        team.addPlayer(new Player("Trent", "0123456789"));
        assertEquals(1, team.getTotalPlayers());
    }

    @Test
    public void removePlayerFromTeamIsCorrect() {
        Team team = Team.getInstance();
        team.getPlayers().clear();
        team.addPlayer(new Player("Trent", "0123456789"));
        team.addPlayer(new Player("Bob", "0123456789"));
        team.removePlayer(0);
        assertEquals(1, team.getTotalPlayers());
    }
}