package com.serhii.autorizace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.serhii.autorizace.data.MySQLiteDatabase;
import org.w3c.dom.Text;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by Serhii on 18.03.2017.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private Context context;
    protected List<Post> posts;

    public MyRecycleViewAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.text.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title) TextView title;
        @BindView(R.id.text_news) TextView text;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
         }

         @OnClick(R.id.text_title)
         public void onClickTextTitle(View view){
             Intent intent = new Intent(context, PostActivity.class);
             intent.putExtra("textFromPost",text.getText().toString());
             context.startActivity(intent);
         }
    }
}