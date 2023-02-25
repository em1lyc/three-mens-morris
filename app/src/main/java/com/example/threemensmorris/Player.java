package com.example.threemensmorris;

import java.util.Random;

public class Player {
    // Set variables
    private String username;
    private String player_image;  // For the image
    private Positions[] pieces = null; // This is for the player.
    private int numberOfPieces = 0;

    // Track the rows used
    int[][] playerBoard;

    // Functions
    // Initialize the locations to zero
    public void setPlayerBoard(){
        playerBoard = new int[3][3];
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j ++){
                playerBoard[i][j] = 0;
            }
        }
    }

    // Place the piece on the board
    private void placePiece(){
        pieces[numberOfPieces] = randomLocation();
        Game.movePiece(new Positions(Game.currentPlayer, -1, -1), pieces[numberOfPieces]);
        numberOfPieces ++;
    }


    // Player moves the pieces
    public void makeMove(){
        // Check if we didn't use all the 3 pieces yet
        if (numberOfPieces < 3){
            if (pieces == null){
                pieces = new Positions[3];
            }
            placePiece();
            return;
        }

        // If we move a random piece to a random location
        int pieceNum = new Random().nextInt(3);
        Positions location = randomLocation();

        // Move accordingly
        Game.movePiece(pieces[pieceNum], location);
    }

    // Helper to generate a random piece
    private Positions randomLocation(){
        int posX = new Random().nextInt(3);
        int posY = new Random().nextInt(3);

        // Check if the point in the board is free (without) any pieces
        while(!((Game.get_gameBoard()[posX][posY]) == 0)){
            posX = new Random().nextInt(3);
            posY = new Random().nextInt(3);
        }
        return new Positions(Game.currentPlayer, posX, posY);
    }

}
