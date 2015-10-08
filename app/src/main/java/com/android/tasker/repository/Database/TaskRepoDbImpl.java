package com.android.tasker.repository.Database;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;
import com.android.tasker.repository.AbstractTaskRepo;
import com.android.tasker.repository.RepoCallBack;

import java.util.List;

/**
 * Created by Sibi on 06/10/15.
 */
public class TaskRepoDbImpl extends AbstractTaskRepo {

    @Override
    public void register(User user, RepoCallBack<User> callBack) {
        user.save();
        TaskList taskList = new TaskList();
        taskList.setName("Default");
        taskList.setUserId(user.getId());
        taskList.save();
        taskList = new TaskList();
        taskList.setName("Personal");
        taskList.setUserId(user.getId());
        taskList.save();
        taskList = new TaskList();
        taskList.setName("Work");
        taskList.setUserId(user.getId());
        taskList.save();
        taskList = new TaskList();
        taskList.setName("Finished");
        taskList.setUserId(user.getId());
        taskList.save();
        callBack.onSuccess(user);
    }

    @Override
    public void login(String email, String password, RepoCallBack<User> callBack) {
        List<User> listOfUsers = new Select().from(User.class).execute();

        for (User user : listOfUsers) {
            if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
                callBack.onSuccess(user);
            }
        }

    }

    @Override
    public void updateProfilePic(String profilePicUrl, RepoCallBack<User> callBack) {

    }


    @Override
    public void getTaskLists(RepoCallBack<List<TaskList>> callBack) {
        List<TaskList> taskLists = new Select().from(TaskList.class).where("userId = ?", getUserId()).execute();
        callBack.onSuccess(taskLists);
    }

    @Override
    public void createTaskList(TaskList list, RepoCallBack<TaskList> callBack) {
        TaskList taskList = new TaskList();
        taskList.setName(list.getName());
        taskList.setUserId(getUserId());
        taskList.save();
        callBack.onSuccess(taskList);

    }

    @Override
    public void updateTaskList(TaskList list, RepoCallBack<TaskList> callBack) {

    }

    @Override
    public void deleteTaskList(int taskListId, RepoCallBack<TaskList> callBack) {

    }

    @Override
    public void getTasks(long taskListId, RepoCallBack<List<Task>> callBack) {
        List<Task> tasks = new Select().from(Task.class).where("TaskList = ?",taskListId).execute();
        callBack.onSuccess(tasks);

    }

    @Override
    public void createTask(Task task, RepoCallBack<Task> callBack) {
        Task newTask = new Task();
        newTask.setName(task.getName());
        newTask.setFinished(task.isFinished());
        newTask.setDate(task.getDate());
        newTask.setTime(task.getTime());
        newTask.setTaskListId(task.getTaskListId());
        newTask.save();
        callBack.onSuccess(newTask);

    }

    @Override
    public void updateTask(Task task, RepoCallBack<Task> callBack) {
        List<Task> tasks = new Select().from(Task.class).where("TaskList = ?", task.getTaskListId()).execute();
        for (Task taskObj : tasks) {
            if (taskObj.getId() == task.getId()) {
                task.setName(task.getName());
                task.setFinished(task.isFinished());
                task.setDate(task.getDate());
                task.setTime(task.getTime());
                task.setTaskListId(task.getTaskListId());
                task.save();
                callBack.onSuccess(task);
            }
        }
    }

    @Override
    public void deleteTask(long taskId, RepoCallBack<Task> callBack) {
        new Delete().from(Task.class).where("taskId = ?", taskId).execute();
        callBack.onFailure(null);
    }
}
