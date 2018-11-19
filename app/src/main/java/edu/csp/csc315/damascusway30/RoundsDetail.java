package edu.csp.csc315.damascusway30;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import de.hdodenhof.circleimageview.CircleImageView;


public class RoundsDetail extends AppCompatActivity {

    TextView displayRoundsTime;
    Context context;
    Toolbar toolbar;
    Button verifyButton;
    Resident resident;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds_detail);
        initToolbar();
        List<String> roundsStatus = roundsStatus();
        context = getApplicationContext();

        displayRoundsTime = (TextView)findViewById(R.id.displayRoundsTime);
        displayRoundsTime.setText(getRoundsDate());
        verifyButton = (Button)findViewById(R.id.buttonVerify);
        verifyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                verifyResident();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roundsStatus);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        resident = LocalData.getInstance().getCurrentResident();
        setResident();
        //getIncomingIntent();
    }

    private void verifyResident() {

        String status = spinner.getSelectedItem().toString();

        CheckIn c = new CheckIn(new Date(), resident, status);
        LocalData.getInstance().setCurrentCheckIn(c);
        //LocalData.getInstance().getCurrentRound().CheckIns.add(c);
        resident.isCheckedIn = true;

        Intent intent = new Intent(RoundsDetail.this, MainActivity.class);

        startActivity(intent);

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

    private void getIncomingIntent(){

        if(getIntent().hasExtra("resident_name") && getIntent().hasExtra("room_number") && getIntent().hasExtra("resident_location") && getIntent().hasExtra("resident_photo")){
            String residentName = getIntent().getStringExtra("resident_name");
            String roomNumber = getIntent().getStringExtra("room_number");
            String residentLocation = getIntent().getStringExtra("resident_location");
            String residentPhoto = getIntent().getStringExtra("resident_photo");
            //setResident(residentName, roomNumber, residentLocation, residentPhoto);
        }
    }

    private void setResident(){
        TextView name = findViewById(R.id.residentName);
        name.setText(resident.firstName + " " + resident.lastName);

        TextView room = findViewById(R.id.displayRoom);
       //room.setText(roomNumber);

        TextView location = findViewById(R.id.displayWrIsr);
        //location.setText(residentLocation);

        CircleImageView photo = findViewById(R.id.imageView);
        Glide.with(context)
                .asBitmap()
                .load(resident.photoUrl)
                .into(photo);
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set the toolbar logo to Damascus Way
        toolbar.setLogo(R.drawable.dw_logo);
        //matches remaining background to white
        toolbar.setBackgroundColor(Color.WHITE);
    }

}
