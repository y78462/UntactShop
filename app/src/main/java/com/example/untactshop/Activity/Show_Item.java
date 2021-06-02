package com.example.untactshop.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.untactshop.Adapter.RecommendItemAdapter;
import com.example.untactshop.ItemInfo;
import com.example.untactshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Show_Item extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ImageView image;
    private ItemInfo itemInfo;
    private TextView shop_name;
    private TextView item_name;
    private TextView item_price;
    private TextView text;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        image = findViewById(R.id.item_image);
        item_name = findViewById(R.id.item_name);
        item_price = findViewById(R.id.item_price);
        shop_name = findViewById(R.id.shop_name);
        text = findViewById(R.id.text);

        Intent intent = getIntent();
        itemInfo = (ItemInfo) intent.getSerializableExtra("item");
        Log.d("객체", itemInfo.toString());
        showItem();

        if(itemInfo.getCategory().equals("디지털가전")) {
            text.setVisibility(View.INVISIBLE);
            item_name.setBackgroundColor(Color.WHITE);
            shop_name.setBackgroundColor(Color.WHITE);
        }
        else{
            RecommendedList();
        }

        Button item_chat_btn = (Button) findViewById(R.id.chatting);
        item_chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("로그인 여부1", String.valueOf(FirebaseAuth.getInstance().getCurrentUser()));
                if (FirebaseAuth.getInstance().getCurrentUser() == null) { //로그인 안됐으면 로그인 페이지로 이동시킴
                    //로그인된 유저 확인
//                    Log.d("로그인 여부2", String.valueOf(FirebaseAuth.getInstance().getCurrentUser()));
                    Intent intent = new Intent(Show_Item.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Show_Item.this, ItemChatActivity.class);
                    intent.putExtra("item", itemInfo);
                    startActivity(intent);
                }
            }
        });

    }

    private void showItem() {
        Log.d("객체", itemInfo.toString());

        item_price.setText(itemInfo.getPrice() + "원");
        shop_name.setText(itemInfo.getShop_name());
        item_name.setText(itemInfo.getTitle());

        Glide.with(getApplicationContext())
                .load(itemInfo.getPhotoUrl())
                .into(image);

    }

    public void RecommendedList() {

        ArrayList<ItemInfo> recommendList = new ArrayList<>();
        ArrayList<ItemInfo> allItem = new ArrayList<>();
        ArrayList<ItemInfo> first = new ArrayList<>();
        ArrayList<ItemInfo> second = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        reference.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recommendList.clear();
                allItem.clear();
                first.clear();
                second.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ItemInfo item = dataSnapshot.getValue(ItemInfo.class);

                    if (item.getCategory().equals(itemInfo.getCategory()))
                        allItem.add(item);
                }

                for (ItemInfo item : allItem) {
                    if (itemInfo.getKey1().equals(item.getKey1()) && itemInfo.getKey2().equals(item.getKey2()) && itemInfo.getKey3().equals(item.getKey3())) {
                        if(itemInfo.getTitle().equals(item.getTitle()))
                            continue;
                        first.add(item);
                    } else if ((itemInfo.getKey1().equals(item.getKey1()) && itemInfo.getKey2().equals(item.getKey2())) && (!(itemInfo.getKey1().equals("")) && !(itemInfo.getKey2().equals("")))) {
                        second.add(item);
                    } else if ((itemInfo.getKey1().equals(item.getKey1()) && itemInfo.getKey3().equals(item.getKey3())) && (!(itemInfo.getKey1().equals("")) && !(itemInfo.getKey3().equals("")))) {
                        second.add(item);
                    }
                }

                recommendList.addAll(first);
                recommendList.addAll(second);
                adapter.notifyDataSetChanged();

                if(recommendList.isEmpty()){
                    text.setVisibility(View.INVISIBLE);
                    item_name.setBackgroundColor(Color.WHITE);
                    shop_name.setBackgroundColor(Color.WHITE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        adapter = new RecommendItemAdapter(recommendList, this);
        recyclerView.setAdapter(adapter);
    }
}