package com.android.tasker.repository;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sibi on 17/09/15.
 */
public class TaskRepoMockImpl extends AbstractTaskRepo {

    private User user;
    private List<TaskList> taskLists = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    public TaskRepoMockImpl() {
        user = new User(1, "Aaditya", "aadity@tecol.com", "1234", "https://pbs.twimg.com/profile_images/2156251289/MrBean.jpg", "AQ12adakfine#Fsdlj");

        // sample activity_task_list lists
        taskLists.add(new TaskList(0, "Finished" ,1));
        taskLists.add(new TaskList(1, "Personal", 1));
        taskLists.add(new TaskList(2, "Work", 1));
        taskLists.add(new TaskList(3, "Default", 1));

        // sample tasks
        tasks.add(new Task(1, "Task One", "18:09:2015", "09:56:00", false, 1));
        tasks.add(new Task(2, "Task Two", "18:09:2015", "09:56:00", false, 1));
        tasks.add(new Task(3, "Task Three", "18:09:2015", "09:56:00", false, 2));
        tasks.add(new Task(4, "Task Four", "18:09:2015", "09:56:00", false, 2));
        tasks.add(new Task(5, "Task Five", "18:09:2015", "09:56:00", false, 3));
        tasks.add(new Task(6, "Task Six", "18:09:2015", "09:56:00", false, 3));
    }

    @Override
    public void register(User user, RepoCallBack<User> callBack) {
        this.user = user;
        user.setUserId(1);

        callBack.onSuccess(user);
    }

    @Override
    public void login(String email, String password, RepoCallBack<User> callBack) {
        if (user != null && user.getEmail().equals(email) &&  user.getPassword().equals(password)) {
            callBack.onSuccess(user);
        }

        callBack.onFailure(null);
    }

    @Override
    public void updateProfilePic(String profilePicUrl, RepoCallBack<User> callBack) {
       // user.setImage(profilePicUrl);

        callBack.onSuccess(user);
    }

    @Override
    public void getTaskLists(RepoCallBack<List<TaskList>> callBack) {
        callBack.onSuccess(taskLists);
    }

    @Override
    public void createTaskList(TaskList list, RepoCallBack<TaskList> callBack) {
        long lastId = taskLists.get(taskLists.size() - 1).getTaskListId();

        list.setTaskListId(lastId + 1);
        list.setUserId(1);
        taskLists.add(list);

        callBack.onSuccess(list);
    }

    @Override
    public void updateTaskList(TaskList list, RepoCallBack<TaskList> callBack) {
        for (TaskList taskList : taskLists) {
            if (taskList.getTaskListId() == list.getTaskListId()) {
                taskList.setName(list.getName());

                callBack.onSuccess(taskList);
            }
        }

        callBack.onFailure(null);
    }

    @Override
    public void deleteTaskList(int taskListId, RepoCallBack<TaskList> callBack) {
        for (TaskList taskList : taskLists) {
            if (taskList.getTaskListId() == taskListId) {
                taskLists.remove(taskList);

                callBack.onSuccess(taskList);
            }
        }

        callBack.onFailure(null);
    }

    @Override
    public void getTasks(long taskListId, RepoCallBack<List<Task>> callBack) {
        List<Task> tasksOfList = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getTaskListId() == taskListId) {
                tasksOfList.add(task);
            }
        }

        callBack.onSuccess(tasksOfList);
    }

    @Override
    public void createTask(Task task, RepoCallBack<Task> callBack) {
        long lastId = tasks.get(tasks.size() - 1).getTaskId();
        task.setTaskId(lastId + 1);
        tasks.add(task);

        callBack.onSuccess(task);
    }

    @Override
    public void updateTask(Task taskToUpdate, RepoCallBack<Task> callBack) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskToUpdate.getTaskId()) {
                task.setName(taskToUpdate.getName());
                task.setDate(taskToUpdate.getDate());
                task.setTime(taskToUpdate.getTime());
                task.setFinished(taskToUpdate.isFinished());
                task.setTaskListId(taskToUpdate.getTaskListId());

                callBack.onSuccess(task);
                return;
            }
        }

        callBack.onFailure(null);
    }

    @Override
    public void deleteTask(long taskId, RepoCallBack<Task> callBack) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                tasks.remove(task);
                callBack.onSuccess(task);
                return;
            }
        }

        callBack.onFailure(null);
    }

}
