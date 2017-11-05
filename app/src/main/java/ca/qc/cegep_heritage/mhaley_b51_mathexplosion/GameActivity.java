package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView txtEquation = (TextView) findViewById(R.id.txtEquation);

        SharedPreferences sharedPrefs = GameActivity.this.getSharedPreferences("options", Context.MODE_PRIVATE);

        Equation eq = EquationGenerator.generateEquation(sharedPrefs
                .getString("operators","+"),
                    sharedPrefs.getInt("operations", 1));

        txtEquation.setText(eq.getEquation());

    }
}
