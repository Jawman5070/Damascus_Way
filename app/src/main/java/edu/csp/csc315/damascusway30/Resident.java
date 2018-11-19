package edu.csp.csc315.damascusway30;

public class Resident implements Comparable<Resident> {

    int id;
    String firstName;
    String lastName;
    String middleName;
    String photoUrl;
    //boolean sexOffender;
    //String riskLevel;
    //String eyeColor;
    //String hairColor;
    String roomNumber;
    String location;

    public Resident(String fName, String lName, String photoUrl) {
        this.firstName = fName;
        this.lastName = lName;
        //this.middleName = mName;
        this.photoUrl = photoUrl;
        //this.sexOffender = sexOffender;
        //this.riskLevel = riskLevel;
        //this.eyeColor = eyeColor;
        //this.hairColor = hairColor;
        //this.roomNumber = roomNumber;
        //this.location = location;


        // Default Photo
        if(photoUrl.length() <  3)
        {
            this.photoUrl = "https://i.imgur.com/coRRgCY.jpg";
        }

    }

    public Resident(int id, String fName, String lName, String photoUrl) {
        this(fName, lName, photoUrl);
        this.id = id;
    }

    // Sorts by lastName
    public int compareTo(Resident resident) {
        return this.getlName().compareTo(resident.getlName());
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return firstName;
    }

    public void setfName(String fName) {
        this.firstName = fName;
    }

    public String getlName() {
        return lastName;
    }

    public void setlName(String lName) {
        this.lastName = lName;
    }



    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }

    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return location;
    }
}
