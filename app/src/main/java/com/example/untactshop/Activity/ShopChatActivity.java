package com.example.untactshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.untactshop.Adapter.ChatAdapter;
import com.example.untactshop.ChatData;
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

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ShopChatActivity extends AppCompatActivity {

    TextView name, category, location;
    String shop_name, shop_location, shop_category;
    CircleImageView circleImageView;

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
        setContentView(R.layout.chat);

        name = (TextView) findViewById(R.id.chat_text_name);
        category = (TextView) findViewById(R.id.chat_text_category);
        location = (TextView) findViewById(R.id.chat_text_location);
        circleImageView = findViewById(R.id.chat_img_category);

        Intent intent = getIntent();
        shop_name = intent.getStringExtra("name");
        name.setText(shop_name);

        readExcel();
        category.setText(shop_category);
        location.setText(shop_location);

        Log.d("intent", shop_name);
        Log.d("intent", shop_category);
        Log.d("intent", shop_location);


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
                    chat.setViewType(0);

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
        mAdapter = new ChatAdapter(chatList, ShopChatActivity.this, nick);

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


    public void readExcel() {
        Log.i("실행흐름", "readExcel 함수 실행");

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("shop_data.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Log.i("실행흐름", "try문");

            if (wb != null) {
                Sheet sheet = wb.getSheet(0);   // 시트 불러오기
                if (sheet != null) {
                    int colTotal = sheet.getColumns();    // 전체 컬럼
                    int rowIndexStart = 1;                  // row 인덱스 시작
                    int rowTotal = sheet.getColumn(colTotal - 1).length;

                    for (int row = rowIndexStart; row < rowTotal; row++) {
                        TableRow tableRow = new TableRow(this); //tablerow 생성
                        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        String keyword = sheet.getCell(2, row).getContents();

                        if (keyword.equals(shop_name)) {
                            Log.d("가게정보", shop_name);

                            String location2 = sheet.getCell(1, row).getContents();
                            String location3 = sheet.getCell(3, row).getContents();
                            shop_category = sheet.getCell(4, row).getContents();
                            Log.d("가게정보", location2 + ", " + location3 + ", " + category);

                            shop_location = location2 + " " + location3;
                            Log.d("가게정보", shop_location + " " + shop_category);
                        }
                    }
                }
            }

        } catch (IOException e) {
            Log.i("실행흐름", "exception 1");
            e.printStackTrace();

        } catch (BiffException e) {
            Log.i("실행흐름", "exception 2");
            e.printStackTrace();
        }
    }
}
