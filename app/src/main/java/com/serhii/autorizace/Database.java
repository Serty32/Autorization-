package com.serhii.autorizace;

import android.database.sqlite.SQLiteDatabase;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.widget.EditText;

/**
 * Created by Serhii on 13.03.2017.
 */

public interface Database {
    boolean insertUser(String mLogin, String mPassword, String mName);
    boolean enterProgramm(String mLogin, String mPassword);
}