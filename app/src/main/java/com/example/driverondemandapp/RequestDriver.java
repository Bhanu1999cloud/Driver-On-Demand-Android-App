package com.example.driverondemandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.driverondemandapp.com.beans.Driver;
import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;

import java.text.DateFormat;
import java.util.Calendar;

public class RequestDriver extends AppCompatActivity {
    SQLiteDatabase sq;
    DriverManager manager;
    EditText clid,to,from,date;
    Button btnsubmit;
    Intent intent;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour,currentMinute;
    String ampm;
    int year;
    int month;
    int dayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_driver);
        clid=findViewById(R.id.clid);
        date=findViewById(R.id.date);
        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        btnsubmit=findViewById(R.id.btnsubmit);
        clid.setEnabled(false);
        manager =new DriverManager(this);
        sq=manager.OpenDb();
        intent=getIntent();
        String cid=intent.getStringExtra("clientid");
       clid.setText(cid);

date.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
     DatePickerDialog datePickerDialog = new DatePickerDialog(RequestDriver.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }
});


       from.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               calendar=Calendar.getInstance();
               currentHour=calendar.get(Calendar.HOUR_OF_DAY);
               currentMinute=calendar.get(Calendar.MINUTE);
               timePickerDialog=new TimePickerDialog(RequestDriver.this, new TimePickerDialog.OnTimeSetListener() {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int i, int i1) {
                       if(i >=12) {
                           ampm = "PM";
                       }
                       else
                           ampm="AM";
                       from.setText(String.format("%02d:%02d",i, i1)+ampm);
                   }
               },currentHour,currentMinute,false);
               timePickerDialog.show();
           }
       });
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar=Calendar.getInstance();
                currentHour=calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute=calendar.get(Calendar.MINUTE);
            timePickerDialog=new TimePickerDialog(RequestDriver.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    if(i >=12) {
                        ampm = "PM";
                    }
                    else
                        ampm="AM";
                    to.setText(String.format("%02d:%02d",i, i1)+ampm);
                }
            },currentHour,currentMinute,false);
            timePickerDialog.show();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date.length()==0 || from.length()==0 || to.length()==0)
                {
                  btnsubmit.setError("Please fill all the details");
                }
                else {
                    String cliid = clid.getText().toString();
                    String da = date.getText().toString();
                    String fr = from.getText().toString();
                    String t = to.getText().toString();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DriverConstant.CID, cliid);
                    contentValues.put(DriverConstant.COL_DATE, da);
                    contentValues.put(DriverConstant.FROM, fr);
                    contentValues.put(DriverConstant.TO, t);
                    long row = sq.insert(DriverConstant.NAME, null, contentValues);
                    if (row > 0) {
                        Toast.makeText(RequestDriver.this, cliid + "" + da + " " + fr + " " + t, Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(RequestDriver.this, AssignDriver.class);
                    intent.putExtra("cid", cliid);
                    intent.putExtra("date", da);
                    intent.putExtra("from", fr);
                    intent.putExtra("to", t);
                    startActivity(intent);
                }

            }
        });
    }
}
