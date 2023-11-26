package com.example.chessgame;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    // Additional methods and properties specific to the queen can be added
    public ArrayList<Positions> ValidPositions(Positions position) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

        // Loop for movements towards the right
        for (int i = col; i < 8; i++) {
            int newCol = i;
            vp = new Positions(row, newCol);
            validPositions.add(vp);
        }
        // Loop for movements towards the left
        for (int i = col; i > 0; i--) {
            int newCol = i;
            vp = new Positions(row, newCol);
            validPositions.add(vp);
        }
        // Loop for movements towards the bottom
        for (int i = row; i < 8; i++) {
            int newRow = i;
            vp = new Positions(newRow, col);
            validPositions.add(vp);
        }
        // Loop for movements towards the top
        for (int i = row; i >= 0; i--) {
            int newRow = i;
            vp = new Positions(newRow, col);
            validPositions.add(vp);
        }
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