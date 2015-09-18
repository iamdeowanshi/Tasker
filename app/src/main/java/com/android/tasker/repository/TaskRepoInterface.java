package com.android.tasker.repository;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;

import java.util.List;

/**
 * Created by Sibi on 16/09/15.
 */
public interface TaskRepoInterface {

    User register(User user);

    int getUserId();

    String getAccessToken();

    void setAccessToken();

    void setUserId();

    User login(String email, String password);

    User updateProfilePic(User user);

    // task list operations
    List<TaskList> getTaskLists();

    TaskList createTaskList(TaskList list);

    TaskList updateTaskList(TaskList list);

    Boolean deleteTaskList(int taskListId);

    // task operations

    List<Task> getTasks(int taskListId);

    Task createTask(Task task);

    Boolean updateTask(Task task);

    Boolean deleteTask(int taskId);

}
