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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anubhav.aro.dailymotivationalquotesnonmvp.R;
import com.anubhav.aro.dailymotivationalquotesnonmvp.util.JobSchedulerService;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private RelativeLayout parentLayout;
    private JobScheduler jobScheduler;
    private JobSchedulerService jobSchedulerService;
    private JobInfo jobInfo;
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "SHARED_PREFS";
    private Typeface custom_font;

    private int[] colors = {R.color.orange, R.color.yellow,
            R.color.green, R.color.blue, R.color.red};

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

    private void initViews() {
        textView = findViewById(R.id.quote_text);
        parentLayout = findViewById(R.id.main_layout);
        custom_font = Typeface.createFromAsset(getAssets(), "fonts/brody.ttf");
    }

    private void updateView() {

        if (sharedPreferences != null) {
            if (sharedPreferences.getString("error_message", null) == null) {
                //addNotification();

                textView.setText(sharedPreferences.getString("quote_title", null));
                textView.setTypeface(custom_font);

                int num = generateRandomNumber();
                parentLayout.setBackgroundColor(getResources().getColor(colors[num]));
            }
        }
    }

    private int generateRandomNumber() {
        int min = 0;
        int max = 4;

        Random random = new Random();
        int rNum = random.nextInt((max - min) + 1) + min;
        return rNum;
    }
}
