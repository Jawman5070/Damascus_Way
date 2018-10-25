package edu.csp.csc315.damascusway30;

public class Resident {

    int id;
    String fName;
    String lName;
    String mName;
    String photoUrl;
    boolean sexOffender;
    String riskLevel;
    String eyeColor;
    String hairColor;

    public Resident(int id, String fName, String lName, String mName, String photoUrl,
                    boolean sexOffender, String riskLevel, String eyeColor, String hairColor) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.photoUrl = photoUrl;
        this.sexOffender = sexOffender;
        this.riskLevel = riskLevel;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    public Resident(String fName, String lName, String mName, String photoUrl,
                    boolean sexOffender, String riskLevel, String eyeColor, String hairColor) {
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.photoUrl = photoUrl;
        this.sexOffender = sexOffender;
        this.riskLevel = riskLevel;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setSexOffender(boolean sexOffender) {
        this.sexOffender = sexOffender;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @Override
    public String toString() {
        return lName + ", " + fName + " " + mName + " ";
    }
}
