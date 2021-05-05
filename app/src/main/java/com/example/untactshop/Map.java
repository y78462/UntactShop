package com.example.untactshop;

import android.database.Cursor;
import android.database.SQLException;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.IOException;

public class Map extends AppCompatActivity {

    Cursor c = null;
    GestureDetector gestureDetector = null;
    FrameLayout frame;
    DisplayMetrics dm;
    float density;
    String map = "Myeongdong";
    int scale = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        frame = findViewById(R.id.frame);
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = dm.density;

        MapDatabaseHelper myDbHelper = new MapDatabaseHelper(Map.this); // Reading SQLite database.
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        c = myDbHelper.query("Shop", null, null, null, null, null, null); // SQLDataRead


//        //DB 쿼리 확인
//        String result = "";
//        while (c.moveToNext()) {
//            if (c.getString(0).equals(map)) {
//                break;
//                result += c.getString(0)
//                        + " | "
//                        + c.getString(1)
//                        + " | "
//                        + c.getString(2)
//                        + " | "
//                        + c.getInt(3)
//                        + "| "
//                        + c.getInt(4)
//                        + "| "
//                        + c.getInt(5)
//                        + "| "
//                        + c.getInt(6)
//                        + "| "
//                        + c.getString(7)
//                        + "\n";
//            }
//        }
//        Log.i("DB", result);

        SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.myeongdong));
        makeText();
        //Refer to ID value.
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // gesture 디텍팅으로 지하철 위치 읽기

            @Override
            public boolean onDown(MotionEvent ev) {
                Log.d("Down","!!");
                return false;
            }

            @Override
            public void onLongPress(MotionEvent ev) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());
                    float x = sCoord.x;
                    float y = sCoord.y;

                    /*비율에 맞는 px 구하기*/
                    int x_cor = (int) (x / density);
                    int y_cor = (int) (y / density);

                    // Loop for finding the station.
                    if (c.moveToFirst()) {

                        do {
                            if (c.getString(0).equals(map)) {
                                if ((x_cor > c.getInt(3)) && (x_cor < c.getInt(5)) && (y_cor > c.getInt(4)) && (y_cor < c.getInt(6))) {
                                    String target = c.getString(2); // 유저가 클릭한 가게이름
                                    String num = c.getString(1); //가게 번호
                                    Log.d("확인", "가게번호 " + num + "가게 이름 " + target);
                                } // send Station Name (column 1)
                            }
                        } while (c.moveToNext());
                    }
                    Log.d("tag","onlongpass");
                }
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent ev) {
                Log.d("더블 탭 스케일", "scale" + imageView.getScale());
                int action = ev.getAction();

                if(action==MotionEvent.ACTION_UP) {
                    if (imageView.getScale() == imageView.getMaxScale()) { //더블 탭 해서 축소됨
                        Log.d("탭탭", "in1");
                        scale = 1;
                    } else { //더블탭해서 확대됨
                        Log.d("탭탭", "in2");
                        //setShopMax(imageView);
                        scale = 2;
                    }
                }
                Log.d("더블 탭", "scale" + scale);
                return super.onDoubleTapEvent(ev);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent ev) {
                Log.d("함수", "호출성공");
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());
                    float x = sCoord.x;
                    float y = sCoord.y;

                    /*비율에 맞는 px 구하기*/
                    int x_cor = (int) (x / density);
                    int y_cor = (int) (y / density);

                    Log.d("좌표", "new x" + x_cor);
                    Log.d("좌표", "new y" + y_cor + "density" + density);

                    // Loop for finding the station.
                    if (c.moveToFirst()) {
                        do {
                            if (c.getString(0).equals(map)) {
                                if ((x_cor > c.getInt(3)) && (x_cor < c.getInt(5)) && (y_cor > c.getInt(4)) && (y_cor < c.getInt(6))) {
                                    String target = c.getString(2); // 유저가 클릭한 가게
                                    String num = c.getString(1);
                                    Log.d("확인", "가게번호 " + num + "가게 이름 " + target);
                                } // send Station Name (column 1)
                            }
                        } while (c.moveToNext());
                    }
                }
                Log.d("onsingletapconfirmed","dd");
                return super.onSingleTapConfirmed(ev);
            }

            @Override
            public boolean onSingleTapUp(MotionEvent ev) {
                Log.d("onsingletapup","dd");
                return super.onSingleTapUp(ev);
            }
        }
        );

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
 //               scale = 0; //scale 초기화
                gestureDetector.onTouchEvent(motionEvent);
                setShop(imageView);
                Log.d("imageview222", "scale"+imageView.getScale());
                Log.d("scale","!!"+scale);

                if (imageView.getScale() >= 2 && scale == 0) { //더블탭 안하고 확대
                    textVisible();
                }
                else if(imageView.getScale() < 2 && scale == 0){ //더블탭 안하고 축소
                    textInVisible();
                }
                else if(scale == 1){ //더블탭해서 축소
                    textInVisible();
                    scale=0;
                }
                else if(scale == 2){ //더블탭해서 확대
                    //textVisible();
                    scale=0;
                }
                Log.d("scale","!!"+scale);
                return false;
            }
        });
    }

    private void makeText() {
        TextView shop;
        if (c.moveToFirst()) {
            do {

                if (c.getString(0).equals(map)) {

                    shop = new TextView(getApplicationContext());
                    shop.setText(c.getString(2)); //가게이름 보이게
                    shop.setTextSize(10);
                    shop.setTag(c.getString(1)); //가게번호
                    shop.setVisibility(View.INVISIBLE);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    shop.setLayoutParams(params);
                    frame.addView(shop);
                    Log.d("Text", c.getString(2));
                }
            } while (c.moveToNext());
        }
    }

    private void textVisible() {
        if (c.moveToFirst()) {
            do {
                if (c.getString(0).equals(map)) {
                    frame.findViewWithTag(c.getString(1)).setVisibility(View.VISIBLE);
                }
            } while (c.moveToNext());
        }
    }

    private void textInVisible() {
        if (c.moveToFirst()) {
            do {
                if (c.getString(0).equals(map)) {
                    frame.findViewWithTag(c.getString(1)).setVisibility(View.INVISIBLE);
                }
            } while (c.moveToNext());
        }
    }

    private void setShop(SubsamplingScaleImageView imageView) {
        if (c.moveToFirst()) {
            do {
                if (c.getString(0).equals(map)) {
                    PointF sCoord = imageView.sourceToViewCoord(c.getFloat(3) * density, c.getFloat(4) * density);
                    frame.findViewWithTag(c.getString(1)).setX(sCoord.x);
                    frame.findViewWithTag(c.getString(1)).setY(sCoord.y);
                }
            } while (c.moveToNext());
        }
    }
}