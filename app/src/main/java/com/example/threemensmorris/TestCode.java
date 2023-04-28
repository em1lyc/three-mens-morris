package com.example.threemensmorris;

/*
                    TestCode
                    --------
        This is a small class used to check the algorithm and everything from Game.
        It allows testers to check certain behaviors of the code and is runnable
        directly with IntelliJ or any other Java editor. I doubt Android studio would
        run it.
 */
public class TestCode {
    public static void main(String[] args) {

        Game newGame = new Game(R.layout.overall_layout);
        newGame.run();
    }
}
