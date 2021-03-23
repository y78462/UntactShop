package com.example.untactshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

<<<<<<< HEAD
public class Map extends AppCompatActivity {
=======
import com.example.untactshop.R;

public class map extends AppCompatActivity {
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        TextView name = this.findViewById(R.id.textView);
        name.setText(getIntent().getStringExtra("name"));
    }
}