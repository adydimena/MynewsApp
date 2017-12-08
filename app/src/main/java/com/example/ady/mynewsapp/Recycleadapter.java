package com.example.ady.mynewsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ady.mynewsapp.Newspojo.Article;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ady on 11/14/2017.
 */

public class Recycleadapter extends RecyclerView.Adapter<Recycleadapter.ViewHolder> {
    List<Article> list = new ArrayList<>();
    Context context;
    int click = 0;
    String TAG = "RecycleAdapter";

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public Recycleadapter(List<Article> list, Context contex) {

        this.list = list;
        this.context = contex;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.recycleviewlayout, null);

        return new ViewHolder(view);//this is not used
        //TODO delete this file when done!! not used
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (list.get(position) != null) ;
      // for ( int k = 0; k< 16;k++) {
          // Log.d(TAG, "onBindViewHolder: inside for loop "+ list.get(position).getTemp());
        holder.title.setText(list.get(position).getTitle());
        holder.descr.setText(list.get(position).getDescription());
        holder.Author.setText(list.get(position).getAuthor());
        holder.linearlayoutbtnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"you have clicked on "+ list.get(position).getTitle(),Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: " + list.get(position).getUrlToImage() );
                
            }
        });

    }
    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView title;
        TextView Author;
        TextView descr;
        LinearLayout linearlayoutbtnclick;
        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.theTitle);
            Author = itemView.findViewById(R.id.theTheAuthor);
            descr = itemView.findViewById(R.id.theDescre);
            linearlayoutbtnclick = itemView.findViewById(R.id.linearlayoutbtnclick);


        }
    }
}


