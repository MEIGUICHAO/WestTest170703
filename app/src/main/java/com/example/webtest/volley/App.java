package com.example.webtest.volley;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;

import com.jude.http.RequestManager;


/**
 * Created by Allen Lin on 2016/02/17.
 */
public class App extends Application {
    private static App sContext;

    private static PackageInfo packInfo;

    private static Handler mMainThreadHandler;
    private static Looper mMainThreadLooper;
    private static Thread mMainThread;
    private static int mMainThreadId;

    public static App getIns()
    {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        this.mMainThreadHandler = new Handler();
        this.mMainThreadLooper = getMainLooper();
        this.mMainThread = Thread.currentThread();
        this.mMainThreadId = android.os.Process.myTid();
        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setCacheEnable(false);

        sContext = this;
    }

    public static Context getContext() {
        return sContext;
    }



    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    /**
     * 获取apk包名路径
     */
    @SuppressLint("Override")
    public String getDataDir()
    {
        if (packInfo == null)
            getAppInfo();
        return packInfo != null && packInfo.applicationInfo != null ? packInfo.applicationInfo.dataDir : "";
    }

    private void getAppInfo()
    {
        // 获取packageManager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try
        {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}

