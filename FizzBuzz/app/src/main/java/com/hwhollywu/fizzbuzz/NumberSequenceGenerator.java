package com.hwhollywu.fizzbuzz;

import android.content.Context;

import java.util.Arrays;

public class NumberSequenceGenerator {

    String[] numbers = new String[20];
    private Context context;

    // compiler will provide a default constructor if not provided by the user
    NumberSequenceGenerator(){
        this.context = context;
        generateNumbers();
    }

    public String getSequence(){
        /*StringBuilder builder = new StringBuilder();
        for (String number: numbers){
            builder.append(number).append(" ");
        }
        return builder.toString(); */

        return Arrays.toString(numbers);

    }

    private void generateNumbers(){
        for (int i = 1; i<=20; i++){
            if (i%15 ==0){
                numbers[i-1]="FizzBuzz";
            }else if (i %3 ==0){
                numbers[i-1] = "Fizz";
            }else if (i %5 ==0){
                numbers[i-1] = "Buzz";
            }else {
                numbers[i - 1] = i + ""; // convert to string
            }
        }

    }
}