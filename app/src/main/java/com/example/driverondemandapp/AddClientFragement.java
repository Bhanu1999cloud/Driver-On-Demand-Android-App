package com.example.driverondemandapp;

import android.content.ContentValues;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;



public class AddClientFragement extends Fragment {
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.addclientfragement,null,false);
        final EditText id,name,email,phone,address;
        final Button btnsubmit;
        ImageView imageView;
        id=view.findViewById(R.id.id);
        name=view.findViewById(R.id.name);
        email=view.findViewById(R.id.email);
        phone=view.findViewById(R.id.phone);
        address=view.findViewById(R.id.address);
        btnsubmit=view.findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id.length() == 0 || name.length() == 0 || email.length() == 0 || phone.length() == 0 || address.length() == 0) {
                    btnsubmit.setError("Please enter all the details");
                } else {
                    String cid = id.getText().toString();
                    String cname = name.getText().toString();
                    String cemail = email.getText().toString();
                    String cphone = phone.getText().toString();
                    String caddress = address.getText().toString();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DriverConstant.COL_ID, cid);
                    contentValues.put(DriverConstant.COL_NAME, cname);
                    contentValues.put(DriverConstant.COL_EMAIL, cemail);
                    contentValues.put(DriverConstant.COL_PHONE, cphone);
                    contentValues.put(DriverConstant.COL_ADDRESS, caddress);
                    long row = sq.insert(DriverConstant.TABLE_NAME, null, contentValues);

                    if (row > 0) {
                        Toast.makeText(getContext(), "Data Added", Toast.LENGTH_SHORT).show();

                    }

                    Intent intent = new Intent(getContext(), RequestDriver.class);
                    intent.putExtra("clientid", cid);
                    startActivity(intent);
                    id.setText(null);
                    name.setText(null);
                    email.setText(null);
                    phone.setText(null);
                    address.setText(null);
                }
            }
        });

        return view;

}}
