package com.serhii.autorizace.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Serhii on 11.03.2017.
 */


public class MySQLiteDatabase extends SQLiteOpenHelper implements Database {

    public static final String DATABASE_NAME = "vk.dp";
    public static final int DATABASE_VERSION = 1;

    public static final class Users {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_NAME = "name";
    }

    public MySQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + Users.TABLE_NAME + "("
                + Users.COLUMN_LOGIN + "," + Users.COLUMN_PASSWORD + "," + Users.COLUMN_NAME
                + ")";
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean register(String login, String password, String name) {
        if (!login.isEmpty() && !password.isEmpty()) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] projection = {Users.COLUMN_LOGIN, Users.COLUMN_NAME, Users.COLUMN_PASSWORD};
            String selection = Users.COLUMN_LOGIN + "=?";
            String[] selectionArgs = {login};
            Cursor cursor = db.query(Users.TABLE_NAME, projection, selection, selectionArgs,
                    null, null, null);
            if (cursor.getCount() == 0) {
                values.put(Users.COLUMN_LOGIN, login);
                values.put(Users.COLUMN_PASSWORD, password);
                values.put(Users.COLUMN_NAME, name);
            }
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    public boolean login(String login, String password) {

        SQLiteDatabase db = getReadableDatabase();
        String selection = Users.COLUMN_LOGIN + "=?";
        String[] projection = {Users.COLUMN_LOGIN, Users.COLUMN_PASSWORD};
        String[] selectionArgs = {login};
        Cursor cursor = db.query(Users.TABLE_NAME, projection, selection,
                selectionArgs, null, null, null);
        if (cursor.moveToFirst() && cursor.getString(cursor.getColumnIndex(Users.COLUMN_PASSWORD)).equals(password)) {
            cursor.close();
            return true;
        }
        cursor.close();
        return  false;
    }
}
