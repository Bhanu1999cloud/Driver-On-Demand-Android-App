package com.example.driverondemandapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ShowClientDetails extends AppCompatActivity {
    EditText txt3,txt4,txt5;
    TextView  txt1,txt2,txtedit,txtupdate;
    Intent intent;

    FloatingActionButton edit,update,delete;
DriverManager manager;
SQLiteDatabase sq;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_client_details);
        manager = new DriverManager(this);
        sq = manager.OpenDb();
        txtedit = findViewById(R.id.txtedit);
        txtupdate = findViewById(R.id.txtupdate);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        edit = findViewById(R.id.edit);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        txt3.setEnabled(false);
        txt4.setEnabled(false);
        txt5.setEnabled(false);
        intent = getIntent();
        String d1 = intent.getStringExtra("id");
        String d2 = intent.getStringExtra("name");
        String d3 = intent.getStringExtra("email");
        String d4 = intent.getStringExtra("phone");
        String d5 = intent.getStringExtra("address");
        txt1.setText(d1);
        txt2.setText(d2);
        txt3.setText(d3);
        txt4.setText(d4);
        txt5.setText(d5);
        txtupdate.setVisibility(View.GONE);
        update.setVisibility(View.GONE);
        edit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View view) {

                txt3.setEnabled(true);
                txt4.setEnabled(true);
                txt5.setEnabled(true);
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
                snackbar.setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = txt1.getText().toString();
                        String email = txt3.getText().toString();
                        String phone = txt4.getText().toString();
                        String address = txt4.getText().toString();
                        String[] args = {
                                id
                        };
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DriverConstant.COL_EMAIL, email);
                        contentValues.put(DriverConstant.COL_PHONE, phone);
                        contentValues.put(DriverConstant.COL_ADDRESS, address);
                        int rw = sq.update(DriverConstant.TABLE_NAME, contentValues, DriverConstant.COL_ID + "=?", args);
                        if (rw > 0) {
                            Toast.makeText(ShowClientDetails.this, "Table Updated Successfully", Toast.LENGTH_SHORT).show();

                        }
                        update.setVisibility(View.GONE);
                        edit.setVisibility(View.VISIBLE);
                        txtupdate.setVisibility(View.GONE);
                        txtedit.setVisibility(View.VISIBLE);
                        txt3.setEnabled(false);
                        txt4.setEnabled(false);
                        txt5.setEnabled(false);

                    }

                });
                View v = snackbar.getView();
                TextView action = v.findViewById(com.google.android.material.R.id.snackbar_action);
                action.setTypeface(action.getTypeface(), Typeface.BOLD_ITALIC);
                action.setTextSize(15);
                TextView textView = v.findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.setActionTextColor(Color.RED);
                textView.setTypeface(action.getTypeface(), Typeface.BOLD);
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
                        String id = txt1.getText().toString();
                        String[] args = {
                                id
                        };
                        int rw = sq.delete(DriverConstant.TABLE_NAME, DriverConstant.COL_ID + "=?", args);
                        if (rw > 0) {
                            Toast.makeText(ShowClientDetails.this, "Record Deleted", Toast.LENGTH_SHORT).show();

                        }

                    }


                });

                View v = snackbar.getView();
                TextView action = v.findViewById(com.google.android.material.R.id.snackbar_action);
                action.setTypeface(action.getTypeface(), Typeface.BOLD_ITALIC);
                action.setTextSize(15);
                TextView textView = v.findViewById(com.google.android.material.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.setActionTextColor(Color.BLUE);
                textView.setTypeface(action.getTypeface(), Typeface.BOLD);
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
