package com.example.driverondemandapp.com.dbtask;

import java.io.ByteArrayOutputStream;

public class DriverConstant {
    public static final String DB_NAME="dbdriver";
    public static final String TABLE_NAME="clientdetail";
    public static final String COL_ID="clientid";
    public static final String COL_NAME="clientname";
    public static final String COL_EMAIL="clientemail";
    public static final String COL_PHONE="clientphone";
    public static final String COL_ADDRESS="clientaddress";

    public static final String T_NAME="driverdetail";
    public static final String C_ID="driverid";
    public static final String C_NAME="drivername";
    public static final String C_MAIL="drivermail";
    public static final String C_PHONE="driverphone";
    public static final String C_AGE="driverage";
    public static final String C_GENDER="drivergender";
    public static final String C_ADDRESS="driveraddress";

    public static final String NAME="request";
    public static final String ID="requestid";
    public static final String CID="clientid";
    public static final String COL_DATE="date";
    public static final String FROM="ftime";
    public static final String TO="ttime";

    public static final String ASSIGN="assign";
    public static final String ASS_ID="assignid";
    public static final String D_ID="driverid";
    public static final String CL_ID="clientid";
    public static final String DATE="date";
    public static final String FR="ftime";
    public static final String T="ttime";
    public static final String CHARGES="charges";

    public static final String TAB_NAME="feedback";
    public static final String F_ID="fid";
    public static final String DR_ID="driverid";
    public static final String CLI_ID="clientid";

    public static final String COL_TEXT="text";
    public static final String COL_SMILEY="smiley";


    public static final int DB_VERSION=1;

    public static final String TBL_QUERY="create table clientdetail(clientid text primary key,clientname text,clientemail text,clientphone text,clientaddress text)";
    public static final String T_QUERY="create table driverdetail(driverid text primary key,drivername text,drivermail text,driverphone text,driverage text,drivergender text,driveraddress text)";
    public static final String QUERY="create table request(requestid integer primary key autoincrement,clientid text,date date,ftime integer,ttime integer)";
    public static final String A_QUERY="create table assign(assignid integer primary key autoincrement,driverid text,clientid text,date date,ftime integer,ttime integer,charges float)";
    public static final String F_QUERY="create table feedback(fid text primary key,driverid text,clientid text,text text,smiley text)";
}


