package com.tchristofferson.teammanager.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* Represents a player on the team */
public class Player {

    //Player's name
    private String name;
    //Player's phone number
    private String phone;
    //A List of all of the player's at-bats
    private final List<AtBat> atBats;

    public Player(String name, String phone, List<AtBat> atBats) {
        this.name = name;
        this.phone = phone;
        this.atBats = atBats;
    }

    public Player(String name, String phone) {
        this(name, phone, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AtBat> getAtBats() {
        return new ArrayList<>(atBats);
    }

    public void addAtBat(AtBat atBat) {
        atBats.add(atBat);
    }

    public AtBat removeAtBat(int index) {
        return atBats.remove(index);
    }

    public AtBat getAtBat(int index) {
        if (IndexUtil.isInvalidIndex(atBats, index))
            return null;

        return atBats.get(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name) &&
                phone.equals(player.phone) &&
                atBats.equals(player.atBats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, atBats);
    }
}
