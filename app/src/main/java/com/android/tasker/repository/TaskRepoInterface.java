package com.android.tasker.repository;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;

import java.util.List;

/**
 * Created by Sibi on 16/09/15.
 */
public interface TaskRepoInterface {

    int getUserId();

    void setAccessToken();

    String getAccessToken();

    void setUserId();

    User register(User user);

    User login(String email, String password);

    User updateProfilePic(String profilePicUrl);

    // task list operations
    List<TaskList> getTaskLists();

    TaskList createTaskList(TaskList list);

    TaskList updateTaskList(TaskList list);

    TaskList deleteTaskList(int taskListId);

    // task operations

    List<Task> getTasks(int taskListId);

    Task createTask(Task task);

    Task updateTask(Task task);

    Task deleteTask(int taskId);

}
