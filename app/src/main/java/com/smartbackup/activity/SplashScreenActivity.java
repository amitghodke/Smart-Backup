package com.smartbackup.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.smartbackup.R;

public class SplashScreenActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                SplashScreenActivity.this.finish();
            }
        },5000);
    }

}
