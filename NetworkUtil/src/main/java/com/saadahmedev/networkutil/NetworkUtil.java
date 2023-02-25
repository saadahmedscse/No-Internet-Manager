package com.saadahmedev.networkutil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);

            for (int networkType : networkTypes()) {
                if (networkCapabilities.hasTransport(networkType)) return true;
            }
        }
        else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo.isConnectedOrConnecting() && networkInfo.isAvailable()) return true;

        }
        doNoInternetTask(doIfNoInternetAvailable);
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private int[] networkTypes() {
        return new int[]{
                NetworkCapabilities.TRANSPORT_WIFI,
                NetworkCapabilities.TRANSPORT_CELLULAR,
                NetworkCapabilities.TRANSPORT_ETHERNET
        };
    }

    private void doNoInternetTask(int action) {
        switch (action) {
            case DO_NOTHING:
                break;
            case SHOW_TOAST_MESSAGE:
                Toast.makeText(context, Constants.NO_INTERNET_MESSAGE, Toast.LENGTH_SHORT).show();
                break;
            case SHOW_NO_INTERNET_ACTIVITY:
                break;
            case SHOW_NO_INTERNET_DIALOG:
                break;
        }
    }
}
