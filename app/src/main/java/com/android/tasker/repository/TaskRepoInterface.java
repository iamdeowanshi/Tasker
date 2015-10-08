package com.android.tasker.repository;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;

import java.util.List;

/**
 * Created by Sibi on 16/09/15.
 */
public interface TaskRepoInterface {

    long getUserId();

    void setAccessToken(String accessToken);

    String getAccessToken();

    void setUserId(long id);

    void register(User user, RepoCallBack<User> callBack);

    void login(String email, String password, RepoCallBack<User> callBack);

    void updateProfilePic(String profilePicUrl, RepoCallBack<User> callBack);

    // activity_task_list list operations
    void getTaskLists(RepoCallBack<List<TaskList>> callBack);

    void createTaskList(TaskList list, RepoCallBack<TaskList> callBack);

    void updateTaskList(TaskList list, RepoCallBack<TaskList> callBack);

    void deleteTaskList(int taskListId, RepoCallBack<TaskList> callBack);

    // activity_task_list operations

    void getTasks(long taskListId, RepoCallBack<List<Task>> callBack);

    void createTask(Task task, RepoCallBack<Task> callBack);

    void updateTask(Task task, RepoCallBack<Task> callBack);

    void deleteTask(long taskId, RepoCallBack<Task> callBack);

}
