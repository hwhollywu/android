package com.hwhollywu.fizzbuzz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class FizzBuzzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.numberSequence);
        NumberSequenceGenerator numberSequence = new NumberSequenceGenerator();
        String sequence = numberSequence.getSequence();
        textView.setText(sequence);

    }
}
