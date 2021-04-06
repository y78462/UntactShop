package com.example.untactshop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Map extends AppCompatActivity {

    public List<shopInfo> shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        TextView name = this.findViewById(R.id.textView);
        TextView name1 = this.findViewById(R.id.textView1);
        TextView name2 = this.findViewById(R.id.textView2);
        TextView name3 = this.findViewById(R.id.textView3);

        name.setText(getIntent().getStringExtra("name"));

        initLoadDB();
        name1.setText(shopList.get(0).getShop_name()+shopList.get(0).getRoom_name()+shopList.get(0).getCategory());
        name2.setText(shopList.get(999).getShop_name()+shopList.get(999).getRoom_name()+shopList.get(999).getCategory());
        name3.setText(shopList.get(1999).getShop_name()+shopList.get(1999).getRoom_name()+shopList.get(1999).getCategory());


    }

    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
        shopList = mDbHelper.getTableData();

        // db 닫기
        mDbHelper.close();
    }
}