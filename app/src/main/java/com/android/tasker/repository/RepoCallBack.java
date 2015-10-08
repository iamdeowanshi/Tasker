package com.android.tasker.repository;

/**
 * Created by Sibi on 05/10/15.
 */
public interface RepoCallBack<T> {

    void onSuccess(T data);

    void onFailure(Throwable tr);

}
