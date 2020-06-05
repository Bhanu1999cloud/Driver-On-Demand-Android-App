package com.example.driverondemandapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.driverondemandapp.com.beans.Driver;
import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;

import java.util.ArrayList;

public class ViewDriverFragment extends Fragment {
    ListView list;
    ArrayList<Driver> driverlist;
    ArrayAdapter<Driver> adapter;
    Driver driver;
    DriverManager manager;
    SQLiteDatabase sq;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=new DriverManager(getContext());
        sq=manager.OpenDb();
    }
    public void populatelist() {
        Cursor cursor = sq.query(DriverConstant.T_NAME, null, null, null, null, null, null);

        while (cursor != null && cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex(DriverConstant.C_ID));
            String name = cursor.getString(cursor.getColumnIndex(DriverConstant.C_NAME));
            String email=cursor.getString(cursor.getColumnIndex(DriverConstant.C_MAIL));
            String phone = cursor.getString(cursor.getColumnIndex(DriverConstant.C_PHONE));
            String age=cursor.getString(cursor.getColumnIndex(DriverConstant.C_AGE));
            String gender = cursor.getString(cursor.getColumnIndex(DriverConstant.C_GENDER));
           String address=cursor.getString(cursor.getColumnIndex(DriverConstant.C_ADDRESS));

            driver =new Driver(id,name,email,phone,age,gender,address);
            driverlist.add(driver);

        }

      adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, driverlist);
        list.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewdriverfragment,null,false);
        list=view.findViewById(R.id.list);
        driverlist=new ArrayList<>();
        populatelist();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                driver = driverlist.get(i);
                String did = driver.getId();
                String dname = driver.getName();
                String demail = driver.getEmail();
                String dphone = driver.getPhone();
                String dage = driver.getAge();
                String dgender = driver.getGender();
                String daddress = driver.getAddress();
                Intent intent = new Intent(getContext(), ShowDriverDetails.class);
                intent.putExtra("driverid", did);
                intent.putExtra("drivername", dname);
                intent.putExtra("driveremail", demail);
                intent.putExtra("driverphone", dphone);
               intent.putExtra("driverage",dage);
                intent.putExtra("drivergender", dgender);
                intent.putExtra("driveraddress", daddress);
                startActivity(intent);
            }
        });
        return view;
    }
}
