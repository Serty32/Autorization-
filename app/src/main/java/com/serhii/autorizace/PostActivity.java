package com.serhii.autorizace;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.serhii.autorizace.data.Database;
import com.serhii.autorizace.data.MySQLiteDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Serhii on 21.03.2017.
 */

public class PostActivity extends AppCompatActivity {

    @BindView(R.id.text_post) TextView textPost;
    @BindView(R.id.text_comment) TextView textComment;
    @BindView(R.id.button_comment) Button buttonAddComment;
    Database db = new MySQLiteDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button_comment)
    public void addComment(View view){

    }

}
