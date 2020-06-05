package com.example.driverondemandapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

import com.example.driverondemandapp.com.beans.CallC;
import com.example.driverondemandapp.com.beans.CallD;
import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;

import java.util.ArrayList;

public class CallDriver extends Fragment {
    ListView calldriver ;
    ArrayList<CallD> list;
    ArrayAdapter<CallD> adapter;
    CallD calld;
    DriverManager manager;
    SQLiteDatabase sq;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager=new DriverManager(getContext());
        sq=manager.OpenDb();
    }
    public void populateList()
    {
        Cursor cursor=sq.query(DriverConstant.T_NAME,null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            String nm=cursor.getString(cursor.getColumnIndex(DriverConstant.C_NAME));
            String ph=cursor.getString(cursor.getColumnIndex(DriverConstant.C_PHONE));
            calld=new CallD(nm,ph);
            list.add(calld);
        }
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        calldriver.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.calldriver,null,false);
        calldriver=view.findViewById(R.id.calldriver);
        list=new ArrayList<>();
        populateList();
        calldriver.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
calld=list.get(i);
String nam=calld.getName();
String ph=calld.getPhone();
                Intent intent=new Intent(Intent.ACTION_CALL);
                Uri number=Uri.parse("tel:"+ph);
                intent.setData(number);
                startActivity(intent);
            }
        });
        return view;
    }
}
