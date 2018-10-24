package edu.csp.csc315.damascusway30;

import java.util.Date;
import java.util.List;

public class Round {

    public Date TimeStamp;
    public String Location;
    public Employee Employee;
    public List<CheckIn> CheckIns;


    public Round()
    {

    }

    void AddCheckIn(CheckIn check)
    {
        CheckIns.add(check);
    }


}
