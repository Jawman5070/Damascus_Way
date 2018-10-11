package edu.csp.csc315.damascusway30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //variables
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started.");

        initImageBitMaps();

    }

    private void initImageBitMaps(){
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

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
