package com.hwhollywu.timehelloworld;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends ActionBarActivity {

        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final EditText etName = (EditText) findViewById(R.id.etName);
            Button btnTime = (Button) findViewById(R.id.btnTime);
            final TextView tvTime = (TextView) findViewById(R.id.textTime);

            btnTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (etName.getText().toString().isEmpty()) {
                        //show error
                        // always extract the string element
                        etName.setError(getString(R.string.error_massage));
                    } else {
                        String time = etName.getText() + " " +
                                getString(R.string.time_header) +
                                new Date(System.currentTimeMillis()).toString();

                        // search: what is Toast
                        Toast.makeText(MainActivity.this, time, Toast.LENGTH_LONG).show();
                        tvTime.setText(time);
                    }
                }
            });


        }
}


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/

