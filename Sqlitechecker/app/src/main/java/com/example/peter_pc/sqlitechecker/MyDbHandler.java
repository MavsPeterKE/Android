package com.example.peter_pc.sqlitechecker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.peter_pc.sqlitechecker.DatabaseConfigs.UserInfoDbConfigs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Peter-PC on 3/10/2017.
 */

public class MyDbHandler extends SQLiteOpenHelper {

    // Database Name
    private static final String DATABASE_NAME = "usersData.db";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Query that creates the user table with the respective columns
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserInfoDbConfigs.UserInfoEntry.TABLE_NAME + " (" +
                    UserInfoDbConfigs.UserInfoEntry.COLUMNNAME_ID + " TEXT," +
                    UserInfoDbConfigs.UserInfoEntry.COLUMNNAME_FNAME + " TEXT," +
                    UserInfoDbConfigs.UserInfoEntry.COLUMNNAME_LNAME + " TEXT)";
    //Query that deletes the table
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserInfoDbConfigs.UserInfoEntry.TABLE_NAME;

    //Constructor that created the database when class object is initialized
    public MyDbHandler(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS", "DATABASE CREATED SUCCESSFULLY");
    }

    //creates the Table within the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.e("DATABASE OPERATIONS", "Table Created");
    }

    //Upgrades incase of changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL(SQL_DELETE_ENTRIES);
        // Create tables again
        onCreate(db);
    }

    //method that saves new records to the database
    public void addUsersInfo(String userID, String Ufname,String Ulname,SQLiteDatabase db){

        //Map retrieved values to the respective columns
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(UserInfoDbConfigs.UserInfoEntry.COLUMNNAME_ID,userID);
        contentvalues.put(UserInfoDbConfigs.UserInfoEntry.COLUMNNAME_FNAME,Ufname);
        contentvalues.put(UserInfoDbConfigs.UserInfoEntry.COLUMNNAME_LNAME,Ulname);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserInfoDbConfigs.UserInfoEntry.TABLE_NAME, null, contentvalues);

        //log result to see row inserted
        Log.e("NewRecord Inserted", String.valueOf(newRowId) );
    }

    //method that retrieves all data from the database
    public void listUsers(SQLiteDatabase db){
    Cursor cursor=db.rawQuery("SELECT *FROM "+UserInfoDbConfigs.UserInfoEntry.TABLE_NAME,null);
    while (cursor.moveToNext()){
        String values=cursor.getString(0)+cursor.getString(1)+cursor.getString(2);
        Log.e("Saved Users",values );
    }
}
}
