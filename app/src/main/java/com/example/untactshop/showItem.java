package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class showItem extends AppCompatActivity {

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
    }

    private void upLoadOrder(ItemInfo item) {


        Order order = new Order();
        order.setuId(mAuth.getUid());
        order.setItemName(item.getTitle());
        order.setItemPrice(item.getPrice());
        order.setShopName(item.getShop_name());
        order.setImageUrl(item.getPhotoUrl());

        database.getReference().child("Orders"+order.getuId()).push().setValue(order);

    }

    private void showItem() {
        Log.d("객체", itemInfo.toString());

        item_price.setText(itemInfo.getPrice()+"원");
        shop_name.setText(itemInfo.getShop_name());
        item_name.setText(itemInfo.getTitle());

        Glide.with(getApplicationContext())
                .load(itemInfo.getPhotoUrl())
                .into(image);

    }

}
