package com.example.lenovo.myquizgameapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class Home extends AppCompatActivity {
    FButton playGame,quit;
    TextView tQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        playGame =findViewById(R.id.playGame);
        quit =  findViewById(R.id.quit);
        tQ = findViewById(R.id.tQ);

        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
//        playGame.setTypeface(typeface);
//        quit.setTypeface(typeface);
//        tQ.setTypeface(typeface);
    }
}
