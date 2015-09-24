package com.android.tasker.model;

/**
 * Created by Sibi on 16/09/15.
 */
public class Task {

    private int id;
    private String name;
    private String date;
    private String time;
    private boolean finished;
    private int taskListId;

    public Task() {
    }

    public Task(int id, String name, String date, String time, boolean finished, int taskListId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.finished = finished;
        this.taskListId = taskListId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(int taskListId) {
        this.taskListId = taskListId;
    }

}
