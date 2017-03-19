package com.serhii.autorizace;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.serhii.autorizace.data.Database;
import com.serhii.autorizace.data.MySQLiteDatabase;

/**
 * Created by Serhii on 12.03.2017.
 */

public class EnterActivity extends AppCompatActivity {
     private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new MySQLiteDatabase(this);
        setContentView(R.layout.activity_enter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(db.getPosts());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(EnterActivity.this,AddActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}