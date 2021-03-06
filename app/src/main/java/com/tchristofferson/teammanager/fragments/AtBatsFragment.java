package com.tchristofferson.teammanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tchristofferson.teammanager.AtBatActivity;
import com.tchristofferson.teammanager.DeleteItemTouchHelper;
import com.tchristofferson.teammanager.R;
import com.tchristofferson.teammanager.TeamManagerApplication;
import com.tchristofferson.teammanager.adapters.AtBatsListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AtBatsFragment extends Fragment {

    private final int playerPosition;
    private AtBatsListAdapter adapter;

    public AtBatsFragment(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_at_bats, container, false);

        //Setting up the recycler view similar to MainActivity, but using fragments instead of a new activity
        RecyclerView recyclerView = view.findViewById(R.id.at_bats_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AtBatsListAdapter(playerPosition);
        recyclerView.setAdapter(adapter);
        //Sets lines in between each row of the recycler view
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        new ItemTouchHelper(new DeleteItemTouchHelper(new DeleteItemTouchHelper.Callback() {
            @Override
            protected void onRowSwipeDelete(int row) {
                TeamManagerApplication.getTeam().getPlayer(playerPosition).removeAtBat(row);
            }
        }, adapter, getContext())).attachToRecyclerView(recyclerView);

        //Setting the on click listener for the circle + button
        view.findViewById(R.id.add_at_bat_btn).setOnClickListener(v -> {
            //When button is clicked the AtBatActivity is started
            Intent intent = new Intent(getContext(), AtBatActivity.class);
            //Adding the position/at bat number to the intent for the next activity to use
            intent.putExtra(getString(R.string.player_position_key), playerPosition);
            intent.putExtra(getString(R.string.at_bat_position_key), -1);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
