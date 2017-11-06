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

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView txtEquation = (TextView) findViewById(R.id.txtEquation);

        SharedPreferences sharedPrefs = GameActivity.this.getSharedPreferences("options", Context.MODE_PRIVATE);

        final Equation eq = EquationGenerator.generateEquation(sharedPrefs
                .getString("operators","+"),
                    sharedPrefs.getInt("operations", 1));

        txtEquation.setText(eq.getEquation());

        Button btnAnswer = (Button) findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            EditText edtxtUserAnswer = (EditText) findViewById(R.id.edtxtUserAnswer);
            @Override
            public void onClick(View v) {
                if (eq.checkIsAnswer(Integer.parseInt(edtxtUserAnswer.getText().toString()))) {
                    Toast toast = Toast
                            .makeText(GameActivity.this, "Correct!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
}
