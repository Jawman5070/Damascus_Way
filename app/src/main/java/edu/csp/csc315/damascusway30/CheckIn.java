package edu.csp.csc315.damascusway30;

import java.util.Date;

public class CheckIn {

    public Date TimeStamp;
    public Resident Resident;
    public String Status;
    public String Notes;

    public CheckIn(Date date, Resident resident, String status)
    {
        TimeStamp = date;
        Resident = resident;
        Status = status;
    }

    public CheckIn(Date date, Resident resident, String status, String notes)
    {
        this(date,resident,status);
        Notes = notes;
    }


}
