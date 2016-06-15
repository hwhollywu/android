package com.hwhollywu.minesweeper.Model;


import com.hwhollywu.minesweeper.View.MinesweeperView;

public class MinesweeperModel {
    //disable a constructor, can't create objects inside the class
    private static MinesweeperModel instance = null;

    public static MinesweeperModel getInstance() {
        if (instance == null) {
            instance = new MinesweeperModel();
            instance.resetModel();
        }
        return instance;
    }

    public static final short EMPTY = 0;
    public static final short MINE = 1;
    public static final short FLAG = 2;
    public static final short WRONGFLAG =3;

    public static final boolean OPEN = true;
    public static final boolean UNOPENED = false;
    public static int mineNumber = 3;
    public static int unfoundedMine =3;

    // for Minesweeper
    //    private Field[][] model = {
    private short[][] model = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
    };

    private boolean[][] modelOpen = {
            {UNOPENED, UNOPENED, UNOPENED, UNOPENED, UNOPENED},
            {UNOPENED, UNOPENED, UNOPENED, UNOPENED, UNOPENED},
            {UNOPENED, UNOPENED, UNOPENED, UNOPENED, UNOPENED},
            {UNOPENED, UNOPENED, UNOPENED, UNOPENED, UNOPENED},
            {UNOPENED, UNOPENED, UNOPENED, UNOPENED, UNOPENED},
    };

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public short setFieldContent(int x, int y, short content) {
        return model[x][y] = content;
    }

    public boolean getOpen(int x, int y) {
        return modelOpen[x][y];
    }

    public void setOpen(int x, int y) {
        modelOpen[x][y] = OPEN;
    }

    public void resetModel() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                model[i][j] = EMPTY;
                modelOpen[i][j] = UNOPENED;
            }
        }
        //set 3 mines
        setMines();
    }

    public void setMines() {
            int ri = (int) (Math.random() * 5);
            int rj = (int) (Math.random() * 5);
            if (model[ri][rj] == EMPTY){
                model[ri][rj] = MINE;
                mineNumber--;
            }
            while (mineNumber != 0) {
                setMines();
            }
        }



    public int getNeighbors(int x, int y) {
        // neighbors =
        // {(x-1, y-1),(x-1, y),(x-1, y+1),(x, y-1),(x, y+1),(x+1, y-1),(x+1, y),(x+1, y+1)}
        int neighbors = 0;
        for (int i = x - 1; i <= x + 1; i++) {
             for (int j = y - 1; j <= y + 1; j++) {
                 if (i != -1 && j != -1 && i !=5 && j!=5) {
                     if (model[i][j] == MINE || model[i][j] == FLAG)
                         neighbors++;
                 }
             }
        }
        return neighbors;
    }
}



