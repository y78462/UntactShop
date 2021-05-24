package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_home);

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
                startActivity(intent);
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
                        startActivity(new Intent(getApplicationContext(), Shopping_shop.class));
                        overridePendingTransition(0, 0);
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

        mBottomNV.setSelectedItemId(R.id.nav_home);
    }
}