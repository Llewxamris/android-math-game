package ca.qc.cegep_heritage.mhaley_b51_mathexplosion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd - hh:mm a",
                Locale.ENGLISH);

        TextView txtCurrentDateTime = (TextView) findViewById(R.id.txtCurrentDateTime);
        txtCurrentDateTime.setText(dateFormatter.format(date));
    }
}
