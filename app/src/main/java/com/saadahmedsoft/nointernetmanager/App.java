package com.saadahmedsoft.nointernetmanager;

import android.app.Application;

import com.saadahmedev.networkutil.NetworkUtil;
import com.saadahmedev.networkutil.utils.TaskType;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NetworkUtil.initialize(this)
                .doIfNoInternetAvailable(TaskType.SHOW_TOAST_MESSAGE)
                .registerBroadcastReceiver();
    }
}
