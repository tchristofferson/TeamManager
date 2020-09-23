package com.tchristofferson.teammanager;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/*
 * Activity that shows a screen to add a player to the team
 */
public class AddPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        ActionBar actionBar = getSupportActionBar();

        //Setting action bar title
        if (actionBar != null)
            actionBar.setTitle("Add Player");
    }
}
