package com.anubhav.aro.dailymotivationalquotesnonmvp.main;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        JobInfo.Builder builder = new
                JobInfo.Builder(1, new ComponentName(getPackageName(),
                JobSchedulerService.class.getName()));

        builder.setPeriodic(3000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

    }

    private void initViews() {
        textView = findViewById(R.id.quote_text);
        imageView = findViewById(R.id.quote_image);
    }

}
