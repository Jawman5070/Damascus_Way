package edu.csp.csc315.damascusway30;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoundsActivity extends AppCompatActivity {

    private static final String TAG = "RoundsActivity";



    private TextView currentTime;
    private TextView employeeName;
    private Button logOut;
    private Button createNewRound;
    private Spinner locationSelection;
    private ScrollView recentRounds;
    Toolbar toolbar;

    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);
        initToolbar();
        employee = LocalData.getInstance().getCurrentEmployee();
        currentTime = (TextView) findViewById(R.id.Time);
        employeeName = (TextView) findViewById(R.id.EmployeeText);
        logOut = (Button) findViewById(R.id.LogoutButton);
        createNewRound = (Button) findViewById(R.id.NewRound);
        locationSelection = (Spinner) findViewById(R.id.locationSpinner);
        recentRounds = (ScrollView) findViewById(R.id.scrollView2);

        employeeName.setText(employee.getfName() + " " + employee.getlName());
        currentTime.setText(new Date().toString());

        // Dev Values -------------------------
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Rochester");
        spinnerArray.add("Golden Valley");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        locationSelection.setAdapter(adapter);
        // -------------------------------


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        createNewRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewRound();
            }
        });

    }

    private void logout()
    {
        Intent i = new Intent(RoundsActivity.this, Login.class);
        LocalData.getInstance().setCurrentEmployee(null);
        startActivity(i);
    }

    private void startNewRound()
    {
        String locale = locationSelection.getSelectedItem().toString();

        Round r = new Round(new Date(), locale, employee);
        // Get list of residents from the database for location
        DatabaseIO db = new DatabaseIO(); //-- This should be stored in the LocalData instead?
        db.GetResidents(locale);
        Intent i = new Intent(RoundsActivity.this, MainActivity.class);
        LocalData.getInstance().setCurrentRound(r);
        startActivity(i);
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
