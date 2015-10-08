package com.android.tasker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.tasker.model.Task;
import com.android.tasker.model.TaskList;
import com.android.tasker.repository.RepoCallBack;
import com.android.tasker.repository.RepositoryFactory;
import com.android.tasker.repository.TaskRepoInterface;

import java.util.Calendar;
import java.util.List;

public class UpdateTask extends AppCompatActivity {

    private Spinner spinner;
    private EditText edtDate;
    private EditText edtTaskName;
    private EditText edtTime;
    private Button buttonUpdate;
    private CheckBox checkBoxFinished;
    private List<TaskList> taskListItems;
    private ImageView imageAddList;
    int mDay, mMonth, mYear;
    int min, hr;
    private Context context = this;
    private List<Task> taskItems;
    private Task task;

    RepoCallBack<TaskList> createTaskListRepoCallBack = new RepoCallBack<TaskList>() {
        @Override
        public void onSuccess(TaskList data) {

        }

        @Override
        public void onFailure(Throwable tr) {

        }
    };

    RepoCallBack<Task> updateTaskCallBack = new RepoCallBack<Task>() {
        @Override
        public void onSuccess(Task data) {

        }

        @Override
        public void onFailure(Throwable tr) {

        }
    };

    RepoCallBack<List<TaskList>> listRepoCallBack = new RepoCallBack<List<TaskList>>() {
        @Override
        public void onSuccess(List<TaskList> data) {
            taskListItems = data;
        }

        @Override
        public void onFailure(Throwable tr) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        final Task task = getIntent().getExtras().getParcelable("Task");

        RepositoryFactory.getTaskRepo().getTaskLists(listRepoCallBack);
        spinner = (Spinner) findViewById(R.id.listSpinner);


        ArrayAdapter<TaskList> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, taskListItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        edtTaskName = (EditText) findViewById(R.id.editTextTask);
        edtDate = (EditText) findViewById(R.id.editTextDate);
        edtTime = (EditText) findViewById(R.id.editTextTime);
        buttonUpdate = (Button) findViewById(R.id.buttonAdd);
        imageAddList = (ImageView) findViewById(R.id.addList);
        checkBoxFinished = (CheckBox) findViewById(R.id.checkBoxFinished);
        long taskListId = task.getTaskListId();

        edtTaskName.setText(task.getName());
        edtDate.setText(task.getDate());
        edtTime.setText(task.getTime());
        checkBoxFinished.setChecked(task.isFinished());
        spinner.setSelection((int) taskListId);


        imageAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a Dialog component
                final Dialog dialog = new Dialog(context);

                //tell the Dialog to use the dialog.xml as it's layout description
                dialog.setContentView(R.layout.add_list);
                dialog.setTitle("Add New TaskList");

                final EditText listEditText = (EditText) dialog.findViewById(R.id.editTextAddList);
                Button addButton = (Button) dialog.findViewById(R.id.buttonAddList);

                final TaskRepoInterface repo = RepositoryFactory.getTaskRepo();
                final TaskList taskList = new TaskList();

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String listName = listEditText.getText().toString();


                        if (listName.equals(null) || listName == "" || listName.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Enter Name", Toast.LENGTH_SHORT).show();
                        } else {

                            taskList.setName(listName);
                            repo.createTaskList(taskList, createTaskListRepoCallBack);
                            spinner.setSelection((int) taskList.getTaskListId() - 1);
                            String message = taskList.getName();
                            Toast.makeText(getApplicationContext(), "is:" + message, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });


                dialog.show();
            }

        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar currentDate = Calendar.getInstance();
                mYear = currentDate.get(Calendar.YEAR);
                mMonth = currentDate.get(Calendar.MONTH);
                mDay = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(UpdateTask.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        edtDate.setText(mDay + "-" + mMonth + "-" + mYear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();
                min = currentTime.get(Calendar.MINUTE);
                hr = currentTime.get(Calendar.HOUR);

                TimePickerDialog mTimePicker = new TimePickerDialog(UpdateTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hr, int min) {
                        edtTime.setText(hr + ":" + min);
                    }
                }, hr, min, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }


        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = edtTaskName.getText().toString();
                String date = edtDate.getText().toString();
                String time = edtTime.getText().toString();
                boolean finished = checkBoxFinished.isChecked();
                long taskListId = spinner.getSelectedItemPosition();
                long id = task.getTaskId();

                if (finished) {
                    taskListId = 3;
                    Toast.makeText(getApplicationContext(), "Added to Finished List", Toast.LENGTH_SHORT).show();
                }
                Task task = new Task(id, taskName, date, time, finished, taskListId);
                RepositoryFactory.getTaskRepo().updateTask(task, updateTaskCallBack);
                finish();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
