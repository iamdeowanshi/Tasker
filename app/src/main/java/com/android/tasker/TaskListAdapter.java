package com.android.tasker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tasker.model.Task;
import com.android.tasker.model.User;
import com.android.tasker.repository.RepoCallBack;
import com.android.tasker.repository.RepositoryFactory;

import java.util.List;

/**
 * Created by Sibi on 28/09/15.
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private List<Task> tasks;
    final private Context context;
    private User user;

    RepoCallBack<Task> deleteCallBack = new RepoCallBack<Task>() {
        @Override
        public void onSuccess(Task data) {

        }

        @Override
        public void onFailure(Throwable tr) {

        }
    };


    public TaskListAdapter(List<Task> taskItems, Context activity) {
        tasks = taskItems;
        this.context = activity;
    }

    @Override
    public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskListAdapter.ViewHolder viewHolder,final int position) {

        Task task = tasks.get(position);
        viewHolder.textView.setText(tasks.get(position).getName());
        if (!task.isFinished()) {
            viewHolder.finished.setVisibility(View.VISIBLE);
            viewHolder.crossImage.setVisibility(View.GONE);
            viewHolder.finished.setChecked(tasks.get(position).isFinished());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Task task = tasks.get(position);
                    if (!task.isFinished()) {
                        Intent intent = new Intent(view.getContext(), UpdateTask.class);
                        intent.putExtra("Task", (Parcelable) task);
                        context.startActivity(intent);
                    }
                }
            });

            viewHolder.finished.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Task task = tasks.get(position);
                    task.setFinished(true);
                    task.setTaskListId(0);
                    viewHolder.textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    viewHolder.finished.setClickable(false);

                }

            });
        } else if (task.isFinished()) {
            viewHolder.crossImage.setVisibility(View.VISIBLE);
            viewHolder.finished.setVisibility(View.INVISIBLE);
            viewHolder.crossImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Task task = tasks.get(position);
                    RepositoryFactory.getTaskRepo().deleteTask(task.getTaskId(), deleteCallBack);
                    viewHolder.textView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show();
                    viewHolder.crossImage.setClickable(false);
                }

            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        View itemView;
        TextView textView;
        CheckBox finished;
        ImageView crossImage;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            textView = (TextView) itemView.findViewById(R.id.taskName);
            finished = (CheckBox) itemView.findViewById(R.id.isTaskFinished);
            crossImage = (ImageView) itemView.findViewById(R.id.crossImage);

        }
    }

    @Override
    public int getItemCount() {
        return tasks != null ? tasks.size() : 0;
    }

}
