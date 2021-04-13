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

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.IOException;

public class Map extends AppCompatActivity {

    Cursor c = null;
    GestureDetector gestureDetector = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

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
        String result = "";
        while (c.moveToNext()) {
            result += c.getString(0)
                    + " | "
                    + c.getString(1)
                    + " | "
                    + c.getInt(2)
                    + "| "
                    + c.getInt(3)
                    + "| "
                    + c.getInt(4)
                    + "| "
                    + c.getInt(5)
                    + "| "
                    + c.getString(6)
                    + "\n";
        }
        Log.i("DB", result);

        SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.myeongdong));

        //Refer to ID value.
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // gesture 디텍팅으로 지하철 위치 읽기
            @Override
            public boolean onSingleTapUp(MotionEvent ev) {

                Log.d("함수", "호출성공");
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());

                    float x = sCoord.x;
                    float y = sCoord.y;

                    /*density 구하기*/
                    DisplayMetrics dm = new DisplayMetrics();
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    float density = dm.density;

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
                                Log.d("확인","가게번호"+num+"가게 이름"+target);
                            } // send Station Name (column 1)

                        } while (c.moveToNext());
                    }
                }
                return super.onSingleTapUp(ev);
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
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
}

