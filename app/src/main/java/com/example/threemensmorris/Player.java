package com.meghana.mythreemensmorris;/*
                Player class
                ------------
      This class will have all the possible function related to how the user
      can interact with the interface while the game is running.
 */

import androidx.annotation.NonNull;

import com.meghana.mythreemensmorris.Positions;


public class Player {
    private String player_name;
    private String player_icon;
    public int player_ID;
    public int numOfPieces;
    private final int score;
    private final Positions[] pieces = new Positions[3];

    Player(){
        this.score = 0;
        this.numOfPieces = 0;
        setPlayerPieces();
    }


    @NonNull
    @Override
    public String toString(){
        return this.player_name;
    }

    // Set up player's information
    public void setInformation(String player_name, String player_icon, int player_ID){
        this.player_name = player_name;
        this.player_icon = player_icon;
        this.player_ID = player_ID;
    }

    /*
    Method: setPlayer_name
            Setter for the player's name
     */
    public void setPlayer_name(String player_name){
        this.player_name = player_name;
    }

//    /*
//    Method: getPlayer_name
//            Getter for the player's name
//     */
//    public String getPlayer_name(){
//        return player_name;
//    }

    /*
    Method: setPlayer_icon
            Getter for the player's icon
     */
    public void setPlayer_icon(String player_icon){
        this.player_icon = player_icon;
    }

    /*
    Method: getPlayer_icon
            Getter for the player's icon
     */
    public String getPlayer_icon(){
        return player_name;
    }

    /*
    Method: setPlayer_ID
            Setter for the player's id
     */
    public void setPlayer_ID(int player_id){
        this.player_ID = player_id;
    }

    /*
    Method: getPlayer_name
            Getter for the player's id
     */
    public int getPlayer_ID(){
        return player_ID;
    }

    /*
    Method: getScore
            Return the player's score
     */
    public int getScore(){
        return score;
    }


    /*
        Method: SetPlayerPieces
            Set the pieces ready for the game. By default, we can set them
            to (-1, -1) just to mean that they aren't on the board yet.
     */

    public void setPlayerPieces(){
        Positions locations;
        for (int i = 0; i < 3; i ++){
            locations = new Positions(-1, -1);
            pieces[i] = locations;
        }
    }

    /*
        Method: getPiece
            This returns the position of a piece on the board
     */
    public Positions getPiece(int pieceNumber){
        return pieces[pieceNumber];
    } //position of the 1,2,3 pieces

    // Place a piece in the board
    public void placePiece(int pieceNumber, int x, int y){
        pieces[pieceNumber].setPosX(x);
        pieces[pieceNumber].setPosY(y);
    }
    public int getPieceNumber(int x, int y) { //piece number
         for(int i = 0; i < 3; i++) {
             if(pieces[i].getPosX()==x && pieces[i].getPosY()==y) {
                 return i;
             }
         }
         return -1;
    }
    // Display the positions of each piece
    public void infos(){
        System.out.println("There are " + numOfPieces + " on board for " + player_name);
        for (int i = 0; i < pieces.length; i ++){
            System.out.println("Piece [" + i + "] = (" + pieces[i].getPosX() + ", " + pieces[i].getPosY() + ")");
        }
    }
}

