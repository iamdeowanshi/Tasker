package com.android.tasker.repository;

/**
 * Created by Sibi on 17/09/15.
 */
public class RepositoryFactory {

    private static TaskRepoInterface repo;

    public static TaskRepoInterface getTaskRepo() {
        if (repo == null) {
            new TaskRepoMock();
        }

        return repo;
    }

}
