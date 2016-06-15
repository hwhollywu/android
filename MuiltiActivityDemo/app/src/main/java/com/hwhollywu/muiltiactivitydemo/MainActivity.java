package com.hwhollywu.muiltiactivitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hwhollywu.muiltiactivitydemo.data.User;
import com.hwhollywu.muiltiactivitydemo.data.UserDataManager;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "KEY_USERNAME";
    public static final String KEY_ADDRESS = "KEY_ADDRESS";
    public static final String KEY_USER = "KEY_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etAddress = (EditText) findViewById(R.id.etAddress);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent intentStartDetails = new Intent();
                intentStartDetails.setClass(MainActivity.this, DetailsActivity.class);

                /*
                intentStartDetails.putExtra(KEY_USERNAME, etUserName.getText().toString());
                intentStartDetails.putExtra(KEY_ADDRESS, etAddress.getText().toString());

                intentStartDetails.putExtra(KEY_USER, new User(
                        etUserName.getText().toString(),etAddress.getText().toString()));*/

                UserDataManager.getInstance().setMainUser(new User(
                        etUserName.getText().toString(),etAddress.getText().toString()));

                startActivity(intentStartDetails);

                //finish();
            }
        });

    }
}
