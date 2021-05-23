package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Shop_random_items extends AppCompatActivity {
    private BottomNavigationView mBottomNV;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_random_shop);

        mBottomNV = findViewById(R.id.bottom_navigation_view);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Shopping.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_shop:
                        return true;

                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(), Show_orders.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_my:
                        startActivity(new Intent(getApplicationContext(), My_page.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }


        });
        mBottomNV.setSelectedItemId(R.id.nav_shop);
    }


}
