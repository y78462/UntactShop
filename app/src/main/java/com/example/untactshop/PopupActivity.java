package com.example.untactshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class PopupActivity extends Activity {

    TextView txtText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);

        //UI 객체생성
        txtText = (TextView) findViewById(R.id.txtText);

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("점포명");
        txtText.setText(data);
    }

    //위치정보 버튼 클릭
    public void location_info(View v) {
        Intent intent = new Intent(getApplicationContext(), LocationInfoActivity.class);
//        intent.putExtra("검색데이터", search_text);
        startActivity(intent);
    }

    //매장정보 버튼 클릭
    public void shop_info(View v) {
        Intent intent = new Intent(getApplicationContext(), ShopInfoActivity.class);
//        intent.putExtra("검색데이터", search_text);
        startActivity(intent);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //바깥레이어 클릭시 안닫히게
//        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        //안드로이드 백버튼 막기
//        return;
//    }
}
