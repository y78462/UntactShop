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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";
    // Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        Button signUpButton = (Button) findViewById(R.id.signUpButton); //회원가입 버튼
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "success");
                signUp();
            }
        });

        Button gotoLoginButton = (Button)findViewById(R.id.gotoLoginButton); //로그인 버튼
        gotoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goto_login = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(goto_login);
            }
        });


    }




    private void signUp() {

        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.passwordCheckEditText)).getText().toString();
        int C = password.length();
        int Ch = passwordCheck.length();
        int E = email.length();
        if(C>0 && E>0 && Ch>0)
        {
            if (C < 6) {
                Toast.makeText(this, "비밀번호는 여섯자리 이상을 입력 해 주십시오", Toast.LENGTH_SHORT).show();
            }
            if (password.equals(passwordCheck)) {
                //String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //성공시 유아이
                                    Log.d(TAG, "createUserWithEmail:success");
                                    startToast("회원가입 성공하였습니다.");

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    go_setinfo(email,password);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    if(task.getException() != null)
                                    {
                                        startToast(task.getException().toString());
                                    }

                                    //실패시 유아이 로
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(this, "이메일이나 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
        }



    }

    private void startToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void go_setinfo(String id, String pw)
    {
        Intent intent = new Intent(SignUpActivity.this, MemberInitActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("id",id);
        intent.putExtra("pw",pw);
        startActivity(intent);
    }
}
