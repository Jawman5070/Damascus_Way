package edu.csp.csc315.damascusway30;

import java.util.Date;
import java.util.List;

public class Round {

    private int Id;
    public Date TimeStamp;
    public String Location;
    public Employee Employee;
    public List<CheckIn> CheckIns;


    public Round(Date time, String location, Employee employee)
    {
        TimeStamp = time;
        Location = location;
        Employee = employee;

    }

    void AddCheckIn(CheckIn check)
    {
        CheckIns.add(check);
    }


}
