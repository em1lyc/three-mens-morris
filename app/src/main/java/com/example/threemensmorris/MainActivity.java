package com.meghana.mythreemensmorris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    }
    public void multiClick(View v) {
        Intent multiActivityIntent = new Intent(this, Multiplayer.class);
        startActivity(multiActivityIntent);
    }
    public void aiClick(View v) {
        Intent AIActivityIntent = new Intent(this, AIPlayer.class);
        startActivity(AIActivityIntent);
    }
}