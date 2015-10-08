package com.android.tasker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sibi on 16/09/15.
 */
@Table(name = "Task")
public class Task extends BaseModel implements Parcelable {

    @Column(name = "taskId")
    private long taskId;
    @Column(name = "taskName")
    private String name;
    @Column(name = "taskDate")
    private String date;
    @Column(name = "taskTime")
    private String time;
    @Column(name = "taskFinished")
    private boolean finished;
    @Column(name = "TaskList")
    private long taskListId;

    public Task() {

    }

    public Task(String name, String date, String time, boolean finished, int taskListId) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.finished = finished;
        this.taskListId = taskListId;
    }

    public Task(long taskId, String name, String date, String time, boolean finished, long taskListId) {
        this.taskId = taskId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.finished = finished;
        this.taskListId = taskListId;
    }

    protected Task(Parcel in) {
        taskId = in.readInt();
        name = in.readString();
        date = in.readString();
        time = in.readString();
        finished = in.readByte() != 0;
        taskListId = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public String toString() {
        return getName();
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(long taskListId) {
        this.taskListId = taskListId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(taskId);
        parcel.writeString(name);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeByte((byte) (finished ? 1 : 0));
        parcel.writeLong(taskListId);

    }

    @Override
    public String toJSON() {

        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name", name);
            jsonObj.put("date", date);
            jsonObj.put("time", time);
            jsonObj.put("finished", finished);
            jsonObj.put("taskId", taskId);
            jsonObj.put("task_list_id", taskListId);

            return jsonObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Task fromJSON(String json) {
        try {
            JSONObject jsonObj = new JSONObject(json);
            setName(jsonObj.getString("name"));
            setDate(jsonObj.getString("date"));
            setTime(jsonObj.getString("time"));
            setFinished(jsonObj.getBoolean("finished"));
            setTaskId(jsonObj.getInt("taskId"));
            setTaskListId(jsonObj.getInt("task_list_id"));

            return this;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
