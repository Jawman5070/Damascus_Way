package edu.csp.csc315.damascusway30;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);

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
        spinnerArray.add("Location 1");
        spinnerArray.add("Location 2");
        spinnerArray.add("Location 3");

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
        // DatabaseIO db = new DatabaseIO("", "", ""); -- This should be stored in the LocalData instead?
        // var residentList = db.GetResidents("Selected Location");



        Intent i = new Intent(RoundsActivity.this, MainActivity.class);
        LocalData.getInstance().setCurrentRound(r);
        startActivity(i);
    }
}
