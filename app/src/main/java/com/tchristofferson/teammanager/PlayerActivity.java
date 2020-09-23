package com.tchristofferson.teammanager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tchristofferson.teammanager.adapters.PlayerPagerAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

/*
 * Activity showing the tab layout with two tabs (At Bats & Stats)
 */
public class PlayerActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        ActionBar actionBar = getSupportActionBar();

        //Setting the action bar title to Player #, the number is fetched from the intent which corresponds to MainActivity recycler view number/row
        if (actionBar != null)
            actionBar.setTitle("Player " + getIntent().getExtras().getInt(getString(R.string.list_item_position)));

        //Setting views
        tabLayout = findViewById(R.id.player_tabs);
        viewPager = findViewById(R.id.player_view_pager);
        //Setting ViewPager2's adapter
        viewPager.setAdapter(new PlayerPagerAdapter(this));

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
