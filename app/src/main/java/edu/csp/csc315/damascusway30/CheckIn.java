package edu.csp.csc315.damascusway30;

import java.util.Date;

public class CheckIn {

    public int Id;
    public String TimeStamp;
    public Resident Resident;
    public String Status;
    public String Notes = "";

    public CheckIn(String dateString, Resident resident, String status)
    {
        TimeStamp = dateString;
        Resident = resident;
        Status = status;
    }

    public CheckIn(int id, String dateString, Resident resident, String status)
    {
        Id = id;
        TimeStamp = dateString;
        Resident = resident;
        Status = status;
    }

    public CheckIn(String dateString, Resident resident, String status, String notes)
    {
        this(dateString,resident,status);
        Notes = notes;
    }


}
