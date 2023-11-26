package com.example.chessgame;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    public ArrayList<Positions> ValidPositions(Positions position) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

        // Loop for diagonal movements towards the top-left
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            int newRow = row - i;
            int newCol = col - i;
            vp = new Positions(newRow, newCol);
            validPositions.add(vp);
        }

        // Loop for diagonal movements towards the top-right
        for (int i = 1; row - i >= 0 && col + i < 8; i++) {
            int newRow = row - i;
            int newCol = col + i;
            vp = new Positions(newRow, newCol);
            validPositions.add(vp);
        }

        // Loop for diagonal movements towards the bottom-left
        for (int i = 1; row + i < 8 && col - i >= 0; i++) {
            int newRow = row + i;
            int newCol = col - i;
            vp = new Positions(newRow, newCol);
            validPositions.add(vp);
        }

        // Loop for diagonal movements towards the bottom-right
        for (int i = 1; row + i < 8 && col + i < 8; i++) {
            int newRow = row + i;
            int newCol = col + i;
            vp = new Positions(newRow, newCol);
            validPositions.add(vp);
        }
        return validPositions;
    }
}
