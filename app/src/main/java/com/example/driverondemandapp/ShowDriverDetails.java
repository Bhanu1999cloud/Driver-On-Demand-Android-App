package com.example.driverondemandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ShowDriverDetails extends AppCompatActivity {
FloatingActionButton edit,update,delete;
TextView drid,name,gender;
EditText email,phone,age,address;
    Intent intent;
    DriverManager manager;
    SQLiteDatabase sq;
    TextView txtupdate,txtedit;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_driver_details);
        manager = new DriverManager(this);
        sq = manager.OpenDb();
        txtedit = findViewById(R.id.txtedit);
        txtupdate = findViewById(R.id.txtupdate);
        drid = findViewById(R.id.drid);
        name= findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        email = findViewById(R.id.email);
        phone= findViewById(R.id.phone);
        age=findViewById(R.id.age);
        address = findViewById(R.id.address);
        edit=findViewById(R.id.edit);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        email.setEnabled(false);
        phone.setEnabled(false);
        age.setEnabled(false);
        address.setEnabled(false);
        intent = getIntent();
        String d1 = intent.getStringExtra("driverid");
        String d2 = intent.getStringExtra("drivername");
        String d3 = intent.getStringExtra("driveremail");
        String d4 = intent.getStringExtra("driverphone");
       String d5=intent.getStringExtra("driverage");
        String d6 = intent.getStringExtra("drivergender");
        String d7=intent.getStringExtra("driveraddress");
        drid.setText(d1);
        name.setText(d2);
        email.setText(d3);
        phone.setText(d4);
        age.setText(d5);
        gender.setText(d6);
        address.setText(d7);
        txtupdate.setVisibility(View.GONE);
        update.setVisibility(View.GONE);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email.setEnabled(true);
                phone.setEnabled(true);
                age.setEnabled(true);
                address.setEnabled(true);
                edit.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);
                txtedit.setVisibility(View.GONE);
                txtupdate.setVisibility(View.VISIBLE);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Snackbar snackbar = Snackbar.make(view, "DO YOU REALLY WISH TO UPDATE?", Snackbar.LENGTH_LONG);
                snackbar.setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = drid.getText().toString();
                        String mail = email.getText().toString();
                        String ph = phone.getText().toString();
                        String a=age.getText().toString();
                        String add =address.getText().toString();
                        String[] args = {
                                id
                        };
                        ContentValues contentValues = new ContentValues();
                     contentValues.put(DriverConstant.C_MAIL,mail);
                     contentValues.put(DriverConstant.C_PHONE,ph);
                     contentValues.put(DriverConstant.C_AGE,a);
                     contentValues.put(DriverConstant.C_ADDRESS,add);
                        int rw = sq.update(DriverConstant.T_NAME, contentValues, DriverConstant.C_ID + "=?", args);
                        if (rw > 0) {
                            Toast.makeText(ShowDriverDetails.this, "Table Updated Successfully", Toast.LENGTH_SHORT).show();

                        }
                        update.setVisibility(View.GONE);
                        edit.setVisibility(View.VISIBLE);
                        email.setEnabled(false);
                        phone.setEnabled(false);
                        age.setEnabled(false);
                        address.setEnabled(false);
                        txtedit.setVisibility(View.VISIBLE);
                        txtupdate.setVisibility(View.GONE);
                    }
                });
                snackbar.show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Snackbar snackbar = Snackbar.make(view, "Do YOU WANT TO DELETE RECORD PERMANENTLY?", Snackbar.LENGTH_LONG);
                snackbar.setAction("YES", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = drid.getText().toString();
                        String[] args = {
                                id
                        };
                        int rw = sq.delete(DriverConstant.T_NAME, DriverConstant.C_ID + "=?", args);
                        if (rw > 0) {
                            Toast.makeText(ShowDriverDetails.this, "Record Deleted", Toast.LENGTH_SHORT).show();

                        }

                    }


                });

                snackbar.show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(manager!=null)
            manager.CloseDb();
    }
}
