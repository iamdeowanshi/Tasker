package com.android.tasker.model;

/**
 * Created by Sibi on 16/09/15.
 */
public class TaskList {

    private int id;
    private String name;
    private int userId;

    public TaskList() {

    }

    public TaskList(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;

    }

    @Override
    public String toString() {
        return getName();
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
