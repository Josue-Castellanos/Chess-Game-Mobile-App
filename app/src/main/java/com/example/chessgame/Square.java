package com.example.chessgame;

public class Square {
    private Piece currentPiece;

    // New empty square
    public Square() {
        // Default constructor, square is initially empty
        this.currentPiece = null;
    }

    // New square with piece
    public Square(Piece piece) {
        this.currentPiece = piece;
    }

    public Piece getPiece() {
        return this.currentPiece;
    }

    public void setPiece(Piece piece) {
        this.currentPiece = piece;
    }


    public boolean isEmpty() {
        return currentPiece == null;
    }



}