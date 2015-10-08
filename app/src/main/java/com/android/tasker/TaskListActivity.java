package com.android.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;
import com.android.tasker.repository.RepoCallBack;
import com.android.tasker.repository.RepositoryFactory;
import com.android.tasker.repository.TaskRepoInterface;

import java.util.List;

public class TaskListActivity extends ActionBarActivity implements NavigationCallBacks {

    private FloatingActionButton addTask;
    private List<TaskList> taskListItems;
    private Toolbar mToolbar;
    private long taskListId;
    private User user;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    RepoCallBack<List<TaskList>> listRepoCallBack = new RepoCallBack<List<TaskList>>() {
        @Override
        public void onSuccess(List<TaskList> data) {
            taskListItems = data;
        }

        @Override
        public void onFailure(Throwable tr) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /* Initializing NavigationDrawer fragment */
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.initDrawer(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        TaskRepoInterface repo = RepositoryFactory.getTaskRepo();
        repo.getTaskLists(listRepoCallBack);

        addTask = (FloatingActionButton) findViewById(R.id.fab);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
                        startActivity(intent);
            }
        });

        onItemSelected(0);
    }

    @Override
    public void onItemSelected(int selectedIdx) {

        if (taskListItems == null || taskListItems.size() == 0) {
            return;
        }

        Toast.makeText(this, "Menu item selected -> " + selectedIdx, Toast.LENGTH_SHORT).show();

        taskListId = taskListItems.get(selectedIdx).getTaskListId();

        Bundle bundle = new Bundle();
        bundle.putLong("selected_task_list_id", taskListId);

        Fragment fragment = TaskListFragment.getInstance(bundle);
        addFragment(fragment);
    }

    private void addFragment(Fragment fragment) {
        if (fragment != null) {
            /* Getting FragmentManager reference */
            FragmentManager fragmentManager = getSupportFragmentManager();

            /* Create fragment transaction */
            FragmentTransaction ft = fragmentManager.beginTransaction();

            /* Adding a fragment to the fragment transaction */
            ft.replace(R.id.container, fragment);

            /* Committing the transaction */
            ft.commit();
        }
    }

    @Override
    public void onBackPressed () {
    /* Close navigation drawer, if open */
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

}
