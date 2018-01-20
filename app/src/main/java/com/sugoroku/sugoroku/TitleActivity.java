package com.sugoroku.sugoroku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
    }

    public boolean onTouchEvent(MotionEvent event){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        return super.onTouchEvent(event);
    }
}
