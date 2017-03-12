package com.serhii.autorizace;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.serhii.autorizace.data.HelpClass;

/**
 * Created by Serhii on 11.03.2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public static  final  String DATABASE_NAME = "vk.dp";
    public  static final int DATABASE_VERSION = 1;
    public DataBase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + HelpClass.Users.TABLE_NAME + "("
                + HelpClass.Users._Id + "," + HelpClass.Users.COLUMN_PASSWORD + "," + HelpClass.Users.COLUMN_NAME
                + ")";
        db.execSQL(SQL_CREATE_USER_TABLE);

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
