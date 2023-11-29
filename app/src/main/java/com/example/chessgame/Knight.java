package com.example.chessgame;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    // Knight moves
    public ArrayList<Positions> ValidPositions(Positions position, Square[][] Board) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

        int[] moveRows = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] moveCols = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < moveRows.length; i++) {
            int newRow = row + moveRows[i];
            int newCol = col + moveCols[i];

            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square newSquare = Board[newRow][newCol];
                if (newSquare.isEmpty() || newSquare.getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                }

            }
        }
        return validPositions;
    }
}
