package com.example.threemensmorris;

public class Positions {
    // Variables
    private int playerID;
    private int posX;
    private int posY;

    // Constructor
    Positions(int player_ID, int x, int y){
        playerID = player_ID;
        posX = x;
        posY = y;
    }

    // Setters
    public void setPlayerID(int player_ID){
        this.playerID = player_ID;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    // Getters
    int getPlayerID(){
        return playerID;
    }

    int getPosX(){
        return posX;
    }

    int getPosY(){
        return posY;
    }
}
