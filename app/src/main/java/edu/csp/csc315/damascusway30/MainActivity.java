package edu.csp.csc315.damascusway30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //variables use for hard-coded values
    //private ArrayList<String> mNames = new ArrayList<>();
    //private ArrayList<String> mImageUrls = new ArrayList<>();

    //Variables for RecyclerView Adapter
    private RecyclerViewAdapter adapter;
    //DatabaseIO to get residents from Database
    private DatabaseIO databaseIO;
    //ArrayList to hold the resident Objects
    private ArrayList<Resident> residents;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitMaps();
        initRecyclerView();
    }

    private void initImageBitMaps(){
        //Add the residents to the ArrayList for RecyclerView
        //residents.add();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, residents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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
    }


/*Used with hardcoded values
    private void initImageBitMaps() {
        Log.d(TAG, "initImageBitMaps: preparing bitmaps.");

        mImageUrls.add("https://i.imgur.com/Ha3MFuv.jpg");
        mNames.add("Joel Schuessler");

        mImageUrls.add("https://i.imgur.com/y19ovIo.jpg");
        mNames.add("Cheng Thao");

        mImageUrls.add("https://i.imgur.com/NF7LAhw.jpg");
        mNames.add("El Jefe");

        mImageUrls.add("https://i.imgur.com/Kw7Ua01.jpg");
        mNames.add("Robert Krueger");

        mImageUrls.add("https://i.imgur.com/5q4G9P9.jpg");
        mNames.add("Sailor Jerry");

        mImageUrls.add("https://i.imgur.com/S1KQZwN.jpg");
        mNames.add("Tyrion Lannister");

        mImageUrls.add("https://i.imgur.com/FKpCI8Y.jpg");
        mNames.add("Sarah Severson");

        mImageUrls.add("https://i.imgur.com/coRRgCY.jpg");
        mNames.add("Darth Vader");

        mImageUrls.add("https://i.imgur.com/CT8ae03.jpg");
        mNames.add("Mike Davidovich");

        mImageUrls.add("https://i.imgur.com/sHAYnvR.jpg");
        mNames.add("Heather Kooiker");

        initRecyclerView();
    }
*/


}
