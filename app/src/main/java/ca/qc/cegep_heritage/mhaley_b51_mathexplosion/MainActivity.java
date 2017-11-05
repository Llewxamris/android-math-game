package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Context;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnOptions = (Button) findViewById(R.id.btnOptions);
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchOptionsActivity();
            }
        });

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainActivity.this,
                        GameActivity.class);
                startActivity(gameIntent);
            }
        });

        SharedPreferences sharedPrefs = MainActivity.this.getSharedPreferences("options", Context.MODE_PRIVATE);

        if(sharedPrefs.getInt("difficulty", -1) == -1) {
            sharedPrefs.edit().putString("operators", "+-").apply();
            sharedPrefs.edit().putInt("difficulty", 2).apply();
            sharedPrefs.edit().putInt("operations", 1).apply();

        }

    }

    private void switchOptionsActivity() {
        Intent optionsIntent = new Intent(this, OptionsActivity.class);
        startActivity(optionsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
