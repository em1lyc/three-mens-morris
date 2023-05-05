package com.meghana.mythreemensmorris;

import android.os.Handler;
import android.os.Message;
import java.util.Scanner;

public class Game {
    // Variables
    public static int[][] gameBoard; //make it public so after each move, you can update gameboard layout
    public static Player[] players;
    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private final int EMPTY = 0;
    private int TURN = 0;
    public boolean haswinner = false;
    private static Handler mainHandler;
    public static int handlerInit;


    // Constructor
    Game(){
        initGameBoard();
        TURN = 0;
    }

    // Run method

    public void run(String name1, String name2){
        if(name1.equals("")) {
            name1 = "Player1";
        }
        if(name2.equals("")) {
            name2 = "Player2";
        }
        // Set players
        Player player1 = new Player();
        player1.setInformation(name1, "X", 1);
        Player player2 = new Player();
        player2.setInformation(name2, "O", 2);

        players = new Player[2];
        players[0] = player1;
        players[1] = player2;

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Three - Men - Morris");
        System.out.println("-------------------------------");
        boolean gameOn = true;

        // Place the pieces first
        while (gameOn) {
            int turn = 0;
            while (turn != 2) {
                display();

                // place the pieces
                while(true) {

                    System.out.println("\nPlayer " + turn + ": " + players[turn]);
                    System.out.print("\tEnter the x-coord: ");
                    int x = input.nextInt();

                    System.out.print("\tEnter the y-coord: ");
                    int y = input.nextInt();

                    System.out.print("\tEnter the piece: ");
                    int pieceNum = input.nextInt();
                    Positions location = players[turn].getPiece(pieceNum);
                    if (makeMove(players[turn], pieceNum, x, y)) {
                        // Check
                        System.out.println("\nDebug");
                        System.out.println("------");
                        System.out.println("Player ID: " + players[turn].player_ID);
                        System.out.println("Player Location: (x = " + location.getPosX() + "; y = " + location.getPosY() + ")");
                        System.out.println("Piece on board: " + pieceNum);
                        players[turn].infos();

                        // What's meant to be there
                        if(checkWinner(players[turn])){
                            gameOn = false;
                            break;
                        }
                        break;
                    } else {
                        System.out.println("\n\nThis is not a valid move");
                    }
                }

                System.out.println("\n------------------------------");
                turn ++;
            }
        }
        System.out.println("End of the Game! There was a winner");
    }

    // Display game in the terminal
    public void display(){
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < 3; j ++){
                if (j == 2) {
                    System.out.print(gameBoard[i][j]);
                } else {
                    System.out.print(gameBoard[i][j] + "  ---  ");
                }
            }
            System.out.println("\n");
        }
    }

    /*
    Method: InitGameBoard
            Initialize the gameBoard in the format:
                        0 --- 0 --- 0
                        0 --- 0 --- 0
                        0 --- 0 --- 0
            By default, 0 means that the places are free
     */
    public void initGameBoard(){
        // Set the board
        gameBoard = new int [WIDTH][HEIGHT];

        for (int i = 0; i < WIDTH; i ++){
            for (int j = 0; j < HEIGHT; j ++){
                gameBoard[i][j] = EMPTY;
            }
        }
    }

    /*
    method: isValid
            Check whether the current position and the next position to
            see if it's a legal move. The possible moves for one piece
            are the ones adjacent to that piece.
     */
    public boolean isValid(int oldX, int oldY, int newX, int newY){
        // Check the adjacent positions
        if (Math.abs(oldX - newX) <= 1 && Math.abs(oldY - newY) <= 1) {
            // Positions are adjacent
            return true;
        } else if (Math.abs(oldX - newX) == 1 && Math.abs(oldY - newY) == 1) {
            // Positions are diagonal
            return true;
        }

        // Positions are not adjacent or diagonal
        return false;
    }

    /*
    Method: makeMove
            Make the player move on the game board based
            on their player ID (or Icon in future changes)
     */
    public boolean makeMoveMain(Player player, int old_x, int old_y, int x, int y){ //if true, then move is made, old_x and old_y can be -1
        // Place the pieces on the board first
        if(checkWinner(players[0]) || checkWinner(players[1])) {
            System.out.println("THERES A WIN!");
            haswinner = true;
            return true;

        }
        int pieceNumber = player.getPieceNumber(old_x,old_y); // if -1, that means that you just placed one. else you slide one

        if (player.numOfPieces < 3){
            // Invalid case
            if (gameBoard[y][x] != 0) { //something is already there
                return false;
            }

            // Set the new position
            gameBoard[y][x] = player.player_ID;
            player.placePiece(pieceNumber, x ,y); //what is piecenumber - piece
            player.numOfPieces ++;
            if(checkWinner(players[0]) || checkWinner(players[1])) {
                System.out.println("THERES A WIN!");
                haswinner = true;
                return true;
            }
            return true;
        }
        //return false;
        // Now, after putting all the pieces, we can make moves around the board
        if(gameBoard[y][x]!=0) {
            return false;
        }
        if(gameBoard[old_y][old_x]==0) {
            return false;
        }
        if(pieceNumber==-1) {
            return false;
        }
        Positions piece = player.getPiece(pieceNumber);
        int pieceX = piece.getPosX();
        int pieceY = piece.getPosY();
        gameBoard[old_y][old_x] = EMPTY; // Free that spot - gameboard is x then y

        // Find the move accordingly
        //if (isValid(old_x, old_y, x, y)){
            piece.setPosX(x);
            piece.setPosY(y);
            gameBoard[y][x] = player.player_ID;
            //return true;
        //}
        if(checkWinner(players[0]) || checkWinner(players[1])) {
            System.out.println("THERES A WIN!");
            haswinner = true;
            return true;
        }
        return true;


    }
    public boolean makeMove(Player player, int pieceNumber, int x, int y){ //if true, then move is made
        // Place the pieces on the board first
        if (player.numOfPieces < 3){
            // Invalid case
            if (gameBoard[y][x] != 0) { //something is already there
                return false;
            }

            // Set the new position
            gameBoard[y][x] = player.player_ID;
            player.placePiece(pieceNumber, x ,y); //what is piecenumber - piece
            player.numOfPieces ++;
            return true;
        }

        // Now, after putting all the pieces, we can make moves around the board
        Positions piece = player.getPiece(pieceNumber);
        int pieceX = piece.getPosX();
        int pieceY = piece.getPosY();
        gameBoard[pieceY][pieceX] = EMPTY; // Free that spot

        // Find the move accordingly
        if (isValid(pieceX, pieceY, x, y)){
            piece.setPosX(x);
            piece.setPosY(y);
            gameBoard[y][x] = player.player_ID;
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
