package com.example.untactshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.untactshop.MemberInfo;
import com.example.untactshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberInitActivity extends AppCompatActivity {



    private static final String TAG = "MemberInitActivity";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_init);

        Intent secondIntent = getIntent();
        String id;
        String pw;
        id = secondIntent.getStringExtra("id");
        pw = secondIntent.getStringExtra("pw");

        mAuth = FirebaseAuth.getInstance();
        //signIn(id,pw);

        Button checkButton = (Button) findViewById(R.id.checkButton); // 버튼
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "success");
                signIn(id,pw);

            }
        });


    }

    private void set_userinfo() {

        String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phoneNumberEditText)).getText().toString();
        String bday = ((EditText) findViewById(R.id.birthDayEditText)).getText().toString();
        String address = ((EditText) findViewById(R.id.addressEditText)).getText().toString();

       int N=name.length();
       int P=phone.length();
       int B=bday.length();
       int A=address.length();

        if(N>0 && P>0 && B>0 && A>0)
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            MemberInfo memberInfo = new MemberInfo(name, phone, bday, address);
            if(user !=null)
            {
                db.collection("users").document(user.getUid()).set(memberInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        startToast("회원정보 저장 완료!");
                        new_home();
                        finish();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                                startToast("등록 실패!");
                            }
                        });

            }
        }else
        {
            startToast("회원 정보를 모두 입력해 주세요");
        }

    }
    private void signIn(String id, String pw) {

        int C = pw.length();
        int E = id.length();
        if(C>0 && E>0)
        {
            mAuth.signInWithEmailAndPassword(id, pw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //성공시
                                Toast.makeText(MemberInitActivity.this, "로그인 성공!",
                                        Toast.LENGTH_SHORT).show();
                                set_userinfo();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MemberInitActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //실패시
                            }
                        }
                    });

        }else
        {
            Toast.makeText(this, "아이디 비번이 널임", Toast.LENGTH_SHORT).show();
        }



    }

    private void startToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void new_home()
    {
        Intent signin_intent = new Intent(MemberInitActivity.this, Home_login.class);
        signin_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(signin_intent);

    }

}
