package com.serhii.autorizace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serhii.autorizace.data.MySQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText mLogin;
    private EditText mPassword;
    private Button mEnter;
    private MySQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = new MySQLiteDatabase(this);
        mLogin = (EditText) findViewById(R.id.edit_login);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mEnter = (Button) findViewById(R.id.button_enter);

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQLiteDatabase db = new MySQLiteDatabase(MainActivity.this);
                if (db.login(mLogin.getText().toString(), mPassword.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, EnterActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No rule wrote log or pass", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onClickRegistration(View view) {
        Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(intent);
    }
}