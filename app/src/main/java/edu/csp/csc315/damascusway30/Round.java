package edu.csp.csc315.damascusway30;

import java.util.Date;
import java.util.List;

public class Round {

    public int Id;
    public String TimeStamp;
    public String Location;
    public Employee Employee;


    public Round(int id, String time, String location, Employee employee) {
        Id = id;
        TimeStamp = time;
        Location = location;
        Employee = employee;

    }

    public Round(String time, String location, Employee employee) {

        TimeStamp = time;
        Location = location;
        Employee = employee;
    }
}