package com.example.untactshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity {

    TextView name, category, location;
    String shop_name, shop_location, shop_category;
    CircleImageView circleImageView;

    private RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList;
    private String nick = "nick2";

    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        name = (TextView) findViewById(R.id.chat_text_name);
        category = (TextView) findViewById(R.id.chat_text_category);
        location = (TextView) findViewById(R.id.chat_text_location);
        circleImageView = findViewById(R.id.chat_img_category);

        Intent intent = getIntent();
        shop_name = intent.getStringExtra("name");
        name.setText(shop_name);

        shop_category = intent.getStringExtra("category");
        category.setText(shop_category);

        shop_location = intent.getStringExtra("location");
        location.setText(shop_location);

//        Log.d("intent", shop_name);
//        Log.d("intent", shop_category);
//        Log.d("intent", shop_location);

        switch (shop_category) {
            case "음식점":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_restaurant));
                break;
            case "패션의류":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_fashion));
                break;
            case "쇼핑미용":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_beauty));
                break;
            case "디지털 가전":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_digital));
                break;
            case "편의시설":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_convenient));
                break;
            case "기타매장":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_etc));
                break;
            case "공방":
                circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_craft));
                break;
        }

        Button_send = findViewById(R.id.Button_send);
        EditText_chat = findViewById(R.id.EditText_chat);


        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = EditText_chat.getText().toString(); //msg

                long mNow = (System.currentTimeMillis() + 9);
                Date mReDate = new Date(mNow);
                SimpleDateFormat mFormat = new SimpleDateFormat("a HH:mm");
                String time = mFormat.format(mReDate);

                if (msg != null) {
                    ChatData chat = new ChatData();
                    chat.setNickname(nick);
                    chat.setMsg(msg);
                    chat.setTime(time);
                    myRef.push().setValue(chat);
                }
            }
        });

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList, ChatActivity.this, nick);

        mRecyclerView.setAdapter(mAdapter);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

//        ChatData chat = new ChatData();
//        chat.setNickname(nick);
//        chat.setMsg("hi");
//        myRef.setValue(chat);
        //caution!!!

        myRef.addChildEventListener(new ChildEventListener() { //새로운 밸류가 추가되면 호출되는 메소드
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("CHATCHAT", dataSnapshot.getValue().toString());

                if (dataSnapshot.getValue(ChatData.class) == null) {
                }

                ChatData chat = dataSnapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat);

//                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //0.채팅 앱 만들기
        //1. recycleView - 어떤 데이터를 반복해 보여주는 용도로 많이씀.
        //2. 데이터베이서 내용을 넣는다.
        //3. 상대방폰에 채팅 내용이 보이게.

        //1-1. recycleview - chat data
        //1. message, nickname, ismine - Data Transfer Object(데이터를 교환하는 객체)

    }
}