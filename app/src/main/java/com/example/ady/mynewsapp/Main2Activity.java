package com.example.ady.mynewsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ady.mynewsapp.Model.DataStorage.DataBaseHelper;

public class Main2Activity extends AppCompatActivity {
    ImageView image;
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        image = findViewById(R.id.picFromNews);
        imageURL = "";
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){
            imageURL = bundle.getString("magic");
        }
        //Toast.makeText(this," the recieved string" + imageURL,Toast.LENGTH_LONG).show();
        Glide.with(this)
                .load(imageURL)
                .into(image);
    }
    public void savednews(View view) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(Main2Activity.this);
        boolean insert;
        insert = dataBaseHelper.insertData(imageURL);
        if(insert){
            Toast.makeText(Main2Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(Main2Activity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
        }
    }
}