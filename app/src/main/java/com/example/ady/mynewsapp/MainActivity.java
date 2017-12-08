package com.example.ady.mynewsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.ady.mynewsapp.Newspojo.Article;
import com.example.ady.mynewsapp.Newspojo.NewsNow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Article> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        RetrofitHelper.responseback()
                .enqueue(new Callback<NewsNow>() {
                    @Override
                    public void onResponse(Call<NewsNow> call, Response<NewsNow> response) {
                        Log.d(TAG, "onResponse: HEREEEEEEEEE " + response.body().getArticles().get(0).getTitle());
                        Log.d(TAG, "onResponse: HEREEEEEEEEE " + response.body().getArticles().size());
                        Toast.makeText(MainActivity.this
                                ,"Response " + response.body().getArticles().get(0).getAuthor()
                                ,Toast.LENGTH_LONG).show();
                        list = response.body().getArticles();
                        RecyclerView recyclerView = findViewById(R.id.recycleMainLayout);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MainActivity.this);
                        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                        Recycleadapter recycleadapter = new Recycleadapter(list,MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(itemAnimator);
                        recyclerView.setAdapter(recycleadapter);


                    }

                    @Override
                    public void onFailure(Call<NewsNow> call, Throwable t) {

                    }
                });
    }
}
