package com.serhii.autorizace;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.serhii.autorizace.data.HelpClass;

/**
 * Created by Serhii on 15.03.2017.
 */

public class HelpenerClass extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vk.db";
    public static final int DATABASE_VERSION = 1;

    public HelpenerClassClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + HelpClass.Users.TABLE_NAME + "("
                + HelpClass.Users._Id + "," + HelpClass.Users.COLUMN_PASSWORD + "," + HelpClass.Users.COLUMN_NAME
                + ")";
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
