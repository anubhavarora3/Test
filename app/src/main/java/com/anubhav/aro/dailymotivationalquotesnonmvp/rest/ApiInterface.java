package com.anubhav.aro.dailymotivationalquotesnonmvp.rest;

import com.anubhav.aro.dailymotivationalquotesnonmvp.model.QuoteObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by anubhav on 20/03/18.
 */

public interface ApiInterface {

    @GET("qod.json")
    Call<QuoteObject> getQuote(@Query("category") String category);
}
