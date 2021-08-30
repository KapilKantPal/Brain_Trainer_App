package com.programmingmanav.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    //Game Values.
    int Level = 1;
    int score;
    int TotalQuestion=0;
    int opt1;
    int opt2;
    int opt3;
    int opt4;

    //Important Values.
    Boolean go = false;
    int IValue;
    int IIValue;
    int IIIValue;
    int Ans;
    int tag;


    //Initialisation
    TextView Start;
    Random random;

    CountDownTimer countDownTimer;
    LinearLayout topLinerLayout;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView scoreView;
    TextView questionView;
    TextView timerView;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //TopLinerLayout.
        topLinerLayout = (LinearLayout) findViewById(R.id.linearLayout);
        timerView =  findViewById(R.id.timerTextView);
        questionView = findViewById(R.id.questionTextView);
        scoreView = findViewById(R.id.scoreTextView);

        //option Text View.
        textView1 = findViewById(R.id.TextView1);
        textView2 = findViewById(R.id.TextView2);
        textView3 = findViewById(R.id.TextView3);
        textView4 = findViewById(R.id.TextView4);

        //result Text View.
        result = findViewById(R.id.result);

        //Random Number;
        random = new Random();

        //Option
        optEnable(false);

        //CountDown Timer
        countDownTimer = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long l) {
                if(l<10000){
                    timerView.setText("0"+l/1000+"s");
                }else{
                    timerView.setText(l/1000+"s");
                }

            }

            @Override
            public void onFinish() {
                timerView.setText("00s");
                optEnable(false);
                result.setEnabled(true);
                result.setText("Tap to play!");
            }
        };
    }

    public void opt(View view) {
        TextView optView = (TextView) view;
        if(go){
           String x =  optView.getTag().toString();
            TotalQuestion++;
            if(score > 13){
                Level = 3;
            }else if(score > 8){
                Level = 2;
            }
            if(tag == Integer.parseInt(x)){
                score++;
                result.setText("Correct :)");
            }else{
                result.setText("Wrong :(");
            }
            final Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
            optView.startAnimation(animation);

        }
        genQuestion();
        scoreView.setText(score+"/"+TotalQuestion);
    }

    public void start(View view) {
        Start = (TextView) view;
        go = true;
        optEnable(true);
        textView1.setAlpha(1);
        textView2.setAlpha(1);
        textView3.setAlpha(1);
        textView4.setAlpha(1);
        topLinerLayout.setAlpha(1);
        restart(view);
        genQuestion();
        scoreView.setText(score+"/"+TotalQuestion);
        Start.animate().translationY(5000).setDuration(500);
        result.setAlpha(1);

    }

    public void restart(View view) {
        score = 0;
        TotalQuestion = 0;
        Level = 1;
        scoreView.setText(score+"/"+TotalQuestion);
        result.setText(" ");
        optEnable(true);
        genQuestion();
        countDownTimer.start();
        result.setEnabled(false);
    }

    public void genQuestion(){
        switch(Level){
            case 1: IValue = (int)(Math.random()*(11)+10);
                    IIValue = (int)(Math.random()*(11)+10);
                    Ans = IValue + IIValue;
                    questionView.setText(IValue+"+"+IIValue);
                    Log.i("Level", "1");
                    break;
            case 2: IValue = (int)(Math.random()*(31)+20);
                    IIValue = (int)(Math.random()*(31)+20);
                    Ans = IValue + IIValue;
                    questionView.setText(IValue+"+"+IIValue);
                    Log.i("Level", "2");
                    break;
            case 3: IValue = (int)(Math.random()*(51)+50);
                    IIValue = (int)(Math.random()*(51)+50);
                    IIIValue = (int)(Math.random()*(51));
                    Ans = IValue + IIValue +IIIValue;
                    questionView.setText(IValue+"+"+IIValue+"+"+IIIValue);
                    Log.i("Level", "3");
                    break;
        }
        setOption();
        textView1.setText(String.valueOf(opt1));
        textView2.setText(String.valueOf(opt2));
        textView3.setText(String.valueOf(opt3));
        textView4.setText(String.valueOf(opt4));
    }

    public void setOption(){
        int opt[] = new int[4];
        int ansOption = random.nextInt(4);
        tag = ansOption;
        opt[ansOption] = Ans;
        switch(ansOption){
            case 0: opt[1] = Ans+1;
                    opt[2] = Ans+2;
                    opt[3] = Ans+3;
                    //tag = 0;
                    break;
            case 1: opt[0] = Ans-1;
                    opt[2] = Ans+1;
                    opt[3] = Ans+2;
                    //tag = 1;
                    break;
            case 2: opt[0] = Ans-2;
                    opt[1] = Ans-1;
                    opt[3] = Ans+1;
                    //tag = 2;
                    break;
            case 3: opt[0] = Ans-3;
                    opt[1] = Ans-2;
                    opt[2] = Ans-1;
                    //tag = 3;
                    break;
        }
        opt1 = opt[0];
        opt2 = opt[1];
        opt3 = opt[2];
        opt4 = opt[3];
    }
    public void optEnable(Boolean x){
        textView1.setEnabled(x);
        textView2.setEnabled(x);
        textView3.setEnabled(x);
        textView4.setEnabled(x);
    }
}