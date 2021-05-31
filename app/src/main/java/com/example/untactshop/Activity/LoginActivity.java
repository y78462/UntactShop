package com.example.untactshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.untactshop.Activity.Home_admin;
import com.example.untactshop.Activity.Home_login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private static final String TAG = "SignInActivity";
    // Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Button loginButton = (Button) findViewById(R.id.loginButton); //로그인 버튼
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "success");
                signIn();
            }
        });
        Button gotoSignUp = (Button)findViewById(R.id.gotoSignUp); //회원가입 버튼
        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin_intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signin_intent);
            }
        });

        Button gotoPasswordResetButton = (Button)findViewById(R.id.gotoPasswordResetButton); //비번재설정 버튼
        gotoPasswordResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin_intent = new Intent(LoginActivity.this, PasswordResetActivity.class);
                startActivity(signin_intent);
            }
        });

    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
////        if(currentUser != null){
////            //reload();
////        }
//    }


    private void signIn() {

        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        int C = password.length();
        int E = email.length();
        //admin lpgin
        if(email.equals("admin@admin.com"))
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //성공시
                                Toast.makeText(LoginActivity.this, "관리자 로그인 성공!",
                                        Toast.LENGTH_SHORT).show();
                                Intent admin_intent = new Intent(LoginActivity.this, Home_admin.class);
                                admin_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(admin_intent);
                                
                                //finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //실패시
                            }
                        }
                    });


        }
        if(C>0 && E>0)
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //성공시
                                Toast.makeText(LoginActivity.this, "로그인 성공!",
                                        Toast.LENGTH_SHORT).show();
                                new_home();
                                //finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //실패시
                            }
                        }
                    });

        }else
        {
            Toast.makeText(this, "이메일이나 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
        }



    }

    private void startToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void new_home()
    {
        Intent signin_intent = new Intent(LoginActivity.this, Home_login.class);
        signin_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(signin_intent);

    }
}
