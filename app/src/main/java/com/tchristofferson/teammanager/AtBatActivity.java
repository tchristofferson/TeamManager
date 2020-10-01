package com.tchristofferson.teammanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tchristofferson.teammanager.models.AtBat;
import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/*
 * Activity for entering data about a player's at bat (screen showing the count and result)
 */
public class AtBatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_bat);
        Bundle bundle = getIntent().getExtras();

        int playerPosition = bundle.getInt(getString(R.string.player_position_key));
        int atBatPosition = bundle.getInt(getString(R.string.at_bat_position_key));

        Team team = TeamManagerApplication.getTeam();
        Player player = team.getPlayer(playerPosition);
        AtBat atBat = player.getAtBat(atBatPosition);

        if (atBat == null)
            atBat = new AtBat(0, 0, AtBat.Result.values()[0]);

        ActionBar actionBar = getSupportActionBar();

        //Setting the action bar title. Using string resource for the key in the intent to store the at bat number/row from AtBats fragment recycler view
        if (actionBar != null)
            actionBar.setTitle("At Bat");

        //Setting text view
        TextView ballsTextView = findViewById(R.id.balls_count);
        TextView strikesTextView = findViewById(R.id.strikes_count);
        Spinner spinner = findViewById(R.id.at_bat_result_spinner);
        Button saveButton = findViewById(R.id.at_bat_save_btn);
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_item, AtBat.Result.values()));
        final AtBat finalAtBat = atBat;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                finalAtBat.setResult(AtBat.Result.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
            }
        });

        saveButton.setOnClickListener(v -> {
            if (atBatPosition == -1) {
                player.addAtBat(finalAtBat);
            } else {
                int strikes = Integer.parseInt(strikesTextView.getText().toString());
                int balls = Integer.parseInt(ballsTextView.getText().toString());

                finalAtBat.setStrikes(strikes);
                finalAtBat.setBalls(balls);
                finalAtBat.setResult((AtBat.Result) spinner.getSelectedItem());
            }

            finish();
        });

        ballsTextView.setText(String.valueOf(atBat.getBalls()));
        strikesTextView.setText(String.valueOf(atBat.getStrikes()));
    }
}
