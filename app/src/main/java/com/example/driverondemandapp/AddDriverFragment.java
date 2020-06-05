package com.example.driverondemandapp;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;

public class AddDriverFragment extends Fragment {
    DriverManager manager;
    SQLiteDatabase sq;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=new DriverManager(getActivity());
        sq=manager.OpenDb();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.adddriverfragment,null,false);
        final Button btnadd,btnview;
        final EditText driverid, drivername, drivermail, driverphone, driverage, driveraddress;
        final RadioGroup rdgender;
        btnadd = view.findViewById(R.id.btnadd);
        driverid = view.findViewById(R.id.driverid);
        drivername =view.findViewById(R.id.drivername);
        drivermail = view.findViewById(R.id.drivermail);
        driverphone = view.findViewById(R.id.driverphone);
        driverage = view.findViewById(R.id.driverage);
        driveraddress =view.findViewById(R.id.driveraddress);
        rdgender = view.findViewById(R.id.rdgender);
btnadd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (driverid.length() == 0 || drivername.length() == 0 || drivermail.length() == 0 || driverage.length() == 0 || driveraddress.length() == 0) {
            btnadd.setError("Please fill all the details");
        } else {
            String id = driverid.getText().toString();
            String name = drivername.getText().toString();
            String mail = drivermail.getText().toString();
            String phone = driverphone.getText().toString();
            String age = driverage.getText().toString();
            int genderid = rdgender.getCheckedRadioButtonId();
            RadioButton rb = rdgender.findViewById(genderid);
            String gender = rb.getText().toString();
            String address = driveraddress.getText().toString();
            ContentValues contentValues = new ContentValues();


            if (Integer.parseInt(age) < 20 || Integer.parseInt(age) > 45) {
                driverage.setError("Age Must Be Greater Than 20 and less than 45");
            } else {


                contentValues.put(DriverConstant.C_ID, id);
                contentValues.put(DriverConstant.C_NAME, name);
                contentValues.put(DriverConstant.C_MAIL, mail);
                contentValues.put(DriverConstant.C_PHONE, phone);
                contentValues.put(DriverConstant.C_AGE, age);
                contentValues.put(DriverConstant.C_GENDER, gender);
                contentValues.put(DriverConstant.C_ADDRESS, address);
                long row = sq.insert(DriverConstant.T_NAME, null, contentValues);
                if (row > 0) {
                    Toast.makeText(getContext(), "data added", Toast.LENGTH_SHORT).show();

                }
                driverid.setText(null);
                drivername.setText(null);
                drivermail.setText(null);
                driverphone.setText(null);
                driverage.setText(null);
                rdgender.clearCheck();
                driveraddress.setText(null);
            }

        }
    }

});

  return view;
}}
