package com.hwhollywu.tictactoe;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hwhollywu.tictactoe.view.TicTacToeView;


public class MainActivity extends AppCompatActivity {


    public static Chronometer chronometerCircle;
    public static Chronometer chronometerCross;
    public static long timeWhenCircleStopped = 0;
    public static long timeWhenCrossStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layoutRoot);

        final TicTacToeView gameView = (TicTacToeView) findViewById(R.id.gameView);

        chronometerCircle = (Chronometer) findViewById(R.id.circlechronometer);
        chronometerCross = (Chronometer) findViewById(R.id.crosschronometer);

        Button btnClear = (Button) findViewById(R.id.btnClear);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        TicTacToeView.gameOver = true;

        btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view){
                    if (TicTacToeView.gameOver == true) {
                        chronometerCircle.start();
                        TicTacToeView.gameOver = false;
                    }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear the game board
                //gameView.clearGameArea();
                Snackbar.make(layoutRoot,"Are you sure?", Snackbar.LENGTH_LONG).setAction("Yes",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gameView.clearGameArea();
                            }
                        }).show();
            }
        });
    }

    public void showToastMessage(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    public static void startCircleChronometer() {
        chronometerCircle.setBase(SystemClock.elapsedRealtime() + timeWhenCircleStopped);
        chronometerCircle.start();

    }

    public static void pauseCircleChronometer() {
        timeWhenCircleStopped = chronometerCircle.getBase() - SystemClock.elapsedRealtime();
        chronometerCircle.stop();

    }

    public static void startCrossChronometer() {
        chronometerCross.setBase(SystemClock.elapsedRealtime() + timeWhenCrossStopped);
        chronometerCross.start();
    }

    public static void pauseCrossChronometer() {
        timeWhenCrossStopped = chronometerCross.getBase() - SystemClock.elapsedRealtime();
        chronometerCross.stop();
    }

    public static void stopChronometer(){
        chronometerCircle.stop();
        chronometerCross.stop();
    }

}