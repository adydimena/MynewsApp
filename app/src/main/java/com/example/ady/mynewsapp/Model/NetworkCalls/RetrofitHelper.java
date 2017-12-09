package com.example.ady.mynewsapp.Model.NetworkCalls;


import com.example.ady.mynewsapp.Model.NewsPojo.NewsNow;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Ady on 11/29/2017.
 */

public class RetrofitHelper {

    public static final String BASE_URL = "https://newsapi.org/";
    public static final String APIKEY = "5f268820fa92450bada72a4252dd01af";


    //    build the retrofit object to be used
    public static Retrofit create() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
    public static Call<NewsNow> responseback(){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.responseback();
    }


    //    create an interface for http verbs
    interface RetrofitService  {



       @GET("v2/top-headlines?sources=bbc-news&apiKey=5f268820fa92450bada72a4252dd01af")
       Call<NewsNow> responseback();
        //Call<ForeCastbyHour> responseback(@Path("zipcode")int zipcode);
    }
}