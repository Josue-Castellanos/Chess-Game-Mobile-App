package com.example.chessgame;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    // Additional methods and properties specific to the queen can be added
    public ArrayList<Positions> ValidPositions(Positions position, Square[][] Board) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

        // Loop for movements towards the right
        for (int i = col; i < 8; i++) {
            int newCol = i;
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
        // Loop for movements towards the left
        for (int i = col; i > 0; i--) {
            int newCol = i;
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
        // Loop for movements towards the bottom
        for (int i = row; i < 8; i++) {
            int newRow = i;
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
        // Loop for movements towards the top
        for (int i = row; i >= 0; i--) {
            int newRow = i;
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
        // Loop for diagonal movements towards the top-left
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            int newRow = row - i;
            int newCol = col - i;
            // Check if a piece is on front squares
            if (Board[newRow][newCol].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                vp = new Positions(newRow, newCol);
                validPositions.add(vp);
            };
        }

        // Loop for diagonal movements towards the top-right
        for (int i = 1; row - i >= 0 && col + i < 8; i++) {
            int newRow = row - i;
            int newCol = col + i;
            // Check if a piece is on front squares
            if (Board[newRow][newCol].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                vp = new Positions(newRow, newCol);
                validPositions.add(vp);
            }
        }

        // Loop for diagonal movements towards the bottom-left
        for (int i = 1; row + i < 8 && col - i >= 0; i++) {
            int newRow = row + i;
            int newCol = col - i;
            // Check if a piece is on front squares
            if (Board[newRow][col].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                vp = new Positions(newRow, newCol);
                validPositions.add(vp);
            }
        }

        // Loop for diagonal movements towards the bottom-right
        for (int i = 1; row + i < 8 && col + i < 8; i++) {
            int newRow = row + i;
            int newCol = col + i;
            // Check if a piece is on front squares
            if (Board[newRow][newCol].getPiece() != null) {
                break;
            }
            // save valid positions
            else if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                vp = new Positions(newRow, newCol);
                validPositions.add(vp);
            }
        }
        return validPositions;
    }
}