package com.example.driverondemandapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.driverondemandapp.com.beans.Driver;
import com.example.driverondemandapp.com.dbtask.DriverConstant;
import com.example.driverondemandapp.com.dbtask.DriverManager;

import java.util.ArrayList;

public class AssignDriver extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
EditText cid,date,from,to,charge;
Button btnsubmit;
Intent intent;
    Spinner spinner;
    ArrayList<String> arrayList;
    SQLiteDatabase sq;
    DriverManager manager;
    String driverid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_driver);
       cid=findViewById(R.id.cid);
        date=findViewById(R.id.date);
        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        charge=findViewById(R.id.charge);
        spinner=findViewById(R.id.spinner);
        manager =new DriverManager(this);
        sq=manager.OpenDb();
        arrayList=new ArrayList<>();
        cid.setEnabled(false);
        date.setEnabled(false);
        from.setEnabled(false);
        to.setEnabled(false);
        intent=getIntent();
        String clid=intent.getStringExtra("cid");
        String dat=intent.getStringExtra("date");
        String fr=intent.getStringExtra("from");
        String too=intent.getStringExtra("to");
        cid.setText(clid);
        date.setText(dat);
        from.setText(fr);
        to.setText(too);
        btnsubmit=findViewById(R.id.btnsubmit);


        String q= "Select * From driverdetail where driverid not in(select driverid from assign Where date='"+dat+"' AND (('"+fr+"'>=ftime AND '"+fr+"' <=ttime) OR ('"+too+"' >=ftime AND '"+too+"' <ttime) OR (ftime >= '"+fr+"' AND ttime <= '"+too+"') OR (ttime >= '"+fr+"' AND ttime <= '"+too+"')))";
        Cursor cursor = sq.rawQuery(q, null);

        while (cursor!=null && cursor.moveToNext())
        {
            String drid=cursor.getString(cursor.getColumnIndex(DriverConstant.C_ID));
            arrayList.add(drid);
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clieid=cid.getText().toString();
                String d=date.getText().toString();
                String fro=from.getText().toString();
                String tooo=to.getText().toString();
                float charges=Float.parseFloat(charge.getText().toString());
                ContentValues contentValues=new ContentValues();
                contentValues.put(DriverConstant.D_ID,driverid);
                contentValues.put(DriverConstant.CL_ID,clieid);
                contentValues.put(DriverConstant.DATE,d);
                contentValues.put(DriverConstant.FR,fro);
                contentValues.put(DriverConstant.T,tooo);
                contentValues.put(DriverConstant.CHARGES,charges);
                long row=sq.insert(DriverConstant.ASSIGN,null,contentValues);
                if (row>0)
                {
                    Toast.makeText(AssignDriver.this, clieid+" "+d+" "+fro+" "+tooo+" "+charges, Toast.LENGTH_SHORT).show();

                }
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent=new Intent(AssignDriver.this,MainActivity.class);
                        startActivity(intent);

                    }
                },2000);


//               FragmentManager fm=getSupportFragmentManager();
//            HomeFragment fragment=new HomeFragment();
//            fm.beginTransaction().replace(R.id.frag,fragment).commit();


            }

        });

    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
driverid=arrayList.get(position);
        Toast.makeText(this, "Driver id in"+driverid, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
