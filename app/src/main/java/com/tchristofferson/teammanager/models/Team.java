package com.tchristofferson.teammanager.models;

import java.util.List;

/* Represents the entire team of players */
public class Team {

    //List of all players on the team
    private final List<Player> players;

    public Team(List<Player> players) {
        this.players = players;
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
