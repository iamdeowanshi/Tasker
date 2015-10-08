package com.android.tasker.repository.NetworkApi;

import android.util.Log;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.model.User;
import com.android.tasker.repository.AbstractTaskRepo;
import com.android.tasker.repository.RepoCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by Sibi on 17/09/15.
 */
public class TaskRepoNetworkImpl extends AbstractTaskRepo {
    private HttpClient httpClient = new HttpClient();

    @Override
    public void register(final User user, final RepoCallBack<User> callBack) {
        final String url = "http://todoapp.getsandbox.com/users";
        final String data = user.toJSON();
        new Thread(new Runnable() {
            public void run() {
                try {
                    String response = httpClient.post(url, data);
                    user.fromJSON(response);
                    callBack.onSuccess(user);

                } catch (IOException e) {
                    callBack.onFailure(null);
                }
            }
        }).start();

    }


    @Override
    public void login(String email, String password, final RepoCallBack<User> callBack) {
        final String url = "http://todoapp.getsandbox.com/auth/login";
        JSONObject jsonObj = new JSONObject();
        final User user = new User();
        String data = null;
        try {
            jsonObj.put("email", email);
            jsonObj.put("password", password);
            data = jsonObj.toString();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        final String finalData = data;
        Log.d("step", "1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("step", "2");
                    String response = httpClient.post(url, finalData);
                    Log.d("step", "3");
                    user.fromJSON(response);
                    callBack.onSuccess(user);

                } catch (IOException e) {
                    callBack.onFailure(null);
                }
            }
        }).start();
        Log.d("step", "4");
    }

    @Override
    public void updateProfilePic(String profilePicUrl, RepoCallBack<User> callBack) {

    }

    @Override
    public void getTaskLists(RepoCallBack<List<TaskList>> callBack) {

    }

    @Override
    public void createTaskList(TaskList list, RepoCallBack<TaskList> callBack) {

    }

    @Override
    public void updateTaskList(TaskList list, RepoCallBack<TaskList> callBack) {

    }

    @Override
    public void deleteTaskList(int taskListId, RepoCallBack<TaskList> callBack) {

    }

    @Override
    public void getTasks(long taskListId, RepoCallBack<List<Task>> callBack) {

    }

    @Override
    public void createTask(Task task, RepoCallBack<Task> callBack) {

    }

    @Override
    public void updateTask(Task task, RepoCallBack<Task> callBack) {

    }

    @Override
    public void deleteTask(long taskId, RepoCallBack<Task> callBack) {

    }

}
