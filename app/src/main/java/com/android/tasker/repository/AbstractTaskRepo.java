package com.android.tasker.repository;

/**
 * Created by Sibi on 18/09/15.
 */
public abstract class AbstractTaskRepo implements TaskRepoInterface {

    protected int userId;
    protected String accessToken;

    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public void setAccessToken() {

    }

    @Override
    public String getAccessToken() {
        return null;
    }

    @Override
    public void setUserId() {

    }

}
