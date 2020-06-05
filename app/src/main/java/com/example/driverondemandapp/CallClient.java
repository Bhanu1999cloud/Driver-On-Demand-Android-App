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
import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;

import java.util.ArrayList;

public class CallClient extends Fragment {
    ListView callclient ;
    ArrayList<CallC> calllist;
    ArrayAdapter<CallC> adapter;
    CallC call;
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
        Cursor cursor=sq.query(DriverConstant.TABLE_NAME,null,null,null,null,null,null);
        while (cursor!=null && cursor.moveToNext())
        {
            String nm=cursor.getString(cursor.getColumnIndex(DriverConstant.COL_NAME));
            String ph=cursor.getString(cursor.getColumnIndex(DriverConstant.COL_PHONE));
            call=new CallC(nm,ph);
            calllist.add(call);
        }
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, calllist);
        callclient.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.callclient,null,false);
        callclient=view.findViewById(R.id.callclient);
        calllist=new ArrayList<>();
        populateList();
        callclient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
call=calllist.get(i);
String nam=call.getName();
String ph=call.getPhone();
                Intent intent=new Intent(Intent.ACTION_CALL);
                Uri number=Uri.parse("tel:"+ph);
                intent.setData(number);
                startActivity(intent);
            }
        });
        return view;
    }
}
