package com.example.ady.mynewsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.example.ady.mynewsapp.Model.Adapters.Recycleadapter;
import com.example.ady.mynewsapp.Model.NetworkCalls.RetrofitHelper;
import com.example.ady.mynewsapp.Model.NewsPojo.Article;
import com.example.ady.mynewsapp.Model.NewsPojo.NewsNow;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 15;
    private List<Article> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                Toast.makeText(this,
                        "It does not makesense but If you dont allow me to se your Location You wont see your News"
                        ,Toast.LENGTH_LONG).show();
                RequestPermission();

            } else {
                RequestPermission();
            }

        }else
            NetworkCall();
    }

    private void RequestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
    }

    private void NetworkCall() {
        RetrofitHelper.responseback()
                .enqueue(new Callback<NewsNow>() {
                    @Override
                    public void onResponse(Call<NewsNow> call, Response<NewsNow> response) {
                        Log.d(TAG, "onResponse: MAKING THE CALL" + response.body().getArticles().get(0).getAuthor());
                        // Log.d(TAG, "onResponse: HEREEEEEEEEE " + response.body().getArticles().get(0).getTitle());
                        // Log.d(TAG, "onResponse: HEREEEEEEEEE " + response.body().getArticles().size());
                        // Toast.makeText(MainActivity.this
                        //        ,"Response " + response.body().getArticles().get(0).getAuthor()
                        //       ,Toast.LENGTH_LONG).show();
                        list = response.body().getArticles();
                        RecyclerView recyclerView = findViewById(R.id.recycleMainLayout);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MainActivity.this);
                        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                        Recycleadapter recycleadapter = new Recycleadapter(list, MainActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(itemAnimator);
                        recyclerView.setAdapter(recycleadapter);
                    }

                    @Override
                    public void onFailure(Call<NewsNow> call, Throwable t) {

                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    NetworkCall();
                else{
                    Toast.makeText(this,"You Have disable this and It does not makesense but cant get you news"
                            ,Toast.LENGTH_LONG).show();
                }

            }
        }
    }
}


