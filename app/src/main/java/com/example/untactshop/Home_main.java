package com.example.untactshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_main extends AppCompatActivity { //시작 홈화면(로그인 하기 전)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

        Button map_button = (Button)findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map_intent = new Intent(Home_main.this, Map_search.class);
                startActivity(map_intent);
            }
        });

        Button shop_button = (Button)findViewById(R.id.shop_button);
        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shop_intent = new Intent(Home_main.this, Shopping.class);
                startActivity(shop_intent);
            }
        });

        Button login_button = (Button)findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent = new Intent(Home_main.this, Login.class);
                startActivity(login_intent);
            }
        });
    }
}