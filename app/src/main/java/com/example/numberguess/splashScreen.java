package com.example.numberguess;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashScreen extends AppCompatActivity {

    ImageView alphaa;
    ImageView alphas;

    Animation animationa,animations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        alphaa = findViewById(R.id.imageView);
        alphas = findViewById(R.id.imageView3);

        animationa = AnimationUtils.loadAnimation(this,R.anim.alphabeta);
        animations = AnimationUtils.loadAnimation(this,R.anim.alphabets);

        alphaa.setAnimation(animationa);
        alphas.setAnimation(animations);

        CountDownTimer count = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

                startActivity(new Intent(splashScreen.this,MainActivity.class));
                finish();
            }
        }.start();


    }
}