package com.serhii.autorizace.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.serhii.autorizace.Post;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

    public static final class News implements BaseColumns{
        public static final String TABLE_NAME = "news";
        public static  String _ID = BaseColumns._ID;
        public static final String COLUMN_CAPTION = "caption";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_COMMENT = "comment";

    }

    public MySQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + Users.TABLE_NAME + "("
                + Users.COLUMN_LOGIN + "," + Users.COLUMN_PASSWORD + "," + Users.COLUMN_NAME + ")";
        String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + News.TABLE_NAME + "(" + News._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + News.COLUMN_CAPTION + "," +
                News.COLUMN_TEXT + "," + News.COLUMN_COMMENT + ")";
        db.execSQL(SQL_CREATE_NEWS_TABLE);
        db.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertNews(String caption, String news) {
        if (!caption.isEmpty() && !news.isEmpty()) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] projection = {News._ID};
            Cursor cursor = db.query(News.TABLE_NAME, projection, null , null , null , null , null);
                values.put(News.COLUMN_CAPTION, caption);
                values.put(News.COLUMN_TEXT, news);
                long newRowID = db.insert(News.TABLE_NAME, null, values);
                if (newRowID == -1) {
                    cursor.close();
                    db.close();
                    return false;
                } else {
                    cursor.close();
                    db.close();
                    return true;
                }
        }
        return false;
    }

    public boolean insertComment(String comment){
        if(!comment.isEmpty()) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] projection = {News._ID};
            Cursor cursor = db.query(News.TABLE_NAME, projection, null, null, null, null, null);
            values.put(News.COLUMN_COMMENT,comment);
            long newRowId = db.insert(News.TABLE_NAME, null, values);
            if(newRowId == -1) {
                cursor.close();
                db.close();
                return false;
            } else {
                cursor.close();
                db.close();
                return true;
            }
        }
        return false;
    }

    public boolean register(String login, String password, String name) {
        if (!login.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            String[] projection = {Users.COLUMN_LOGIN};
            String selection = Users.COLUMN_LOGIN + " = ?";
            String[] selectionArgs = new String[]{login};
            Cursor cursor = db.query(Users.TABLE_NAME, projection, selection, selectionArgs,
                    null, null, null);
            if (cursor.getCount() == 0) {
                cursor.close();
                values.put(Users.COLUMN_LOGIN, login);
                values.put(Users.COLUMN_PASSWORD, password);
                values.put(Users.COLUMN_NAME, name);
                long newRowId = db.insert(Users.TABLE_NAME, null, values);
                if(newRowId == -1)
                    return  false;
                else {
                    db.close();
                    return true;
                }
            }
            cursor.close();
        }
        return false;
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
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return  false;
    }

    public List<Post> getPosts() {
        SQLiteDatabase db = getReadableDatabase();
        List<Post> posts = new ArrayList<>();
        String[] projection = {News.COLUMN_CAPTION, News.COLUMN_TEXT};
        Cursor cursor = db.query(News.TABLE_NAME, projection, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(News.COLUMN_CAPTION));
            String text = cursor.getString(cursor.getColumnIndex(News.COLUMN_TEXT));
            posts.add(new Post(title, text));
        }
        return  posts;
    }
}