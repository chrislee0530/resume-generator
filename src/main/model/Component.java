package model;

import org.json.JSONObject;
import persistence.Writable;

public abstract class Component implements Writable {
    protected String location;
    protected String startYear;
    protected String startMonth;
    protected String endYear;
    protected String endMonth;
    protected String description;

    public Component(String location, String startYear, String startMonth,
            String endYear, String endMonth, String description) {
        this.location = location;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public String getStartYear() {
        return startYear;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getEndYear() {
        return endYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract JSONObject toJson();
}
