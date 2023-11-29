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
        for (int i = col + 1; i < 8; i++) {
            newCol = i;
            newRow = row;
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square newSquare = Board[newRow][newCol];
                if (newSquare.isEmpty()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                } else if (newSquare.getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                }
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }

        // Checking valid squares to the left of the piece.
        for (int i = col - 1; i >= 0; i--) {
            newCol = i;
            newRow = row;
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square newSquare = Board[newRow][newCol];
                if (newSquare.isEmpty()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                } else if (newSquare.getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                }
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }

        // Checking valid squares going down
        for (int i = row + 1; i < 8; i++) {
            newRow = i;
            newCol = col;
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square newSquare = Board[newRow][newCol];
                if (newSquare.isEmpty()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                } else if (newSquare.getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                }
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }

        // Checking valid squares going up
        for (int i = row - 1; i >= 0; i--) {
            newRow = i;
            newCol = col;
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square newSquare = Board[newRow][newCol];
                if (newSquare.isEmpty()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                } else if (newSquare.getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, newCol);
                    validPositions.add(vp);
                }
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }

        return validPositions;
    }
}