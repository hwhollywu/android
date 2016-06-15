package com.hwhollywu.highlowgame;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GameActivity extends AppCompatActivity {

    public static final String KEY_GENERATED = "KEY_GENERATED";
    private int generateNum =0;
    public static int guessNumber =0;
    private TextView guess;


    @BindView(R.id.etGuess)
    EditText etGuess;

    @BindView(R.id.tvData)
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        guess = (TextView) findViewById(R.id.txtGuess);

        ButterKnife.bind(this);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_GENERATED)) {
            generateNum = savedInstanceState.getInt(KEY_GENERATED);
        } else {
            generateNewNumber();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_GENERATED, generateNum);

        super.onSaveInstanceState(outState);
    }


    private void generateNewNumber(){
        Random rand = new Random(System.currentTimeMillis());
        generateNum= rand.nextInt(100);
    }

    @OnClick(R.id.btnGuess)
    public void guessButtonPressed(){
        // generate a random number
        // determine if it's larger or smaller
        int guessNum = Integer.parseInt(etGuess.getText().toString());
        if (guessNum < generateNum) {
            tvData.setText("Your number is smaller");
            guessNumber++;
            guess.setText("Guess Number: " + guessNumber);

        }else if (guessNum >generateNum){
            tvData.setText("Your number is larger");
            guessNumber++;
            guess.setText("Guess Number: " + guessNumber);
        }else {
            tvData.setText("You have won, the number is: "+ guessNum);

            startActivity(new Intent(this,WinActivity.class));

        }

    }


}
