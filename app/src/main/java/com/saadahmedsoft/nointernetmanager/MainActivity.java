package com.saadahmedsoft.nointernetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.saadahmedev.networkutil.NetworkUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkUtil networkUtil = NetworkUtil.getInstance(this);
        if (networkUtil.isInternetAvailable(NetworkUtil.SHOW_TOAST_MESSAGE)) {
            //has
        }
    }
}