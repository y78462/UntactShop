package com.example.untactshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactshop.Adapter.ShopAdapter;
import com.example.untactshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Shopping_shop extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Shop> shops;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private BottomNavigationView mBottomNV;

    boolean LogIn = false;
    @Override
    protected void onResume() {

        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_shop);

        shops = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) { //로그인 안됐으면 회원가입 페이지로 이동시킴.\
            //로그인된 유저 확인
            Log.d("로그인 여부", "true");
            LogIn = true;
        }
        else
        {
            Log.d("로그인 여부", "fail");
            LogIn = false;
        }

        reference.child("Shop").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shops.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.d("shop", "shop" + dataSnapshot.getKey());
                    Shop shop = dataSnapshot.getValue(Shop.class);
                    shop.setShopname(dataSnapshot.getKey());

                    shops.add(shop);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new ShopAdapter(shops, this);
        recyclerView.setAdapter(adapter);

//        adapter.setOnClickListener(new AdapterView.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int pos1 = getBindingAdapterPosition();
//                int pos2 = getAbsoluteAdapterPosition();
//                Log.d("pos" , "1" + pos1 + "2" + pos2);
//            }
//        });


        //shop_search_btn
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        ImageButton shop_search_btn = (ImageButton) findViewById(R.id.shop_search_btn); //쇼핑하기 버튼
        shop_search_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                TextView shop_search_text = (TextView) findViewById(R.id.shop_search_text);
                String search_title = shop_search_text.getText().toString();
                Log.d("shop search_title", "search_title"+ search_title);
//                if (FirebaseAuth.getInstance().getCurrentUser() != null) { //로그인 안됐으면 회원가입 페이지로 이동시킴.\
//                    //로그인된 유저 확인
//                    Log.d("로그인 여부", "true");
//                    LogIn = true;
//                }
//                else
//                {
//                    Log.d("로그인 여부", "fail");
//                    LogIn = false;
//                }

                if (search_title == null) {
                    reference.child("Shop").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            shops.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Shop shop = dataSnapshot.getValue(Shop.class);
                                shop.setShopname(dataSnapshot.getKey());
                                shops.add(shop);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    return;
                }
                reference.child("Shop").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        shops.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Log.d("shopkey", "shopkey" + dataSnapshot.getKey());
                            Log.d("shopvalue", "shopvalue" + dataSnapshot.getKey());

                            if (dataSnapshot.getKey().equals(search_title)) {
                                Shop shop = dataSnapshot.getValue(Shop.class);
                                shop.setShopname(dataSnapshot.getKey());
                                shops.add(shop);
                            }
//                            shop.setShopname(dataSnapshot.getKey());
//                            shops.add(shop);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                imm.hideSoftInputFromWindow(shop_search_text.getWindowToken(), 0);
            }
        });


        mBottomNV = findViewById(R.id.bottom_navigation_view);
        mBottomNV.setSelectedItemId(R.id.nav_shop);
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
                        startToast("로그인변수 "+LogIn);
                        if (LogIn)
                        {
                            startActivity(new Intent(getApplicationContext(), Show_orders.class));
                            overridePendingTransition(0, 0);
                            return true;
                        } else {
                            startToast("로그인을 하세요");
                            mBottomNV.setSelectedItemId(R.id.nav_home);
                            return true;
                        }

                    case R.id.nav_my:
                        startToast("로그인변수 "+LogIn);
                        if (LogIn)
                        {
                            startActivity(new Intent(getApplicationContext(), My_page.class));
                            overridePendingTransition(0, 0);
                            return true;
                        } else {
                            startToast("로그인을 하세요");
                            mBottomNV.setSelectedItemId(R.id.nav_home);
                            return true;
                        }
                }
                return false;
            }
        });

    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        mBottomNV.setSelectedItemId(R.id.nav_home);
    }
}
