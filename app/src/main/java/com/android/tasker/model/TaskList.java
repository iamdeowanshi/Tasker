package com.android.tasker.model;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sibi on 16/09/15.
 */
@Table(name = "TaskList")
public class TaskList extends BaseModel {

    @Column(name = "taskListId")
    private long taskListId;
    @Column(name = "taskListName")
    private String name;
    @Column(name = "userId")
    private long userId;

    public TaskList() {
    }

    public TaskList(int taskListId, String name, int userId) {
        this.taskListId = taskListId;
        this.name = name;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return getName();
    }

    public long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(long taskListId) {
        this.taskListId = taskListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toJSON() {
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name", name);
            jsonObj.put("taskListId", taskListId);
            jsonObj.put("user_id", userId);

            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TaskList fromJSON(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            setName(jsonObj.getString("name"));
            setTaskListId(jsonObj.getLong("taskListId"));
            setUserId(jsonObj.getInt("user_id"));

            return this;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
