package com.android.tasker.repository;

/**
 * Created by Sibi on 18/09/15.
 */
public abstract class AbstractTaskRepo implements TaskRepoInterface {

    protected long userId;
    protected String accessToken;

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public void setUserId(long id) {
        this.userId = id;
    }

}
