package com.example.driverondemandapp.com.dbtask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.driverondemandapp.com.beans.Driver;

public class DriverHelper extends SQLiteOpenHelper {
    Context context;
    public DriverHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
sqLiteDatabase.execSQL(DriverConstant.TBL_QUERY);
sqLiteDatabase.execSQL(DriverConstant.T_QUERY);
sqLiteDatabase.execSQL(DriverConstant.QUERY);
sqLiteDatabase.execSQL(DriverConstant.A_QUERY);
sqLiteDatabase.execSQL(DriverConstant.F_QUERY);
        Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
