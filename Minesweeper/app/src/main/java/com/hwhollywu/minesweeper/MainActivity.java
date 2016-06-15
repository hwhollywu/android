package com.hwhollywu.minesweeper;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hwhollywu.minesweeper.View.MinesweeperView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    public static final boolean TRY = false;
    public static final boolean FLAG = true;
    public static boolean mode = TRY;

    private static TextView mineNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layoutRoot);

        final MinesweeperView gameView = (MinesweeperView) findViewById(R.id.gameView);

        final Button btnToggle = (Button) findViewById(R.id.btnToggle);
        final Button btnClear = (Button) findViewById(R.id.btnClear);
        mineNumber = (TextView) findViewById(R.id.minenumber);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear the game board
                //gameView.clearGameArea();
                Snackbar.make(layoutRoot, "Are you sure?", Snackbar.LENGTH_LONG).setAction("Yes",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gameView.clearGameArea();
                            }
                        }).show();
            }
        });


        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // if not mine- gameOver
            mode = !mode;
                if (mode == TRY){
                    btnToggle.setText("Try a field");
                }
                else if (mode ==FLAG){
                    btnToggle.setText("Place a flag");
                }
        }});

    }

    public void showSnackbarMessage(String message) {
        final LinearLayout layoutRoot = (LinearLayout) findViewById(R.id.layoutRoot);
        Snackbar.make(layoutRoot, message, Snackbar.LENGTH_SHORT).show();
    }

    static public void changeMineText(String message) {
        mineNumber.setText(message);
    }

}
