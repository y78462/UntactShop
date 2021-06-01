package com.example.untactshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactshop.Adapter.OrderAdapter;
import com.example.untactshop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_orders extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemInfo> items;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;


    private BottomNavigationView mBottomNV;
    private boolean LogIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_orders);


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

        items = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        reference.child("Orders_"+auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ItemInfo item = dataSnapshot.getValue(ItemInfo.class);
                    items.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new OrderAdapter(items, this);
        recyclerView.setAdapter(adapter);


        mBottomNV = findViewById(R.id.bottom_navigation_view);
        mBottomNV.setSelectedItemId(R.id.nav_cart);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Shopping.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_shop:
                        startActivity(new Intent(getApplicationContext(), Shopping_shop.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_cart:
                        return true;

                    case R.id.nav_my:
                        if (LogIn)
                        {
                            startActivity(new Intent(getApplicationContext(), My_page.class));
                            overridePendingTransition(0, 0);
                            return true;
                        }
                        else{
                            startToast("로그인을 하세요");
                            mBottomNV.setSelectedItemId(R.id.nav_cart);
                            return true;
                        }
                }
                return false;
            }


        });

    }  private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        mBottomNV.setSelectedItemId(R.id.nav_home);
    }
}
