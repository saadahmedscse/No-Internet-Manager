package com.saadahmedev.networkutil.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.saadahmedev.networkutil.NetworkUtil;
import com.saadahmedev.networkutil.utils.Constants;

public class ConnectivityReceiver extends BroadcastReceiver {

    private boolean hadConnection = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!hadConnection && NetworkUtil.isInternetAvailable(context)) {
            hadConnection = true;
            Toast.makeText(context, "Connections restored", Toast.LENGTH_SHORT).show();
            return;
        }
        if (hadConnection && !NetworkUtil.isInternetAvailable(context)) {
            hadConnection = false;
            Toast.makeText(context, Constants.NO_INTERNET_AVAILABLE, Toast.LENGTH_SHORT).show();
        }
    }
}
