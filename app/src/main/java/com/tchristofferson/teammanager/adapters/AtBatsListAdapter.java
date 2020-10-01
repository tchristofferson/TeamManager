package com.tchristofferson.teammanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tchristofferson.teammanager.AtBatActivity;
import com.tchristofferson.teammanager.R;
import com.tchristofferson.teammanager.models.Player;
import com.tchristofferson.teammanager.models.Team;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * RecyclerView Adapter for displaying rows
 * Similar to TeamListAdapter
 */
public class AtBatsListAdapter extends RecyclerView.Adapter<AtBatsListAdapter.AtBatsViewHolder> {

    private final int playerPosition;
    private final Player player;

    public AtBatsListAdapter(int playerPosition) {
        this.playerPosition = playerPosition;
        this.player = Team.getInstance().getPlayer(playerPosition);
    }

    @NonNull
    @Override
    public AtBatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //R.layout.list_item represents a single row layout
        //Inflate created the view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new AtBatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtBatsViewHolder holder, int position) {
        //Setting the text of the row through the view holder, which represents a row
        holder.textView.setText(player.getAtBat(position).getResult().toString());
        //Also need to set the position so it can be passed onto the next activity
        holder.atBatPosition = position;
    }

    @Override
    public int getItemCount() {
        //Returns the number of at bats/rows to display in the recycler view
        return player.getAtBats().size();
    }

    /*
     * A Recycler View Holder represents a row in the recycler view
     */
    public class AtBatsViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private final TextView textView;
        private int atBatPosition;

        public AtBatsViewHolder(@NonNull View view) {
            super(view);
            context = view.getContext();
            textView = view.findViewById(R.id.team_list_textview);

            //Setting the on click handler for the text view in each row
            textView.setOnClickListener(v -> {
                //When a user clicks a row (text view in row) the AtBatActivity is started
                Intent intent = new Intent(context, AtBatActivity.class);
                //Passing the position/at bat number to the intent so the next activity can use it
                intent.putExtra(context.getString(R.string.player_position_key), playerPosition);
                intent.putExtra(context.getString(R.string.at_bat_position_key), atBatPosition);
                context.startActivity(intent);
            });
        }

    }

}
