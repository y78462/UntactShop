package com.example.untactshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.untactshop.MemberInfo;
import com.example.untactshop.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Infor_modify extends AppCompatActivity {

    private static final String TAG = "Mypage_modify";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor_modify);

        //데이터 가져와서 이름,폰번,생년월일,주소 넣기
        set_user_info();

        Button modify_button = findViewById(R.id.modify_button); //개인정보수정 버튼
        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // 업데이트 함수 호출
                update_user_info();
                Intent intent = new Intent(Infor_modify.this, My_page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
            }
        });
    }
    private void set_user_info()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(user.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                MemberInfo memberInfo = documentSnapshot.toObject(MemberInfo.class);
                //startToast(memberInfo.getName());
                if (memberInfo != null)
                {
                    TextView name = (TextView) findViewById(R.id.input_name);
                    TextView phone = (TextView) findViewById(R.id.input_phone);
                    TextView bday = (TextView) findViewById(R.id.input_bday);
                    TextView address = (TextView) findViewById(R.id.input_address);

                    name.setText(memberInfo.getName());
                    phone.setText(memberInfo.getPhone());
                    bday.setText(memberInfo.getBday());
                    address.setText(memberInfo.getAddress());

                }

            }
        });

    }
    private void startToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void update_user_info()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        EditText nameEdit = findViewById(R.id.input_name);
        String new_name = nameEdit.getText().toString();
        EditText phoneEdit = findViewById(R.id.input_phone);
        String new_phone = phoneEdit.getText().toString();
        EditText bdayEdit = findViewById(R.id.input_bday);
        String new_bday = bdayEdit.getText().toString();
        EditText addEdit = findViewById(R.id.input_address);
        String new_address = addEdit.getText().toString();

        DocumentReference washingtonRef = db.collection("users").document(user.getUid());


        //name
        washingtonRef
                .update("name", new_name)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "name successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
        //phone
        washingtonRef
                .update("phone", new_phone)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "phone successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
        //bday
        washingtonRef
                .update("bday", new_bday)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "bday successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
        //address
        washingtonRef
                .update("address", new_address)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "address successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
        startToast("회원정보 수정 완료!");


    }
}