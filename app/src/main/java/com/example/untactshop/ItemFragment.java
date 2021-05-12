package com.example.untactshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ItemFragment extends AppCompatActivity {

    private GridView gridView;
    private ItemAdapter adapter;
    private TextView shop_name;
    private View view;
    private ArrayList<ItemInfo> items;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String shop;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_fragment);

        shop_name = (TextView) findViewById(R.id.shop_name);
        items = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        gridView = (GridView)findViewById(R.id.gridView);
        shop = "shop1";
        shop_name.setText(shop);

        reference.child(shop).addValueEventListener(new ValueEventListener() {
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

        adapter = new ItemAdapter(items, this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemInfo selectitem = (ItemInfo)adapter.getItem(i);
                Toast.makeText(getApplicationContext(), selectitem.getShop_name(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),selectitem.getTitle(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), selectitem.getPrice(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),selectitem.getPhotoUrl(), Toast.LENGTH_LONG).show();
            }
        });
    }



}
