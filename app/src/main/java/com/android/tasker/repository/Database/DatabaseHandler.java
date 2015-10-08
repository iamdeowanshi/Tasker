package com.android.tasker.repository.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sibi on 06/10/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int databaseVersion = 1;
    private static final String databaseName = "Tasker";
    private static final String tableUser = "user";
    private static final String tableTaskList = "taskList";
    private static final String tableTask = "task";

    public static final String userId = "userId";
    public static final String userName = "userName";
    public static final String userEmail = "userEmail";
    public static final String userPassword = "userPassword";
    public static final String userImage = "userImage";

    public static final String taskListId = "taskListId";
    public static final String taskListName = "taskListName";

    private static final String taskId = "taskId";
    private static final String taskName = "taskName";
    private static final String taskDate = "taskDate";
    private static final String taskTime = "taskTime";
    private static final String taskFinished = "taskFinished";

    public DatabaseHandler(Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + tableUser + "("
                + userId + " INTEGER PRIMARY KEY AUTOINCREMENT," + userName + " TEXT,"
                + userEmail + " TEXT" + userPassword + " VARCHAR(20)," + userImage + " VARCHAR" + ")";
        sqLiteDatabase.execSQL(createUserTable);

        String createTaskListTable = "CREATE TABLE " + tableTaskList + "("
                + taskListId + " INTEGER PRIMARY KEY AUTOINCREMENT," + taskListName + " TEXT,"
                + userId + " INTEGER" + ")";
        sqLiteDatabase.execSQL(createTaskListTable);

        String createTaskTable = "CREATE TABLE " + tableTask + "("
                + taskId + " INTEGER PRIMARY KEY AUTOINCREMENT," + taskName + " TEXT,"
                + taskDate + " VARCHAR" + taskTime + " VARCHAR," + taskFinished + " INTEGER" + taskListId + " INTEGER" + ")";
        sqLiteDatabase.execSQL(createTaskTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableUser);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableTaskList);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableTask);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

}
