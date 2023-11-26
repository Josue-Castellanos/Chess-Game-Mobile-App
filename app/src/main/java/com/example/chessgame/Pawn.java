package com.example.chessgame;

import android.icu.text.Transliterator;

import java.util.ArrayList;

public class Pawn extends Piece{
    boolean isFirstMove = true;
    public Pawn(boolean isWhite, int imageResourceId, boolean isFirstMove) {
        super(isWhite, imageResourceId);
    }

    // if first move make to be able to go 2 squares forward, after that only 1
    @Override
    public ArrayList<Positions> ValidPositions(Positions position) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

        // If pawn is white
        if (isWhite()) {
            // If pawns first move then its valid to take 2 squares forward
            if (isFirstMove) {
                for (int i = 1; i <= 2; i++) {
                    int newRow = row - i;
                    vp = new Positions(newRow,col);
                    validPositions.add(vp);
                    this.isFirstMove = false;
                }
            }
            // Else only take 1 square forward
            else {
                int newRow = row - 1;
                vp = new Positions(newRow, col);
                validPositions.add(vp);
            }
        }
        else {
            // If pawns first move then its valid to take 2 squares forward
            if (isFirstMove) {
                for (int i = 1; i <= 2; i++) {
                    int newRow = row + i;
                    vp = new Positions(newRow, col);
                    validPositions.add(vp);
                    this.isFirstMove = false;
                }
            }
            // Else only take 1 square forward
            else {
                int newRow = row + 1;
                vp = new Positions(newRow, col);
                validPositions.add(vp);
            }
        }
        return validPositions;
    }
}
