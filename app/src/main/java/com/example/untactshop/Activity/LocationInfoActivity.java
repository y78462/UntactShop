package com.example.untactshop;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.IOException;

public class LocationInfoActivity extends Activity {

    Cursor c = null;
    GestureDetector gestureDetector = null;
    FrameLayout frame;
    DisplayMetrics dm;
    float density;
    Button button;
    int scale = 0;
    String map = "";
    String shopName ="";
    String area = "";
    String id;
    int x1;
    int x2;
    int y1;
    int y2;
    String category;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_info);
        frame = findViewById(R.id.frame);
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = dm.density;

        Intent shop_name = getIntent();

        shopName = shop_name.getStringExtra("점포명");

        MapDatabaseHelper myDbHelper = new MapDatabaseHelper(LocationInfoActivity.this); // Reading SQLite database.
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

//        String st_name = map_name.getStringExtra("name");
        settingInfo();
        makeMaker();
        SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
        Log.i("시부랄","x : "+((x1+x2)/2)+" y : "+((y1+y2)/2));
//        imageView.setDoubleTapZoomScale(imageView.getScale());
        switch(map){
            case "Myeongdong":
                imageView.setImage(ImageSource.resource(R.drawable.map_myeongdong));
                //map = "Myeongdong";
                area = "명동권";
                break;
            case "MyeongdongStation":
                imageView.setImage(ImageSource.resource(R.drawable.map_myeongdongstation));
                //map = "MyeongdongStation";
                area = "명동권";
                break;
            case "Sogong":
                imageView.setImage(ImageSource.resource(R.drawable.map_sogong));
                //map = "Sogong";
                area = "명동권";
                break;
            case "Hoehyeon":
                imageView.setImage(ImageSource.resource(R.drawable.map_hoehyeon));
                //map = "Hoehyeon";
                area = "명동권";
                break;
            case "Namdaemun":
                imageView.setImage(ImageSource.resource(R.drawable.map_namdaemun));
                //map = "Namdaemun";
                area = "명동권";
                break;
            case "Euljiro2zone":
                imageView.setImage(ImageSource.resource(R.drawable.map_euljiro2zone));
                //map = "Euljiro2zone";
                area = "을지로권";
                break;
            case "Euljiro3zone":
                imageView.setImage(ImageSource.resource(R.drawable.map_euljiro3zone));
                //map = "Euljiro3zone";
                area = "을지로권";
                break;
            case "Euljiro4zone":
                imageView.setImage(ImageSource.resource(R.drawable.map_euljiro4zone));
                //map = "Euljiro4zone";
                area = "을지로권";
                break;
            case "CityHall":
                imageView.setImage(ImageSource.resource(R.drawable.map_cityhall));
                //map = "CityHall";
                area = "을지로권";
                break;
            case "EuljiEntrance":
                imageView.setImage(ImageSource.resource(R.drawable.map_euljientrance));
                //map = "EuljiEntrance";
                area = "을지로권";
                break;
            case "Inhyeon":
                imageView.setImage(ImageSource.resource(R.drawable.map_inhyeon));
                //map = "Inhyeon";
                area = "을지로권";
                break;
            case "Sindang":
                imageView.setImage(ImageSource.resource(R.drawable.map_sindang));
                //map = "Sindang";
                area = "을지로권";
                break;
            case "Jonggak":
                imageView.setImage(ImageSource.resource(R.drawable.map_jonggak));
                //map = "Jonggak";
                area = "종로권";
                break;
            case "Jongno4ga":
                imageView.setImage(ImageSource.resource(R.drawable.map_jongno4ga));
                //map = "Jongno4ga";
                area = "종로권";
                break;
            case "ChongGye5":
                imageView.setImage(ImageSource.resource(R.drawable.map_chonggye5));
                //map = "ChongGye5";
                area = "종로권";
                break;
            case "ChongGye6":
                imageView.setImage(ImageSource.resource(R.drawable.map_chonggye6));
                //map = "ChongGye6";
                area = "종로권";
                break;
            case "MaJeonGyo":
                imageView.setImage(ImageSource.resource(R.drawable.map_majeongyo));
                //map = "MaJeonGyo";
                area = "종로권";
                break;
            case "DongDaeMun":
                imageView.setImage(ImageSource.resource(R.drawable.map_dongdaemun));
                //map = "DongDaeMun";
                area = "종로권";
                break;
            case "GangnamStation":
                imageView.setImage(ImageSource.resource(R.drawable.map_gangnamstation));
                //map = "GangnamStation";
                area = "강남권";
                break;
            case "JamSilStation":
                imageView.setImage(ImageSource.resource(R.drawable.map_jamsilstation));
                //map = "JamSilStation";
                area = "강남권";
                break;
            case "GangNamTerminalPart1":
                imageView.setImage(ImageSource.resource(R.drawable.map_gangnamterminalpart1));
                //map = "GangNamTerminalPart1";
                area = "터미널권";
                break;
            case "GangNamTerminalPart2":
                imageView.setImage(ImageSource.resource(R.drawable.map_gangnamterminalpart2));
                //map = "GangNamTerminalPart2";
                area = "터미널권";
                break;
            case "GangNamTerminalPart3":
                imageView.setImage(ImageSource.resource(R.drawable.map_gangnamterminalpart3));
                //map = "GangNamTerminalPart3";
                area = "터미널권";
                break;
            case "YeongDeungPoStation":
                imageView.setImage(ImageSource.resource(R.drawable.map_yeongdeungpostation));
                //map = "YeongDeungPoStation";
                area = "영등포권";
                break;
            case "YeongDeungPoRotary":
                imageView.setImage(ImageSource.resource(R.drawable.map_yeongdeungporotary));
                //map = "YeongDeungPoRotary";
                area = "영등포권";
                break;
            case "YeongDeungPoMarket":
                imageView.setImage(ImageSource.resource(R.drawable.map_yeongdeungpomarket));
                //map = "YeongDeungPoMarket";
                area = "영등포권";
                break;
        }


        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);

                PointF sCoord = imageView.sourceToViewCoord((((x1+x2)/2) * density), (((y1+y2)/2) * density));
                frame.findViewWithTag(id).setVisibility(View.VISIBLE);
                frame.findViewWithTag(id).setX(sCoord.x - (frame.findViewWithTag(id).getWidth()/2));
                frame.findViewWithTag(id).setY(sCoord.y - frame.findViewWithTag(id).getHeight());

            }

        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                setMaker(imageView);
