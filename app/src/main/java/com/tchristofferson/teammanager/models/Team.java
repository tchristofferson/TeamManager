package com.tchristofferson.teammanager.models;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private static final Team team = new Team(new ArrayList<>());

    public static Team getInstance() {
        return team;
    }

    private final List<Player> players;

    private Team(List<Player> players) {
        this.players = players;
        players.add(new Player("Trent", "6086170527"));
    }

    public int getTotalPlayers() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int index) {
        if (IndexUtil.isInvalidIndex(players, index))
            return null;

        return players.get(index);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player removePlayer(int index) {
        if (IndexUtil.isInvalidIndex(players, index))
            return null;

        return players.remove(index);
    }
}