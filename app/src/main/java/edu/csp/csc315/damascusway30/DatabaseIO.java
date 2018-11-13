package edu.csp.csc315.damascusway30;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.NotYetConnectedException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseIO {

    Connection _sqlConnection;
    String _connectionString;
    String _user;
    String _password;


    DatabaseIO(String connectionString, String user, String password)
    {
       _connectionString = connectionString;
       _user = user;
       _password = password;
        //DataSource dataSource = new DataSource();
        //dataSource.setUser("scott");
        //dataSource.setPassword("tiger");
        //dataSource.setServerName("myDBHost.example.org");

       Object results = ExecuteSQLCommand("SELECT * FROM RESIDENTS");

    }

    DatabaseIO(){}

    private Object ExecuteSQLCommand(String commandText)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            _sqlConnection = DriverManager.getConnection(_connectionString, _user, _password);
            Boolean isConnected = _sqlConnection.isClosed();
            Statement statement = _sqlConnection.createStatement();
            ResultSet result = statement.executeQuery(commandText);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    private void getResponse(int method, String url, JSONObject result, final IVolleyCallback callback)
    {
        //RequestQueue queue = LocalData.getInstance().queue;

        StringRequest request = new StringRequest(Request.Method.GET, url,  new Response.Listener<String>(){

            @Override
            public void onResponse(String Response) {
                callback.onSuccessResponse(Response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();

            }
        });
        LocalData.getInstance().queue.add(request);

    }










    Round GetRound(int id)
    {
        // return a specific Round
        throw new NotYetConnectedException();
    }

    List<Round> GetRounds(String location)
    {
        // return a list of rounds for a location
        throw new NotYetConnectedException();
    }

    int SaveRound(Round round)
    {
        //Push Round To Database and return ID
        // Add insert/update logic
        throw new NotYetConnectedException();
    }

    CheckIn GetCheckin(int id)
    {
        // get a checkin from the db
        throw new NotYetConnectedException();
    }

    int SaveCheckIn (CheckIn checkIn)
    {
        // save a specific checkin
        throw new NotYetConnectedException();
    }

    Employee GetEmployee(String email, String password)
    {
        // try to find employee with matching credentials in the db
        throw new NotYetConnectedException();
    }

    public Resident ParsingExample()
    {
        final Resident testMan = new Resident();

        String testUrl = "https://jsonplaceholder.typicode.com/todos/1";
        getResponse(Request.Method.GET, testUrl, null, new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                try{
                    JSONObject response = new JSONObject(result);
                        String value = response.getString("title");

                        testMan.setfName(value);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        return testMan;
    }

    List<Resident> GetResidents(String location)
    {

        String residentURL = "TBD";
        final ArrayList<Resident> residents = new ArrayList<>();


        getResponse(Request.Method.GET, residentURL, null, new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                try{
                    JSONObject response = new JSONObject(result);
                    Iterator keys = response.keys();
                    while(keys.hasNext()) // Iterate through the collection of residents
                    {
                        Object key = keys.next();
                        JSONObject value = response.getJSONObject((String)key);
                        String firstName = value.getString("fName");
                        String lastName = value.getString("lName");

                        Resident r = new Resident();
                        r.setfName(firstName);
                        r.setlName(lastName);

                        residents.add(r);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return residents;



        // 1) Create and open connection to Web API
        // 2) Call the correct php script to run SQL query
        // 3) Return a JSON result object
        // 4) Close connection
        // 5) Parse JSON object into local list of residents


        // return list of residents for location
        //throw new NotYetConnectedException();
    }



}
