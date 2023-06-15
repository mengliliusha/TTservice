package com.example.fknm;

import android.app.Application;
import android.content.Context;

import com.example.fknm.ServerThread;

/**
 * Created by sgll on 2018/12/10.
 */
public class MyApplication extends Application {
    public static Context mContext;
    public static ServerThread mServer;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mServer = new ServerThread();
        mServer.start();
//        new ServerThread().start();
    }
}
