package com.example.chessgame;

import com.example.chessgame.Positions;
import com.example.chessgame.Square;
import java.util.ArrayList;

public abstract class Piece {

    private final boolean isWhite; // Indicates the color of the piece
    private final int imageResourceId; // Resource ID for the piece image

    public Piece(boolean isWhite, int imageResourceId) {
        this.isWhite = isWhite;
        this.imageResourceId = imageResourceId;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public ArrayList<Positions> ValidPositions(Positions position, Square[][] Board) {
        ArrayList<Positions> validPositions = new ArrayList<>();
        Positions validPosition;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                validPosition = new Positions(i, j);
                validPositions.add(validPosition);
            }
        }
        return validPositions;
    }
}
