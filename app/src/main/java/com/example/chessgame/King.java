package com.example.chessgame;

import java.util.ArrayList;

public class King extends Piece{
    public King(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    public ArrayList<Positions> ValidPositions(Positions position) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;


        // King moves
        int[] moveRows = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] moveCols = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < moveRows.length; i++) {
            int newRow = row + moveRows[i];
            int newCol = col + moveCols[i];

            if (row >= 0 && row < 8 && col >= 0 && col < 8) {
                vp = new Positions(newRow, newCol);
                validPositions.add(vp);
            }
        }

        return validPositions;
    }
}