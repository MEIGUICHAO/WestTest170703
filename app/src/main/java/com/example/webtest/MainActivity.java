package com.example.webtest;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    private String strTime;
    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;

    public String getStrTime() {
        return strTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        strTime = sdf.format(new Date(System.currentTimeMillis()));
        WA_MainFragment.start(MainActivity.this, R.id.container);


        powerManager = (PowerManager) this.getSystemService(Service.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Lock");
    }

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }
}
