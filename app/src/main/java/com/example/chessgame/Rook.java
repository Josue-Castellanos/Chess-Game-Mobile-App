package com.example.chessgame;

import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    @Override
    public ArrayList<Positions> ValidPositions(Positions position, Square[][] Board) {
        int newCol;
        int newRow;
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

        // Rooks can move any number of squares, up and down and side to side.
        // Rooks can move by getting current Row position and have it reach 7 and 0

        // Checking valid squares to the right of the piece
        for (int i = col; i < 8; i++) {
            newCol = i;
            // Check if a piece is on front squares
            if (Board[row][newCol].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (row >= 0 && row < 8 && newCol >= 0 && newCol < 8) {
                vp = new Positions(row, newCol);
                validPositions.add(vp);
            }
        }
        // Checking valid squares to the left of the piece.
        for (int i = col; i > 0; i--) {
            newCol = i;
            // Check if a piece is on front squares
            if (Board[row][newCol].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (row >= 0 && row < 8 && newCol >= 0 && newCol < 8) {
                vp = new Positions(row, newCol);
                validPositions.add(vp);
            }
        }
        // Checking valid squares going up
        for (int i = row; i < 8; i++) {
            newRow = i;
            // Check if a piece is on front squares
            if (Board[newRow][col].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                vp = new Positions(newRow, col);
                validPositions.add(vp);
            }
            vp = new Positions(newRow, col);
            validPositions.add(vp);
        }
        // Checking valid squares going down
        for (int i = row; i >= 0; i--) {
            newRow = i;
            // Check if a piece is on front squares
            if (Board[newRow][col].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                vp = new Positions(newRow, col);
                validPositions.add(vp);
            }
        }

        return validPositions;
    }
}
