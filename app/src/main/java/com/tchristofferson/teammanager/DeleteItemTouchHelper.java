package com.tchristofferson.teammanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class DeleteItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private final Callback callback;
    private final RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter;
    private final Context context;

    public DeleteItemTouchHelper(Callback callback, RecyclerView.Adapter<? extends RecyclerView.ViewHolder> adapter, Context context) {
        super(0, ItemTouchHelper.LEFT);
        this.callback = callback;
        this.adapter = adapter;
        this.context = context;
    }

    //For moving an item up or down
    //Returning false = item didn't move
    //Won't be called because my recycler views don't support moving items
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction != ItemTouchHelper.LEFT)
            return;

        callback.onRowSwipeDelete(viewHolder.getAdapterPosition());
        adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE)
            return;

        if (dX < 0) {
            Drawable icon = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_baseline_delete_24, null);
            View itemView = viewHolder.itemView;
            drawBackground(c, itemView, dX);//Draws red background
            drawIcon(c, itemView, dX, icon);//Draws trash icon
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    private void drawIcon(Canvas canvas, View itemView, float dX, Drawable icon) {
        int topMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
        int sideOffset = (int) dX / -2;
        int leftBound = itemView.getRight() + (int) dX + sideOffset;
        int rightBound = itemView.getRight() + (int) dX + icon.getIntrinsicWidth() + sideOffset;
        int topBound = itemView.getTop() + topMargin;
        int bottomBound = itemView.getBottom() - topMargin;

        Rect rect = new Rect(leftBound, topBound, rightBound, bottomBound);
        icon.setBounds(rect);
        icon.draw(canvas);
    }

    private void drawBackground(Canvas canvas, View itemView, float dX) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        RectF backgroundRectangle = new RectF(itemView.getRight() + dX, itemView.getTop(),
                itemView.getRight(), itemView.getBottom());
        canvas.drawRect(backgroundRectangle, paint);
    }

    public static abstract class Callback {
        protected abstract void onRowSwipeDelete(int row);
    }
}
