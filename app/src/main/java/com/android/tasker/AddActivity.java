package com.android.tasker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sibi on 15/09/15.
 */
public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
