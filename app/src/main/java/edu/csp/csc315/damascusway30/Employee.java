package edu.csp.csc315.damascusway30;

public class Employee {

    int id;
    String fName;
    String lName;

    public Employee (int id, String fName, String lName) {

        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }
}
