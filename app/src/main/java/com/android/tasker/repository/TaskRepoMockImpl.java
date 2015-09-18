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
        // sample task lists
        taskLists.add(new TaskList(1, "Personal", 1));
        taskLists.add(new TaskList(2, "Work", 1));
        taskLists.add(new TaskList(3, "Default", 1));

        // sample tasks
        tasks.add(new Task(1, "Task One", "18:09:2015", "09:56:00", false, 1));
        tasks.add(new Task(2, "Task Two", "18:09:2015", "09:56:00", true, 1));
        tasks.add(new Task(3, "Task Three", "18:09:2015", "09:56:00", false, 2));
        tasks.add(new Task(4, "Task Four", "18:09:2015", "09:56:00", false, 2));
        tasks.add(new Task(5, "Task Five", "18:09:2015", "09:56:00", true, 3));
        tasks.add(new Task(6, "Task Six", "18:09:2015", "09:56:00", false, 3));
    }

    @Override
    public User register(User user) {
        this.user = user;
        user.setId(1);

        return user;
    }

    @Override
    public User login(String email, String password) {
        if (user.getEmail().equals(email) &&  user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    @Override
    public User updateProfilePic(String profilePicUrl) {
        user.setImage(profilePicUrl);

        return user;
    }

    @Override
    public List<TaskList> getTaskLists() {
        return taskLists;
    }

    @Override
    public TaskList createTaskList(TaskList list) {
        int lastId = taskLists.get(taskLists.size() - 1).getId();

        list.setId(lastId + 1);
        list.setUserId(1);
        taskLists.add(list);

        return list;
    }

    @Override
    public TaskList updateTaskList(TaskList list) {
        for (TaskList taskList : taskLists) {
            if (taskList.getId() == list.getId()) {
                taskList.setName(list.getName());

                return taskList;
            }
        }

        return null;
    }

    @Override
    public TaskList deleteTaskList(int taskListId) {
        for (TaskList taskList : taskLists) {
            if (taskList.getId() == taskListId) {
                taskLists.remove(taskList);

                return taskList;
            }
        }

        return null;
    }

    @Override
    public List<Task> getTasks(int taskListId) {
        List<Task> tasksOfList = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getTaskListId() == taskListId) {
                tasksOfList.add(task);
            }
        }

        return tasksOfList;
    }

    @Override
    public Task createTask(Task task) {
        int lastId = tasks.get(tasks.size() - 1).getId();
        task.setId(lastId + 1);
        tasks.add(task);

        return task;
    }

    @Override
    public Task updateTask(Task taskToUpdate) {
        for (Task task : tasks) {
            if (task.getId() == taskToUpdate.getId()) {
                task.setName(taskToUpdate.getName());
                task.setDate(taskToUpdate.getDate());
                task.setTime(taskToUpdate.getTime());
                task.setFinished(taskToUpdate.isFinished());
                task.setTaskListId(taskToUpdate.getTaskListId());

                return task;
            }
        }

        return null;
    }

    @Override
    public Task deleteTask(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                tasks.remove(task);

                return task;
            }
        }

        return null;
    }

}
