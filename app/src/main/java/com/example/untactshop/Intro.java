package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Intro  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Home_main.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}