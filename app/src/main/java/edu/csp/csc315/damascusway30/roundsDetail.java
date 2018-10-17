package edu.csp.csc315.damascusway30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class roundsDetail extends AppCompatActivity {

    TextView displayRoundsTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds_detail);

        displayRoundsTime = (TextView)findViewById(R.id.displayRoundsTime);
        displayRoundsTime.setText(getRoundsDate());
    }

    public String getRoundsDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy hh':00' a");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        String date = simpleDateFormat.format(new Date());
        return date;
    }

}
