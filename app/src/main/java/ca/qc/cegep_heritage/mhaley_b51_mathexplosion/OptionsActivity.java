package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        final CheckBox chckbxAddition = (CheckBox) findViewById(R.id.chckbxAddition);
        final CheckBox chckbxSubraction = (CheckBox) findViewById(R.id.chckbxSubtraction);
        final CheckBox chckbxMultiplication = (CheckBox) findViewById(R.id.chckbxMultiplication);
        final CheckBox chckbxDivision = (CheckBox) findViewById(R.id.chckbxDivision);

        final CheckBox[] operatorCheckboxes = {chckbxAddition, chckbxSubraction, chckbxMultiplication,
                chckbxDivision };

        final CheckBox chckbxAllOperations = (CheckBox) findViewById(R.id.chckbxAllOperators);
        chckbxAllOperations.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setCheckboxes(!isChecked, operatorCheckboxes);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_options, R.layout.support_simple_spinner_dropdown_item);

        final Spinner spnnrDifficulty = (Spinner) findViewById(R.id.spnnrDifficulty);
        spnnrDifficulty.setAdapter(adapter);

        // Load Shared Preferences
        final SharedPreferences sharedPrefs = OptionsActivity.this.getSharedPreferences("options", Context.MODE_PRIVATE);

        if(sharedPrefs.getString("operators", "+").equals("all")) {
            setCheckboxes(false, operatorCheckboxes);
            chckbxAllOperations.setChecked(true);
        } else {
            String[] selectedOperators = sharedPrefs.getString("operators", "+").split("");
            for (String selectedOperator : selectedOperators) {
                switch (selectedOperator) {
                    case "+":
                        chckbxAddition.setChecked(true);
                        break;
                    case "-":
                        chckbxSubraction.setChecked(true);
                        break;
                    case "*":
                        chckbxMultiplication.setChecked(true);
                        break;
                    case "/":
                        chckbxDivision.setChecked(true);
                        break;
                    default:
                }
            }
        }

        spnnrDifficulty.setSelection(sharedPrefs.getInt("difficulty", 0));

        final RadioButton rdoSingleOperation = (RadioButton) findViewById(R.id.rdoSingleOperation);
        RadioButton rdoDoubleOperation = (RadioButton) findViewById(R.id.rdoDoubleOperation);

        if (sharedPrefs.getInt("operations", 0) == 0) {
            rdoSingleOperation.setChecked(true);
        } else {
            rdoDoubleOperation.setChecked(true);
        }

        // Saving user preferences
        Button btnSaveOptions = (Button) findViewById(R.id.btnSaveOptions);
        btnSaveOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chckbxAllOperations.isChecked()) {
                    sharedPrefs.edit().putString("operators", "all").apply();
                } else {
                    String operators = "";
                    if (chckbxAddition.isChecked()) {
                        operators += "+";
                    }

                    if (chckbxSubraction.isChecked()) {
                        operators += "-";
                    }

                    if(chckbxMultiplication.isChecked()) {
                        operators += "*";
                    }

                    if(chckbxDivision.isChecked()) {
                        operators += "/";
                    }

                    sharedPrefs.edit().putString("operators", operators).apply();
                }

                sharedPrefs.edit().putInt("difficulty", spnnrDifficulty.getSelectedItemPosition())
                        .apply();

                if (rdoSingleOperation.isChecked()) {
                    sharedPrefs.edit().putInt("operations", 0).apply();
                } else {
                    sharedPrefs.edit().putInt("operations", 1).apply();
                }

                Intent mainIntent = new Intent(OptionsActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });

        // Leave the Options Menu
        Button btnCancelOptions = (Button) findViewById(R.id.btnCancelOptions);
        btnCancelOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(OptionsActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    private static void setCheckboxes(boolean isEnabled, CheckBox[] checkboxes) {
        if(isEnabled) {
            for (CheckBox checkbox : checkboxes) {
                checkbox.setClickable(true);
                checkbox.setTextColor(Color.parseColor("#000000"));
            }
        } else {
            for (CheckBox checkbox : checkboxes) {
                checkbox.setChecked(false);
                checkbox.setClickable(false);
                checkbox.setTextColor(Color.parseColor("#CCCCCC"));
            }
        }
    } // setCheckboxes(...)

}
