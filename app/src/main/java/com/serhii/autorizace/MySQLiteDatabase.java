package com.serhii.autorizace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.ViewDebug;
import android.widget.EditText;
import android.widget.Toast;

import com.serhii.autorizace.data.HelpClass;

/**
 * Created by Serhii on 11.03.2017.
 */


public class MySQLiteDatabase extends SQLiteOpenHelper implements Database {

    public static final String DATABASE_NAME = "vk.dp";
    public static final int DATABASE_VERSION = 1;
    public HelpClass mDataBase;

    public MySQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + HelpClass.Users.TABLE_NAME + "("
                + HelpClass.Users._Id + "," + HelpClass.Users.COLUMN_PASSWORD + "," + HelpClass.Users.COLUMN_NAME
                + ")";
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    public boolean insertUser(String mLogin, String mPassword, String mName) {
        if (!mLogin.isEmpty() && !mPassword.isEmpty()) {
            android.database.sqlite.SQLiteDatabase db = mDataBase.getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] projection = {HelpClass.Users._Id, HelpClass.Users.COLUMN_NAME,
                    HelpClass.Users.COLUMN_PASSWORD};
            String selection = HelpClass.Users._Id + "=?";
            String[] selectionArgs = {mLogin};
            Cursor cursor = db.query(HelpClass.Users.TABLE_NAME, projection, selection, selectionArgs,
                    null, null, null);
            if (cursor.getCount() == 0) {
                values.put(HelpClass.Users._Id, mLogin);
                values.put(HelpClass.Users.COLUMN_PASSWORD, mPassword);
                values.put(HelpClass.Users.COLUMN_NAME, mName);
            }
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    public boolean enterProgramm(String mLogin, String mPassword) {

         db = mDataBase.getWritableDatabase();
        String selection = HelpClass.Users._Id + "=?";
        String[] projection = {HelpClass.Users._Id, HelpClass.Users.COLUMN_PASSWORD};
        String[] selectionArgs = {mLogin};
        Cursor cursor = db.query(HelpClass.Users.TABLE_NAME, projection, selection,
                selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(cursor.getColumnIndex(HelpClass.Users.COLUMN_PASSWORD))
                    .equals(mPassword))
                return true;
            else
                return false;
        }
        cursor.close();
        return  false;

    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
