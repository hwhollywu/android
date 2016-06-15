package com.hwhollywu.tictactoe.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hwhollywu.tictactoe.MainActivity;
import com.hwhollywu.tictactoe.R;
import com.hwhollywu.tictactoe.model.TicTacToeModel;

public class TicTacToeView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintCircle;
    private Paint paintText;
    private Paint paintBorder;
    //private List<Point> circles = new ArrayList<Point>();
    private Bitmap bitmapBg;
    private Bitmap bitmapLogo;
    public static boolean gameOver = true;

    public TicTacToeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paintBg = new Paint();
        paintBg.setColor(Color.BLACK);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.CYAN);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(10);

        paintBorder = new Paint();
        paintBorder.setColor(Color.BLACK);
        paintBorder.setStrokeWidth(5);
        paintBorder.setStyle(Paint.Style.STROKE);

        paintCircle = new Paint();
        paintCircle.setColor(Color.RED);
        paintCircle.setStyle(Paint.Style.FILL);

        paintText = new Paint();
        paintText.setColor(Color.alpha(100));
        paintText.setTextSize(60);

        bitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.vassar);
        bitmapLogo = BitmapFactory.decodeResource(getResources(), R.drawable.logo);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);

        canvas.drawBitmap(bitmapBg, 0, 0, null);

        canvas.drawText("Click to play", 30, 90, paintText);

        drawGameBoard(canvas);

        drawPlayers(canvas);

        //paintText.getTextBounds();


    }

    private void drawPlayers(Canvas canvas) {
        // type circles.for
        /*for (Point circle : circles) {
            canvas.drawCircle(circle.x, circle.y, 40, paintCircle);
        }
        */

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, j)
                        == TicTacToeModel.CIRCLE) {

                    // draw a circle at the center of the field
                    // X coordinate: left side of the square + half width of the square
                    float centerX = i * getWidth() / 3 + getWidth() / 12 - bitmapLogo.getHeight() / 6 - 20;
                    float centerY = j * getHeight() / 3 + getHeight() / 12 - bitmapLogo.getHeight() / 6 - 20;
                    int radius = getHeight() / 6 + bitmapLogo.getHeight() / 3;

                    //canvas.drawCircle(centerX, centerY, radius, paintCircle);
                    //draw logo instead
                    canvas.drawBitmap(bitmapLogo, centerX, centerY, null);

                } else if
                        (TicTacToeModel.getInstance().getFieldContent(i, j)
                                == TicTacToeModel.CROSS) {
                    canvas.drawLine(i * getWidth() / 3, j * getHeight() / 3,
                            (i + 1) * getWidth() / 3,
                            (j + 1) * getHeight() / 3, paintLine);

                    canvas.drawLine((i + 1) * getWidth() / 3, j * getHeight() / 3,
                            i * getWidth() / 3, (j + 1) * getHeight() / 3, paintLine);
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmapBg = Bitmap.createScaledBitmap(bitmapBg,
                getWidth(), getHeight(), false);
        int radius = getHeight() / 6 + bitmapLogo.getHeight() / 3;
        bitmapLogo = Bitmap.createScaledBitmap(bitmapLogo,
                radius, radius, false);

    }


    private void drawBackground(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
    }

    private void drawGameBoard(Canvas canvas) {
        //draw a line
        //canvas.drawLine(0,0,getWidth(), getHeight(), paintLine);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBorder);
        canvas.drawLine(0, getHeight() / 3, getWidth(), getHeight() / 3, paintBorder);
        canvas.drawLine(0, 2 * getHeight() / 3, getWidth(), 2 * getHeight() / 3, paintBorder);

        canvas.drawLine(getWidth() / 3, 0, getWidth() / 3, getHeight(), paintBorder);
        canvas.drawLine(2 * getWidth() / 3, 0, 2 * getWidth() / 3, getHeight(), paintBorder);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int tX = ((int) event.getX()) / (getWidth() / 3);
        int tY = ((int) event.getY()) / (getHeight() / 3);

        checkStatus(tX, tY);


        if (!gameOver && event.getAction() == MotionEvent.ACTION_DOWN) {
            //create array
            //circles.add(new Point((int) event.getX(), (int)event.getY()));

            if (tX < 3 && tY < 3 &&
                    TicTacToeModel.getInstance().getFieldContent(tX, tY) == TicTacToeModel.EMPTY) {
                TicTacToeModel.getInstance().setFieldContent
                        (tX, tY, TicTacToeModel.getInstance().getNextPlayer());
                TicTacToeModel.getInstance().changeNextPlayer();

                if (TicTacToeModel.getInstance().getNextPlayer() == TicTacToeModel.CROSS){
                    MainActivity.pauseCircleChronometer();
                    MainActivity.startCrossChronometer();
                } else if (TicTacToeModel.getInstance().getNextPlayer() == TicTacToeModel.CIRCLE){
                    MainActivity.pauseCrossChronometer();
                    MainActivity.startCircleChronometer();
                }

                ((MainActivity) getContext()).showToastMessage(getResources().getString
                        (R.string.txt_next_player) + " " +
                        TicTacToeModel.getInstance().getNextPlayerString());
            }


            // repaint the view, call the old functions
            invalidate();


        }

        return true;
    }


    public void checkStatus(int x, int y) {

        //check column
        checkColumn(x);

        //check rows
        if (!gameOver)
            checkRow(y);

        //check diagonal
        if (!gameOver)
            checkDiagonal(x, y);

        //check tie
        if (!gameOver)
            checkTie();
    }

    private void checkTie() {
        int countFull = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, j) != TicTacToeModel.EMPTY)
                    countFull++;
                }
            }
        if (countFull ==9) {
            //report tie
            //report win for cross
            ((MainActivity) getContext()).showToastMessage(
                    getContext().getString(R.string.game_draw));
            gameOver = true;
            MainActivity.stopChronometer();
        }
    }

    private void checkDiagonal(int x, int y) {
        if (x == y) {
            for (int i = 0; i < 3; i++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, i) != TicTacToeModel.CIRCLE)
                    break;
                if (i == 2) {
                    //report win for circle
                    //report win for cross
                    ((MainActivity) getContext()).showToastMessage(
                            getContext().getString(R.string.win_circle));
                    gameOver = true;
                    MainActivity.stopChronometer();

                }
            }

            for (int i = 0; i < 3; i++) {
                if (TicTacToeModel.getInstance().getFieldContent(i, i) != TicTacToeModel.CROSS)
                    break;
                if (i == 2) {
                    //report win for cross
                    //report win for cross
                    ((MainActivity) getContext()).showToastMessage(
                            getContext().getString(R.string.win_cross));
                    gameOver = true;
                    MainActivity.stopChronometer();

                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (TicTacToeModel.getInstance().getFieldContent(i, 2 - i) != TicTacToeModel.CIRCLE)
                break;
            if (i == 2) {
                //report win for circle
                //report win for cross
                ((MainActivity) getContext()).showToastMessage(
                        getContext().getString(R.string.win_circle));
                gameOver = true;
                MainActivity.stopChronometer();

            }
        }
        for (int i = 0; i < 3; i++) {
            if (TicTacToeModel.getInstance().getFieldContent(i, 2 - i) != TicTacToeModel.CROSS)
                break;
            if (i == 2) {
                //report win for cross
                //report win for cross
                ((MainActivity) getContext()).showToastMessage(
                        getContext().getString(R.string.win_cross));
                gameOver = true;
                MainActivity.stopChronometer();

            }
        }
    }

    private void checkRow(int y) {
        for (int i = 0; i < 3; i++) {
            if (TicTacToeModel.getInstance().getFieldContent(i, y) != TicTacToeModel.CIRCLE)
                break;
            if (i == 2) {
                //report win for circle
                ((MainActivity) getContext()).showToastMessage
                        (getContext().getString(R.string.win_circle));
                gameOver = true;
                MainActivity.stopChronometer();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (TicTacToeModel.getInstance().getFieldContent(i, y) != TicTacToeModel.CROSS)
                break;
            if (i == 2) {
                //report win for cross
                ((MainActivity) getContext()).showToastMessage(
                        getContext().getString(R.string.win_cross));
                gameOver = true;
                MainActivity.stopChronometer();
            }
        }
    }

    private void checkColumn(int x) {
        for (int i = 0; i < 3; i++) {
            if (TicTacToeModel.getInstance().getFieldContent(x, i) != TicTacToeModel.CIRCLE)
                break;
            if (i == 2) {
                //report win for circle
                ((MainActivity) getContext()).showToastMessage
                        (getContext().getString(R.string.win_circle));
                gameOver = true;
                MainActivity.stopChronometer();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (TicTacToeModel.getInstance().getFieldContent(x, i) != TicTacToeModel.CROSS)
                break;
            if (i == 2) {
                //report win for cross
                ((MainActivity) getContext()).showToastMessage(
                        getContext().getString(R.string.win_cross));
                gameOver = true;
                MainActivity.stopChronometer();
            }
        }
    }


    public void clearGameArea() {
        //circles.clear();
        TicTacToeModel.getInstance().resetModel();
        MainActivity.stopChronometer();
        MainActivity.chronometerCircle.setBase(SystemClock.elapsedRealtime());
        MainActivity.chronometerCross.setBase(SystemClock.elapsedRealtime());
        gameOver = false;
        invalidate();
    }

    /*
    public void stopGame(){
        // show text
        // disable clicking

        setFocusableInTouchMode(false);
        setWillNotDraw(true);

    }*/


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int d = (w == 0 ? h : (h == 0 ? w : (w < h ? w : h)));
        //if(w==0),d=h;
        //else if (h==0), d=w
        //else if (w<h), d=w;
        //else if (w>=h), d=h;

        setMeasuredDimension(d, d);
    }

}
