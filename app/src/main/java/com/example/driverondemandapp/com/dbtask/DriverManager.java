package com.example.driverondemandapp.com.dbtask;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DriverManager {
    DriverHelper helper;
    Context context;
    public DriverManager(Context context)
    {
        this.context=context;
        helper=new DriverHelper(context,DriverConstant.DB_NAME,null,DriverConstant.DB_VERSION);
    }
    public SQLiteDatabase OpenDb()
    {
        SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
        return sqLiteDatabase;
    }
    public void CloseDb()
    {
        if(helper!=null)
        helper.close();
    }
}

