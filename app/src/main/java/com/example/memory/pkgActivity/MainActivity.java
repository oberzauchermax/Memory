package com.example.memory.pkgActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.memory.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public enum Player {
        PLAYER1,
        PLAYER2
    }

    private Database db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllViews();
        registerEventHandlers();
        initOtherThings();
    }

    protected void getAllViews() {
        btn1stDicing = findViewById(R.id.button1stDicing);
    }

    private void registerEventHandlers() {
        btn1stDicing.setOnClickListener(this);
    }

    private void initOtherThings() {
        try {
            db = Database.getInstance();
            collDices = new ArrayList<>();
            games = new ArrayList<String>();
            adapterGames = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, games);
            collDices.add(ivDice1);
            collDices.add(ivDice2);
            collDices.add(ivDice3);
            collDices.add(ivDice4);
            collDices.add(ivDice5);
            gameState = GameState.FIRST_DICING;
            collGameSets = new ArrayList<>();
            createGameSet();
        } catch (Exception ex) {
            popUpMessage("error:" + ex.getMessage());
        }
    }

    private void popUpMessage(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}