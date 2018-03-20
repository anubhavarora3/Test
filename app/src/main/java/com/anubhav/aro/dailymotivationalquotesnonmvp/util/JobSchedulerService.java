package com.anubhav.aro.dailymotivationalquotesnonmvp.util;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.anubhav.aro.dailymotivationalquotesnonmvp.model.QuoteObject;
import com.anubhav.aro.dailymotivationalquotesnonmvp.model.Quotes;
import com.anubhav.aro.dailymotivationalquotesnonmvp.rest.ApiInterface;
import com.anubhav.aro.dailymotivationalquotesnonmvp.rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by anubhav on 20/03/18.
 */

public class JobSchedulerService extends JobService {

    private static List<Quotes> quotesList;
    private static final String CATEGORY = "inspire";
    private SharedPreferences sharedPreferences;
    //private static final String PREF_NAME = "SHARED_PREFS";


    public JobSchedulerService() {
    }

    public JobSchedulerService(SharedPreferences sharedPreferences) {
        quotesList = new ArrayList<>();
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        new JobTask().execute();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


    private class JobTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            ApiInterface apiInterface = ApiService.getRetrofit().create(ApiInterface.class);
            Call<QuoteObject> call = apiInterface.getQuote(CATEGORY);
            call.enqueue(new Callback<QuoteObject>() {
                @Override
                public void onResponse(Call<QuoteObject> call, Response<QuoteObject> response) {
                    Toast.makeText(JobSchedulerService.this, "onResponse", Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        quotesList = response.body().getContents().getQuotesList();
                        Quotes quotes = quotesList.get(0);
                        editor.putString("quote_title" , quotes.getQuote());
                        editor.putString("quote_url", quotes.getBackground());
                        editor.putString("error_message", null);
                        editor.apply();
                    } else {
                        editor.putString("error_message", null);
                        editor.apply();
                    }
                }

                @Override
                public void onFailure(Call<QuoteObject> call, Throwable t) {
                    Toast.makeText(JobSchedulerService.this, "onFailure", Toast.LENGTH_SHORT).show();
                    editor.putString("quote_title", null);
                    editor.putString("quote_url", null);
                    editor.putString("error_message", null);
                    editor.apply();
                }
            });
            return null;
        }

    }

//
//    public void fetchData() {
//        ApiInterface apiInterface = ApiService.getRetrofit().create(ApiInterface.class);
//        Call<QuoteObject> call = apiInterface.getQuote(CATEGORY);
//        call.enqueue(new Callback<QuoteObject>() {
//            @Override
//            public void onResponse(Call<QuoteObject> call, Response<QuoteObject> response) {
//                if (response.isSuccessful()) {
//                    quotesList = response.body().getContents().getQuotesList();
//                    Toast.makeText(getApplicationContext(), "success " + quotesList.size(), Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<QuoteObject> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
