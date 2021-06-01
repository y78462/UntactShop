package com.example.untactshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.untactshop.Adapter.ChatAdapter;
import com.example.untactshop.ChatData;
import com.example.untactshop.ItemInfo;
import com.example.untactshop.MemberInfo;
import com.example.untactshop.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ItemChatActivity extends AppCompatActivity {
    private ItemInfo itemInfo;
    ImageView item_img;
    TextView item_name, item_price;
    String shop_name;

    private RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList;
    private String nick;

    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;
    private String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_chat);

        item_name = (TextView) findViewById(R.id.chat_item_name);
        item_price = (TextView) findViewById(R.id.chat_item_price);
        item_img = findViewById(R.id.chat_item_img);

        Intent intent = getIntent();
        itemInfo = (ItemInfo) intent.getSerializableExtra("item");
        Log.d("CHATCHAT객체", itemInfo.toString());

        item_price.setText(itemInfo.getPrice() + "원");
        item_name.setText(itemInfo.getTitle());

        shop_name = itemInfo.getShop_name();

        Glide.with(getApplicationContext())
                .load(itemInfo.getPhotoUrl())
                .into(item_img);


        Button_send = findViewById(R.id.Button_send);
        EditText_chat = findViewById(R.id.EditText_chat);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(user.getUid());
        Email = user.getEmail();
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                MemberInfo memberInfo = documentSnapshot.toObject(MemberInfo.class);
                if (memberInfo != null) {
                    nick = memberInfo.getName();
                }
            }
        });


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
                    chat.setShop_name(shop_name);

                    if (Email.contains("admin")) {
                        chat.setIdentify("admin");
                    } else {
                        chat.setIdentify("user");
                    }

//                    myRef.push().setValue(chat);
                    myRef.child("Chat").child(shop_name).push().setValue(chat);
                }

                EditText_chat.setText(null); //텍스트 입력 창 비우기
            }
        });

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList, ItemChatActivity.this, nick);

        mRecyclerView.setAdapter(mAdapter);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

//        ChatData chat = new ChatData();
//        chat.setNickname(nick);
//        chat.setMsg("hi");
//        myRef.setValue(chat);
        //caution!!!

        myRef.child("Chat").child(shop_name).addChildEventListener(new ChildEventListener() { //새로운 밸류가 추가되면 호출되는 메소드
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("CHATCHAT", dataSnapshot.getValue().toString());

                if (dataSnapshot.getValue(ChatData.class) == null) {
                }

                ChatData chat = dataSnapshot.getValue(ChatData.class); //data 받아오기

                if (shop_name.equals(chat.getShop_name())) {
                    ((ChatAdapter) mAdapter).addChat(chat); //data를 adapter에 넣기

//                mAdapter.notifyDataSetChanged();

                    mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                }
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