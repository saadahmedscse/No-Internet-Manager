package com.saadahmedev.networkutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.saadahmedev.networkutil.utils.Constants;

public class NetworkUtil {

    public static final int SHOW_NO_INTERNET_ACTIVITY = 0x00000001;
    public static final int SHOW_NO_INTERNET_DIALOG = 0x00000002;
    public static final int DO_NOTHING = 0x00000003;
    public static final int SHOW_TOAST_MESSAGE = 0x00000004;

    private final Context context;

    private NetworkUtil(Context context) {
        this.context = context;
    }

    public static NetworkUtil getInstance(Context context) {
        return new NetworkUtil(context);
    }

    public boolean isInternetAvailable(int doIfNoInternetAvailable) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            try {
                String command = "ping -c 1 google.com";
                if (Runtime.getRuntime().exec(command).waitFor() == 0) return true;
                doNoInternetTask(doIfNoInternetAvailable, Constants.CONNECTION_PROBLEM);
                return false;
            } catch (Exception e) {
                doNoInternetTask(doIfNoInternetAvailable, Constants.CONNECTION_PROBLEM);
                return false;
            }
        }

        doNoInternetTask(doIfNoInternetAvailable, Constants.NO_INTERNET_AVAILABLE);
        return false;
    }

    private void doNoInternetTask(int action, String message) {
        switch (action) {
            case DO_NOTHING:
                break;
            case SHOW_TOAST_MESSAGE:
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                break;
            case SHOW_NO_INTERNET_ACTIVITY:
                break;
            case SHOW_NO_INTERNET_DIALOG:
                break;
        }
    }
}
