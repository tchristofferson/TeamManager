package com.tchristofferson.teammanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tchristofferson.teammanager.PlayerActivity;
import com.tchristofferson.teammanager.R;
import com.tchristofferson.teammanager.TeamManagerApplication;
import com.tchristofferson.teammanager.models.Team;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * RecyclerView adapter for displaying rows
 * Similar to AtBatsListAdapter
 */
public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.TeamViewHolder> {

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //R.layout.list_item represents a single row layout
        //Inflate created the view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TeamViewHolder holder, int position) {
        Team team = TeamManagerApplication.getTeam();
        //Setting the text of the row through the view holder, which represents a row
        holder.textView.setText(team.getPlayer(position).getName());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        //Returns the number of players/rows to display in the recycler view
        return TeamManagerApplication.getTeam().getTotalPlayers();
    }

    /*
     * A Recycler View Holder represents a row in the recycler view
     */
    public static class TeamViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private final TextView textView;
        private int position;

        public TeamViewHolder(View view) {
            super(view);
            context = view.getContext();
            textView = view.findViewById(R.id.team_list_textview);

            //Setting the on click handler for the text view in each row
            textView.setOnClickListener(v -> {
                //When a user clicks a row (text view in row) the PlayerActivity is started
                Intent intent = new Intent(context, PlayerActivity.class);
                //Passing the position/player number to the intent so the next activity can use it
                intent.putExtra(context.getString(R.string.player_position_key), position);
                context.startActivity(intent);
            });
        }
    }

}
