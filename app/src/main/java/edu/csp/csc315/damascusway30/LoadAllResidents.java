package edu.csp.csc315.damascusway30;

import android.content.Intent;
import android.os.AsyncTask;
import android.se.omapi.Reader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadAllResidents extends AsyncTask<String, String, String> {

        // url to get all products list
        String url_all_products = "http://localhost:63343/Web-App-master/android-connect/get-resident.php";
        JSONParser jParser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        ArrayList<HashMap<String, String>> productsList;
        productsList = new ArrayList<HashMap<String, String>>();

        // JSON Node names
        String TAG_SUCCESS = "success";
        String TAG_PRODUCTS = "products";
        String TAG_PID = "pid";
        String TAG_NAME = "name";

        // residents JSONArray
        JSONArray residents = null;

        JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);

        Log.d("All Products: ", json.toString());

        try {
        // Checking for SUCCESS TAG
        int success = json.getInt(TAG_SUCCESS);

        if (success == 1) {
        // products found
        // Getting Array of Products
        residents = json.getJSONArray(TAG_PRODUCTS);

        // looping through All Products
        for (int i = 0; i < residents.length(); i++) {
        JSONObject c = residents.getJSONObject(i);

        // Storing each json item in variable
        String id = c.getString(TAG_PID);
        String name = c.getString(TAG_NAME);

        // creating new HashMap
        HashMap<String, String> map = new HashMap<String, String>();

        // adding each child node to HashMap key => value
        map.put(TAG_PID, id);
        map.put(TAG_NAME, name);

        // adding HashList to ArrayList
        productsList.add(map);
        }
        } else {

        }
        } catch (JSONException e) {
        e.printStackTrace();
        }

        }


/*
        //http://chillyfacts.com/java-send-http-getpost-request-and-read-json-response/
            String url = "http://localhost:63343/Web-App-master/android-connect/db_connect.php";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONArray residents = new JSONArray(response.toString());
            return Resident.fromJson(residents);
*/
