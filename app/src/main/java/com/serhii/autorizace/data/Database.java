package com.serhii.autorizace.data;

import android.database.sqlite.SQLiteDatabase;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.widget.EditText;

import com.serhii.autorizace.Post;

import java.util.List;

/**
 * Created by Serhii on 13.03.2017.
 */

public interface Database {

    boolean register(String login, String password, String name);

    boolean login(String login, String password);

    boolean insertNews(String caption, String news);

    boolean insertComment(String comment);

    List<Post> getPosts();
}