package com.example.chessgame;

import java.util.ArrayList;

public class King extends Piece{
    boolean isFirstMove = true;
    public King(boolean isWhite, int imageResourceId, boolean isFirstMove) {
        super(isWhite, imageResourceId);
    }

    public ArrayList<Positions> ValidPositions(Positions position, Square[][] Board) {
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

            // save valid positions
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square newSquare = Board[newRow][newCol];
                if (newSquare.isEmpty()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                } else if (newSquare.getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                }
            }
        }
        return validPositions;
    }
}