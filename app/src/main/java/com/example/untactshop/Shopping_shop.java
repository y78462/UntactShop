package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        reference.child("Shop").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shops.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
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
