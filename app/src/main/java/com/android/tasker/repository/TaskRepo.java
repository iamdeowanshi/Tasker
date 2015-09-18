package com.android.tasker.repository;

/**
 * Created by Sibi on 18/09/15.
 */
public abstract class TaskRepo implements TaskRepoInterface{

    private int userId;
    private String accessToken;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
