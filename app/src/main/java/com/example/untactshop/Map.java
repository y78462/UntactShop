package com.example.untactshop;

import android.database.Cursor;
import android.database.SQLException;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.IOException;

public class Map extends AppCompatActivity {

    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        TextView name = this.findViewById(R.id.textView);
//        TextView name1 = this.findViewById(R.id.textView1);
//        TextView name2 = this.findViewById(R.id.textView2);
//        TextView name3 = this.findViewById(R.id.textView3);
//
//        name.setText(getIntent().getStringExtra("name"));

//        Cursor c = null;
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


        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.myeongdong));

        //final SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) findViewById(R.id.Myeongdong); // 명동 이미지뷰
        //ImageView imageView = this.findViewById(R.id.Myeongdong);
        //Refer to ID value.
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() { // gesture 디텍팅으로 지하철 위치 읽기
            @Override
            public boolean onSingleTapUp(MotionEvent ev) {
                if (imageView.isReady()) {
                    PointF sCoord = imageView.viewToSourceCoord(ev.getX(), ev.getY());
                    int x_cor = (int) sCoord.x;
                    int y_cor = (int) sCoord.y;

                    // Loop for finding the station.
                    if (c.moveToFirst()) {
                        do {

                            if ((x_cor > c.getInt(2)) && (x_cor < c.getInt(4)) && (y_cor > c.getInt(3)) && (y_cor < c.getInt(5))) {
                                String targetStation = c.getString(1); // 유저가 클릭한 지하철역
                            } // send Station Name (column 1)
                        } while (c.moveToNext());
                    }

                    /////////////////////////////////////////////////////////////////////////////

                }
                return super.onSingleTapUp(ev);
            }
        });



    }

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