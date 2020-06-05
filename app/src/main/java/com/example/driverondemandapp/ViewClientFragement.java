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

import com.example.driverondemandapp.com.beans.ClientBean;
import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;

import java.util.ArrayList;

public class ViewClientFragement extends Fragment{
    DriverManager manager;
    SQLiteDatabase sq;
    ListView listview;
    ArrayList<ClientBean>arrayList;
    ArrayAdapter<ClientBean>arrayAdapter;
    ClientBean clientBean;
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

            String id=cursor.getString(cursor.getColumnIndex(DriverConstant.COL_ID));
            String name=cursor.getString(cursor.getColumnIndex(DriverConstant.COL_NAME));
            String email=cursor.getString(cursor.getColumnIndex(DriverConstant.COL_EMAIL));
            String phone=cursor.getString(cursor.getColumnIndex(DriverConstant.COL_PHONE));
            String address=cursor.getString(cursor.getColumnIndex(DriverConstant.COL_ADDRESS));

            clientBean=new ClientBean(id,name,email,phone,address);
            arrayList.add(clientBean);

        }
        arrayAdapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,arrayList);
        listview.setAdapter(arrayAdapter);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewclientfragement,null,false);

        listview=view.findViewById(R.id.listview);
        arrayList=new ArrayList<>();
        populateList();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
 clientBean=arrayList.get(i);

 String cid=clientBean.getId();
 String cname=clientBean.getName();
 String cemail=clientBean.getEmail();
 String cphone=clientBean.getPhone();
 String caddress=clientBean.getAddress();


                Intent intent=new Intent(getContext(),ShowClientDetails.class);
                intent.putExtra("id",cid);
                intent.putExtra("name",cname);
                intent.putExtra("email",cemail);
                intent.putExtra("phone",cphone);
                intent.putExtra("address",caddress);
                startActivity(intent);

            }
        });



return view;
    }


}
