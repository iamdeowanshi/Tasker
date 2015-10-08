package com.android.tasker;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tasker.model.TaskList;

import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private final Context mContext;
    private List<TaskList> mData;
    private int mSelectedPosition;
    private int mTouchedPosition = -1;
    private NavigationCallBacks mNavigationCallbacks;



    public NavigationDrawerAdapter(List<TaskList> data, Context context) {
        mData = data;
        this.mContext = context;

    }

    public void setNavigationCallbacks(NavigationCallBacks navigationCallbacks) {
        mNavigationCallbacks = navigationCallbacks;
    }

    @Override
    public NavigationDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_list_item, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NavigationDrawerAdapter.ViewHolder viewHolder, final int i) {
        String message = mData.get(i).getName();
        viewHolder.textView.setText(mData.get(i).getName());

        /* handle gestures and click events */
        handleRowEvents(viewHolder.itemView, i);

        /* Highlight selected row */
        if (mSelectedPosition == i || mTouchedPosition == i) {
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#BFD596"));
            //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();


        } else {
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    private void handleRowEvents(View itemView, final int i) {
        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchPosition(i);
                        return false;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        touchPosition(-1);
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        return false;
                }
                return true;
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNavigationCallbacks != null)
                    mNavigationCallbacks.onItemSelected(i);
            }
        });
    }

    private void touchPosition(int position) {
        int lastPosition = mTouchedPosition;
        mTouchedPosition = position;
        if (lastPosition >= 0)
            notifyItemChanged(lastPosition);
        if (position >= 0)
            notifyItemChanged(position);
    }

    public void setSelectedRow(int position) {
        int lastPosition = mSelectedPosition;
        mSelectedPosition = position;

        /* Required to update the color selection */
        notifyItemChanged(lastPosition);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        /* Do null check to avoid NullPointerExceptions */
        return mData != null ? mData.size() : 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textListItem);
        }
    }

}
