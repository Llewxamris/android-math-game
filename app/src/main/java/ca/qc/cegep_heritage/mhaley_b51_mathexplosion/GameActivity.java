package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Equation equation;
    private int lives = 3;
    private long time;
    private long currTime;
    private int score = 0;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            loseLife();
            SharedPreferences sharedPrefs = GameActivity.this.getSharedPreferences("options", Context.MODE_PRIVATE);
            showNewEquation(sharedPrefs.getString("operators","+"),
                    sharedPrefs.getInt("operations", 1));
            currTime = time;
            timerHandler.postDelayed(this, time);
        }
    };

    Handler progressBarHandler = new Handler();
    Runnable progressBarRunnable = new Runnable() {
        @Override
        public void run() {
            ProgressBar progbarTimer = (ProgressBar) findViewById(R.id.progbarTimer);
            progbarTimer.setMax((int) time);
            progbarTimer.setProgress((int) currTime);
            currTime -= 1000;
            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final SharedPreferences sharedPrefs = GameActivity.this.getSharedPreferences("options", Context.MODE_PRIVATE);

        showNewEquation(sharedPrefs.getString("operators","+"),
                sharedPrefs.getInt("operations", 1));
        setTimer(sharedPrefs.getInt("difficulty", 2));
        currTime = time;
        ProgressBar progbarTimer = (ProgressBar) findViewById(R.id.progbarTimer);
        progbarTimer.setMax((int) time);
        progbarTimer.setProgress((int) currTime);
        timerHandler.postDelayed(timerRunnable, time);
        progressBarHandler.postDelayed(progressBarRunnable, 1000);

        Button btnAnswer = (Button) findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtxtUserAnswer = (EditText) findViewById(R.id.edtxtUserAnswer);
                if (equation.checkIsAnswer(Integer.parseInt(edtxtUserAnswer.getText().toString()))) {
                    increaseScore();
                    timerHandler.removeCallbacks(timerRunnable);
                    timerHandler.postDelayed(timerRunnable, time);
                    progressBarHandler.removeCallbacks(progressBarRunnable);
                    progressBarHandler.postDelayed(progressBarRunnable, 1000);
                } else {
                    loseLife();
                    timerHandler.removeCallbacks(timerRunnable);
                    timerHandler.postDelayed(timerRunnable, time);
                    progressBarHandler.removeCallbacks(progressBarRunnable);
                    progressBarHandler.postDelayed(progressBarRunnable, 1000);
                }
                showNewEquation(sharedPrefs.getString("operators","+"),
                        sharedPrefs.getInt("operations", 1));
            }
        });
    }

    @Override
    public void onPause() {
        timerHandler.removeCallbacks(timerRunnable);
        progressBarHandler.removeCallbacks(progressBarRunnable);
        super.onPause();
    }

    private void showNewEquation(String operators, int operations) {
        equation = EquationGenerator.generateEquation(operators, operations);

        TextView txtEquation = (TextView) findViewById(R.id.txtEquation);
        txtEquation.setText(equation.getEquation());
    }

    private void increaseScore() {
        score++;

        TextView txtScore = (TextView) findViewById(R.id.txtScore);
        txtScore.setText(String.valueOf(score));

        EditText edtxtUserAnswer = (EditText) findViewById(R.id.edtxtUserAnswer);
        edtxtUserAnswer.setText("");

        if(score % 5 == 0) {
            time -= 5;
        }
    }

    private void loseLife() {
        lives--;
        TextView txtLives = (TextView) findViewById(R.id.txtLives);
        txtLives.setText(String.valueOf(lives));

        EditText edtxtUserAnswer = (EditText) findViewById(R.id.edtxtUserAnswer);
        edtxtUserAnswer.setText("");

        if(lives == 0) {
            timerHandler.removeCallbacks(timerRunnable);
            Intent gameOverIntent = new Intent(GameActivity.this, GameOverActivity.class);
            startActivity(gameOverIntent);
        }
    }

    private void setTimer(int difficulty) {
        switch (difficulty) {
            case 0:
                time = 60000;
                break;
            case 1:
                time = 45000;
                break;
            case 2:
                time = 30000;
                break;
            case 3:
                time = 15000;
                break;
            case 4:
                time = 5000;
                break;
            default:
                time = 30000;
                break;
        }
    }
}
