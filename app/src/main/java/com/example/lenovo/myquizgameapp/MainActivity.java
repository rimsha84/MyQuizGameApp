package com.example.lenovo.myquizgameapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import info.hoang8f.widget.FButton;
import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {
    FancyButton buttonA, buttonB, buttonC, buttonD;
    TextView questionText, triviaQuizText, timeText, resultText, coinText;
    QuizHelper triviaQuizHelper;
    Questions currentQuestion;
    List<Questions> list;
    int qid = 0;
    int timeValue = 20;
    int coinValue = 0;
    CountDownTimer countDownTimer;
    Typeface tb, sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText =  findViewById(R.id.triviaQuestion);
        buttonA =  findViewById(R.id.buttonA);
        buttonB =  findViewById(R.id.buttonB);
        buttonC =  findViewById(R.id.buttonC);
        buttonD =  findViewById(R.id.buttonD);
        triviaQuizText =  findViewById(R.id.triviaQuizText);
        timeText =  findViewById(R.id.timeText);
        resultText =  findViewById(R.id.resultText);
        coinText =  findViewById(R.id.coinText);

        //Setting typefaces for textview and buttons
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        triviaQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
         timeText.setTypeface(tb);
       //  buttonA.setCustomTextFont(String.valueOf(sb));
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);
        triviaQuizHelper = new QuizHelper(this);
        //Make db writable
        triviaQuizHelper.getWritableDatabase();

        if (triviaQuizHelper.getAllOfTheQuestions().size() == 0) {
            //If not added then add the ques,options in table
            triviaQuizHelper.allQuestion();
        }
        list = triviaQuizHelper.getAllOfTheQuestions();
        Collections.shuffle(list);

        currentQuestion = list.get(qid);

        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
                timeText.setText(String.valueOf(timeValue) + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == -1) {

                    //Since user is out of time setText as time up
                    resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method
                    disableButton();
                }
            }

            //Now user is out of time
            public void onFinish() {
                //We will navigate him to the time up activity using below method
                timeUp();
            }
        }.start();

        updateQueAndOptions();


    }

    private void updateQueAndOptions() {

        questionText.setText(currentQuestion.getQuestion());
        buttonA.setText(currentQuestion.getOpta());
        buttonB.setText(currentQuestion.getOptb());
        buttonC.setText(currentQuestion.getOptc());
        buttonD.setText(currentQuestion.getOptd());


        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();
        coinText.setText(String.valueOf(coinValue));
        coinValue++;
    }
    public void buttonA(View view) {
        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOpta().equals(currentQuestion.getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            }
            else {
                gameWon();
            }
        }
        else {
            gameLostPlayAgain();
        }
    }
    public void buttonB(View view) {
        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOptb().equals(currentQuestion.getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            }
            else {
                gameWon();
            }
        }
        else {
            gameLostPlayAgain();
        }
    }
    public void buttonC(View view) {
        //compare the option with the ans if yes then make button color green
        if (currentQuestion.getOptc().equals(currentQuestion.getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            }
            else {
                gameWon();
            }
        }
        else {
            gameLostPlayAgain();
        }
    }
    public void buttonD(View view) {
        if (currentQuestion.getOptd().equals(currentQuestion.getAnswer())) {
            buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            }
            else {
                gameWon();
            }
        }
        else {
            gameLostPlayAgain();
        }
    }

    private void gameLostPlayAgain() {

        Intent intent = new Intent(this, PlayAgain.class);
        startActivity(intent);
        finish();
        }

    private void gameWon() {

        Intent intent = new Intent(this, GameWon.class);
        startActivity(intent);
        finish();
    }
    public void timeUp(){

        Intent intent = new Intent(this, TimeUp.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }
    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

    private void correctDialog() {

        final Dialog dialogCorrect = new Dialog(MainActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();
        //Since the dialog is show to user just pause the timer in background
        onPause();

        TextView correctText = dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext =  dialogCorrect.findViewById(R.id.dialogNext);
        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //This will dismiss the dialog
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                //get the que and 4 option and store in the currentQuestion
                currentQuestion = list.get(qid);
                //Now this method will set the new que and 4 options
                updateQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });

    }

    private void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }

    private void resetColor() {
        buttonA.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonB.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonC.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        buttonD.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.white));

    }

    private void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }


}
