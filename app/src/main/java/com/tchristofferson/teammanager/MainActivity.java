package com.tchristofferson.teammanager;

import android.content.Intent;
import android.os.Bundle;

import com.tchristofferson.teammanager.adapters.TeamListAdapter;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
 * Main activity
 * This activity will display a RecyclerView (a list) of players on the team
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        //Setting the action bar title
        if (actionBar != null)
            actionBar.setTitle("Team");

        //Getting recycler view from R.layout.activity_main
        RecyclerView recyclerView = findViewById(R.id.team_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //Setting a custom recycler view adapter
        recyclerView.setAdapter(new TeamListAdapter());
        //Adding recycler view decoration. It adds the lines beneath each row
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //Set the click listener for the circle + button
        findViewById(R.id.add_player_btn).setOnClickListener(v -> {
            //Starting a new activity (AddPlayerActivity)
            Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
            startActivity(intent);
        });
    }
}