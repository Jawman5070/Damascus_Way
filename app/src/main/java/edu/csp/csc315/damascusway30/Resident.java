package edu.csp.csc315.damascusway30;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Resident implements Comparable<Resident> {

    int id;
    String firstName;
    String lastName;
    String middleName;
    String photoUrl;
    boolean sexOffender;
    String riskLevel;
    String eyeColor;
    String hairColor;

/*
    public Resident(String fName, String lName, String mName, String photoUrl,
                    boolean sexOffender, String riskLevel, String eyeColor, String hairColor) {
        this.firstName = fName;
        this.lastName = lName;
        this.middleName = mName;
        this.photoUrl = photoUrl;
        this.sexOffender = sexOffender;
        this.riskLevel = riskLevel;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    public Resident(int id, String fName, String lName, String mName, String photoUrl,
                    boolean sexOffender, String riskLevel, String eyeColor, String hairColor) {
        this(fName, lName, mName, photoUrl, sexOffender, riskLevel, eyeColor, hairColor);
        this.id = id;
    }
*/

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

    public String getmName() {
        return middleName;
    }

    public void setmName(String mName) {
        this.middleName = mName;
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
        return lastName + ", " + firstName + " " + middleName + " ";
    }


    // https://guides.codepath.com/android/converting-json-to-models#fetching-json-results
    public static Resident fromJson(JSONObject jsonObject) {
        Resident r = new Resident();
        try {
            r.id = jsonObject.getInt("Resident_ID)");
            r.firstName = jsonObject.getString("Resident_LName");
            r.middleName = jsonObject.getString("Resident_MName");
            r.lastName = jsonObject.getString("Resident_LName");
            r.photoUrl = jsonObject.getString("Resident_Photo");
            r.sexOffender = jsonObject.getBoolean("Resident_Sex_Offender");
            r.riskLevel = jsonObject.getString("Resident_Risk_Level");
            r.eyeColor = jsonObject.getString("Resident_Eye_Color");
            r.hairColor = jsonObject.getString("Resident_Hair_Color");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return r;
    }

    // https://guides.codepath.com/android/converting-json-to-models#fetching-json-results
    public static ArrayList<Resident> fromJson(JSONArray jsonArray) {
        JSONObject residentJson;
        ArrayList<Resident> residents = new ArrayList<Resident>(jsonArray.length());
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                residentJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Resident resident = Resident.fromJson(residentJson);
            if (resident != null) {
                residents.add(resident);
            }
        }
        return residents;
    }
}
