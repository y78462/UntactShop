package com.example.untactshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class My_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);

        Button order_search = (Button)findViewById(R.id.order_search); //주문조회 버튼
        order_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order_intent = new Intent(My_page.this, Order_search.class);
                startActivity(order_intent);
            }
        });

        Button infor_modify = (Button)findViewById(R.id.infor_modify); //개인정보수정 버튼
        infor_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infor_intent = new Intent(My_page.this, Infor_modify.class);
                startActivity(infor_intent);
            }
        });

        Button logout = (Button)findViewById(R.id.logout); //로그아웃 버튼
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logout_intent = new Intent(My_page.this, Home_main.class);
                startActivity(logout_intent);
            }
        });
    }
}