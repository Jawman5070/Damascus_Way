package edu.csp.csc315.damascusway30;

public class Employee {

    int id;
    String firstName;
    String lastName;

    public Employee (int id, String fName, String lName) {

        this.id = id;
        this.firstName = fName;
        this.lastName = lName;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return firstName;
    }

    public String getlName() {
        return lastName;
    }
}
