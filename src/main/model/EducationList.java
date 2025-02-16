package model;

import java.util.ArrayList;

// Represents all educations of the user
public class EducationList {
    private ArrayList<Education> educationList;
    
    // EFFECTS: constructs an empty list of educations
    public EducationList() {
        educationList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: reorders the list from the most recent to the least recent education
    public void mostRecentEducations() {
        ArrayList<Education> holder = new ArrayList<>();

        while(!educationList.isEmpty()) {
            int maxMonths = -1;
            Education recentEducation = null;
            for (Education education : educationList) {
                int educationMonths = education.getEndYear() * 12 + education.getEndMonth();
                if (education.getEndYear() == 0) {
                    recentEducation = education;
                    break;
                }
                if (educationMonths > maxMonths) {
                    maxMonths = educationMonths;
                    recentEducation = education;
                }
            }
            holder.add(recentEducation);
            educationList.remove(recentEducation);
        }
        educationList.addAll(holder);
    }


    // MODIFIES: this
    // EFFECTS: adds a new education to the list, unless there already exists one
    public void addEducation(Education education) {
        if (!educationList.contains(education)) {
            educationList.add(education);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes an education from the list if it exists
    public void removeEducation (Education education) {
        if (educationList.contains(education)) {
            educationList.remove(education);
        }
    }

    // EFFECTS: returns the list of education
    public ArrayList<Education> getEducations() {
        return educationList;
    }

}
