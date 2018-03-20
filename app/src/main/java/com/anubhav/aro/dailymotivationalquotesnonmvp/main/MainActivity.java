package com.anubhav.aro.dailymotivationalquotesnonmvp.main;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.anubhav.aro.dailymotivationalquotesnonmvp.R;
import com.anubhav.aro.dailymotivationalquotesnonmvp.util.JobSchedulerService;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;
    private JobScheduler jobScheduler;
    private JobSchedulerService jobSchedulerService;
    private JobInfo jobInfo;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "SHARED_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        sharedPreferences = getSharedPreferences(PREF_NAME, this.MODE_PRIVATE);

        jobSchedulerService = new JobSchedulerService(sharedPreferences);

        jobInfo = new JobInfo.Builder(1, new ComponentName(this, JobSchedulerService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(3000)
                .build();

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);

        updateView();

    }

    private void updateView() {
        if (sharedPreferences != null) {
            if (sharedPreferences.getString("error_message", null) != null) {
                textView.setText(sharedPreferences.getString("quote_title", null));
            }
        }
    }

    private void initViews() {
        textView = findViewById(R.id.quote_text);
        imageView = findViewById(R.id.quote_image);
    }

}
