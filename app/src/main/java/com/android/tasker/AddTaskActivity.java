package com.android.tasker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.android.tasker.model.TaskList;
import com.android.tasker.repository.RepositoryFactory;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Sibi on 15/09/15.
 */
public class AddTaskActivity extends AppCompatActivity {

    private Spinner list;
    private EditText edtDate;
    private EditText edtTaskName;
    private EditText edtTime;
    private Button buttonAdd;
    private List<TaskList> taskListItems;
    private ImageView imageAddList;
    int mDay,mMonth,mYear;
    TaskList taskList;
    int min,hr;


    @Override
    public String toString() {
        return taskList.getName();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);


        taskListItems = RepositoryFactory.getTaskRepo().getTaskLists();
        list = (Spinner) findViewById(R.id.listSpinner);



        ArrayAdapter<TaskList> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, taskListItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);



        edtTaskName = (EditText) findViewById(R.id.editTextTask);
        edtDate = (EditText) findViewById(R.id.editTextDate);
        edtTime = (EditText) findViewById(R.id.editTextTime);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        imageAddList = (ImageView) findViewById(R.id.addList);

        imageAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(AddTaskActivity.this).create(); //Read Update
                dialog.setContentView(R.layout.add_list);
                dialog.setTitle("Add New Task List");

                final EditText editTextKeywordToBlock=(EditText)dialog.findViewById(R.id.editTextAddList);
                Button buttonAddList=(Button)dialog.findViewById(R.id.buttonAddList);
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

                DatePickerDialog mDatePicker = new DatePickerDialog(AddTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
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

                TimePickerDialog mTimePicker = new TimePickerDialog(AddTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hr, int min) {
                        edtTime.setText(hr + ":" + min);
                    }
                }, hr, min, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }


        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = edtTaskName.getText().toString();
                String date = edtDate.getText().toString();
                String time = edtTime.getText().toString();
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
