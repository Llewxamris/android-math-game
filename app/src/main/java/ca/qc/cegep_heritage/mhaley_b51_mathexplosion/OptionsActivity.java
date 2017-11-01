package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.CheckBox;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Spinner spnnrDifficulty = (Spinner) findViewById(R.id.spnnrDifficulty);
        CheckBox chckbxAddition = (CheckBox) findViewById(R.id.chckbxAddition);
        CheckBox chckbxSubraction = (CheckBox) findViewById(R.id.chckbxSubtraction);
        CheckBox chckbxMultiplication = (CheckBox) findViewById(R.id.chckbxMultiplication);
        CheckBox chckbxDivision = (CheckBox) findViewById(R.id.chckbxDivision);
        final CheckBox[] operatorCheckboxes = {chckbxAddition, chckbxSubraction, chckbxMultiplication,
                chckbxDivision };
        CheckBox chckbxAllOperations = (CheckBox) findViewById(R.id.chckbxAllOperators);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_options, R.layout.support_simple_spinner_dropdown_item);

        spnnrDifficulty.setAdapter(adapter);

        chckbxAllOperations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (CheckBox operatorCheckbox : operatorCheckboxes) {
                        operatorCheckbox.setChecked(false);
                        operatorCheckbox.setClickable(false);
                        operatorCheckbox.setTextColor(Color.parseColor("#CCCCCC"));
                    }
                } else {
                    for (CheckBox operatorCheckbox : operatorCheckboxes) {
                        operatorCheckbox.setClickable(true);
                        operatorCheckbox.setTextColor(Color.parseColor("#000000"));
                    }
                }
            }
        });
    }
}
