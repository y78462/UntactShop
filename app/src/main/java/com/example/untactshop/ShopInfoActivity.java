package com.example.untactshop;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ShopInfoActivity extends Activity {

    TextView name, category, location;
    String shop_name, shop_location, shop_category;
    Cursor c = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_info);

        name = (TextView) findViewById(R.id.text_name);
        category = (TextView) findViewById(R.id.text_category);
        location = (TextView) findViewById(R.id.text_locaton);

        Intent intent = getIntent();
        shop_name = intent.getStringExtra("점포명");
        name.setText(shop_name);

        readExcel();
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

                            String location1 = sheet.getCell(0, row).getContents();
                            String location2 = sheet.getCell(1, row).getContents();
                            String location3 = sheet.getCell(3, row).getContents();
                            shop_category = sheet.getCell(4, row).getContents();
                            Log.d("가게정보", location1 + ", " + location2 + ", " + location3 + ", " + category);

                            shop_location = location1 + "권 " + location2 + " " + location3;
                            Log.d("가게정보", shop_location);

                            location.setText(shop_location);
                            category.setText(shop_category);
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


//    private void DBread() {
//
//        MapDatabaseHelper myDbHelper = new MapDatabaseHelper(ShopInfoActivity.this); // Reading SQLite database.
//        try {
//            myDbHelper.createDataBase();
//        } catch (IOException ioe) {
//            throw new Error("Unable to create database");
//        }
//        try {
//            myDbHelper.openDataBase();
//        } catch (SQLException sqle) {
//            throw sqle;
//        }
//
//
////        Cursor sel = myDB.query("data", "name = google", null, null, null, null, null, null);
//
//
//
//
//        String[] columns = {"id", "name", "category"};
//        Cursor c1 = myDbHelper.query(null, columns, null, null, null, null, null);
//        Log.d("DB", " DB read");
//
//        int recordCount = c1.getCount();
//
//        for (int i = 0; i < recordCount; i++) {
//            c1.moveToNext();
//            String name = c1.getString(1);
//            if(name == shop_name) {
//                String id = c1.getString(0);
//                String category = c1.getString(2);
//                Log.d("DB",id + ", " + name + ", " + category);
//            }
//        }
//        c1.close();
//    }
}