//                imageView.setDoubleTapZoomScale(imageView.getScale());

                return true;
            }
        });
    }

    private void makeMaker() {
        ImageView marker;

                    marker = new ImageView(getApplicationContext());
                    marker.setTag(id); //가게번호
                    marker.setVisibility(View.INVISIBLE);
                    FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
                    marker.setImageResource(R.drawable.maker2);
                    marker.setLayoutParams(params);
                    frame.addView(marker);
    }

//    private void setMaker(SubsamplingScaleImageView imageView) {
//        PointF sCoord = imageView.sourceToViewCoord((((x1+x2)/2) * density), (((y1+y2)/2) * density));
//        frame.findViewWithTag(id).setX(sCoord.x - (frame.findViewWithTag(id).getWidth()/2));
//        frame.findViewWithTag(id).setY(sCoord.y - frame.findViewWithTag(id).getHeight());
//    }

    private void settingInfo() {
        if (c.moveToFirst()) {
            do {
                if (c.getString(2).equals(shopName)) {
                    map = c.getString(0);
                    id = c.getString(2);
                    x1 = c.getInt(3);
                    y1 = c.getInt(4);
                    x2 = c.getInt(5);
                    y2 = c.getInt(6);
                    //category = c.getString(7);
                }
            } while (c.moveToNext());

        }
    }


}
