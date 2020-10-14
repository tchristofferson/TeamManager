package com.tchristofferson.teammanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tchristofferson.teammanager.models.AtBat;
import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static com.tchristofferson.teammanager.TestObjectUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static Gson gson;

    @BeforeClass
    public static void beforeClass() {
        gson = TeamManagerApplication.getGson();
    }

    @Test
    public void atBatEqualityTest() {
        AtBat[] equalAtBats = getSameAtBats();
        AtBat[] unequalAtBats = getDifferentAtBats();
        assertEquals(equalAtBats[0], equalAtBats[1]);
        assertNotEquals(unequalAtBats[0], unequalAtBats[1]);
    }

    @Test
    public void playerEqualityTest() {
        Player[] equalPlayers = getSamePlayers();
        Player[] unequalPlayers = getDifferentPlayers();
        assertEquals(equalPlayers[0], equalPlayers[1]);
        assertNotEquals(unequalPlayers[0], unequalPlayers[1]);
    }

    @Test
    public void teamEqualityTest() {
        Team[] equalTeams = getSameTeams();
        Team[] unequalTeams = getDifferentTeams();
        assertEquals(equalTeams[0], equalTeams[1]);
        assertNotEquals(unequalTeams[0], unequalTeams[1]);
    }

    @Test
    public void atBatSerializationDeserializationTest() {
        AtBat oldAtBat = getAtBat();
        String json = gson.toJson(oldAtBat);
        AtBat newAtBat = gson.fromJson(json, new TypeToken<AtBat>(){}.getType());

        assertEquals(oldAtBat, newAtBat);
    }

    @Test
    public void playerSerializationDeserializationTest() {
        Player oldPlayer = getPlayer();
        String json = gson.toJson(oldPlayer);
        Player newPlayer = gson.fromJson(json, new TypeToken<Player>(){}.getType());

        assertEquals(oldPlayer, newPlayer);
    }

    @Test
    public void teamSerializationDeserializationTest() {
        Team oldTeam = getTeam();
        String json = gson.toJson(oldTeam);
        Team newTeam = gson.fromJson(json, new TypeToken<Team>(){}.getType());

        assertEquals(oldTeam, newTeam);
    }
}