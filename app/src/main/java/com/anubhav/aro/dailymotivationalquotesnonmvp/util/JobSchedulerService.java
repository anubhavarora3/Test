package com.anubhav.aro.dailymotivationalquotesnonmvp.util;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;

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

    public JobSchedulerService() {
        quotesList = new ArrayList<>();
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //fetchData();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


    private static class JobTask extends AsyncTask<Void, Void, ArrayList<Quotes>> {

        private final JobService jobService;

        public JobTask(JobService jobService) {
            this.jobService = jobService;
        }

        @Override
        protected ArrayList<Quotes> doInBackground(Void... voids) {
            ApiInterface apiInterface = ApiService.getRetrofit().create(ApiInterface.class);
            Call<QuoteObject> call = apiInterface.getQuote(CATEGORY);
            call.enqueue(new Callback<QuoteObject>() {
                @Override
                public void onResponse(Call<QuoteObject> call, Response<QuoteObject> response) {
                    if (response.isSuccessful()) {
                        quotesList = response.body().getContents().getQuotesList();

                    } else {
                    }
                }

                @Override
                public void onFailure(Call<QuoteObject> call, Throwable t) {
                }
            });
            return (ArrayList<Quotes>) quotesList;
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
