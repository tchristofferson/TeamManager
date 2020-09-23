package com.tchristofferson.teammanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/*
 * Activity for entering data about a player's at bat (screen showing the count and result)
 */
public class AtBatActivity extends AppCompatActivity {

    private TextView ballsTextView;
    private TextView strikesTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_bat);

        ActionBar actionBar = getSupportActionBar();

        //Setting the action bar title. Using string resource for the key in the intent to store the at bat number/row from AtBats fragment recycler view
        if (actionBar != null)
            actionBar.setTitle("At Bat " + getIntent().getExtras().getInt(getString(R.string.list_item_position)) + ": Single");

        //Setting text view
        ballsTextView = findViewById(R.id.balls_count);
        strikesTextView = findViewById(R.id.strikes_count);

        /*
         * Setting add and subtract buttons' listeners
         * There are two buttons (add & subtract) for balls and strikes counter
         */

        findViewById(R.id.sub_ball_btn).setOnClickListener(v -> {
            int balls = getBalls() - 1;

            //Make sure balls don't go below 0
            if (balls < 0)
                balls = 0;

            setBalls(balls);
        });

        findViewById(R.id.add_ball_btn).setOnClickListener(v -> {
            int balls = getBalls() + 1;

            //Make sure balls don't go over 4
            if (balls > 4)
                balls = 4;

            setBalls(balls);
        });

        findViewById(R.id.sub_strike_btn).setOnClickListener(v -> {
            int strikes = getStrikes() - 1;

            //Make sure strikes don't go below 0
            if (strikes < 0)
                strikes = 0;

            setStrikes(strikes);
        });

        findViewById(R.id.add_strike_btn).setOnClickListener(v -> {
            int strikes = getStrikes() + 1;

            //Make sure strikes don't go over 3
            if (strikes > 3)
                strikes = 3;

            setStrikes(strikes);
        });
    }

    /*
     * Private methods to conform to DRY (don't repeat yourself)
     */

    private void setBalls(int balls) {
        setTextViewNumber(ballsTextView, balls);
    }

    private void setStrikes(int strikes) {
        setTextViewNumber(strikesTextView, strikes);
    }

    private void setTextViewNumber(TextView textView, int n) {
        textView.setText(String.valueOf(n));
    }

    private int getBalls() {
        return getIntFromTextView(ballsTextView);
    }

    private int getStrikes() {
        return getIntFromTextView(strikesTextView);
    }

    private int getIntFromTextView(TextView textView) {
        return Integer.parseInt(textView.getText().toString());
    }
}
