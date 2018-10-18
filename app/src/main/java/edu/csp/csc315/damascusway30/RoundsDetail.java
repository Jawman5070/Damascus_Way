package edu.csp.csc315.damascusway30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RoundsDetail extends AppCompatActivity {

    TextView displayRoundsTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds_detail);
        List<String> roundsStatus = roundsStatus();

        displayRoundsTime = (TextView)findViewById(R.id.displayRoundsTime);
        displayRoundsTime.setText(getRoundsDate());

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roundsStatus);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public String getRoundsDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy hh':00' a");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        String date = simpleDateFormat.format(new Date());
        return date;
    }

    public List<String> roundsStatus() {

        List<String> list = new ArrayList<String>();

        list.add("To Work");
        list.add("At Work");
        list.add("To Facility");
        list.add("From Facility");
        list.add("To Support Group");
        list.add("From Support Group");
        list.add("To Church");
        list.add("At Church");
        list.add("On Job Search (Check Daily Planner)");
        list.add("On Pass (Check Daily Planner or Pass)");

        return list;
    }

}
