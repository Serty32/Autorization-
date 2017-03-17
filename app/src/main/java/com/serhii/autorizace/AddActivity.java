package com.serhii.autorizace;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serhii.autorizace.data.Database;
import com.serhii.autorizace.data.MySQLiteDatabase;

/**
 * Created by Serhii on 16.03.2017.
 */
public class AddActivity extends AppCompatActivity {
    private EditText edCaption;
    private EditText edText;
    private Button button_add;
    Database db = new MySQLiteDatabase(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edCaption = (EditText)findViewById(R.id.edit_caption);
        edText = (EditText)findViewById(R.id.edit_news);
        button_add = (Button)findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.insertNews(edText.getText().toString(), edCaption.getText().toString()))
                    Toast.makeText(AddActivity.this, "News has been add", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}