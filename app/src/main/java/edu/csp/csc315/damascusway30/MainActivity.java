package edu.csp.csc315.damascusway30;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //Variables for RecyclerView Adapter
    private RecyclerViewAdapter adapter;
    //DatabaseIO to get residents from Database
    private DatabaseIO databaseIO;
    //List to hold the residents
    private List<Resident> mResidents = new ArrayList<>();
    //Create Toolbar
    Toolbar toolbar;
    private Employee loggedInEmployee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");
        loggedInEmployee = LocalData.getInstance().getCurrentEmployee();
        initToolbar();
        initImageBitMaps();
        initRecyclerView();
/*
        //If screen size less than 600, portrait only, else landscape only, this should come first to prevent re-draw of portrait/landscape versions
        if(getResources().getBoolean(R.bool.landscape_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        }
        else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        }
*/
    }

    private void initImageBitMaps(){

        //Create some residents to work with for testing
        Resident joel = new Resident("Joel", "Schuessler", "Clifton", "https://i.imgur.com/Ha3MFuv.jpg", false, "High", "Green", "Brown", "101", "WR");
        Resident cheng = new Resident("Cheng", "Thao", "", "https://i.imgur.com/y19ovIo.jpg", false, "High", "Brown", "Black", "201", "ISR");
        Resident jeff = new Resident("El", "Jefe", "", "https://i.imgur.com/NF7LAhw.jpg", true, "Low", "Blue", "Red", "101", "WR");
        Resident robert = new Resident("Robert", "Krueger", "", "https://i.imgur.com/Kw7Ua01.jpg", true, "Medium", "Brown", "Brown", "201", "ISR");
        Resident jerry = new Resident("Sailor", "Jerry", "", "https://i.imgur.com/5q4G9P9.jpg", false, "Medium", "Brown", "Brown", "102", "WR");
        Resident tyrion = new Resident("Tyrion", "Lannister", "", "https://i.imgur.com/S1KQZwN.jpg", false, "Medium", "Brown", "Brown", "102", "WR");
        Resident sarah = new Resident("Sarah", "Severson", "", "https://i.imgur.com/FKpCI8Y.jpg", true, "Medium", "Brown", "Brown", "202", "WR");
        Resident vadar = new Resident("Mike", "Vader", "", "https://i.imgur.com/coRRgCY.jpg", true, "Medium", "Brown", "Brown", "203", "ISR");
        Resident mike = new Resident("Mike", "Davidovich", "", "https://i.imgur.com/CT8ae03.jpg", false, "Medium", "Brown", "Brown", "202", "WR");
        Resident heather = new Resident("Heather", "Kooiker", "", "https://i.imgur.com/sHAYnvR.jpg", true, "Medium", "Brown", "Brown", "103", "ISR");
        //Add residents to List
        // Add different residents based on location of the round

        Round r = LocalData.getInstance().getCurrentRound();
        String location = r.Location;
        if(location.equalsIgnoreCase("Location 1")) {
            mResidents.add(joel);
            mResidents.add(cheng);
            mResidents.add(jeff);
            mResidents.add(robert);
        }
        else if (location.equalsIgnoreCase("Location 2")) {
            mResidents.add(jerry);
            mResidents.add(tyrion);
            mResidents.add(sarah);
            mResidents.add(vadar);
            mResidents.add(mike);
        }
        else {
            mResidents.add(heather);
        }

        // sort list A-Z

        Collections.sort(mResidents);
        //Create RecyclerView from List
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this, mResidents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set the toolbar logo to Damascus Way
        toolbar.setLogo(R.drawable.dw_logo);
        //matches remaining background to white
        toolbar.setBackgroundColor(Color.WHITE);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        Log.d(TAG, "OnCreateOptionsMenu: create menu");

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
