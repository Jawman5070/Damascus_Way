package edu.csp.csc315.damascusway30;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.NotYetConnectedException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void SaveCheckIn (CheckIn checkIn)
    {
        int currentRoundId = LocalData.getInstance().getCurrentRound().Id;
        // TEMP VALUE REMOVE WHEN ROUNDS WORK IS COMPLETED!
        currentRoundId = 3;

        String residentURL = "http://www.worldofadventurecraft.com/api/save-checkin.php?" +
                "RoundId="+currentRoundId+"&ResidentId="+checkIn.Resident.id+"&Time='"+checkIn.TimeStamp+"'&Status='"+checkIn.Status+"'&Notes='"+checkIn.Notes+"'";
        final Employee[] found = {null};


        getResponse(Request.Method.GET, residentURL, null, new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                try{
                    //String shortString = result.substring(1);  //Trim int from front of string
                    JSONObject response = new JSONObject(result);
                    JSONArray residentList = response.getJSONArray("employees");
                    for(int i = 0; i < 1; i++)
                    {
                        int id = Integer.parseInt(residentList.getJSONObject(i).getString("TestId"));
                        LocalData.getInstance().getCurrentCheckIn().Id = id;
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void GetEmployee(String user, String password)
    {
        String residentURL = "http://www.worldofadventurecraft.com/api/get-employee.php?user='"+user+"'&pass='"+password+"'";
        final Employee[] found = {null};


        getResponse(Request.Method.GET, residentURL, null, new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                try{
                    //String shortString = result.substring(1);  //Trim int from front of string
                    JSONObject response = new JSONObject(result);
                    JSONArray residentList = response.getJSONArray("employees");
                    for(int i = 0; i < 1; i++)
                    {
                        int id = Integer.parseInt(residentList.getJSONObject(i).getString("Staff_ID"));
                        String firstName = residentList.getJSONObject(i).getString("Staff_FName");
                        String lastName = residentList.getJSONObject(i).getString("Staff_LName");

                        found[0] = new Employee(id, firstName, lastName);

                    }


                    LocalData.getInstance().setCurrentEmployee(found[0]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public void GetResidents(String location)
    {

        String residentURL = "http://www.worldofadventurecraft.com/api/get-resident-v2.php?location="+location;
        final ArrayList<Resident> residents = new ArrayList<>();


        getResponse(Request.Method.GET, residentURL, null, new IVolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                    try{
                    String shortString = result;
                    if(result.charAt(0) == 49)
                    {
                        shortString = result.substring(1);  //Trim int from front of string
                    }
                    JSONObject response = new JSONObject(shortString);
                    JSONArray residentList = response.getJSONArray("residents");
                    for(int i = 0; i < residentList.length(); i++)
                    {
                        int id = Integer.parseInt(residentList.getJSONObject(i).getString("Resident_ID"));
                        String firstName = residentList.getJSONObject(i).getString("Resident_FName");
                        String lastName = residentList.getJSONObject(i).getString("Resident_LName");
                        String photoUrl = residentList.getJSONObject(i).getString("Resident_Photo");

                        //Will Add the default image if null or blank in Database
                        if (photoUrl.equals("null")){
                            photoUrl = "http://www.worldofadventurecraft.com/img/avatar.jpg";
                        }
                        else if (photoUrl.equals("")){
                            photoUrl = "http://www.worldofadventurecraft.com/img/avatar.jpg";
                        }

                        System.out.println("PhotoURL = " + photoUrl);

                        Resident r = new Resident(id, firstName, lastName, photoUrl);

                        JSONArray checkInList = residentList.getJSONObject(i).getJSONArray("check ins");
                        for(int j = 0; j < checkInList.length(); j++)
                        {
                            int checkInId = Integer.parseInt(checkInList.getJSONObject(j).getString("Check_In_ID"));
                            String checkInTime = checkInList.getJSONObject(j).getString("Check_In_Time");
                            String checkInStatus = checkInList.getJSONObject(j).getString("Check_In_Status");
                            r.addCheckin(new CheckIn(checkInId,checkInTime,r,checkInStatus));
                        }


                        residents.add(r);
                    }


                    LocalData.getInstance().residentList = residents;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}
