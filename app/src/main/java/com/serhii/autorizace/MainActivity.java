package com.serhii.autorizace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serhii.autorizace.data.MySQLiteDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_enter) Button mEnter;
    @BindView(R.id.edit_login) EditText mLogin;
    @BindView(R.id.edit_password) EditText mPassword;
    private MySQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new MySQLiteDatabase(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_enter)
    public void button_enter() {
        db = new MySQLiteDatabase(MainActivity.this);
        if (db.login(mLogin.getText().toString(), mPassword.getText().toString())) {
            Intent intent = new Intent(MainActivity.this, EnterActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "No rule wrote log or pass", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickRegistration(View view) {
        Intent intent = new Intent(MainActivity.this,RegistrationActivity.class);
        startActivity(intent);
    }
}