package com.tchristofferson.teammanager.models;

import android.os.Parcel;

import java.util.Objects;

/* Represents an at-bat for a player */
public class AtBat {

    //number of strikes
    private int strikes;
    //number of balls
    private int balls;
    //The result/outcome of the at-bat
    private Result result;

    public AtBat(int strikes, int balls, Result result) {
        this.strikes = strikes;
        this.balls = balls;
        this.result = result;
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtBat atBat = (AtBat) o;
        return strikes == atBat.strikes &&
                balls == atBat.balls &&
                result == atBat.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strikes, balls, result);
    }

    //Represents the result/outcome of an at-bat
    public enum Result {
        SINGLE("Single"),
        DOUBLE("Double"),
        TRIPLE("Triple"),
        HOME_RUN("Home Run"),
        WALK("Walk"),
        HIT_BY_PITCH("Hit By Pitch"),
        STRIKEOUT("Strikeout"),
        FIELDERS_CHOICE("Fielders Choice"),
        FLY_OUT("Fly Out"),
        BASE_ON_ERROR("Base On Error"),
        OUT("Out"),
        SACRIFICE_BUNT("Sac Bunt");

        //The string that will be displayed on screen for a specific Result
        private final String displayName;

        Result(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
