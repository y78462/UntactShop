package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.untactshop.Activity.Shop;
import com.example.untactshop.Activity.Show_Item;
import com.example.untactshop.Adapter.ItemAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ItemFragment extends AppCompatActivity {

    private GridView gridView;
    private ItemAdapter adapter;
    private TextView shop_name;
    private TextView category;
    private TextView location;
    private CircleImageView circleImageView;
    private Shop shop;
    private ArrayList<ItemInfo> items;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_fragment);

        shop_name = findViewById(R.id.shop_name);
        circleImageView = findViewById(R.id.img_category);
        category = findViewById(R.id.category);
        location = findViewById(R.id.location);
        shop_name = findViewById(R.id.shop_name);
        items = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        gridView = (GridView)findViewById(R.id.gridView);

        Intent intent = getIntent();
        shop = (Shop) intent.getSerializableExtra("shop");
        String shop_name = shop.getShopname();
        set_shopinfo();

        reference.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ItemInfo item = dataSnapshot.getValue(ItemInfo.class);

                    if(item.getShop_name().equals(shop_name))
                        items.add(item);
                }
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
                ItemInfo selectitem = (ItemInfo)adapter.getItem(i);
                Intent intent = new Intent(ItemFragment.this, Show_Item.class);
                intent.putExtra("item", selectitem);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void set_shopinfo() {
        shop_name.setText(shop.getShopname());
        category.setText(shop.getCategory());
        location.setText(shop.getLocation());
        String shop_category = shop.getCategory();
        switch (shop_category) {
            case "패션의류":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_fashion));
                break;
            case "쇼핑미용":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_beauty));
                break;
            case "디지털 가전":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_digital));
                break;
        }
    }
}
