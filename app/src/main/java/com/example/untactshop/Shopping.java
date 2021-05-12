package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Shopping extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    int i = 0;
    private ItemInfo itemInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
//                db.getReference().child("items").child("가게_상품").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {  //변화된 값이 DataSnapshot 으로 넘어온다.
//                        //데이터가 쌓이기 때문에  clear()
//
//                        for (DataSnapshot ds : dataSnapshot.getChildren())           //여러 값을 불러와 하나씩
//                        {
//                            itemInfo = ds.getValue(ItemInfo.class);
//                            Intent intent = new Intent(getApplicationContext(), showItem.class);
//                            intent.putExtra("item", itemInfo);
//                            startActivity(intent);
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

                itemInfo = new ItemInfo("아이패드", "황재환", "디지털가전", "10000", "https://lh3.googleusercontent.com/proxy/XNSdArCecu4efq2bsondvI0huTpaMOAYY0WoHR1F6ihkChvu07iifNPvbJ7FFgkDs2aivKbQjbepQrWZnuGk-F25zIoqZE07GioJ9gi2rqlLNOwlq0t8HwI");
                Intent intent = new Intent(getApplicationContext(), Show_Item.class);
                intent.putExtra("item", itemInfo);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Log.i("하이","i : " + i);
                    Order order = new Order();
                    order.setuId(auth.getUid());
                    order.setItemName("아이템"+i);
                    order.setItemPrice("가격");
                    order.setShopName("가게이름"+i);
                    order.setImageUrl("https://www.mpps.co.kr/kfcs_api_img/KFCS/goods/DL_2174430_20210203113535665.png");
                    Log.i("하이", "11 : "+ order.getuId());
                    db.getReference().child("Orders_"+auth.getUid()).push().setValue(order);
                    i++;
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Show_orders.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemFragment.class);
                intent.putExtra("shop", "나나");
                startActivity(intent);
            }
        });
    }
}
