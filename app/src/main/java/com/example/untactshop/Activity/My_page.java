package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.untactshop.Activity.Infor_modify;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class My_page extends AppCompatActivity {

    private BottomNavigationView mBottomNV;

    private static final String TAG = "Mypage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_page);


        //데이터 가져와서 이름,폰번,생년월일,주소 넣기
        set_user_info();


        Button infor_modify = (Button) findViewById(R.id.infor_modify); //개인정보수정 버튼
        infor_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infor_intent = new Intent(My_page.this, Infor_modify.class);
                startActivity(infor_intent);
            }
        });

        mBottomNV = findViewById(R.id.bottom_navigation_view);
        mBottomNV.setSelectedItemId(R.id.nav_my);
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //NavigationItemSelecte
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Shopping.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_shop:
                        startActivity(new Intent(getApplicationContext(), Shopping_shop.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_cart:
                        startActivity(new Intent(getApplicationContext(), Show_orders.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_my:
                        return true;
                }
                return false;
            }
        });


    }

    private void set_user_info() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                MemberInfo memberInfo = documentSnapshot.toObject(MemberInfo.class);
                //startToast(memberInfo.getName());
                if (memberInfo != null) {
                    TextView name = (TextView) findViewById(R.id.nameTextView);
                    TextView phone = (TextView) findViewById(R.id.phoneNumberTextView);
                    TextView bday = (TextView) findViewById(R.id.birthDayTextView);
                    TextView address = (TextView) findViewById(R.id.addressTextView);

                    name.setText(memberInfo.getName());
                    phone.setText(memberInfo.getPhone());
                    bday.setText(memberInfo.getBday());
                    address.setText(memberInfo.getAddress());
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        mBottomNV.setSelectedItemId(R.id.nav_home);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}