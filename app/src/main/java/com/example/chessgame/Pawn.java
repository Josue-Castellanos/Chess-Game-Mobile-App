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
            // If pawns first move then its valid to take 2 squares forward
            if (isFirstMove) {
                // Valid two steps forward
                for (int i = forwardMove; i <= initialMove; i++) {
                    int newRow = row - i;

                    // Check if a piece is on front squares, ignore square if so
                    if (Board[newRow][col].getPiece() != null) {
                        break;
                    }
                    // save valid positions
                    else if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                        vp = new Positions(newRow, col);
                        validPositions.add(vp);
                    }
                }
                this.isFirstMove = false;
            }
            // Else only take 1 square forward
            else {
                // Valid 1 step forward
                int newRow = row - forwardMove;
                // If there is a piece in front ignore that square
                if (Board[newRow][col].getPiece() == null) {
                    vp = new Positions(newRow, col);
                    validPositions.add(vp);
                }

                // Check for capturing diagonally left
                int leftCaptureCol = col - 1;
                if (leftCaptureCol >= 0 && Board[newRow][leftCaptureCol].getPiece() != null
                        && !Board[newRow][leftCaptureCol].getPiece().isWhite()) {
                    vp = new Positions(newRow, leftCaptureCol);
                    validPositions.add(vp);
                }

                // Check for capturing diagonally right
                int rightCaptureCol = col + 1;
                if (rightCaptureCol < 8 && Board[newRow][rightCaptureCol].getPiece() != null
                        && !Board[newRow][rightCaptureCol].getPiece().isWhite()) {
                    vp = new Positions(newRow, rightCaptureCol);
                    validPositions.add(vp);
                }
            }
        }
        // BLACK PAWN
        else {
            // If pawns first move then its valid to take 2 squares forward
            if (isFirstMove) {
                for (int i = 1; i <= initialMove; i++) {
                    int newRow = row + i;
                    if (Board[newRow][col].getPiece() != null) {
                        break;
                    } else if (newRow >= 0 && newRow < 8 && col >= 0 && col < 8) {
                        vp = new Positions(newRow, col);
                        validPositions.add(vp);
                    }
                    this.isFirstMove = false;
                }
            }
            // Else only take 1 square forward
            else {
                // Valid 1 step forward
                int newRow = row + forwardMove;
                // If there is a piece in front ignore that square
                if (Board[newRow][col].getPiece() == null) {
                    vp = new Positions(newRow, col);
                    validPositions.add(vp);
                }

                // Check for capturing diagonally left
                int leftCaptureCol = col - 1;
                if (leftCaptureCol >= 0 && Board[newRow][leftCaptureCol].getPiece() != null
                        && Board[newRow][leftCaptureCol].getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, leftCaptureCol);
                    validPositions.add(vp);
                }

                // Check for capturing diagonally right
                int rightCaptureCol = col + 1;
                if (rightCaptureCol < 8 && Board[newRow][rightCaptureCol].getPiece() != null
                        && Board[newRow][rightCaptureCol].getPiece().isWhite() != isWhite()) {
                    vp = new Positions(newRow, rightCaptureCol);
                    validPositions.add(vp);
                }
            }
        }
        return validPositions;
    }
}
