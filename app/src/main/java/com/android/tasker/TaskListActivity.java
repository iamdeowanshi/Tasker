package com.android.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.repository.RepositoryFactory;
import com.android.tasker.repository.TaskRepoInterface;

import java.util.List;

public class TaskListActivity extends ActionBarActivity implements NavigationCallBacks {

    private FloatingActionButton addTask;
    private CharSequence mDrawerTitle;
    private List<TaskList> taskListItems;
    private DrawerLayout mDrawerLayout;
    private ListView tasksList;
    private List<Task> taskItems;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mTitle = "Navigation Drawer";
    private int selectedPosition;
    private Toolbar mToolbar;

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
//        ArrayAdapter<Task> taskAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, array.list);
//        tasksList.setAdapter(taskAdapter);

       // getActionBar().setTitle(mTitle);
        //ArrayAdapter<Task> taskArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, (List) taskItems);

        mTitle = (String) getTitle();
        mDrawerTitle = "TaskListActivity List";

  //        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
  //      mDrawerList = (ListView) findViewById(R.id.drawer_list);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /* Initializing NavigationDrawer fragment */
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_drawer);
        mNavigationDrawerFragment.initDrawer(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        onItemSelected(0);

        TaskRepoInterface repo = RepositoryFactory.getTaskRepo();
        taskListItems = repo.getTaskLists();

        //ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, taskListItems);



        addTask = (FloatingActionButton) findViewById(R.id.fab);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
                startActivity(intent);
            }
        });

//        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                selectedPosition = position;
//                //Replace fragment content
//                //updateFragment();
//                mDrawerLayout.closeDrawer(mDrawerList);
//            }
//        });

        /* Getting reference to the ActionBarDrawerToggle */
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, null, R.string.drawer_open, R.string.drawer_close) {
//            /* Called when drawer is closed */
//            public void onDrawerClosed(View view) {
//                //Put your code here
//            }
//
//            /* Called when a drawer is opened */
//            public void onDrawerOpened(View drawerView) {
//                //Put your code here
//            }
//        };
///* Setting DrawerToggle on DrawerLayout */
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    public void onItemSelected(int position) {

    }
    @Override
    public void onBackPressed() {
        /* Close navigation drawer, if open */
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


}
