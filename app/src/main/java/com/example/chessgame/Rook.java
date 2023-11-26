package com.example.chessgame;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    @Override
    public ArrayList<Positions> ValidPositions(Positions position) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

        // Rooks can move any number of squares, up and down and side to side.
        // Rooks can move by getting current Row position and have it reach 7 and 0

        // Checking valid squares to the right of the piece
        for (int i = col; i < 8; i++) {
            int newCol = i;
            vp = new Positions(row, i);
            validPositions.add(vp);
        }
        // Checking valid squares to the left of the piece.
        for (int i = col; i > 0; i--) {
            vp = new Positions(row, i);
            validPositions.add(vp);
        }
        // Checking valid squares going up
        for (int i = row; i < 8; i++) {
            vp = new Positions(i, col);
            validPositions.add(vp);
        }
        // Checking valid squares going down
        for (int i = row; i >= 0; i--) {
            vp = new Positions(i, col);
            validPositions.add(vp);
        }

        return validPositions;
    }
}
