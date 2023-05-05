/*
            The Position Class
            ------------------
     This class will hold all the information about the location of each piece
     the player has on the game board.
 */

package com.meghana.mythreemensmorris;

public class Positions {
    private int posX;
    private int posY;

    public Positions(int x, int y) {
        posX = x;
        posY = y;
    }

    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

}

