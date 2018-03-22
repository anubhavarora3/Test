package com.anubhav.aro.dailymotivationalquotesnonmvp.main;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
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
    private Typeface custom_font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        initViews();
        sharedPreferences = getSharedPreferences(PREF_NAME, this.MODE_PRIVATE);

        jobSchedulerService = new JobSchedulerService();

        jobInfo = new JobInfo.Builder(1, new ComponentName(this, JobSchedulerService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(1000 * 60 * 60 * 24)
                .build();

        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);

        updateView();

    }

    private void updateView() {
        if (sharedPreferences != null) {
            if (sharedPreferences.getString("error_message", null) == null) {
                textView.setText(sharedPreferences.getString("quote_title", null));
                textView.setTypeface(custom_font);
                //textView.setText(R.string.test_string);
//                Picasso.with(this)
//                        .load(sharedPreferences.getString("quote_url", null))
//                        .into(imageView);
            }
        }
    }

    private void initViews() {
        textView = findViewById(R.id.quote_text);
        imageView = findViewById(R.id.quote_image);
        custom_font = Typeface.createFromAsset(getAssets(), "fonts/brody.ttf");
    }

}
