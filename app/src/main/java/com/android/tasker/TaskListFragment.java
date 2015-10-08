package com.android.tasker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tasker.model.Task;
import com.android.tasker.repository.RepoCallBack;
import com.android.tasker.repository.RepositoryFactory;

import java.util.List;

/**
 * Created by Sibi on 24/09/15.
 */
public class TaskListFragment extends Fragment {

    private List<Task> taskItems;
    private RecyclerView listView;

    RepoCallBack<List<Task>> listRepoCallBack = new RepoCallBack<List<Task>>() {
        @Override
        public void onSuccess(List<Task> data) {
            taskItems = data;
        }

        @Override
        public void onFailure(Throwable tr) {

        }
    };

    public static Fragment getInstance(Bundle bundle) {
        TaskListFragment taskListViewFragment = new TaskListFragment();
        taskListViewFragment.setArguments(bundle);
        return taskListViewFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        listView = (RecyclerView) view.findViewById(R.id.fragment_list);
        listView.setLayoutManager(layoutManager);
        listView.setHasFixedSize(true);

        long selectedTaskListId = getArguments().getInt("selected_task_list_id");
        RepositoryFactory.getTaskRepo().getTasks(selectedTaskListId, listRepoCallBack );
//        String resultantJson = taskItems.get(0).toJSON();
//        Toast.makeText(getActivity(), resultantJson, Toast.LENGTH_SHORT).show();
//        Task resultTask = taskItems.get(0).fromJSON(resultantJson);
//        Toast.makeText(getActivity(), resultTask.getName(), Toast.LENGTH_SHORT).show();
//        String jsonArray = taskItems.get(0).toJSONArray(taskItems);
//        List<BaseModel> resultList = taskItems.get(0).fromJSONArray(jsonArray);
        TaskListAdapter adapter = new TaskListAdapter(taskItems, getActivity());

        listView.setAdapter(adapter);
        return view;
    }
}