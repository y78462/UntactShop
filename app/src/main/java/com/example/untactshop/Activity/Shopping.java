package com.example.untactshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.untactshop.Adapter.ItemAdapter;
import com.example.untactshop.Adapter.ItemSliderAdapter;
import com.example.untactshop.ItemInfo;
import com.example.untactshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;


public class Shopping extends AppCompatActivity {

    int i = 0;

    private BottomNavigationView mBottomNV;
    private GridView gridView;
    private ItemAdapter adapter;
    private ArrayList<ItemInfo> items;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private ViewPager2 sliderViewPager;
    private LinearLayout layoutIndicator;

    private String[] images = new String[] {
            "https://cdn.pixabay.com/photo/2017/08/01/11/48/woman-2564660_960_720.jpg",
            "https://cdn.pixabay.com/photo/2016/11/19/20/17/catwalk-1840941_960_720.jpg",
            "https://cdn.pixabay.com/photo/2017/08/05/00/12/girl-2581913_960_720.jpg",
            "https://cdn.pixabay.com/photo/2020/09/02/18/03/girl-5539094_1280.jpg",
            "https://cdn.pixabay.com/photo/2017/04/06/11/24/fashion-2208045_960_720.jpg"
    };
    private boolean LogIn = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_home);

        sliderViewPager = findViewById(R.id.sliderViewPager);
        layoutIndicator = findViewById(R.id.layoutIndicators);

        sliderViewPager.setOffscreenPageLimit(1);
        sliderViewPager.setAdapter(new ItemSliderAdapter(this, images));

        sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });

        setupIndicators(images.length);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) { //로그인 안됐으면 회원가입 페이지로 이동시킴.\
            //로그인된 유저 확인
            Log.d("쇼핑 로그인 여부", "true");
            LogIn = true;
        }
        else
        {
            Log.d("쇼핑 로그인 여부", "fail");
            LogIn = false;
        }


        items = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        gridView = (GridView)findViewById(R.id.gridView);

        //상품 랜덤
        reference.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ItemInfo item = dataSnapshot.getValue(ItemInfo.class);
                    items.add(item);
                }

                Collections.shuffle(items);

                for(int j=items.size(); j>30; j--){
                    items.remove(0);
                }
                Log.d("size", "d"+items.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new ItemAdapter(items, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemInfo selectitem = (ItemInfo) adapter.getItem(i);
                Intent intent = new Intent(Shopping.this, Show_Item.class);
                intent.putExtra("item", selectitem);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();
            }
        });
        

        //네비게이션 바
        mBottomNV = findViewById(R.id.bottom_navigation_view);
        mBottomNV.setSelectedItemId(R.id.nav_home);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        return true;

                    case R.id.nav_shop:
                       // mBottomNV.setSelectedItemId(R.id.nav_shop);
                        startActivity(new Intent(getApplicationContext(), Shopping_shop.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_cart:
                        if (LogIn)
                        {
                            startActivity(new Intent(getApplicationContext(), Show_orders.class));
                            overridePendingTransition(0, 0);
                            return true;
                        }
                        else{
                            startToast("로그인을 하세요");
                            mBottomNV.setSelectedItemId(R.id.nav_home);
                            return true;
                        }

                    case R.id.nav_my:
                       // startToast("로그인변수 "+LogIn);
                        if (LogIn)
                        {
                            startActivity(new Intent(getApplicationContext(), My_page.class));
                            overridePendingTransition(0, 0);
                            return true;
                        }
                        else{
                            startToast("로그인을 하세요");
                            mBottomNV.setSelectedItemId(R.id.nav_home);
                            return true;
                        }
                }
                return false;
            }
        });


    }
    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_inactive
                ));
            }
        }

    }
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        if (FirebaseAuth.getInstance().getCurrentUser() == null) { //로그인 안됐으면 홈메인
            //로그인된 유저 확인
            Log.d("로그인 여부", "fail");
            Intent intent = new Intent(Shopping.this, Home_main.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(getApplicationContext(), Home_login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}