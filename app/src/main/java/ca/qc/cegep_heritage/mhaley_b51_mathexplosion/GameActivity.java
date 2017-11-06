package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import java.util.Timer;
//import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    private Equation equation;
    private int lives = 3;
    private long time;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final SharedPreferences sharedPrefs = GameActivity.this.getSharedPreferences("options", Context.MODE_PRIVATE);

        showNewEquation(sharedPrefs.getString("operators","+"),
                sharedPrefs.getInt("operations", 1));
        setTimer(sharedPrefs.getInt("difficulty", 2));

//        TimerTask playerTookToLong = getTimerTask(sharedPrefs);
//
//        Timer countDownTimer = new Timer("countDownTimer");
//        countDownTimer.schedule(playerTookToLong, time);

        Button btnAnswer = (Button) findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtxtUserAnswer = (EditText) findViewById(R.id.edtxtUserAnswer);
                if (equation.checkIsAnswer(Integer.parseInt(edtxtUserAnswer.getText().toString()))) {
                    Toast toast = Toast
                            .makeText(GameActivity.this, "Correct!", Toast.LENGTH_LONG);
                    toast.show();
                    increaseScore();
                } else {
                    loseLife();
                }
                showNewEquation(sharedPrefs.getString("operators","+"),
                        sharedPrefs.getInt("operations", 1));
            }
        });
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
            // Lose condition
        }
    }

//    private TimerTask getTimerTask(SharedPreferences sharedPrefs) {
//        return new TimerTask() {
//            @Override
//            public void run() {
//                loseLife();
//                showNewEquation(sharedPrefs.getString("operators","+"),
//                        sharedPrefs.getInt("operations", 1));
//                Timer countDownTimer = new Timer("countDownTimer");
//                countDownTimer.schedule(getTimerTask(), time);
//            }
//        };
//    }

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
