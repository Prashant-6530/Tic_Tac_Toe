package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";
    boolean gameActive = true;
    int activePlayer = 0;
    //0 - Yellow, 1 - Red, 2 - Empty;
    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    TextView tvResults = findViewById(R.id.tvResults);
                    Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
                    tvResults.setText(winner + " has won !");
                    tvResults.setVisibility(View.VISIBLE);
                    btnPlayAgain.setVisibility(View.VISIBLE);
                    //Toast.makeText(this, winner + " has won !", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void playAgain(View view){
        TextView tvResults = findViewById(R.id.tvResults);
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
        tvResults.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        Arrays.fill(gameState, 2);
        gameActive = true;
        activePlayer = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}