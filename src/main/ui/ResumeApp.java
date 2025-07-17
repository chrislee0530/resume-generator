package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// Resume Application
public class ResumeApp {
    private static final String JSON_STORE = "./data/resume.json";
    private Scanner input;
    private Resume resume;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the resume application
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    public ResumeApp() {
        init();
        runResume();
    }

    // MODIFIES: this
    // EFFECTS: initializes the lists of data
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void init() {
        input = new Scanner(System.in);
        resume = new Resume();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void runResume() {
        boolean keepGoing = true;
        String command = null;

        System.out.println("\nWelcome to Resume Generator!");
        System.out.println("\nLet's start building your resume!");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for using Resume Generator!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void processCommand(String command) {
        if (command.equals("p")) {
            profileCommand();
        } else if (command.equals("x")) {
            experienceCommand();
        } else if (command.equals("e")) {
            educationCommand();
        } else if (command.equals("s")) {
            skillsCommand();
        } else if (command.equals("pr")) {
            printResume();
        } else if (command.equals("sv")) {
            saveResume();
        } else if (command.equals("ld")) {
            loadResume();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads resume from file
    // NOTE: ADOPTED FROM DEMO APPLICATION
    private void loadResume() {
        try {
            resume = jsonReader.read();
            System.out.println("Loaded your resume from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the resume to file
    // NOTE: ADOPTED FROM DEMO APPLICATION
    private void saveResume() {
        try {
            jsonWriter.open();
            jsonWriter.write(resume);
            jsonWriter.close();
            System.out.println("Saved your resume to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: prints out the resume
    private void printResume() {
        if (resume.getProfile() == null) {
            System.out.println("\nNo profile found. Please add a profile first.");
            return;
        }

        System.out.println("\nHow many experiences would you like to include?");
        int numExperiences = input.nextInt();
        input.nextLine();

        int numSkills = 0;
        boolean notValid = true;
        while (notValid) {
            System.out.println("\nHow many top skills would you like to include?");
            numSkills = input.nextInt();
            input.nextLine();

            if (numSkills <= resume.getSkills().size()) {
                break;
            } else {
                System.out.println("You only have " + resume.getSkills().size()
                        + " skiils available. Please enter a valid number.");
            }

        }

        doubleLineFrontN();
        System.out.println("               RESUME");
        doubleLineBackN();

        System.out.println(resume.getProfile().getName());
        System.out.println(resume.getProfile().getNumber() + " | " + resume.getProfile().getEmail()
                + " | " + resume.getProfile().getAddress());
        System.out.println(resume.getProfile().getObjective());

        singleLine();
        if (!resume.getExperiences().isEmpty()) {
            System.out.println("\nEXPERIENCE\n");
            List<Experience> sorted = new ArrayList<>(resume.getExperiences());
            sorted.sort(Collections.reverseOrder());
            List<Experience> selected = sorted.subList(0, Math.min(numExperiences, sorted.size()));
            for (Experience e : selected) {
                System.out.println(e.getPosition() + " - " + e.getInstitution());
                if (e.getEndYear().equals("0")) {
                    String endYear = "Present";
                    System.out.println(e.getLocation() + " | " + e.getStartMonth() + "/" + e.getStartYear() + " - "
                            + endYear);
                } else {
                    System.out.println(e.getLocation() + " | " + e.getStartMonth() + "/" + e.getStartYear() + " - "
                            + e.getEndMonth() + "/" + e.getEndYear());
                }
                System.out.println(e.getDescription());
                System.out.println();
            }

            singleLine();
        } else {
            System.out.println("\nNo experiences to display");
        }

        if (!resume.getEducations().isEmpty()) {
            System.out.println("\nEDUCATION\n");
            resume.getEducations().sort(Collections.reverseOrder());
            for (Education e : resume.getEducations()) {
                System.out.println(e.getInstitution() + " - " + e.getLocation());
                System.out.println("GPA: " + e.getGpa());
                if (e.getEndYear().equals("0")) {
                    String endYear = "Present";
                    System.out.println(e.getLocation() + " | " + e.getStartMonth() + "/" + e.getStartYear() + " - "
                            + endYear);
                } else {
                    System.out.println(e.getLocation() + " | " + e.getStartMonth() + "/" + e.getStartYear() + " - "
                            + e.getEndMonth() + "/" + e.getEndYear());
                }
                System.out.println(e.getDescription());
                System.out.println();
            }
            singleLine();
        } else {
            System.out.println("\nNo educations to display");
            singleLine();
        }

        if (!resume.getSkills().isEmpty()) {
            System.out.println("\nSKILLS\n");
            resume.getSkills().sort(Comparator.comparingInt(Skill::getLevel).reversed());
            ArrayList<Skill> selectedSkills = new ArrayList<>(resume.getSkills()
                    .subList(0, Math.min(numSkills, resume.getSkills().size())));
            for (Skill s : selectedSkills) {
                System.out.println(s.getTitle() + " | " + "Level: " + s.getLevel());
            }
            singleLine();
        } else {
            System.out.println("\nNo skills to display.");
        }

        doubleLineFrontN();
        System.out.println("           END OF RESUME");
        doubleLineBackN();
    }

    // MODIFIES: this
    // EFFECTS: processes user command for profile section
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void skillsCommand() {
        String command;

        System.out.println("\nSelect from:");
        System.out.println("\tns -> add new skill");
        System.out.println("\tes -> edit skill");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("ns")) {
            newSkill();
        } else if (command.equals("es")) {
            editSkill();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new skill to the skills list
    private void newSkill() {
        input.nextLine();

        System.out.println("\nPlease enter the skill title/name: ");
        String skillTitle = input.nextLine();

        System.out.println("\nPlease enter your proficiency level (1-5): ");
        int skillLevel = input.nextInt();
        input.nextLine();

        Skill skill = new Skill(skillTitle, skillLevel);
        resume.addSkill(skill);

        System.out.println("\nA new skill has been successfully added!");
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: allows the user to edit an existing skill
    private void editSkill() {
        if (resume.getSkills().isEmpty()) {
            System.out.println("\nNo skills available to edit.");
            return;
        }
        System.out.println("\nSelect a skill to edit:");
        for (int i = 0; i < resume.getSkills().size(); i++) {
            System.out.println("\t" + i + " -> " + resume.getSkills().get(i).getTitle());
            System.out.println("Level: " + resume.getSkills().get(i).getLevel());
        }
        System.out.print("\nEnter the number of the skill you want to edit: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 0 || index >= resume.getSkills().size()) {
            System.out.println("Invalid selection.");
        }

        Skill selectedSkill = resume.getSkills().get(index);

        System.out.println("\nSelect a field to edit:");
        System.out.println("\t1 -> Skill Title/Name");
        System.out.println("\t2 -> Proficiency Level (1-5)");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("\nEnter new skill name: ");
                selectedSkill.setTitle(input.nextLine());
                break;
            case 2:
                System.out.print("\nEnter new proficiency level (1-10): ");
                selectedSkill.setLevel(input.nextInt());
                input.nextLine();
                break;
            default:
                System.out.println("Invalid selection.");
                return;
        }
        System.out.println("\nSkill successfully updated!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for profile section
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void profileCommand() {
        String command;

        System.out.println("\nSelect from:");
        System.out.println("\tnp -> add new profile");
        System.out.println("\tep -> edit profile");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("np")) {
            newProfile();
        } else if (command.equals("ep")) {
            editProfile();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new profile to the resume
    private void newProfile() {
        input.nextLine();

        System.out.println("\nPlease enter your full name: ");
        String name = input.nextLine();

        System.out.println("\nPlease enter your phone number: ");
        String number = input.nextLine();

        System.out.println("\nPlease enter your email: ");
        String email = input.nextLine();

        System.out.println("\nPlease enter your address: ");
        String address = input.nextLine();

        System.out.println("\nPlease enter your brief resume objective: ");
        String objective = input.nextLine();

        resume.setProfile(new Profile(name, number, email, address, objective));

        System.out.println("\nYour profile has been successfully added!");
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: allows user to edit their profile details
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void editProfile() {
        if (resume.getProfile() == null) {
            System.out.println("\nNo profile available to edit. Please create one first");
            return;
        }

        System.out.println("\nSelect a field to edit:");
        System.out.println("\t1 -> Name");
        System.out.println("\t2 -> Phone Number");
        System.out.println("\t3 -> Email");
        System.out.println("\t4 -> Address");
        System.out.println("\t5 -> Resume Objective");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.println("\nEnter new full name: ");
                resume.getProfile().setName(input.nextLine());
                break;
            case 2:
                System.out.println("\nEnter new phone number: ");
                resume.getProfile().setNumber(input.nextLine());
                break;
            case 3:
                System.out.println("\nEnter new email: ");
                resume.getProfile().setEmail(input.nextLine());
                break;
            case 4:
                System.out.println("\nEnter new address: ");
                resume.getProfile().setAddress(input.nextLine());
                break;
            case 5:
                System.out.println("\nEnter new resume objective: ");
                resume.getProfile().setObjective(input.nextLine());
                break;
            default:
                System.out.println("Invalid selection.");
                return;
        }

        System.out.println("\nProfile successfully updated!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for education section
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void educationCommand() {
        String command;

        System.out.println("\nSelect from:");
        System.out.println("\tned -> add new education");
        System.out.println("\teed -> edit education");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("ned")) {
            newEducation();
        } else if (command.equals("eed")) {
            editEducation();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new education to the education list
    private void newEducation() {
        input.nextLine();

        System.out.println("\nPlease enter the school name: ");
        String institution = input.nextLine();

        System.out.println("\nPlease enter the location: ");
        String location = input.nextLine();

        System.out.println("\nPlease enter the gpa: ");
        String gpa = input.nextLine();

        System.out.println("\nPlease enter the starting year: ");
        String startYear = input.nextLine();

        System.out.println("\nPlease enter the starting month: ");
        String startMonth = input.nextLine();

        System.out.println("\nPlease enter the ending year (0, if ongoing): ");
        String endYear = input.nextLine();

        System.out.println("\nPlease enter the ending month (0, if ongoing): ");
        String endMonth = input.nextLine();

        System.out.println("\nPlease enter a short description of your education: ");
        String description = input.nextLine();

        Education education = new Education(gpa, institution, location, startYear, startMonth, endYear, endMonth,
                description);
        resume.addEducation(education);

        System.out.println("\nA new education successfully added!");
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: allows user to edit an existing education
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    public void editEducation() {
        if (resume.getEducations().isEmpty()) {
            System.out.println("\nNo eudcations available to edit.");
            return;
        }
        System.out.println("\nSelect an education to edit:");
        for (int i = 0; i < resume.getEducations().size(); i++) {
            System.out.println("\t" + i + " -> " + resume.getEducations().get(i).getInstitution());
        }

        System.out.print("\nEnter the number of the education you want to edit: ");
        int index = input.nextInt();
        input.nextLine();
        if (index < 0 || index >= resume.getEducations().size()) {
            System.out.println("Invalid selection.");
        }

        Education selectedExperience = resume.getEducations().get(index);

        System.out.println("\nSelect a field to edit:");
        System.out.println("\t1 -> GPA");
        System.out.println("\t2 -> Institution");
        System.out.println("\t3 -> Location");
        System.out.println("\t4 -> Start Year");
        System.out.println("\t5 -> Start Month");
        System.out.println("\t6 -> End Year");
        System.out.println("\t7 -> End Month");
        System.out.println("\t8 -> Description");

        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("\nEnter new gpa: ");
                selectedExperience.setGpa(input.nextLine());
                break;
            case 2:
                System.out.print("\nEnter new school: ");
                selectedExperience.setInstitution(input.nextLine());
                break;
            case 3:
                System.out.print("\nEnter new location: ");
                selectedExperience.setLocation(input.nextLine());
                break;
            case 4:
                System.out.print("\nEnter new start year: ");
                selectedExperience.setStartYear(input.nextLine());
                input.nextLine();
                break;
            case 5:
                System.out.print("\nEnter new start month: ");
                selectedExperience.setStartMonth(input.nextLine());
                input.nextLine();
                break;
            case 6:
                System.out.print("\nEnter new end year (0 if ongoing): ");
                selectedExperience.setEndYear(input.nextLine());
                input.nextLine();
                break;
            case 7:
                System.out.print("\nEnter new end month (0 if ongoing): ");
                selectedExperience.setEndMonth(input.nextLine());
                input.nextLine();
                break;
            case 8:
                System.out.print("\nEnter new description: ");
                selectedExperience.setDescription(input.nextLine());
                break;
            default:
                System.out.println("Invalid selection.");
                return;
        }

        System.out.println("\nEducation successfully updated!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for experience section
    private void experienceCommand() {
        String command;

        System.out.println("\nSelect from:");
        System.out.println("\tnx -> add new experience");
        System.out.println("\tex -> edit experience");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("nx")) {
            newExperience();
        } else if (command.equals("ex")) {
            editExperience();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new experience to the experience list
    private void newExperience() {
        input.nextLine();

        System.out.println("\nPlease enter the position: ");
        String position = input.nextLine();

        System.out.println("\nPlease enter the institution name: ");
        String institution = input.nextLine();

        System.out.println("\nPlease enter the location: ");
        String location = input.nextLine();

        System.out.println("\nPlease enter the starting year: ");
        String startYear = input.nextLine();

        System.out.println("\nPlease enter the starting month: ");
        String startMonth = input.nextLine();

        System.out.println("\nPlease enter the ending year (0, if ongoing): ");
        String endYear = input.nextLine();

        System.out.println("\nPlease enter the ending month (0, if ongoing): ");
        String endMonth = input.nextLine();

        System.out.println("\nPlease enter a short description of your experience: ");
        String description = input.nextLine();

        Experience experience = new Experience(position, institution, location, startYear, startMonth, endYear,
                endMonth, description);
        resume.addExperience(experience);

        System.out.println("\nA new experience successfully added!");
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: allows user to edit an existing experience
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    public void editExperience() {
        if (resume.getExperiences().isEmpty()) {
            System.out.println("\nNo experiences available to edit.");
            return;
        }
        System.out.println("\nSelect an experience to edit:");
        for (int i = 0; i < resume.getExperiences().size(); i++) {
            System.out.println("\t" + i + " -> " + resume.getExperiences().get(i).getPosition());

            System.out.print("\nEnter the number of the experience you want to edit: ");
            int index = input.nextInt();
            input.nextLine();
            if (index < 0 || index >= resume.getExperiences().size()) {
                System.out.println("Invalid selection.");
            }

            Experience selectedExperience = resume.getExperiences().get(index);

            System.out.println("\nSelect a field to edit:");
            System.out.println("\t1 -> Position");
            System.out.println("\t2 -> Institution");
            System.out.println("\t3 -> Location");
            System.out.println("\t4 -> Start Year");
            System.out.println("\t5 -> Start Month");
            System.out.println("\t6 -> End Year");
            System.out.println("\t7 -> End Month");
            System.out.println("\t8 -> Description");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("\nEnter new position: ");
                    selectedExperience.setPosition(input.nextLine());
                    break;
                case 2:
                    System.out.print("\nEnter new institution: ");
                    selectedExperience.setInstitution(input.nextLine());
                    break;
                case 3:
                    System.out.print("\nEnter new location: ");
                    selectedExperience.setLocation(input.nextLine());
                    break;
                case 4:
                    System.out.print("\nEnter new start year: ");
                    selectedExperience.setStartYear(input.nextLine());
                    input.nextLine();
                    break;
                case 5:
                    System.out.print("\nEnter new start month: ");
                    selectedExperience.setStartMonth(input.nextLine());
                    input.nextLine();
                    break;
                case 6:
                    System.out.print("\nEnter new end year (0 if ongoing): ");
                    selectedExperience.setEndYear(input.nextLine());
                    input.nextLine();
                    break;
                case 7:
                    System.out.print("\nEnter new end month (0 if ongoing): ");
                    selectedExperience.setEndMonth(input.nextLine());
                    input.nextLine();
                    break;
                case 8:
                    System.out.print("\nEnter new description: ");
                    selectedExperience.setDescription(input.nextLine());
                    break;
                default:
                    System.out.println("Invalid selection.");
                    return;
            }

            System.out.println("\nExperience successfully updated!");
        }

    }

    // EFFECTS: displays menu of options to user
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> profile");
        System.out.println("\tx -> experience");
        System.out.println("\te -> education");
        System.out.println("\ts -> skills");
        System.out.println("\tpr -> PRINT RESUME");
        System.out.println("\tsv -> save resume");
        System.out.println("\tld -> load resume");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: prints out a line of dashes to act as a divider
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void singleLine() {
        System.out.println("\n------------------------------------");
    }

    // EFFECTS: prints out a double line of dashes to act as a divider (new line at
    // the beginning)
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void doubleLineFrontN() {
        System.out.println("\n====================================");
    }

    // EFFECTS: prints out a double line of dashes to act as a divider (new line at
    // the end)
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void doubleLineBackN() {
        System.out.println("====================================\n");
    }

}
