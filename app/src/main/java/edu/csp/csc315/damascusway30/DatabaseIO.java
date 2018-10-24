package edu.csp.csc315.damascusway30;

import java.nio.channels.NotYetConnectedException;
import java.sql.*;
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
    }

    private Object ExecuteSQLCommand(String commandText)
    {
        try{
            _sqlConnection = DriverManager.getConnection(_connectionString, _user, _password);
            Statement statement = _sqlConnection.createStatement();
            ResultSet result = statement.executeQuery(commandText);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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

    List<Resident> GetResidents(String location)
    {
        // return list of residents for location
        throw new NotYetConnectedException();
    }



}
