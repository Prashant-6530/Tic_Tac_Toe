package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

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
                TextView tvResults = findViewById(R.id.tvResults);
                Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
                GridLayout gridLayout = findViewById(R.id.gridLayout);

                ImageView counter1 = (ImageView) gridLayout.getChildAt(0);
                ImageView counter2 = (ImageView) gridLayout.getChildAt(1);
                ImageView counter3 = (ImageView) gridLayout.getChildAt(2);
                ImageView counter4 = (ImageView) gridLayout.getChildAt(3);
                ImageView counter5 = (ImageView) gridLayout.getChildAt(4);
                ImageView counter6 = (ImageView) gridLayout.getChildAt(5);
                ImageView counter7 = (ImageView) gridLayout.getChildAt(6);
                ImageView counter8 = (ImageView) gridLayout.getChildAt(7);
                ImageView counter9 = (ImageView) gridLayout.getChildAt(8);

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "Yellow";
                        tvResults.setText(winner + " has won !");
                        tvResults.setVisibility(View.VISIBLE);
                        btnPlayAgain.setVisibility(View.VISIBLE);
                        break;
                    } else {
                        winner = "Red";
                        tvResults.setText(winner + " has won !");
                        tvResults.setVisibility(View.VISIBLE);
                        btnPlayAgain.setVisibility(View.VISIBLE);
                        break;
                    }
                }
                else{
                    if(counter1.getDrawable()!=null && counter2.getDrawable()!=null && counter3.getDrawable()!=null &&
                            counter4.getDrawable()!=null && counter5.getDrawable()!=null &&
                            counter6.getDrawable()!=null && counter7.getDrawable()!=null && counter8.getDrawable()!=null &&
                            counter9.getDrawable()!=null){
                        tvResults.setText("It's a draw !");
                        tvResults.setVisibility(View.VISIBLE);
                        btnPlayAgain.setVisibility(View.VISIBLE);
                    }
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
