package com.example.threemensmorris;

import android.os.Message;

import java.util.logging.Handler;

public class Game {
    // Variables
    private static int[][] gameBoard;

    public static int currentPlayer;

    // Constructor
    Game(Handler mainHandler){
        int handlerInit = 0;
        initializeGame();
    }

    // Initialize the game features and board
    private void initializeGame(){
        // Dimensions
        int board_width = 3;
        int board_height = 3;

        gameBoard = new int[board_width][board_height];
        for (int i = 0; i < board_width; i ++){
            for (int j = 0; j < board_height; j ++){
                gameBoard[i][j] = 0;
            }
        }
    }

    // Use threading to pause between moves
    public static void pauseTime(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            // Do nothing.
        }
    }

    // Get the game board
    public static int[][] get_gameBoard(){
        return gameBoard;
    }

    // Move pieces around the board
    public static void movePiece(Positions oldPos, Positions newPos){
        pauseTime();

        // Get the positions from the current to the next one chosen
        int oldX = oldPos.getPosX();
        int oldY = oldPos.getPosY();
        int newX = newPos.getPosX();
        int newY = newPos.getPosY();

        // Check whether it's a new emplacement
        if (oldX != -1){
            gameBoard[oldX][oldX] = 0; // Make it unoccupied
        }

        // Set the player's piece at the new location
        gameBoard[newX][newY] = newPos.getPlayerID();

        // Now, this new position become the old or current one
        oldPos.setPosX(newX);
        oldPos.setPosY(newY);
    }

}
