package com.tchristofferson.teammanager.models;

import android.os.Bundle;

import com.tchristofferson.teammanager.TeamManagerApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//TODO: Make all activities extends BaseActivity
public class BaseActivity extends AppCompatActivity {

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        TeamManagerApplication.saveState(outState);
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TeamManagerApplication.restoreState(savedInstanceState);
    }
}
