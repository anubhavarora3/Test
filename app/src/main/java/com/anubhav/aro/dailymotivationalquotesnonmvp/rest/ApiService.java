package com.anubhav.aro.dailymotivationalquotesnonmvp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anubhav on 20/03/18.
 */

public class ApiService {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://quotes.rest/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
