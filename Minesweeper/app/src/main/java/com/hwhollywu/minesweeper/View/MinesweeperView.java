package com.hwhollywu.minesweeper.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hwhollywu.minesweeper.MainActivity;
import com.hwhollywu.minesweeper.Model.MinesweeperModel;
import com.hwhollywu.minesweeper.R;

public class MinesweeperView extends View{

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintMine;
    private Paint paintFlag;
    private Paint paintText;
    private Bitmap bitmapMine;
    private Bitmap bitmapFlag;
    boolean isGameOver = false;

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paintBg = new Paint();
        paintBg.setColor(Color.GRAY);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine =new Paint();
        paintLine.setColor(Color.YELLOW);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

        paintMine = new Paint();
        paintMine.setColor(Color.BLACK);
        paintMine.setStyle(Paint.Style.FILL);

        paintFlag = new Paint();
        paintFlag.setColor(Color.RED);
        paintFlag.setStyle(Paint.Style.FILL);

        paintText = new Paint();
        paintText.setColor(Color.YELLOW);
        paintText.setTextSize(60);

        bitmapMine = BitmapFactory.decodeResource(getResources(), R.drawable.mine);
        bitmapFlag = BitmapFactory.decodeResource(getResources(), R.drawable.flag);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //background
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);

        //canvas.drawText("Click to play", 30, 90, paintText);

        // board
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);
        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5, paintLine);
        canvas.drawLine(0, 2 * getHeight() / 5, getWidth(), 2 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 3 * getHeight() / 5, getWidth(), 3 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 4 * getHeight() / 5, getWidth(), 4 * getHeight() / 5, paintLine);

        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5, getHeight(), paintLine);

        //player
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MinesweeperModel.getInstance().getOpen(i,j) == MinesweeperModel.OPEN) {
                    if (MinesweeperModel.getInstance().getFieldContent(i, j)
                            == MinesweeperModel.MINE) {

                        // draw a circle at the center of the field
                        // X coordinate: left side of the square + half width of the square
                        float centerX = i * getWidth() / 5 + getWidth() / 10
                                - bitmapMine.getHeight()/2;
                        float centerY = j * getHeight() / 5 + getHeight() / 10
                                - bitmapMine.getHeight()/2;
                        //canvas.drawCircle(centerX, centerY, radius, paintMine);
                        canvas.drawBitmap(bitmapMine, centerX, centerY, null);


                    } else if (MinesweeperModel.getInstance().getFieldContent(i, j)
                            == MinesweeperModel.FLAG) {
                        float centerX = i * getWidth() / 5 + getWidth() / 10
                                - bitmapFlag.getHeight()/2;
                        float centerY = j * getHeight() / 5 + getHeight() / 10
                                - bitmapFlag.getHeight()/2;
                        //canvas.drawCircle(centerX, centerY, radius, paintFlag);
                        canvas.drawBitmap(bitmapFlag, centerX, centerY, null);

                    } else if  (MinesweeperModel.getInstance().getFieldContent(i, j)
                            ==MinesweeperModel.WRONGFLAG){
                        float centerX = i * getWidth() / 5 + getWidth() / 10
                                - bitmapFlag.getHeight()/2;
                        float centerY = j * getHeight() / 5 + getHeight() / 10
                                - bitmapFlag.getHeight()/2;
                        canvas.drawBitmap(bitmapFlag, centerX, centerY, null);
                        canvas.drawLine(i * getWidth() / 5, j * getHeight() / 5,
                                (i + 1) * getWidth() / 5,
                                (j + 1) * getHeight() /5, paintLine);
                        canvas.drawLine((i + 1) * getWidth() / 5, j * getHeight() / 5,
                                i * getWidth() / 5, (j + 1) * getHeight() / 5, paintLine);


                    }else if (MinesweeperModel.getInstance().getFieldContent(i, j)
                            == MinesweeperModel.EMPTY){
                        //showing number
                        float centerX = i * getWidth() / 5 + getWidth() / 10;
                        float centerY = j * getHeight() / 5 + getHeight() / 10;
                        int neighbors = MinesweeperModel.getInstance().getNeighbors(i,j);
                        canvas.drawText(Integer.toString(neighbors), centerX, centerY, paintText);
                    }
                }
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int radiusMine = getHeight() / 10 + bitmapMine.getHeight() / 7;
        bitmapMine = Bitmap.createScaledBitmap(bitmapMine,
                radiusMine, radiusMine, false);
        int radiusFlag = getHeight() / 10 + bitmapFlag.getHeight() / 7;
        bitmapFlag = Bitmap.createScaledBitmap(bitmapFlag,
                radiusFlag, radiusFlag, false);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int tX = ((int) event.getX()) / (getWidth() / 5);
        int tY = ((int) event.getY()) / (getHeight() / 5);

        checkWin();

        if (MinesweeperModel.getInstance().getOpen(tX, tY) == false && !isGameOver
                && event.getAction() == MotionEvent.ACTION_DOWN) {

            MinesweeperModel.getInstance().setOpen(tX, tY);
            // if try mode
            if (MainActivity.mode == MainActivity.TRY){
                if (MinesweeperModel.getInstance().getFieldContent(tX, tY)
                        ==MinesweeperModel.MINE){
                    //gameOver
                    isGameOver = true;
                    ((MainActivity) getContext()).showSnackbarMessage(
                            getContext().getString(R.string.gameOver_boom));
                }
                //else if (MinesweeperModel.getInstance().getFieldContent(tX, tY)
                  //      ==MinesweeperModel.EMPTY){
                    //show number
                }
            // if flag mode
            if (MainActivity.mode == MainActivity.FLAG){
                if (MinesweeperModel.getInstance().getFieldContent(tX, tY)
                        ==MinesweeperModel.MINE){
                    MinesweeperModel.getInstance().setFieldContent(tX,tY, MinesweeperModel.FLAG);
                    MinesweeperModel.unfoundedMine--;
                    String message = MinesweeperModel.unfoundedMine + "" + " " + "Mines Left";
                    MainActivity.changeMineText(message);
                }else if (MinesweeperModel.getInstance().getFieldContent(tX, tY)
                        ==MinesweeperModel.EMPTY){
                    //gameOver
                    isGameOver = true;
                    MinesweeperModel.getInstance().setFieldContent(tX,tY, MinesweeperModel.WRONGFLAG);
                    ((MainActivity) getContext()).showSnackbarMessage(
                            getContext().getString(R.string.gameOver_flag));
                }
            }
            invalidate();
            }
        else {

        }
        return true;
    }

    private void checkWin() {
       /* int countOpen = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (MinesweeperModel.getInstance().getOpen(i, j) ==MinesweeperModel.OPEN)
                    countOpen++;
            }
        }*/
        if (MinesweeperModel.unfoundedMine ==0) {
            //report win
            ((MainActivity) getContext()).showSnackbarMessage(
                    getContext().getString(R.string.gameOver_win));
            isGameOver = true;
        }
    }


    public void clearGameArea() {
        //circles.clear();
        //TicTacToeModel.getInstance().resetModel();
        MinesweeperModel.mineNumber = 3;
        MinesweeperModel.unfoundedMine =3;
        MinesweeperModel.getInstance().resetModel();
        String message = MinesweeperModel.unfoundedMine + "" + " " + "Mines Left";
        MainActivity.changeMineText(message);
        isGameOver = false;
        invalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = (w == 0 ? h : (h == 0 ? w : (w < h ? w : h)));
        setMeasuredDimension(d, d);
    }

}
