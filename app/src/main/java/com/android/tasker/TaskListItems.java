package com.android.tasker;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.repository.RepositoryFactory;

import java.util.List;

/**
 * Created by Sibi on 24/09/15.
 */
public class TaskListItems extends Fragment {

    private List<TaskList> taskListItems;
    private List<Task> taskItems;
    private int selectedPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.task_items, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        taskListItems = RepositoryFactory.getTaskRepo().getTaskLists();
        NavigationDrawerFragment obj = new NavigationDrawerFragment();
        selectedPosition = obj.currentPosition();

        ListView listView = (ListView)view.findViewById(R.id.taskList);
        int  taskListId = taskListItems.get(selectedPosition).getId();
        taskItems = RepositoryFactory.getTaskRepo().getTasks(taskListId);
        ArrayAdapter<Task> taskListadapter = new ArrayAdapter<Task>(getActivity(), android.R.layout.simple_list_item_1, taskItems);
        listView.setAdapter(taskListadapter);

        return view;
    }
}