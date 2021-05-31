package com.example.untactshop.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.untactshop.R;
import com.google.firebase.auth.FirebaseAuth;

public class Home_login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_login);


        if (FirebaseAuth.getInstance().getCurrentUser() == null) { //로그인 안됐으면 회원가입 페이지로 이동시킴.\
            //로그인된 유저 확인
            Log.d("로그인 여부", "fail");
            Intent intent = new Intent(Home_login.this, SignUpActivity.class);
            startActivity(intent);
        }


        Button map_button = (Button) findViewById(R.id.map_button); //지도보기 버튼
        map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map_intent = new Intent(Home_login.this, Choice_shop.class);
                startActivity(map_intent);
            }
        });

        Button shop_button = (Button) findViewById(R.id.shop_button); //쇼핑하기 버튼
        shop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shop_intent = new Intent(Home_login.this, Shopping.class);
                startActivity(shop_intent);
            }
        });


        Button logout_button = (Button) findViewById(R.id.logout_button); //로그아웃 버튼
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그아웃
                AlertDialog.Builder builder = new AlertDialog.Builder(Home_login.this);
                builder.setTitle("로그아웃 ")        // 제목 설정
                        .setMessage("로그아웃 하시겠습니까?")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            // 확인 버튼 클릭시 설정, 오른쪽 버튼입니다.
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //원하는 클릭 이벤트를 넣으시면 됩니다.
                                FirebaseAuth.getInstance().signOut();
                                Intent main_intent = new Intent(Home_login.this, Home_main.class);
                                main_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(main_intent);
                                finish();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            // 취소 버튼 클릭시 설정, 왼쪽 버튼입니다.
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //원하는 클릭 이벤트를 넣으시면 됩니다.
                                startToast("로그아웃 취소");
                            }
                        });
                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();    // 알림창 띄우기
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}