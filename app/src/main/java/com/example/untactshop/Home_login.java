package com.example.untactshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_login);

        Button map_button = (Button)findViewById(R.id.map_button); //지도보기 버튼
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map_intent = new Intent(Home_login.this, Map_search.class);
                startActivity(map_intent);
            }
        });

        Button shop_button = (Button)findViewById(R.id.shop_button); //쇼핑하기 버튼
        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shop_intent = new Intent(Home_login.this, Shopping.class);
                startActivity(shop_intent);
            }
        });

        Button mypage_button = (Button)findViewById(R.id.mypage_button); //마이페이지 버튼
        mypage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mypage_intent = new Intent(Home_login.this, My_page.class);
                startActivity(mypage_intent);
            }
        });
    }
}