package com.example.threemensmorris;

import android.os.Message;

import java.util.logging.Handler;

public class Game {
    // Variables
    private int[][] gameboard;

    int handlerInit;

    // Constructor
    Game(Handler mainHandler){
        handlerInit = 0;
        initializeGame();
    }

    // Initialize the game features and board
    private void initializeGame(){
        // Dimensions
        int board_width = 5;
        int board_height = 5;

        gameboard = new int[board_width][board_height];
        for (int i = 0; i < board_width; i ++){
            for (int j = 0; j < board_height; j ++){
                gameboard[i][j] = 0;
            }
        }
    }

    // Use threading to pause between moves
    public void pauseTime(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            // Do nothing.
        }
    }

    // Get the game board
    public int[][] getGameboard(){
        return gameboard;
    }

    // Move pieces around the board
    public void movePiece(Positions oldPos, Positions newPos){
        pauseTime();

        // Get the positions from the current to the next one chosen
        int oldX = oldPos.getPosX();
        int oldY = oldPos.getPosY();
        int newX = newPos.getPosX();
        int newY = newPos.getPosY();

        // Check whether it's a new emplacement
        if (oldX != -1){
            gameboard[oldX][oldX] = 0; // Make it unoccupied
        }

        // Set the player's piece at the new location
        gameboard[newX][newY] = newPos.getPlayerID();

        // Now, this new position become the old or current one
        oldPos.setPosX(newX);
        oldPos.setPosY(newY);
    }

}
