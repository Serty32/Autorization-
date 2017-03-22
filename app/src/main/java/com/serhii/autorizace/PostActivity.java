package com.serhii.autorizace;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    @BindView(R.id.edit_comment) TextView editComment;
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
        if(db.insertComment(editComment.getText().toString())) {
            Toast.makeText(PostActivity.this, "Comment has been add",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(PostActivity.this, "Incorrect comment",Toast.LENGTH_LONG).show();
        }
    }
}