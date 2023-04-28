package com.example.threemensmorris;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private static int[][] gameBoard;

    int view = R.layout.overall_layout;
    private static Player[] players;
    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private final int EMPTY = 0;

    private static Handler mainHandler;
    public static int handlerInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overall_layout);
        Game game = new Game(R.layout.overall_layout);
        // Set players
        Player player1 = new Player();
        player1.setInformation("Daiki", "X", 1);
        Player player2 = new Player();
        player2.setInformation("Ryota", "O", 2);

        players = new Player[2];
        players[0] = player1;
        players[1] = player2;

        boolean gameOn = true;

        // Place the pieces first
        while (gameOn) {
            int turn = 0;
            while (turn != 2) {
                game.display();

                // place the pieces
                while(true) {

                    int x = input.nextInt();

                    System.out.print("\tEnter the y-coord: ");
                    int y = input.nextInt();

                    System.out.print("\tEnter the piece: ");
                    int pieceNum = input.nextInt();
                    Positions location = players[turn].getPiece(pieceNum);
                    RelativeLayout position_test = findViewById(R.id.blank_circle00);
                    int finalTurn = turn;
                    position_test.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            game.makeMove(players[finalTurn], pieceNum, x, y);
                            if (players[finalTurn].player_ID == 1) {
                                position_test.setBackground(Drawable.createFromPath("@drawable/player1_piece"));
                            }
                        }
                    });
                    if (game.makeMove(players[turn], pieceNum, x, y)) {

                        // Check
                        System.out.println("\nDebug");
                        System.out.println("------");
                        System.out.println("Player ID: " + players[turn].player_ID);
                        System.out.println("Player Location: (x = " + location.getPosX() + "; y = " + location.getPosY() + ")");
                        System.out.println("Piece on board: " + pieceNum);
                        players[turn].infos();

                        // What's meant to be there
                        if(game.checkWinner(players[turn])){
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
}