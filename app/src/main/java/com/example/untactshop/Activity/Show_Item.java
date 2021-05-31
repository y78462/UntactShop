package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.untactshop.Activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Show_Item extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
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
        image = findViewById(R.id.item_image);
        item_name = findViewById(R.id.item_name);
        item_price = findViewById(R.id.item_price);
        shop_name = findViewById(R.id.shop_name);

        Intent intent = getIntent();
        itemInfo = (ItemInfo) intent.getSerializableExtra("item");
        Log.d("객체", itemInfo.toString());
        showItem();

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
}
