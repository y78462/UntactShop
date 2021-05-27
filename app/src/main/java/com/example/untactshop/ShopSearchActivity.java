package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ShopSearchActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    String search_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_search);

        Intent searchIntent = getIntent();
        search_text = searchIntent.getStringExtra("검색데이터");
        Log.i("검색데이터", String.valueOf(search_text));

        EditText editText = (EditText) findViewById(R.id.shop_search_text);
        editText.setText(search_text);

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

                    tableLayout = (TableLayout) findViewById(R.id.tablelayout);

                    for (int row = rowIndexStart; row < rowTotal; row++) {
                        TableRow tableRow = new TableRow(this); //tablerow 생성
                        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        String keyword = sheet.getCell(2, row).getContents();

                        //테이블 생성
                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        int size = Math.round(20 * dm.density);
                        tableRow.setPadding(0, size, 0, 0);

                        if (keyword.contains(search_text)) {
                            Log.i("점포명", keyword.toString());

                            for (int col = 0; col < colTotal; col++) {
                                String content = sheet.getCell(col, row).getContents();
                                Log.i("점포데이터", content);

                                TextView textView = new TextView(this);
                                textView.setText(String.valueOf(content));
                                textView.setGravity(Gravity.CENTER);
                                textView.setTextSize(13);
                                tableRow.addView(textView); //tableRow에 view 추가
                            }

                            tableRow.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Log.i("실행흐름", "클릭 이벤트");

                                    //데이터 담아서 팝업(액티비티) 호출
                                    Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                                    intent.putExtra("점포명", keyword);
                                    startActivityForResult(intent, 1);
                                }
                            });


                            tableLayout.addView(tableRow); //tableLayout에 tableRow 추가
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

    public void search_btn(View view) {
        String search_text;

        EditText editText = (EditText) findViewById(R.id.shop_search_text);
        search_text = "" + editText.getText(); //SpannableString -> String : Type conversion
//        Log.i("검색데이터", String.valueOf(search_text));

        Intent intent = new Intent(getApplicationContext(), ShopSearchActivity.class);
        intent.putExtra("검색데이터", search_text);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }


}
