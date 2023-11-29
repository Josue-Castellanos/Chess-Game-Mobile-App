package com.example.chessgame;

import android.icu.text.Transliterator;

import java.util.ArrayList;

public class Pawn extends Piece{
    boolean isFirstMove = true;
    int initialMove = 2;
    int forwardMove = 1;
    public Pawn(boolean isWhite, int imageResourceId, boolean isFirstMove) {
        super(isWhite, imageResourceId);
    }

    @Override
    public ArrayList<Positions> ValidPositions(Positions position, Square[][] Board) {
        int col = position.getCol();
        int row = position.getRow();
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions vp;

// WHITE PAWN
        if (isWhite()) {
            // If pawns first move then it's valid to take 2 squares forward
            if (isFirstMove) {
                // Valid two steps forward
                for (int i = forwardMove; i <= initialMove; i++) {
                    int newRow = row - i;

                    // Check if a piece is on front squares, ignore square if so
                    if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                        Square square = Board[newRow][col];
                        if (square.getPiece() == null) {
                            vp = new Positions(newRow, col);
                            validPositions.add(vp);
                        } else {
                            break;
                        }
                    }
                }
                this.isFirstMove = false;
            }
            // Else only take 1 square forward
            else {
                // Valid 1 step forward
                int newRow = row - forwardMove;
                // If there is a piece in front ignore that square
                if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                    Square square = Board[newRow][col];
                    if (square.getPiece() == null) {
                        vp = new Positions(newRow, col);
                        validPositions.add(vp);
                    }
                }

                // Check for capturing diagonally left
                int leftCaptureCol = col - 1;
                if (leftCaptureCol >= 0 && leftCaptureCol < 8) {
                    Square square = Board[newRow][leftCaptureCol];
                    if (square.getPiece() != null && !square.getPiece().isWhite()) {
                        vp = new Positions(newRow, leftCaptureCol);
                        validPositions.add(vp);
                    }
                }

                // Check for capturing diagonally right
                int rightCaptureCol = col + 1;
                if (rightCaptureCol < 8 && rightCaptureCol >= 0) {
                    Square square = Board[newRow][rightCaptureCol];
                    if (square.getPiece() != null && !square.getPiece().isWhite()) {
                        vp = new Positions(newRow, rightCaptureCol);
                        validPositions.add(vp);
                    }
                }
            }
        }
        // BLACK PAWN
        else {
            // If pawns first move then it's valid to take 2 squares forward
            if (isFirstMove) {
                for (int i = 1; i <= initialMove; i++) {
                    int newRow = row + i;
                    if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                        Square square = Board[newRow][col];
                        if (square.getPiece() == null) {
                            vp = new Positions(newRow, col);
                            validPositions.add(vp);
                        } else {
                            break;
                        }
                    }
                }
                this.isFirstMove = false;
            }
            // Else only take 1 square forward
            else {
                // Valid 1 step forward
                int newRow = row + forwardMove;
                // If there is a piece in front ignore that square
                if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                    Square square = Board[newRow][col];
                    if (square.getPiece() == null) {
                        vp = new Positions(newRow, col);
                        validPositions.add(vp);
                    }
                }

                // Check for capturing diagonally left
                int leftCaptureCol = col - 1;
                if (leftCaptureCol >= 0 && leftCaptureCol < 8) {
                    Square square = Board[newRow][leftCaptureCol];
                    if (square.getPiece() != null && square.getPiece().isWhite() != isWhite()) {
                        vp = new Positions(newRow, leftCaptureCol);
                        validPositions.add(vp);
                    }
                }

                // Check for capturing diagonally right
                int rightCaptureCol = col + 1;
                if (rightCaptureCol < 8 && rightCaptureCol >= 0) {
                    Square square = Board[newRow][rightCaptureCol];
                    if (square.getPiece() != null && square.getPiece().isWhite() != isWhite()) {
                        vp = new Positions(newRow, rightCaptureCol);
                        validPositions.add(vp);
                    }
                }
            }
        }
        return validPositions;
    }
}
