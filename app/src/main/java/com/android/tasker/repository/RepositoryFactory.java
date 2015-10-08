package com.android.tasker.repository;

import com.android.tasker.repository.NetworkApi.TaskRepoNetworkImpl;

/**
 * Created by Sibi on 17/09/15.
 */
public class RepositoryFactory {

    private static TaskRepoInterface repo;

    public static TaskRepoInterface getTaskRepo() {
        if (repo == null) {
            repo = new TaskRepoNetworkImpl();
        }

        return repo;
    }

}
