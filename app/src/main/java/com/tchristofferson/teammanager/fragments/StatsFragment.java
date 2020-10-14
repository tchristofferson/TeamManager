package com.tchristofferson.teammanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tchristofferson.teammanager.R;
import com.tchristofferson.teammanager.models.AtBat;
import com.tchristofferson.teammanager.models.Player;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatsFragment extends Fragment {

    private final Player player;
    private TextView atBatsTextView;
    private TextView strikeoutsTextView;
    private TextView outsTextView;
    private TextView walksTextView;
    private TextView sacrificeBuntsTextView;
    private TextView singlesTextView;
    private TextView doublesTextView;
    private TextView triplesTextView;
    private TextView homeRunsTextView;
    private TextView onBasePercentageTextView;
    private TextView sluggingAverageTextView;
    private TextView battingAverageTextView;

    public StatsFragment(Player player) {
        this.player = player;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        atBatsTextView = view.findViewById(R.id.at_bats_textview);
        strikeoutsTextView = view.findViewById(R.id.strikeouts_textview);
        outsTextView = view.findViewById(R.id.outs_textview);
        walksTextView = view.findViewById(R.id.walks_textview);
        sacrificeBuntsTextView = view.findViewById(R.id.sac_bunts_textview);
        singlesTextView = view.findViewById(R.id.singles_textview);
        doublesTextView = view.findViewById(R.id.doubles_textview);
        triplesTextView = view.findViewById(R.id.triples_textview);
        homeRunsTextView = view.findViewById(R.id.home_runs_textview);
        onBasePercentageTextView = view.findViewById(R.id.obp_textview);
        sluggingAverageTextView = view.findViewById(R.id.slg_textview);
        battingAverageTextView = view.findViewById(R.id.ba_textview);
        updateStats();

        return view;
    }

    //Runs every time the user switches from at-bats tab to stats
    @Override
    public void onResume() {
        super.onResume();
        updateStats();
    }

    //Updates the stats page
    private void updateStats() {
        List<AtBat> atBats = player.getAtBats();
        int strikeouts, outs, walks, sacBunts, singles, doubles, triples, homeRuns;
        strikeouts = outs = walks = sacBunts = singles = doubles = triples = homeRuns = 0;

        for (AtBat atBat : atBats) {
            switch (atBat.getResult()) {
                case SINGLE:
                    singles++;
                    break;
                case DOUBLE:
                    doubles++;
                    break;
                case TRIPLE:
                    triples++;
                    break;
                case HOME_RUN:
                    homeRuns++;
                    break;
                case WALK:
                case HIT_BY_PITCH:
                    walks++;
                    break;
                case STRIKEOUT:
                    strikeouts++;
                    break;
                case FIELDERS_CHOICE:
                case FLY_OUT:
                case BASE_ON_ERROR:
                case OUT:
                    outs++;
                    break;
                case SACRIFICE_BUNT:
                    sacBunts++;
                    break;
            }
        }

        /* Used for calculations */
        double successfulHits = singles + doubles + triples + homeRuns;
        double onBases = successfulHits + walks;
        //If atBats size is 0 this is set to 1 to make sure I don't divide by 0
        double totalAtBats = atBats.size() == 0 ? 1 : atBats.size();

        double onBasePercentage = onBases / totalAtBats;
        double sluggingAverage = ((singles) + (doubles * 2) + (triples * 3) + (homeRuns * 4)) / (totalAtBats - walks);
        double battingAverage = successfulHits / (totalAtBats - walks);

        /* Setting the text views on the screen */
        setTextView(atBatsTextView, atBats.size());
        setTextView(strikeoutsTextView, strikeouts);
        setTextView(outsTextView, outs);
        setTextView(walksTextView, walks);
        setTextView(sacrificeBuntsTextView, sacBunts);
        setTextView(singlesTextView, singles);
        setTextView(doublesTextView, doubles);
        setTextView(triplesTextView, triples);
        setTextView(homeRunsTextView, homeRuns);

        setTextView(onBasePercentageTextView, onBasePercentage);
        setTextView(sluggingAverageTextView, sluggingAverage);
        setTextView(battingAverageTextView, battingAverage);
    }

    /* Helper methods so I don't repeat code */

    private void setTextView(TextView textView, int i) {
        textView.setText(String.valueOf(i));
    }

    private void setTextView(TextView textView, double d) {
        //String.format makes sure there are only 3 decimal places (%.3f)
        textView.setText(String.format(Locale.US, "%.3f", d));
    }
}
