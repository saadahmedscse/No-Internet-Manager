package com.saadahmedev.networkutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.saadahmedev.networkutil.utils.Constants;
import com.saadahmedev.networkutil.utils.TaskType;

public class NetworkUtil {

    private static TaskType noInternetTask = null;

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private NetworkUtil(Context context) {
        NetworkUtil.context = context;
    }

    public static NetworkUtil initialize(Context context) {
        return new NetworkUtil(context);
    }

    public static boolean isInternetAvailable() {
        if (context == null) throw new RuntimeException("Context hasn't been initialized yet");
        return checkInternet(context, true);
    }

    public static boolean isInternetAvailable(Context context) {
        return checkInternet(context, false);
    }

    private static boolean checkInternet(Context context, boolean isAppClass) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) return true;
        if (isAppClass && noInternetTask != null) doTask();

        return false;
    }

    public NetworkUtil doIfNoInternetAvailable(TaskType task) {
        noInternetTask = task;
        return this;
    }
    
    public void registerBroadcastReceiver() {
        //
    }

    private static void doTask() {
        switch (noInternetTask) {
            case DO_NOTHING:
                break;
            case SHOW_TOAST_MESSAGE:
                Toast.makeText(context, Constants.NO_INTERNET_AVAILABLE, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
