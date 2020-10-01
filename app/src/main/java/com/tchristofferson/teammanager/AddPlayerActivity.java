package com.tchristofferson.teammanager;

import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/*
 * Activity that shows a screen to add a player to the team
 */
public class AddPlayerActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private Button saveButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        boolean isNewPlayer = getIntent().getExtras().getBoolean(getString(R.string.is_new_player_key));

        ActionBar actionBar = getSupportActionBar();

        //Setting action bar title
        if (actionBar != null) {
            if (isNewPlayer)
                actionBar.setTitle("Add Player");
            else
                actionBar.setTitle("Edit Player");
        }

        nameEditText = findViewById(R.id.name_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        saveButton = findViewById(R.id.save_player_btn);

        saveButton.setOnClickListener(v -> {
            Team team = TeamManagerApplication.getTeam();
            String name = nameEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(AddPlayerActivity.this, "Make sure you filled out the name and phone number!", Toast.LENGTH_LONG).show();
                return;
            }

            Player player = new Player(name, PhoneNumberUtils.formatNumber(phone));
            team.addPlayer(player);
            finish();
        });
    }
}
