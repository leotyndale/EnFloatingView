package com.imuxuan.en.floatingview;

import android.app.Application;

public class EnApplication extends Application {

    private static EnApplication instance;

    public static EnApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
