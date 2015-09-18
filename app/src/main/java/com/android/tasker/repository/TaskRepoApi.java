package com.android.tasker.repository;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;

import java.util.List;

/**
 * Created by Sibi on 17/09/15.
 */
public class TaskRepoApi implements TaskRepo {

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public void setAccessToken() {

    }

    @Override
    public void setUserId() {

    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public User updateProfilePic(User user) {
        return null;
    }

    @Override
    public List<TaskList> getTaskLists() {
        return null;
    }

    @Override
    public TaskList createTaskList(TaskList list) {
        return null;
    }

    @Override
    public TaskList updateTaskList(TaskList list) {
        return null;
    }

    @Override
    public Boolean deleteTaskList(int taskListId) {
        return null;
    }

    @Override
    public List<Task> getTasks(int taskListId) {
        return null;
    }

    @Override
    public Task createTask(Task task) {
        return null;
    }

    @Override
    public Boolean updateTask(Task task) {
        return null;
    }

    @Override
    public Boolean deleteTask(int taskId) {
        return null;
    }

}
