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
    private TaskList taskList;

    public Task() {
    }
/*
    public Task(String name, String date, String time, boolean finished, TaskList taskList) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.finished = finished;
        this.taskList = taskList;
    }*/

    public Task(String name, String date, String time, boolean finished) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.finished = finished;
    }

    public String getName() {
        return name;
    }

    public void setName(String taskName) {
        this.name = taskName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }


}
