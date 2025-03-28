package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents the user's profile information including name, contact info, and address
public class Profile implements Writable {
    String name;
    String number;
    String email;
    String address;
    String objective;

    // EFFECTS: constructs a new profile with given information
    public Profile(String name, String number, String email, String address, String objective) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.objective = objective;
        EventLog.getInstance().logEvent(new Event("A new profile added to resume!"));
    }

    // EFFECTS: returns the user's name
    public String getName() {
        return name;
    }

    // EFFECTS: returns the user's phone number
    public String getNumber() {
        return number;
    }

    // EFFECTS: returns the user's email address
    public String getEmail() {
        return email;
    }

    // EFFECTS: returns the user's home address
    public String getAddress() {
        return address;
    }

    // EFFECTS: returns a short reusme objective sentences
    public String getObjective() {
        return objective;
    }

    // MODIFIES: this
    // EFFECTS: updates the user's name with given name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: updates the user's phone number with given number
    public void setNumber(String number) {
        this.number = number;
    }

    // MODIFIES: this
    // EFFECTS: updates the user's email address with given email address
    public void setEmail(String email) {
        this.email = email;
    }

    // MODIFIES: this
    // EFFECTS: updates the user's home address with given home address
    public void setAddress(String address) {
        this.address = address;
    }

    // MODIFIES: this
    // EFFECTS: updates the user's resume objective with given objective
    public void setObjective(String objective) {
        this.objective = objective;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("number", number);
        json.put("email", email);
        json.put("address", address);
        json.put("objective", objective);
        return json;
    }

}
