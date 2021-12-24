package com.example.petofyReplica.sqlite_DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class myDbAdapter {

    myDbHelper helper;

    public myDbAdapter(Context context) {

        helper = new myDbHelper(context);

    }

    public long insertData(String name, String pass) {

        SQLiteDatabase dbb = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.EMAIL, pass);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id;

    }

    public String getAllData() {

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {myDbHelper.UID, myDbHelper.NAME, myDbHelper.EMAIL};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()) {
            
            @SuppressLint("Range") int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            @SuppressLint("Range")String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));

            buffer.append(cid+ "   " + name + "   " + email +" \n");     // todo: change wrt ur REq. of fetching,, wrt Navv drawer
        }
        return buffer.toString();
    }

    @SuppressLint("Range")
    public String getNavName() {
        String name = null;
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] s1 = {myDbHelper.NAME};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, s1, null, null, null, null, null);  //todo: implement RAW query on cursor

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
                          //   getColumnIndex:  This method returns the index number of a column by specifying the name of the column
        }
        return name;
    }

    @SuppressLint("Range")
    public String getNavEmail() {
        String name = null;
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] s1 = {myDbHelper.EMAIL};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, s1, null, null, null, null, null);

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));

        }
        return name;
    }

}
