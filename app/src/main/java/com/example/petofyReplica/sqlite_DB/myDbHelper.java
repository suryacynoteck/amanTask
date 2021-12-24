package com.example.petofyReplica.sqlite_DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbHelper extends SQLiteOpenHelper {

    // https://abhiandroid.com/database/sqlite

    private Context context;
    private static final String DATABASE_NAME = "myDatabase";    // Database Name
     static final String TABLE_NAME = "myTable";      // Table Name
    private static final int DATABASE_VERSION = 1;          // Database Version

     static final String UID = "id";             // Column I (Primary Key)
     static final String NAME = "Name";       //Column II
     static final String EMAIL = "Email";        // Column III

    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ EMAIL +" VARCHAR(225));";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;



    public myDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
