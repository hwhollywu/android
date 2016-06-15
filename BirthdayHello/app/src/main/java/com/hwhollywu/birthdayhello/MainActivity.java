package com.hwhollywu.birthdayhello;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name = (EditText) findViewById(R.id.etName);
        //final EditText date = (EditText) findViewById(R.id.etDate);
        final TextView text = (TextView) findViewById(R.id.etText);
        final DatePicker datepicker = (DatePicker) findViewById(R.id.Date);
        Button btnBirthday = (Button) findViewById(R.id.btnBirthday);

        btnBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()) {
                    //show error
                    name.setError(getString(R.string.error_message));
                //} if (date.getText().toString().isEmpty()){
                //    date.setError(getString(R.string.error_message));
                }else {
                    /*
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    Date birthday = null;
                    try {
                        birthday = format.parse(datepicker.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    */
                    int day = datepicker.getDayOfMonth();
                    int month = datepicker.getMonth();
                    int year =  datepicker.getYear();
                    final Calendar birthdayCal = Calendar.getInstance();
                    birthdayCal.set(year, month, day);
                    /*
                    Date birthday = new Date(datepicker.getYear(),
                            datepicker.getMonth(), datepicker.getDayOfMonth());*/

                    Calendar todayCal = Calendar.getInstance();

                    if (birthdayCal.after(todayCal)) {
                        Toast.makeText(MainActivity.this, "Cannot be born in the future",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                    int age = todayCal.get(Calendar.YEAR) - birthdayCal.get(Calendar.YEAR);
                    // If birth date is greater than today's date then decrement age one year
                    if ( (birthdayCal.get(Calendar.DAY_OF_YEAR) -
                            todayCal.get(Calendar.DAY_OF_YEAR) > 3) ||
                            (birthdayCal.get(Calendar.MONTH) >
                                    todayCal.get(Calendar.MONTH ))){
                        age--;

                    // If birth date and today's date are of same month and
                    // birth day of month is greater than today's day of month then decrement age
                    }else if ((birthdayCal.get(Calendar.MONTH) ==
                            todayCal.get(Calendar.MONTH )) &&
                            (birthdayCal.get(Calendar.DAY_OF_MONTH) >
                                    todayCal.get(Calendar.DAY_OF_MONTH ))){
                        age--;
                    }

                    final int BMonth = birthdayCal.get(Calendar.MONTH);
                    final int CMonth = todayCal.get(Calendar.MONTH);

                    final int BDate = birthdayCal.get(Calendar.DAY_OF_MONTH);
                    final int CDate = todayCal.get(Calendar.DAY_OF_MONTH);

                    birthdayCal.set(Calendar.YEAR, todayCal.get(Calendar.YEAR));
                    birthdayCal.set(Calendar.DAY_OF_WEEK,
                            todayCal.get(Calendar.DAY_OF_WEEK));
                    if (BMonth < CMonth) {
                        birthdayCal.set(Calendar.YEAR,
                                todayCal.get(Calendar.YEAR) + 1);
                    }
                    //added this condition so that it doesn't add in case birthdate is greater
                    // than current date . i.e. yet to come in this month.
                    else if (BMonth == CMonth){
                        if(BDate < CDate){
                            birthdayCal.set(Calendar.YEAR,
                                    todayCal.get(Calendar.YEAR) + 1);
                        }
                    }
                    // Result in millis
                    final long millis = birthdayCal.getTimeInMillis()
                            - todayCal.getTimeInMillis();
                    // Convert to days
                    final long daysLeft = millis / 86400000; // Precalculated
                    // (24 *
                    // 60 *
                    // 60 *
                    // 1000)
                    // final String dayOfTheWeek =
                    // sdf.format(BDay.getTime());
                    /*sdf = new SimpleDateFormat("EEEE");
                     final String dayOfTheWeek = sdf.format(dt);*/

                    String toShow = "Age: "+ String.valueOf(age)+ '\n'+
                            "Next birthday in " + String.valueOf(daysLeft) + " days";

                    Toast.makeText(MainActivity.this, toShow, Toast.LENGTH_LONG)
                            .show();

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