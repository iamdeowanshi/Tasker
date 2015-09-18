package com.android.tasker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sibi on 16/09/15.
 */
public class TaskList {

    private int id;
    private String name;
    private List<Task> tasks = new ArrayList<Task>();
    private User user;

    TaskList() {

    }

    public TaskList(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
