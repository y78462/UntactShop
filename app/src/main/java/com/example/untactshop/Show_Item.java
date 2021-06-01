package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Show_Item extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ImageView image;
    private ItemInfo itemInfo;
    private TextView shop_name;
    private TextView item_name;
    private TextView item_price;



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

        Intent intent = getIntent();
        itemInfo = (ItemInfo) intent.getSerializableExtra("item");
        Log.d("객체", itemInfo.toString());
        showItem();
        //RecommendedList();

        Button item_chat_btn = (Button)findViewById(R.id.chatting);
        item_chat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("로그인 여부1", String.valueOf(FirebaseAuth.getInstance().getCurrentUser()));
                if (FirebaseAuth.getInstance().getCurrentUser() == null) { //로그인 안됐으면 로그인 페이지로 이동시킴
                    //로그인된 유저 확인
                    Log.d("로그인 여부2", String.valueOf(FirebaseAuth.getInstance().getCurrentUser()));
                    Intent intent = new Intent(Show_Item.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(Show_Item.this, ItemChatActivity.class);
                    intent.putExtra("item", itemInfo);
                    reference.child("Orders_" + mAuth.getUid()).setValue(itemInfo);
                    startActivity(intent);
                }
            }
        });

    }

//    private void upLoadOrder(ItemInfo item) {
//
//
//        Order order = new Order();
//        order.setuId(mAuth.getUid());
//        order.setItemName(item.getTitle());
//        order.setItemPrice(item.getPrice());
//        order.setShopName(item.getShop_name());
//        order.setImageUrl(item.getPhotoUrl());
//
//        database.getReference().child("Orders"+order.getuId()).push().setValue(order);
//
//    }

    private void showItem() {
        Log.d("객체", itemInfo.toString());

        item_price.setText(itemInfo.getPrice() + "원");
        shop_name.setText(itemInfo.getShop_name());
        item_name.setText(itemInfo.getTitle());

        Glide.with(getApplicationContext())
                .load(itemInfo.getPhotoUrl())
                .into(image);

    }


//    public void item_chat_btn(View view) {
//        Log.d("로그인 여부1", String.valueOf(FirebaseAuth.getInstance().getCurrentUser()));
//        if (FirebaseAuth.getInstance().getCurrentUser() == null) { //로그인 안됐으면 로그인 페이지로 이동시킴
//            //로그인된 유저 확인
//            Log.d("로그인 여부2", String.valueOf(FirebaseAuth.getInstance().getCurrentUser()));
//            Intent intent = new Intent(Show_Item.this, LoginActivity.class);
//            startActivity(intent);
//        }
//        Intent intent = new Intent(Show_Item.this, ItemChatActivity.class);
//        intent.putExtra("item", itemInfo);
//        startActivity(intent);
//    }

    public List<ItemInfo> RecommendedList() {

        List<ItemInfo> recommendList = new ArrayList<>();
        List<ItemInfo> allItem = new ArrayList<>();
        List<ItemInfo> first = new ArrayList<>();
        List<ItemInfo> second = new ArrayList<>();
        List<ItemInfo> third = new ArrayList<>();



        reference.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ItemInfo item = dataSnapshot.getValue(ItemInfo.class);

                    if(item.getCategory().equals(itemInfo.getCategory()))
                        allItem.add(item);
                }

                Collections.sort(allItem);

                for (ItemInfo info : allItem) {
                    Log.i("정렬", info.getPrice());
                }

                if(itemInfo.getKey1().equals("")&&itemInfo.getKey2().equals("")&&itemInfo.getKey3().equals("")){

                }
                else {
                    for (ItemInfo item : allItem) {
                        if (itemInfo.getKey1().equals(item.getKey1()) && itemInfo.getKey2().equals(item.getKey2()) && itemInfo.getKey3().equals(item.getKey3())) {
                            first.add(item);
                        } else if ((itemInfo.getKey1().equals(item.getKey1()) && itemInfo.getKey2().equals(item.getKey2())) && (!(itemInfo.getKey1().equals("")) && !(itemInfo.getKey2().equals("")))) {
                            second.add(item);
                        } else if ((itemInfo.getKey1().equals(item.getKey1()) && itemInfo.getKey3().equals(item.getKey3())) && (!(itemInfo.getKey1().equals("")) && !(itemInfo.getKey3().equals("")))) {
                            second.add(item);
                        } else if ((itemInfo.getKey2().equals(item.getKey2()) && itemInfo.getKey3().equals(item.getKey3())) && (!(itemInfo.getKey2().equals("")) && !(itemInfo.getKey3().equals("")))) {
                            second.add(item);
                        } else if (itemInfo.getKey1().equals(item.getKey1()) && !(itemInfo.getKey1().equals(""))) {
                            third.add(item);
                        } else if (itemInfo.getKey2().equals(item.getKey2()) && !(itemInfo.getKey2().equals(""))) {
                            third.add(item);
                        } else if (itemInfo.getKey3().equals(item.getKey3()) && !(itemInfo.getKey3().equals(""))) {
                            third.add(item);
                        }

                    }
                    Collections.sort(first);
                    Collections.sort(second);
                    Collections.sort(third);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return recommendList;
    }
}
