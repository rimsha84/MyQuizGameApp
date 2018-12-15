package com.example.lenovo.myquizgameapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayAgain extends AppCompatActivity {

    Button playAgain;
    TextView wrongAnsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_again);

        playAgain =  findViewById(R.id.playAgainButton);
        wrongAnsText = findViewById(R.id.wrongAns);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgain.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playAgain.setTypeface(typeface);
        wrongAnsText.setTypeface(typeface);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
