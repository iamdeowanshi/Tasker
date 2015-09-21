package com.android.tasker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Sibi on 15/09/15.
 */
public class AddActivity extends AppCompatActivity {

    private Spinner list;
    private EditText edtDate;
    private EditText taskName;
    private EditText edtTime;
    int mDay,mMonth,mYear;
    int min,hr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        list = (Spinner) findViewById(R.id.listSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);

        taskName = (EditText) findViewById(R.id.editTextTask);
        edtDate = (EditText) findViewById(R.id.editTextDate);
        edtTime = (EditText)findViewById(R.id.editTextTime);

      edtDate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Calendar currentDate = Calendar.getInstance();
              mYear = currentDate.get(Calendar.YEAR);
              mMonth = currentDate.get(Calendar.MONTH);
              mDay = currentDate.get(Calendar.DAY_OF_MONTH);

              DatePickerDialog mDatePicker = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                  public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                  }
              }, mYear, mMonth, mDay);
              mDatePicker.setTitle("Select date");
              mDatePicker.show();
          }
      });

        edtDate.setText(mDay + "-" + mMonth + "-" + mYear);

        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar currentTime = Calendar.getInstance();
                min = currentTime.get(Calendar.MINUTE);
                hr = currentTime.get(Calendar.HOUR);

                TimePickerDialog mTimePicker = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hr, int min) {
                        edtTime.setText( hr + ":" + min);
                    }
                }, hr, min, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }


    });
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


}
