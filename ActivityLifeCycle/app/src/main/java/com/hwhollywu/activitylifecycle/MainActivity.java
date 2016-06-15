package com.hwhollywu.activitylifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    public static final String TAG_LIFE = "TAG_LIFE";

    int a =0;
    // save a variable so when the activity recreated, it stores the value
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //print statement in Android
        Log.d(TAG_LIFE, "onCreate called");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG_LIFE, "onStart called");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG_LIFE, "onResume called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG_LIFE, "onStop called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG_LIFE, "onDestroy called");
    }

}
