package com.serhii.autorizace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serhii.autorizace.data.Database;
import com.serhii.autorizace.data.MySQLiteDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii on 10.03.2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.edit_login) EditText etLogin;
    @BindView(R.id.edit_password) EditText etPassword;
    @BindView(R.id.edit_jmeno) EditText etName;
    @BindView(R.id.button_registration_database) Button btnRegistration;
    Database db = new MySQLiteDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_registration_database)
    public void button_registration_database() {
        if (db.register(etLogin.getText().toString(),
                etPassword.getText().toString(), etName.getText().toString())) {
            Toast.makeText(RegistrationActivity.this, "User has been add", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(RegistrationActivity.this, "Error at the add user", Toast.LENGTH_LONG).show();
        }
    }
}