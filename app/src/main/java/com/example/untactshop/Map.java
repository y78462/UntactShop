package com.example.untactshop;

import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
        c = myDbHelper.query("Myeongdong", null, null, null, null, null, null); // SQLDataRead


//        DB 쿼리 확인
//        String result = "";
//        while (c.moveToNext()) {
//            result += c.getString(0)
//                    + " | "
//                    + c.getString(1)
//                    + " | "
//                    + c.getInt(2)
//                    + "| "
//                    + c.getInt(3)
//                    + "| "
//                    + c.getInt(4)
//                    + "| "
//                    + c.getInt(5)
//                    + "| "
//                    + c.getString(6)
//                    + "\n";
//        }
//        Log.i("DB", result);

        SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.myeongdong));
        makeText();
        //Refer to ID value.
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // gesture 디텍팅으로 지하철 위치 읽기

            @Override
            public boolean onDown(MotionEvent ev){
                return false;
            }

            @Override
            public void onLongPress(MotionEvent ev){
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());
                    float x = sCoord.x;
                    float y = sCoord.y;

                    /*density 구하기*/

                    /*비율에 맞는 px 구하기*/
                    int x_cor = (int)(x/density);
                    int y_cor = (int)(y/density);


                    // Loop for finding the station.
                    if (c.moveToFirst()) {

                        do {
                            if ((x_cor > c.getInt(2)) && (x_cor < c.getInt(4)) && (y_cor > c.getInt(3)) && (y_cor < c.getInt(5))) {
                                String target = c.getString(1); // 유저가 클릭한 가게
                                String num = c.getString(0);
                                Log.d("확인","가게번호 "+num+"가게 이름 "+target);
                            } // send Station Name (column 1)

                        } while (c.moveToNext());
                    }


                }
            }

//            @Override
//            public boolean onDoubleTap(MotionEvent ev){
//                Log.d("스케일", "scale" + imageView.getScale());
//                if(imageView.getScale() == imageView.getMaxScale()) {
//                    Log.d("탭탭", "in1");
//                    textInVisible();
//                }
//                else {
//                    Log.d("탭탭", "in2");
//                    setShopMax(imageView);
//                    textVisible();
//                }
//                return super.onDoubleTap(ev);
//            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent ev){
                Log.d("함수", "호출성공");
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());
                    float x = sCoord.x;
                    float y = sCoord.y;

                    /*density 구하기*/

                    /*비율에 맞는 px 구하기*/
                    int x_cor = (int)(x/density);
                    int y_cor = (int)(y/density);

                    Log.d("좌표", "new x" + x_cor);
                    Log.d("좌표", "new y" + y_cor + "denstisty" + density);

                    // Loop for finding the station.
                    if (c.moveToFirst()) {

                        do {
                            if ((x_cor > c.getInt(2)) && (x_cor < c.getInt(4)) && (y_cor > c.getInt(3)) && (y_cor < c.getInt(5))) {
                                String target = c.getString(1); // 유저가 클릭한 가게
                                String num = c.getString(0);
                                Log.d("확인","가게번호 "+num+"가게 이름 "+target);
                            } // send Station Name (column 1)

                        } while (c.moveToNext());
                    }


                }

                return super.onSingleTapConfirmed(ev);
            }

            @Override
            public boolean onSingleTapUp(MotionEvent ev) {

//                Log.d("함수", "호출성공");
//                if (imageView.isReady()) {
//                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());
//                    float x = sCoord.x;
//                    float y = sCoord.y;
//
//                    /*density 구하기*/
//
//                    /*비율에 맞는 px 구하기*/
//                    int x_cor = (int)(x/density);
//                    int y_cor = (int)(y/density);
//
//                    Log.d("좌표", "new x" + x_cor);
//                    Log.d("좌표", "new y" + y_cor + "denstisty" + density);
//
//                    // Loop for finding the station.
//                    if (c.moveToFirst()) {
//
//                        do {
//                            if ((x_cor > c.getInt(2)) && (x_cor < c.getInt(4)) && (y_cor > c.getInt(3)) && (y_cor < c.getInt(5))) {
//                                String target = c.getString(1); // 유저가 클릭한 가게
//                                String num = c.getString(0);
//                                Log.d("확인","가게번호 "+num+"가게 이름 "+target);
//                            } // send Station Name (column 1)
//
//                        } while (c.moveToNext());
//                    }
//
//
//                }
//
                return super.onSingleTapUp(ev);


            }
        }
        );

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                setShop(imageView);

                if(imageView.getScale() > 2) {
                    textVisible();
                }
                else
                    textInVisible();

                return false;
            }

        });
}


//    /**
//     * Pixel을 DP로 변경해주는 메서드
//     * @param dp
//     * @return
//     */
//    public static int getPxFromDp(float dp) {
//        int px = 0;
//        Context appContext = WLBApplication.getApplication();
//        px = (int) (dp * appContext.getResources().getDisplayMetrics().density);
//        return px;
//    }

    private void initLoadDB() {

        DataAdapter mDbHelper = new DataAdapter(getApplicationContext());
        mDbHelper.createDatabase();
        mDbHelper.open();

        // db에 있는 값들을 model을 적용해서 넣는다.
//        shopList = mDbHelper.getTableData();

        // db 닫기
        mDbHelper.close();

        Cursor c = null;
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
    }

    private void makeText(){
        TextView shop;
        while (c.moveToNext()) {
            //PointF sCoord = imageView.sourceToViewCoord(c.getFloat(2)*density, c.getFloat(3)*density);
            shop = new TextView(getApplicationContext());
            shop.setText(c.getString(1));
            shop.setTextSize(10);
            //shop.setX(sCoord.x);
            //shop.setY(sCoord.y);
            //shop.setMaxHeight(c.getInt(4)-c.getInt(2));
            //shop.setMaxWidth(c.getInt(5)-c.getInt(3));
            shop.setTag(c.getString(0));
            shop.setVisibility(View.INVISIBLE);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
            shop.setLayoutParams(params);
            frame.addView(shop);
            Log.d("Text", c.getString(1));
        }
    }

    private void textVisible(){

        if (c.moveToFirst()) {

            do {
                frame.findViewWithTag(c.getString(0)).setVisibility(View.VISIBLE);

            } while (c.moveToNext());
        }

    }

    private void textInVisible(){

        if (c.moveToFirst()) {

            do {
                frame.findViewWithTag(c.getString(0)).setVisibility(View.INVISIBLE);

            } while (c.moveToNext());
        }

    }

    private void setShop(SubsamplingScaleImageView imageView){
        if (c.moveToFirst()) {

            do {
                PointF sCoord = imageView.sourceToViewCoord(c.getFloat(2)*density, c.getFloat(3)*density);
                frame.findViewWithTag(c.getString(0)).setX(sCoord.x);
                frame.findViewWithTag(c.getString(0)).setY(sCoord.y);

            } while (c.moveToNext());
        }

    }
//    private void setShopMax(SubsamplingScaleImageView imageView){
//        Log.d("탭탭", "in3");
//        if (c.moveToFirst()) {
//
//            do {
//                PointF sCoord = imageView.sourceToViewCoord(c.getFloat(2)*density*imageView.getMaxScale()/imageView.getScale(), c.getFloat(3)*density*imageView.getMaxScale()/imageView.getScale());
//                linear.findViewWithTag(c.getString(0)).setX(sCoord.x);
//                linear.findViewWithTag(c.getString(0)).setY(sCoord.y);
//
//            } while (c.moveToNext());
//        }
//
//    }

}