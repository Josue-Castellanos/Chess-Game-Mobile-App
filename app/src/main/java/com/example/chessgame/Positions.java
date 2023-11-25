package com.example.chessgame;

public class Positions {
    private int row;
    private int col;

    public Positions(int row, int col) {
        this.row = row;
        this.col =col;
    }

    void setRow(int row) {
        this.row = row;
    }
    void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
