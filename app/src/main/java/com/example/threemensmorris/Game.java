package com.example.threemensmorris;

import android.os.Message;
import android.os.Handler;

public class Game {
    // Variables
    private static int[][] gameBoard;
    private static Player[] players;
    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private final int EMPTY = 0;

    private static Handler mainHandler;
    public static int handlerInit;

    // Constructor
    Game(Handler mainHandler) {
        int handlerInit = 0;
        initializeGame();
    }

    // Initialize the game features and board
    private void initializeGame() {
        gameBoard = new int [WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i ++){
            for (int j = 0; j < HEIGHT; j ++){
                gameBoard[i][j] = EMPTY;
            }
        }
    }

    // Use threading to pause between moves
    public static void pauseTime() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Do nothing.
        }
    }

    /*
    Method: get_gameBoard
            To return the gameBoard we just created
     */
    public static int[][] get_gameBoard() {
        return gameBoard;
    }

    /*
    Method: placePiece
            For players to place their pieces on the board
     */
    public void placePiece(Player player, int pieceNumber, int x, int y) {
        // Check the count of pieces that the player has on board
        if (player.numOfPieces < 3){
            // Place it in an empty spot
            if (gameBoard[x][y] > 0) {
                return;
            }

            // Set the new position
            gameBoard[x][y] = player.player_ID;
            player.placePiece(pieceNumber, x ,y);
            player.numOfPieces ++;
        }
    }

    /*
   Method: makeMove
           Make the player move on the game board based
           on their player ID (or Icon in future changes)
    */
    public boolean makeMove(Player player, int pieceNumber, int x, int y){
        // Set the previous place empty
        Positions piece = player.getPiece(pieceNumber);
        int pieceX = piece.getPosX();
        int pieceY = piece.getPosY();
        gameBoard[pieceX][pieceY] = EMPTY; // Free that spot

        // Find the move accordingly
        if (gameBoard[x][y] == 0){
            piece.setPosX(x);
            piece.setPosY(y);
            gameBoard[x][y] = player.player_ID;
            return true;
        }
        return false;
    }

    /*
    Method: checkWinner
            Find the possible combination to check the winner.
     */
    public boolean checkWinner(Player player){
        // Check horizontally
        for (int i = 0; i < WIDTH; i ++){
            if (gameBoard[i][0] == player.player_ID &&
                    gameBoard[i][1] == player.player_ID &&
                    gameBoard[i][2] == player.player_ID){
                return true;
            }
        }

        // Check vertically
        for (int i = 0; i < HEIGHT; i ++){
            if (gameBoard[0][i] == player.player_ID &&
                    gameBoard[1][i] == player.player_ID &&
                    gameBoard[2][i] == player.player_ID){
                return true;
            }
        }

        // Check diagonally
        if (gameBoard[0][0] == player.player_ID &&
                gameBoard[1][1] == player.player_ID &&
                gameBoard[2][2] == player.player_ID){
            return true;
        }
        if (gameBoard[0][2] == player.player_ID &&
                gameBoard[1][1] == player.player_ID &&
                gameBoard[2][0] == player.player_ID) {
            return true;
        }
        return false;
    }
}

    // Move pieces around the board
//    public static void movePiece(Positions oldPos, Positions newPos){
//        pauseTime();
//
//        // Get the positions from the current to the next one chosen
//        int oldX = oldPos.getPosX();
//        int oldY = oldPos.getPosY();
//        int newX = newPos.getPosX();
//        int newY = newPos.getPosY();
//
//        // Check whether it's a new emplacement
//        if (oldX != -1){
//            gameBoard[oldX][oldX] = 0; // Make it unoccupied
//        }
//
//        // Set the player's piece at the new location
//        gameBoard[newX][newY] = newPos.getPlayerID();
//
//        // Now, this new position become the old or current one
//        oldPos.setPosX(newX);
//        oldPos.setPosY(newY);
//    }
//
//}
