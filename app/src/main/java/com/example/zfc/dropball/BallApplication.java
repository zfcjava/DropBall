package com.example.zfc.dropball;

import android.app.Application;
import android.content.Context;

/**
 * Created by zfc on 2018/5/5.
 */

public class BallApplication extends Application {

    private static Application INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static Application getINSTANCE() {
        return INSTANCE;
    }

    public Context getContext() {
        return getApplicationContext();
    }
}
