package com.serhii.autorizace;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.serhii.autorizace.data.HelpClass;

public class MainActivity extends AppCompatActivity {
    private EditText mLogin;
    private  EditText mPassword;
    private Button mEnter;
    private DataBase mDataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBase = new DataBase(this);
        mLogin = (EditText) findViewById(R.id.Login);
        mPassword = (EditText) findViewById(R.id.Password);
        mEnter = (Button) findViewById(R.id.btn_enter);

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dp = mDataBase.getReadableDatabase();
                String selection = HelpClass.Users._Id + "=?";
                String[] projection = {HelpClass.Users._Id,HelpClass.Users.COLUMN_PASSWORD};
                String[] selectionArgs = {mLogin.getText().toString()};
                Cursor cursor = dp.query(HelpClass.Users.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, null);

                if (cursor.moveToFirst()) {
                    if (cursor.getString(cursor.getColumnIndex(HelpClass.Users.COLUMN_PASSWORD))
                            .equals(mPassword.getText().toString())) {
                        Intent intent = new Intent(MainActivity.this, EnterActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No rule wrote log or pass",
                                Toast.LENGTH_LONG).show();}
                }
                cursor.close();
            }
        });
    }











    public void onClickRegistration(View view) {
        Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(intent);
    }





}


