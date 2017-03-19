package com.serhii.autorizace;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.serhii.autorizace.data.MySQLiteDatabase;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by Serhii on 18.03.2017.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private List<Post> posts;

    public MyRecycleViewAdapter(List<Post> posts) {
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

        TextView title;
        TextView text;

        public ViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.text_title);
            text = (TextView)view.findViewById(R.id.text_news);
        }
    }
}