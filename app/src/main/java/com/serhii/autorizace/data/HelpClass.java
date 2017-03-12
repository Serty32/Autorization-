package com.serhii.autorizace.data;

import android.provider.BaseColumns;

/**
 * Created by Serhii on 10.03.2017.
 */

public final class HelpClass implements BaseColumns {
    private HelpClass(){
    }

    public static final class Users {
        public final static String TABLE_NAME = "users";
        public static final String _Id = "login";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_NAME = "name";

    }
}
