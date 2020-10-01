package com.tchristofferson.teammanager.models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private String phone;
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
}
