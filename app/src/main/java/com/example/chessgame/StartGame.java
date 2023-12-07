package com.example.chessgame;

import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Base64;
// Working On:
// En Passant Rule -
// Castling Rule - Must be the first move for Rook and King pieces
// Checkmate rules - If moving a piece cannot result in your king to be checked
//                 - If king is checked the king needs to uncheck or a piece has to hide king to uncheck
//                 - If no moves can be made to uncheck king then opponent wins
// Pawning Rule - If pawn reaches opposite side of board the pawn can become a Queen, Bishop, Rook, or Knight.
// Count Down timer - A limit of 10 minutes per player, time is added after every move
// Move counter - How many moves does it take to win
// Player Names
// Current Issues:
// checkOpponentAttack - This function is not highlighting the square of attacking pieces
public class StartGame extends AppCompatActivity implements View.OnClickListener {
    VideoView videoView;
    String kingVideoPath;
    Uri uri;
    TextView endGameView;
    Positions checkPosition = new Positions(0, 0);
    Square[][] Board = new Square[8][8];
    Square[][] CheckBoard = new Square[8][8];
    TextView[][] DisplayBoard = new TextView[8][8];
    TextView[][] DisplayBoardValidSquares = new TextView[8][8];
    ArrayList<Positions> listOfPositions = new ArrayList<>();
    ArrayList<King> listOfKings = new ArrayList<>();
    boolean isFirstPlayersTurn;
    boolean isFirstPosition;
    boolean isKingChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        endGameView = findViewById(R.id.endGame);
        endGameView.setVisibility(View.GONE);
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(null);
        kingVideoPath = "android.resource://" + getPackageName() + "/" + R.raw.king_video1;
        uri = Uri.parse(kingVideoPath);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        initializeBoard();

    }

    private void initializeBoard() {
        // Initialize pieces as a chess board
        Piece[][] pieces = {
                // Black pieces
                {new Rook(false, R.drawable.skulls_rook_black, true), new Knight(false, R.drawable.skulls_knight_black), new Bishop(false, R.drawable.skulls_bishop_black), new Queen(false, R.drawable.skulls_queen_black), new King(false, R.drawable.skulls_king_black, true), new Bishop(false, R.drawable.skulls_bishop_black), new Knight(false, R.drawable.skulls_knight_black), new Rook(false, R.drawable.skulls_rook_black, true)},
                {new Pawn(false, R.drawable.skulls_pawn_black, true), new Pawn(false, R.drawable.skulls_pawn_black, true), new Pawn(false, R.drawable.skulls_pawn_black, true), new Pawn(false, R.drawable.skulls_pawn_black, true), new Pawn(false, R.drawable.skulls_pawn_black, true), new Pawn(false, R.drawable.skulls_pawn_black, true), new Pawn(false, R.drawable.skulls_pawn_black, true), new Pawn(false, R.drawable.skulls_pawn_black, true)},
                // empty squares
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                // White pieces
                {new Pawn(true, R.drawable.skulls_pawn_white, true), new Pawn(true, R.drawable.skulls_pawn_white, true), new Pawn(true, R.drawable.skulls_pawn_white, true), new Pawn(true, R.drawable.skulls_pawn_white, true), new Pawn(true, R.drawable.skulls_pawn_white, true), new Pawn(true, R.drawable.skulls_pawn_white, true), new Pawn(true, R.drawable.skulls_pawn_white, true), new Pawn(true, R.drawable.skulls_pawn_white, true)},
                {new Rook(true, R.drawable.skulls_rook_white, true), new Knight(true, R.drawable.skulls_knight_white), new Bishop(true, R.drawable.skulls_bishop_white), new Queen(true, R.drawable.skulls_queen_white), new King(true, R.drawable.skulls_king_white, true), new Bishop(true, R.drawable.skulls_bishop_white), new Knight(true, R.drawable.skulls_knight_white), new Rook(true, R.drawable.skulls_rook_white, true)}
        };

        // Initialize all square pieces on boards
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Board[i][j] = new Square(pieces[i][j]);
                if (Board[i][j].getPiece() == null) {
                    CheckBoard[i][j] = new Square();
                } else {
                    CheckBoard[i][j] = new Square(Board[i][j].getPiece());
                }
            }
        }

        // Initialize boards to GridLayouts
        DisplayBoard = new TextView[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int gridIds = getResources().getIdentifier("rc" + i + j, "id", getPackageName());
                int resId = getResources().getIdentifier("r" + i + j, "id", getPackageName());
                DisplayBoard[i][j] = (TextView) findViewById(resId);
                DisplayBoardValidSquares[i][j] = (TextView) findViewById(gridIds);
            }
        }
        // Set the images of chess pieces on the board
        // corresponding to the piece set on each square
        setBoard();
    }

    private void setBoard() {
        // Display the chess pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = Board[i][j].getPiece();
                if (p != null) {
                    DisplayBoard[i][j].setBackgroundResource(p.getImageResourceId());
                }
            }
        }
        King king1 = ((King)Board[7][4].getPiece());
        King king2 = ((King)Board[0][4].getPiece());
        // Article 1.2 of the Rules of Play says “the player with the light pieces (white)
        // makes the first move, then the players move alternatively, with the player with
        // the dark-coloured pieces, making the next move”.
        isFirstPlayersTurn = true;
        isFirstPosition = true;
    }

    @Override
    public void onClick(@NonNull View v) {
        int viewId = v.getId();
        // Convert the view ID to a string
        String viewIdString = getResources().getResourceEntryName(viewId);
        // Exclude the first character, the first character is a letter
        String numericPartString = viewIdString.substring(1);
        // Now numericPartString contains the numeric part of the ID
        int numericPart = Integer.parseInt(numericPartString);
        // Extract the first and second digits
        int row = numericPart / 10; // Second number is Row
        int col = numericPart % 10;  // First number is Column

        // Create a position to check
        checkPosition = new Positions(row, col);

        // If the first position chosen is empty, ignore the click
        if (isFirstPosition && Board[checkPosition.getRow()][checkPosition.getCol()].getPiece() == null) {
            return;
        }

        // PLAYER 1
        // If its player 1 turn, if its the first move of a player 1, and if the piece is white
        if (isFirstPlayersTurn && isFirstPosition && Board[checkPosition.getRow()][checkPosition.getCol()].getPiece().isWhite()) {
            isKingChecked();
            // save firstPosition, then save all valid positions for piece to move
            listOfPositions.add(checkPosition);
            if (Board[checkPosition.getRow()][checkPosition.getCol()].getPiece() instanceof Pawn && checkPosition.getRow() == 6) {
                ((Pawn) Board[checkPosition.getRow()][checkPosition.getCol()].getPiece()).isFirstMove = true;
            }
            listOfPositions.addAll(Board[checkPosition.getRow()][checkPosition.getCol()].getPiece().ValidPositions(checkPosition, Board));
            // Show the valid squares on the board
            for (Positions position : listOfPositions) {
                int validRow = position.getRow();
                int validCol = position.getCol();
                // Store the color resource ID in the tag
                DisplayBoardValidSquares[validRow][validCol].setBackgroundResource(R.color.validSquareColor);
            }
            isFirstPosition = false;
        } else if (isFirstPlayersTurn && !isFirstPosition) {
            // check if position chosen is valid
            int backgroundColor = ((ColorDrawable) DisplayBoardValidSquares[checkPosition.getRow()][checkPosition.getCol()].getBackground()).getColor();
            if (backgroundColor == ContextCompat.getColor(this, R.color.validSquareColor)) {
                // reset board
                for (Positions position : listOfPositions) {
                    int validRow = position.getRow();
                    int validCol = position.getCol();

                    DisplayBoardValidSquares[validRow][validCol].setBackgroundColor(getResources().getColor(getResources().getIdentifier("rc" + validRow + validCol, "color", getPackageName()), getTheme()));
                }

                Positions firstPosition = listOfPositions.get(0);
                Positions lastPosition = new Positions(row, col);
                if (firstPosition.getRow() == lastPosition.getRow() && firstPosition.getCol() == lastPosition.getCol()) {
                    if (Board[checkPosition.getRow()][checkPosition.getCol()].getPiece() instanceof Pawn && checkPosition.getRow() == 6) {
                        ((Pawn) Board[checkPosition.getRow()][checkPosition.getCol()].getPiece()).isFirstMove = true;
                    }
                    isFirstPosition = true;
                    isFirstPlayersTurn = true;
                    listOfPositions.clear();
                } else {
                    movePieces(firstPosition, lastPosition);
                    listOfPositions.clear();
                }
            }
        }
        // PLAYER 2
        if (!isFirstPlayersTurn && isFirstPosition && !Board[checkPosition.getRow()][checkPosition.getCol()].getPiece().isWhite()) {
            isKingChecked();
            // save firstPosition, then save all valid positions for piece to move
            listOfPositions.add(checkPosition);
            if (Board[checkPosition.getRow()][checkPosition.getCol()].getPiece() instanceof Pawn && checkPosition.getRow() == 1) {
                ((Pawn) Board[checkPosition.getRow()][checkPosition.getCol()].getPiece()).isFirstMove = true;
            }
            listOfPositions.addAll(Board[checkPosition.getRow()][checkPosition.getCol()].getPiece().ValidPositions(checkPosition, Board));
            // Show the valid squares on the board
            for (Positions position : listOfPositions) {
                int validRow = position.getRow();
                int validCol = position.getCol();
                // Store the color resource ID in the tag
                DisplayBoardValidSquares[validRow][validCol].setBackgroundResource(R.color.validSquareColor);
            }
            isFirstPosition = false;
        } else if (!isFirstPlayersTurn && !isFirstPosition) {
            // check if position chosen is valid
            int backgroundColor = ((ColorDrawable) DisplayBoardValidSquares[checkPosition.getRow()][checkPosition.getCol()].getBackground()).getColor();
            if (backgroundColor == ContextCompat.getColor(this, R.color.validSquareColor)) {
                // reset board
                for (Positions position : listOfPositions) {
                    int validRow = position.getRow();
                    int validCol = position.getCol();

                    DisplayBoardValidSquares[validRow][validCol].setBackgroundColor(getResources().getColor(getResources().getIdentifier("rc" + validRow + validCol, "color", getPackageName()), getTheme()));
                }
                // get saved firstPosition from list
                Positions firstPosition = listOfPositions.get(0);
                Positions lastPosition = new Positions(row, col);
                if (firstPosition.getRow() == lastPosition.getRow() && firstPosition.getCol() == lastPosition.getCol()) {
                    if (Board[checkPosition.getRow()][checkPosition.getCol()].getPiece() instanceof Pawn && checkPosition.getRow() == 1) {
                        ((Pawn) Board[checkPosition.getRow()][checkPosition.getCol()].getPiece()).isFirstMove = true;
                    }
                    isFirstPosition = true;
                    isFirstPlayersTurn = false;
                    listOfPositions.clear();
                } else {
                    movePieces(firstPosition, lastPosition);
                    listOfPositions.clear();
                }
            }
        }
    }

    private void movePieces(@NonNull Positions firstPosition, Positions lastPosition) {
        //First Players Turn - White Pieces
        // If first position is white piece and its first players turn
        if (Board[firstPosition.getRow()][firstPosition.getCol()].getPiece().isWhite() && isFirstPlayersTurn) {
            // If the first position has a piece and the second position is empty
            if (Board[lastPosition.getRow()][lastPosition.getCol()].getPiece() == null) {
                Board[lastPosition.getRow()][lastPosition.getCol()].setPiece(Board[firstPosition.getRow()][firstPosition.getCol()].getPiece());
                Board[firstPosition.getRow()][firstPosition.getCol()].setPiece(null);
                DisplayBoard[firstPosition.getRow()][firstPosition.getCol()].setBackgroundResource(0);
                DisplayBoard[lastPosition.getRow()][lastPosition.getCol()].setBackgroundResource(Board[lastPosition.getRow()][lastPosition.getCol()].getPiece().getImageResourceId());
            }
            // If the first position piece and the second position piece do not have matching color
            else if (Board[lastPosition.getRow()][lastPosition.getCol()].getPiece().isWhite() != Board[firstPosition.getRow()][firstPosition.getCol()].getPiece().isWhite()) {
                Board[lastPosition.getRow()][lastPosition.getCol()].setPiece(Board[firstPosition.getRow()][firstPosition.getCol()].getPiece());
                Board[firstPosition.getRow()][firstPosition.getCol()].setPiece(null);
                DisplayBoard[firstPosition.getRow()][firstPosition.getCol()].setBackgroundResource(0);
                DisplayBoard[lastPosition.getRow()][lastPosition.getCol()].setBackgroundResource(Board[lastPosition.getRow()][lastPosition.getCol()].getPiece().getImageResourceId());
            }
            isFirstPlayersTurn = false;
        }
        // Second Players Turn - Black Pieces
        // If first position is black and not first player turn
        else if (!Board[firstPosition.getRow()][firstPosition.getCol()].getPiece().isWhite() && !isFirstPlayersTurn) {
            // If the first position has a piece and the second position is empty
            if (Board[lastPosition.getRow()][lastPosition.getCol()].getPiece() == null) {
                Board[lastPosition.getRow()][lastPosition.getCol()].setPiece(Board[firstPosition.getRow()][firstPosition.getCol()].getPiece());
                Board[firstPosition.getRow()][firstPosition.getCol()].setPiece(null);
                DisplayBoard[firstPosition.getRow()][firstPosition.getCol()].setBackgroundResource(0);
                DisplayBoard[lastPosition.getRow()][lastPosition.getCol()].setBackgroundResource(Board[lastPosition.getRow()][lastPosition.getCol()].getPiece().getImageResourceId());
            }
            // If the first position piece and the second position piece do not have matching color
            else if (Board[lastPosition.getRow()][lastPosition.getCol()].getPiece().isWhite() != Board[firstPosition.getRow()][firstPosition.getCol()].getPiece().isWhite()) {
                Board[lastPosition.getRow()][lastPosition.getCol()].setPiece(Board[firstPosition.getRow()][firstPosition.getCol()].getPiece());
                Board[firstPosition.getRow()][firstPosition.getCol()].setPiece(null);
                DisplayBoard[firstPosition.getRow()][firstPosition.getCol()].setBackgroundResource(0);
                DisplayBoard[lastPosition.getRow()][lastPosition.getCol()].setBackgroundResource(Board[lastPosition.getRow()][lastPosition.getCol()].getPiece().getImageResourceId());
            }
            isFirstPlayersTurn = true;
        }
        isFirstPosition = true;
        isKingChecked();
        if (isKingChecked) {
            checkBoardForKing();
        }
    }

    private void checkBoardForKing() {
        int kingCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board[i][j].getPiece() instanceof King && Board[i][j].getPiece().isWhite()) {
                    kingCount += 1;
                }
                if (Board[i][j].getPiece() instanceof King && !Board[i][j].getPiece().isWhite()) {
                    kingCount += 2;
                }
            }
        }
        switch (kingCount) {
            case 1:
                // Player one is the winner
                endGameView.setText("PLAYER 1 WINS");
                endGameView.setVisibility(View.VISIBLE);
                break;
            case 2:
                // Player 2 is the winner
                endGameView.setText("PLAYER 2 WINS");
                endGameView.setVisibility(View.VISIBLE);
                break;
        }
        endGameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Indicate to players that their King piece is checked by an opposing players piece.
    private void isKingChecked() {
        ArrayList<Positions> List = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board[i][j].getPiece() != null) {
                    List.clear();
                    Positions p = new Positions(i, j);
                    List.addAll(Board[i][j].getPiece().ValidPositions(p, Board));
                    for (int m = 0; m < List.size(); m++) {
                        Positions move = List.get(m);
                        if (Board[List.get(m).getRow()][List.get(m).getCol()].getPiece() instanceof King) {

                            if ((List.get(m).getRow() + List.get(m).getCol()) % 2 == 0) {
                                DisplayBoardValidSquares[List.get(m).getRow()][List.get(m).getCol()].setBackgroundResource(R.color.rc01);
                            } else {
                                DisplayBoardValidSquares[List.get(m).getRow()][List.get(m).getCol()].setBackgroundResource(R.color.white);
                            }

                            if (Board[i][j].getPiece().isWhite() != Board[List.get(m).getRow()][List.get(m).getCol()].getPiece().isWhite()) {
                                DisplayBoardValidSquares[List.get(m).getRow()][List.get(m).getCol()].setBackgroundResource(R.color.kingCheckedColor);
                                isKingChecked = true;
                            }
                        }
                        // Check if any opponent's pieces can attack the king
                        checkOpponentAttack(p, move);
                    }
                    // Iterate through the king's allowed moves
//                    for (Positions move : List) {
//                        // Check if any opponent's pieces can attack the king
//                        checkOpponentAttack(p, move);
//                    }
                }
            }
        }
    }


    private void checkOpponentAttack(Positions p, Positions move) {
        // Iterate through the board to check for opponent's pieces
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (Board[x][y].getPiece() != null && Board[x][y].getPiece().isWhite() != Board[p.getRow()][p.getCol()].getPiece().isWhite()) {
                    ArrayList<Positions> opponentMoves = Board[x][y].getPiece().ValidPositions(new Positions(x, y), Board);
                    if (opponentMoves.contains(move)) {
                        // The king is in check, highlight the attacking square
                        DisplayBoardValidSquares[move.getRow()][move.getCol()].setBackgroundResource(R.color.attackingSquareColor);
                    }
                }
            }
        }
    }
    @Override
    protected void onResume() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        videoView.resume();
        super.onResume();
    }
    @Override
    protected void onPause() {
        videoView.suspend();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        videoView.stopPlayback();
        super.onDestroy();
    }
}
