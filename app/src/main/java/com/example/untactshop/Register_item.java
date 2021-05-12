package com.example.untactshop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Register_item extends AppCompatActivity {

    String title;
    String shop_name;
    String category;
    String price;
    String photoUrl;

    private FirebaseAuth mAuth;
    private static final String TAG = "Register Item Activity";
    // Initialize Firebase Auth

    private ImageView item_view;
    private String picpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_item);

        mAuth = FirebaseAuth.getInstance();

        Button signUpButton = (Button) findViewById(R.id.register_btn); //회원가입 버튼
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "item registersuccess");
                register_item();
            }
        });
        //사진찍기
        Button cam_btn = (Button) findViewById(R.id.cam_btn);
        cam_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camintent = new Intent(Register_item.this,CameraActivity.class);
                startActivityForResult(camintent,0);
            }
        });
        Button gal_btn = (Button) findViewById(R.id.gal_btn);
        gal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galintent = new Intent(Register_item.this,GalleryActivity.class);
                startActivityForResult(galintent,0);

            }
        });

        //사진찍기
        item_view = findViewById(R.id.img_item);
        item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView cardview  = findViewById(R.id.cardview);
                if(cardview.getVisibility() == View.VISIBLE)
                {
                    cardview.setVisibility(View.GONE);
                }else{
                    cardview.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0: {
                if(resultCode == Activity.RESULT_OK){
                    picpath = data.getStringExtra("picpath");
                    //Log.e("path log : ","picpath :"+ picpath);
                    Bitmap bmp = BitmapFactory.decodeFile(picpath);
                    item_view.setImageBitmap(bmp);
                }
                break;
            }
        }
    }


    private void register_item() {

         title = ((EditText) findViewById(R.id.title_item)).getText().toString();
         shop_name = ((EditText) findViewById(R.id.shop_name)).getText().toString();
         category = ((EditText) findViewById(R.id.category_item)).getText().toString();
         price = ((EditText) findViewById(R.id.price_item)).getText().toString();

        //상품사진먼저

        int T = title.length();
        int C = category.length();
        int P = price.length();
        int S = shop_name.length();

        //사진업로드 사진이름: 올리는사람uid / 상점_카테고리_타이틀_price.jpg
        //이미지업로드 코드

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();
        // Create a reference to 'images/mountains.jpg'
        final StorageReference mountainImagesRef = storageRef.child("items/" + "/" + shop_name +"_"+ category +"_"+ title +"_"+ price + ".jpg");
        if(picpath == null)
        {
            startToast("사진경로가 잘못되었습니다");
            //화면 새로고침
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        try {
            InputStream stream = new FileInputStream(new File(picpath));
            UploadTask uploadTask = mountainImagesRef.putStream(stream);
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        Log.e("이미지업로드실패", "이미지업로드실패");
                        throw task.getException();
                    }
                    // Continue with the task to get the download URL
                    return mountainImagesRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        photoUrl = downloadUri.toString();
                        Log.e("이미지업로드성공", downloadUri.toString());

                        //정보등록
                        if (C > 0 && T > 0 && P > 0 && S > 0 ) {
                            // 등록
                            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            DatabaseReference db = FirebaseDatabase.getInstance().getReference();

                            ItemInfo itemInfo = new ItemInfo( title,shop_name,category,price,photoUrl);
                            if (user != null) {
                                //document 에 원래 user.getUid() 였는데 중복되면 하나로 바껴서 상품이름으로 등록
                                db.child(shop_name).child(title).setValue(itemInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                        startToast("상품 등록 완료!");
                                        //화면 새로고침
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error writing document", e);
                                                startToast("상품 등록 실패!");
                                            }
                                        });


                            } else {
                                startToast("상품 정보를 모두 입력하세요");
                            }


                        }
                    } else {
                        // Handle failures
                        Log.e("이미지업로드실패", "이미지업로드실패");
                    }
                }
            });
        } catch (FileNotFoundException e) {
            Log.e("로그", "에러" + e.toString());
        }

        // UploadTask uploadTask = mountainImagesRef.putBytes(bytes);


    }
    private void startToast (String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
