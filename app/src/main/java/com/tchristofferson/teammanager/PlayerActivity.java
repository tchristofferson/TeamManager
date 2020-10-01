package com.tchristofferson.teammanager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tchristofferson.teammanager.adapters.PlayerPagerAdapter;
import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

/*
 * Activity showing the tab layout with two tabs (At Bats & Stats)
 */
public class PlayerActivity extends AppCompatActivity {

    private PlayerPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        int playerPosition = getIntent().getExtras().getInt(getString(R.string.player_position_key));
        Player player = TeamManagerApplication.getTeam().getPlayer(playerPosition);

        ActionBar actionBar = getSupportActionBar();

        //Setting the action bar title to Player #, the number is fetched from the intent which corresponds to MainActivity recycler view number/row
        if (actionBar != null)
            actionBar.setTitle(player.getName());

        //Setting views
        TabLayout tabLayout = findViewById(R.id.player_tabs);
        ViewPager2 viewPager = findViewById(R.id.player_view_pager);
        //Setting ViewPager2's adapter
        adapter = new PlayerPagerAdapter(this, playerPosition);
        viewPager.setAdapter(adapter);

        //Setting the tab mediator
        //Used to determine the text/title for each tab
        //There are only two tabs (0 & 1) (At Bats & Stats)
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            //The text is retrieved using a string resource
            if (position == 0)
                //At Bats
                tab.setText(R.string.at_bats);
            else
                //Stats
                tab.setText(R.string.stats);

        }).attach();
    }
}
