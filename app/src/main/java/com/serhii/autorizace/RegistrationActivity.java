package com.serhii.autorizace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serhii.autorizace.data.Database;
import com.serhii.autorizace.data.MySQLiteDatabase;

/**
 * Created by Serhii on 10.03.2017.
 */

public class RegistrationActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private EditText etName;
    private Button btnRegistration;
    Database db = new MySQLiteDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etLogin = (EditText) findViewById(R.id.edit_login);
        etPassword = (EditText) findViewById(R.id.edit_password);
        etName = (EditText) findViewById(R.id.edit_jmeno);
        btnRegistration = (Button) findViewById(R.id.button_registration_database);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.register(etLogin.getText().toString(),
                        etPassword.getText().toString(), etName.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "User has been add", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Error at the add user", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}