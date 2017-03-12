package com.serhii.autorizace;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.serhii.autorizace.data.HelpClass;

/**
 * Created by Serhii on 10.03.2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private EditText etName;
    private Database mDatabase;
    private Button btnRegistration;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etLogin = (EditText) findViewById(R.id.edit_login);
        etPassword = (EditText) findViewById(R.id.edit_password);
        etName = (EditText) findViewById(R.id.edit_jmeno);
        btnRegistration = (Button) findViewById(R.id.button_registration_database);
        btnInfo = (Button) findViewById(R.id.button_info);
        btnInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displayDatabaseInfo();

            }
        });

        mDatabase = new Database(this);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUser();
            }
        });
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase dp = mDatabase.getReadableDatabase();
        String[] projection = {HelpClass.Users._Id, HelpClass.Users.COLUMN_PASSWORD,
                HelpClass.Users.COLUMN_NAME};
        Cursor cursor = dp.query(HelpClass.Users.TABLE_NAME, projection, null, null, null, null, null);
        TextView displayTextView = (TextView) findViewById(R.id.text_info);

        try {
            displayTextView.setText("Таблица содержит " + cursor.getCount() + " пользователей.\n\n");
            displayTextView.append(HelpClass.Users._Id + "-" + HelpClass.Users.COLUMN_NAME + "-"
                    + HelpClass.Users.COLUMN_PASSWORD + "\n");
            int idColumnIndex = cursor.getColumnIndex(HelpClass.Users._Id);
            int nameColumnIndex = cursor.getColumnIndex(HelpClass.Users.COLUMN_NAME);
            int passColumnIndex = cursor.getColumnIndex(HelpClass.Users.COLUMN_PASSWORD);
            while (cursor.moveToNext()) {
                String currentId = cursor.getString(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentPass = cursor.getString(passColumnIndex);
                displayTextView.append(("\n" + currentId + "-" + currentName + "-" + currentPass));
            }
        } finally {
            cursor.close();
        }
    }

    public void insertUser() {
        SQLiteDatabase dp = mDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        SQLiteDatabase db = mDatabase.getReadableDatabase();
        String selection = HelpClass.Users._Id + "=?";
        String[] projection = {HelpClass.Users._Id};
        String[] selectionArgs = {etLogin.getText().toString()};
        Cursor cursor = db.query(HelpClass.Users.TABLE_NAME,projection,selection,selectionArgs,null,null,null);
        if (!etName.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()
                && cursor.getCount() == 0) {
            values.put(HelpClass.Users._Id, etLogin.getText().toString());
            values.put(HelpClass.Users.COLUMN_NAME, etName.getText().toString());
            values.put(HelpClass.Users.COLUMN_PASSWORD, etPassword.getText().toString());
        }
        long newRowId = dp.insert(HelpClass.Users.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Ошибка при заведении пользователя", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Пользователь заведён под именем: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}
