package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;


public class Shopping extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    public shopInfo test1 = new shopInfo("강남역", "비프루브 강남역점", "A-1,2", "쇼핑미용");
    private ItemInfo itemInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test2);

        Button button1 = (Button) findViewById(R.id.button);
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
                Intent intent = new Intent(getApplicationContext(), showItem.class);
                intent.putExtra("item", itemInfo);
                startActivity(intent);
            }
        });
    }
}
