package com.saadahmedev.networkutil.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.saadahmedev.networkutil.R;

public class NoInternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_animation_view);
        lottieAnimationView.setImageAssetsFolder("Assets");
        lottieAnimationView.setAnimation("no_internet.json");
    }
}