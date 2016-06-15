package com.hwhollywu.muiltiactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hwhollywu.muiltiactivitydemo.data.User;
import com.hwhollywu.muiltiactivitydemo.data.UserDataManager;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // don't create instance of MainActivity as it might disappear

        TextView tvData = (TextView) findViewById(R.id.tvData);

        tvData.setText("Hello "+ UserDataManager.getInstance().getMainUser().getUserName()+
                " you are living in "+UserDataManager.getInstance().getMainUser().getAddress());

        // method1
                /*
        if(getIntent().hasExtra(MainActivity.KEY_USERNAME) &&
                getIntent().hasExtra(MainActivity.KEY_ADDRESS)){
            String userName = getIntent().getStringExtra(MainActivity.KEY_USERNAME);
            String address = getIntent().getStringExtra(MainActivity.KEY_ADDRESS);

            tvData.setText("Hello " + userName + "! you are living in " +address);*/

        // method2
        /*
        if (getIntent().hasExtra(MainActivity.KEY_USER)){
            User user = (User) getIntent().getSerializableExtra(MainActivity.KEY_USER);

            tvData.setText("Hello " + user.getUserName() +
                    "! you are living in " +user.getAddress());

        }*/
        }
    }
