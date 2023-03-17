package com.example.memory.pkgActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memory.R;
import com.example.memory.pkgData.Game;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public enum Player {
        PLAYER1,
        PLAYER2
    }

    private Game g = null;

    private TextView textViewCurrentPlayer;

    private TextView textViewPlayer1;

    private TextView textViewPlayer2;

    private Player player;

    private int pairsPlayer1 = 0;

    private int pairsPlayer2 = 0;
    private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6, ivCard7, ivCard8, ivCard9, ivCard10, ivCard11, ivCard12, ivCard13, ivCard14, ivCard15, ivCard16;
    private ArrayList<ImageView> collCards;

    private Button newGame;

    private int cardFlipped;

    Drawable shapeCurrentPlayer = null;

    Drawable shapePlayer1 = null;

    Drawable shapePlayer2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllViews();
        registerEventHandlers();
        initOtherThings();
    }

    protected void getAllViews() {
        textViewCurrentPlayer = findViewById(R.id.textViewCurrentPlayer);
        textViewPlayer1 = findViewById(R.id.textViewPlayerOne);
        textViewPlayer2 = findViewById(R.id.textViewPlayerTwo);
        newGame = findViewById(R.id.newGame);
        ivCard1 = findViewById(R.id.imageViewCard1);
        ivCard2 = findViewById(R.id.imageViewCard2);
        ivCard3 = findViewById(R.id.imageViewCard3);
        ivCard4 = findViewById(R.id.imageViewCard4);
        ivCard5 = findViewById(R.id.imageViewCard5);
        ivCard6 = findViewById(R.id.imageViewCard6);
        ivCard7 = findViewById(R.id.imageViewCard7);
        ivCard8 = findViewById(R.id.imageViewCard8);
        ivCard9 = findViewById(R.id.imageViewCard9);
        ivCard10 = findViewById(R.id.imageViewCard10);
        ivCard11 = findViewById(R.id.imageViewCard11);
        ivCard12 = findViewById(R.id.imageViewCard12);
        ivCard13 = findViewById(R.id.imageViewCard13);
        ivCard14 = findViewById(R.id.imageViewCard14);
        ivCard15 = findViewById(R.id.imageViewCard15);
        ivCard16 = findViewById(R.id.imageViewCard16);
    }

    private void registerEventHandlers() {
        ivCard1.setOnClickListener(this);
        ivCard2.setOnClickListener(this);
        ivCard3.setOnClickListener(this);
        ivCard4.setOnClickListener(this);
        ivCard5.setOnClickListener(this);
        ivCard6.setOnClickListener(this);
        ivCard7.setOnClickListener(this);
        ivCard8.setOnClickListener(this);
        ivCard9.setOnClickListener(this);
        ivCard10.setOnClickListener(this);
        ivCard11.setOnClickListener(this);
        ivCard12.setOnClickListener(this);
        ivCard13.setOnClickListener(this);
        ivCard14.setOnClickListener(this);
        ivCard15.setOnClickListener(this);
        ivCard16.setOnClickListener(this);
        newGame.setOnClickListener(this);
    }

    private void initOtherThings() {
        try {
            g = new Game();
            player = Player.PLAYER1;
            textViewCurrentPlayer.setText(player + " is playing!");
            cardFlipped = -1;
            shapeCurrentPlayer = textViewCurrentPlayer.getBackground();
            shapePlayer1 = textViewPlayer1.getBackground();
            shapePlayer2 = textViewPlayer2.getBackground();
            shapeCurrentPlayer.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            shapePlayer1.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            shapePlayer2.setColorFilter(Color.rgb(127, 255, 0), PorterDuff.Mode.SRC_IN);
            collCards = new ArrayList<>();
            collCards.add(ivCard1);
            collCards.add(ivCard2);
            collCards.add(ivCard3);
            collCards.add(ivCard4);
            collCards.add(ivCard5);
            collCards.add(ivCard6);
            collCards.add(ivCard7);
            collCards.add(ivCard8);
            collCards.add(ivCard9);
            collCards.add(ivCard10);
            collCards.add(ivCard11);
            collCards.add(ivCard12);
            collCards.add(ivCard13);
            collCards.add(ivCard14);
            collCards.add(ivCard15);
            collCards.add(ivCard16);
        } catch (Exception ex) {
            popUpMessage("error:" + ex.getMessage());
        }
    }
    @Override
    public void onClick(View view) {
        if (view.equals(newGame)) {
            g = new Game();
            for(int cardNumber = 0; cardNumber < collCards.size(); cardNumber++) {
                collCards.get(cardNumber).setImageResource(R.drawable.image0);
            }
            pairsPlayer1 = 0;
            pairsPlayer2 = 0;
            player = Player.PLAYER1;
            cardFlipped = -1;
            shapeCurrentPlayer.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            textViewPlayer1.setText("PLAYER1: 0 Pair");
            textViewPlayer2.setText("PLAYER2: 0 Pair");
            textViewCurrentPlayer.setText(player+" is playing!");
        }else {
            for(int cardNumber = 0; cardNumber < collCards.size(); cardNumber++) {
                if(view.equals(collCards.get(cardNumber))) {
                    //if(collCards.get(cardNumber).getDrawable() == (R.drawable.image0))
                    Drawable drawable = collCards.get(cardNumber).getDrawable();
                    Drawable.ConstantState state1 = drawable.getConstantState();
                    Drawable.ConstantState state2 = getResources().getDrawable(R.drawable.image0).getConstantState();
                    if(state1.equals(state2)) {
                        flipCard(cardNumber);

                        if(cardFlipped > -1) {

                            checkMatch(cardFlipped, cardNumber);

                            cardFlipped = -1;
                        } else {
                            cardFlipped = cardNumber;
                        }
                    }}
            }
        }

    }

    private void flipCard(int id) {
        Log.i("test", id + ", " + g.getNthCard(id));
        switch (g.getNthCard(id)) {
            //case 0: collCards.get(id).setImageResource(R.drawable.image0); break;
            case 1: collCards.get(id).setImageResource(R.drawable.image1); break;
            case 2: collCards.get(id).setImageResource(R.drawable.image2); break;
            case 3: collCards.get(id).setImageResource(R.drawable.image3); break;
            case 4: collCards.get(id).setImageResource(R.drawable.image4); break;
            case 5: collCards.get(id).setImageResource(R.drawable.image5); break;
            case 6: collCards.get(id).setImageResource(R.drawable.image6); break;
            case 7: collCards.get(id).setImageResource(R.drawable.image7); break;
            case 8: collCards.get(id).setImageResource(R.drawable.image8); break;
        }
    }

    @SuppressLint("ResourceAsColor")
    private boolean checkMatch(int id1, int id2) {
        if(g.getNthCard(id1) != g.getNthCard(id2)) {
            Log.i("test", "no Match");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    collCards.get(id1).setImageResource(R.drawable.image0);
                    collCards.get(id2).setImageResource(R.drawable.image0);
                }
            }, 1200);
            if(player == player.PLAYER1){
                player = Player.PLAYER2;
                shapeCurrentPlayer.setColorFilter(Color.rgb(127, 255, 0), PorterDuff.Mode.SRC_IN);
            } else {
                player = Player.PLAYER1;
                shapeCurrentPlayer.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }

            textViewCurrentPlayer.setText(player+" is playing!");
            return false;
        } else {
            if(player == Player.PLAYER1){
                pairsPlayer1++;
                textViewPlayer1.setText(player + ": "+pairsPlayer1+ " Pair");
            } else{
                pairsPlayer2++;
                textViewPlayer2.setText(player + ": "+pairsPlayer2+ " Pair");
            }

            Log.i("test", "Match");
            return true;
        }
    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    private void popUpMessage(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}