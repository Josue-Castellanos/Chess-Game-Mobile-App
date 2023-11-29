package com.example.chessgame;

import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(boolean isWhite, int imageResourceId) {
        super(isWhite, imageResourceId);
    }

    // Additional methods and properties specific to the queen can be added
    public ArrayList<Positions> ValidPositions(Positions position, Square[][] Board) {
        int newRow;
        int newCol;
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

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
// Loop for diagonal movements towards the top-left
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            newRow = row - i;
            newCol = col - i;

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
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }

        // Loop for diagonal movements towards the top-right
        for (int i = 1; row - i >= 0 && col + i < 8; i++) {
            newRow = row - i;
            newCol = col + i;

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
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }

        // Loop for diagonal movements towards the bottom-left
        for (int i = 1; row + i < 8 && col - i >= 0; i++) {
            newRow = row + i;
            newCol = col - i;

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
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }

        // Loop for diagonal movements towards the bottom-right
        for (int i = 1; row + i < 8 && col + i < 8; i++) {
            newRow = row + i;
            newCol = col + i;

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
                // Break out of the loop if the square is not empty
                if (!newSquare.isEmpty()) {
                    break;
                }
            }
        }
        return validPositions;
    }
}