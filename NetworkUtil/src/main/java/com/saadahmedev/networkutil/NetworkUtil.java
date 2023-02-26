/*
 * Copyright 2018-2023 Saad Ahmed
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.saadahmedev.networkutil;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.saadahmedev.networkutil.receiver.ConnectivityReceiver;
import com.saadahmedev.networkutil.utils.Constants;
import com.saadahmedev.networkutil.utils.NoInternetActivity;
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
        if (context == null) throw new RuntimeException("NetworkUtil hasn't been initialized yet");
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
        BroadcastReceiver receiver = new ConnectivityReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.INTENT_FILTER_ACTION);
        context.registerReceiver(receiver, intentFilter);
    }

    private static void doTask() {
        switch (noInternetTask) {
            case DO_NOTHING:
                break;
            case SHOW_TOAST_MESSAGE:
                Toast.makeText(context, Constants.NO_INTERNET_AVAILABLE, Toast.LENGTH_SHORT).show();
                break;
            case SHOW_NO_INTERNET_DIALOG:
                break;
            case SHOW_NO_INTERNET_ACTIVITY:
                Intent intent = new Intent(context, NoInternetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
        }
    }
}
