package com.tchristofferson.teammanager.models;

import android.os.Parcel;

public class AtBat {

    private int strikes;
    private int balls;
    private Result result;

    public AtBat(int strikes, int balls, Result result) {
        this.strikes = strikes;
        this.balls = balls;
        this.result = result;
    }

    protected AtBat(Parcel in) {
        this(in.readInt(), in.readInt(), Result.valueOf(in.readString()));
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
