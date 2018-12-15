package com.example.lenovo.myquizgameapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import info.hoang8f.widget.FButton;

public class TimeUp extends AppCompatActivity {

    FButton playAgainButton;
    TextView timeUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_up);

        playAgainButton = findViewById(R.id.playAgainButton);
        timeUpText = findViewById(R.id.timeUpText);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimeUp.this,MainActivity.class);
                startActivity(intent);
                finish();


            }
        });
//        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
//        timeUpText.setTypeface(typeface);
//        playAgainButton.setTypeface(typeface);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
