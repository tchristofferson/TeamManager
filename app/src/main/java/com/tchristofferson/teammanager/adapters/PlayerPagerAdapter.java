package com.tchristofferson.teammanager.adapters;

import com.tchristofferson.teammanager.fragments.AtBatsFragment;
import com.tchristofferson.teammanager.fragments.StatsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/*
 * This class is used to determine the number of tabs and the fragment for each tab in the tab layout (PlayerActivity)
 */
public class PlayerPagerAdapter extends FragmentStateAdapter {

    public PlayerPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //Determining the fragment depending on the tab position
        //If it is the first tab return AtBatsFragment
        if (position == 0)
            return new AtBatsFragment();

        //Else return StatsFragment
        return new StatsFragment();
    }

    @Override
    public int getItemCount() {
        //Only two tabs are used (At Bats & Stats)
        return 2;
    }
}
