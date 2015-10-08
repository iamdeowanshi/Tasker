package com.android.tasker;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Sibi on 06/10/15.
 */
public class ToDoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
