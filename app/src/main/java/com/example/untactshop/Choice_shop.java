package com.example.untactshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Choice_shop extends AppCompatActivity implements View.OnClickListener {

    Button m;
    Button u;
    Button j;
    Button g;
    Button t;
    Button y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_shop);

        this.InitializeView();
        this.SetListener();
    }

    public void InitializeView() {
        m = (Button) findViewById(R.id.명동권);
        u = (Button) findViewById(R.id.을지로권);
        j = (Button) findViewById(R.id.종로권);
        g = (Button) findViewById(R.id.강남권);
        t = (Button) findViewById(R.id.터미널권);
        y = (Button) findViewById(R.id.영등포권);

    }


    public void onClick(View view) {
        final List<String> ListItems;
        final CharSequence[] items;
        AlertDialog.Builder builder;

        switch (view.getId()) {
            case R.id.명동권:
                ListItems = new ArrayList<>();
                ListItems.add("명동");
                ListItems.add("소공");
                ListItems.add("회현");
                ListItems.add("남대문");
                items = ListItems.toArray(new String[ListItems.size()]);

                builder = new AlertDialog.Builder(this);
                builder.setTitle("명동권");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                    Intent intent = new Intent(Choice_shop.this, Map.class);
                    intent.putExtra("name",selectedText);
                    startActivity(intent);
                    }
                });
                builder.show();
                break;
            case R.id.을지로권:
                ListItems = new ArrayList<>();
                ListItems.add("을지로");
                ListItems.add("시청광장");
                ListItems.add("을지입구");
                ListItems.add("인현");
                ListItems.add("신당");
                items = ListItems.toArray(new String[ListItems.size()]);

                builder = new AlertDialog.Builder(this);
                builder.setTitle("을지로권");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                        Intent intent = new Intent(Choice_shop.this, Map.class);
                        intent.putExtra("name",selectedText);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            case R.id.종로권:
                ListItems = new ArrayList<>();
                ListItems.add("종각");
                ListItems.add("종로4가");
                ListItems.add("종오");
                ListItems.add("청계5가");
                ListItems.add("청계6가");
                ListItems.add("마전교");
                ListItems.add("동대문");
                ListItems.add("청량리");
                items = ListItems.toArray(new String[ListItems.size()]);

                builder = new AlertDialog.Builder(this);
                builder.setTitle("종로권");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                        Intent intent = new Intent(Choice_shop.this, Map.class);
                        intent.putExtra("name",selectedText);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            case R.id.강남권:
                ListItems = new ArrayList<>();
                ListItems.add("강남역");
                ListItems.add("잠실역");
                ListItems.add("잠실 지하 광장");
                items = ListItems.toArray(new String[ListItems.size()]);

                builder = new AlertDialog.Builder(this);
                builder.setTitle("강남권");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                        Intent intent = new Intent(Choice_shop.this, Map.class);
                        intent.putExtra("name",selectedText);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            case R.id.터미널권:
                ListItems = new ArrayList<>();
                ListItems.add("고속터미널역");
                items = ListItems.toArray(new String[ListItems.size()]);

                builder = new AlertDialog.Builder(this);
                builder.setTitle("터미널권");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                        Intent intent = new Intent(Choice_shop.this, Map.class);
                        intent.putExtra("name",selectedText);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            case R.id.영등포권:
                ListItems = new ArrayList<>();
                ListItems.add("영등포역");
                ListItems.add("영등포로터리");
                ListItems.add("영등포시장");
                items = ListItems.toArray(new String[ListItems.size()]);

                builder = new AlertDialog.Builder(this);
                builder.setTitle("영등포권");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        String selectedText = items[pos].toString();
                        Intent intent = new Intent(Choice_shop.this, Map.class);
                        intent.putExtra("name",selectedText);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;

        }

    }


    public void SetListener() {
        m.setOnClickListener(this);
        u.setOnClickListener(this);
        j.setOnClickListener(this);
        g.setOnClickListener(this);
        t.setOnClickListener(this);
        y.setOnClickListener(this);
    }


    public void search_btn(View view) {
        String search_text;

        EditText editText = (EditText) findViewById(R.id.shop_search_text);
        search_text = "" + editText.getText(); //SpannableString -> String : Type conversion
//        Log.i("검색데이터", String.valueOf(search_text));

        Intent intent = new Intent(getApplicationContext(), ShopSearchActivity.class);
        intent.putExtra("검색데이터", search_text);
        startActivity(intent);


    }

}