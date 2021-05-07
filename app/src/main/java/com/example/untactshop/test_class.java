package com.example.untactshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class test_class extends AppCompatActivity {

    Realm realm;

    public shopInfo test1 = new shopInfo("강남역","비프루브 강남역점","A-1,2","쇼핑미용");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_item);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("ShopInfo.realm") //생성할 realm파일 이름 지정
                .schemaVersion(0)
                .build();
        //Realm에 셋팅한 정보 값을 지정
        Realm.setDefaultConfiguration(config);


        //transaction
        realm = Realm.getDefaultInstance();
        addData();
        realm.commitTransaction();

        //select
        shopInfo shop = realm.where(shopInfo.class).equalTo("category", "쇼핑미용").findFirst(); //특정 id가진 데이터불러오기

        //TextView t1 = findViewById(R.id.textbox);
        //t1.setText(shop.getShop_name());

    }

    private void addData(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //Realm에 생성한 test를 저장하는 코드
                realm.copyToRealm(test1);
            }
        });
    }

    private RealmResults getDiaryList(){
        //Realm에 저장된 Diary들을 모두 찾아달라고 Realm에 요청해서 받아오는 코드입니다
        RealmResults diaryRealmResults = realm.where(shopInfo.class).findAll();

        return diaryRealmResults;
    }


}
