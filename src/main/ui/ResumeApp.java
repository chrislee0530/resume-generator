package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Education;
import model.EducationList;
import model.Experience;
import model.ExperienceList;
import model.Profile;
import model.Skill;
import model.Skills;

// Resume Application
public class ResumeApp {
    private Scanner input;
    private Profile profile;
    private EducationList educationList;
    private ExperienceList experienceList;
    private Skills skillsList;


    // EFFECTS: runs the resume application
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    public ResumeApp() {
        runResume();
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

        System.out.println("\nGoodbye!");
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command for profile section
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void skillsCommand() {
        String command;

        System.out.println("\nSelect from:");
        System.out.println("\tnx -> add new skill");
        System.out.println("\tex -> edit skill");
        
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

        System.out.println("\nPlease enter the skill title/name: ");
        String skillTitle = input.nextLine();

        System.out.println("\nPlease enter your proficiency level (1-5): ");
        int skillLevel = input.nextInt();
        input.nextLine(); 

        Skill skill = new Skill(skillTitle, skillLevel);
        skillsList.addSkill(skill);

        System.out.println("\nA new skill has been successfully added!");
    }   

    // MODIFIES: this
    // EFFECTS: processes user command for profile section
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void profileCommand() {
        String command;

        System.out.println("\nSelect from:");
        System.out.println("\tnx -> add new profile");
        System.out.println("\tex -> edit profile");
        
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

    Profile profile = new Profile(name, number, email, address, objective);
    
    this.profile = profile;

    System.out.println("\nYour profile has been successfully added!");
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: allows user to edit their profile details
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    private void editProfile() {
        if (profile == null) {
            System.out.println("\nNo profile available to edit. Please create one first");
        }

        System.out.println("\nSelect a field to edit:");
        System.out.println("\t1 -> Name");
        System.out.println("\t2 -> Phone Number");
        System.out.println("\t3 -> Email");
        System.out.println("\t4 -> Address");
        System.out.println("\t5 -> Resume Objective");

        int choice = input.nextInt();
        input.nextLine();

        switch(choice) {
            case 1:
                System.out.println("\nEnter new full name: ");
                profile.setName(input.nextLine());
                break;
            case 2:
                System.out.println("\nEnter new phone number: ");
                profile.setNumber(input.nextLine());
                break;
            case 3:
                System.out.println("\nEnter new email: ");
                profile.setEmail(input.nextLine());
                break;
            case 4:
                System.out.println("\nEnter new address: ");
                profile.setAddress(input.nextLine());
                break;
            case 5:
                System.out.println("\nEnter new resume objective: ");
                profile.setObjective(input.nextLine());
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
        System.out.println("\tnx -> add new education");
        System.out.println("\tex -> edit education");
        
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

        System.out.println("\nPlease enter the institution name: ");
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

        System.out.println("\nPlease enter a short description of your experience: ");
        String description = input.nextLine();

        Education education = new Education(gpa, institution, location, startYear, startMonth, endYear, endMonth, description);
        educationList.addEducation(education);

        System.out.println("\nA new experience successfully added!");
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: allows user to edit an existing education
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    public void editEducation() {
        if (educationList.getEducations().isEmpty()) {
            System.out.println("\nNo eudcations available to edit.");
        }
        System.out.println("\nSelect an education to edit:");
        for (int i = 0; i < educationList.getEducations().size(); i++) {
            System.out.println("\t" + i + " -> " + educationList.getEducations().get(i).getInstitution());
            }

        System.out.print("\nEnter the number of the education you want to edit: ");
        int index = input.nextInt();
        input.nextLine();
        if (index < 0 || index >= educationList.getEducations().size()) {
            System.out.println("Invalid selection.");
        }

        Education selectedExperience = educationList.getEducations().get(index);

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

        Experience experience = new Experience(position, institution, location, startYear, startMonth, endYear, endMonth, description);
        experienceList.addExperience(experience);

        System.out.println("\nA new experience successfully added!");
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: allows user to edit an existing experience
    // NOTE: CODE BASED OFF OF FITLIFEGYM LECTURE LAB
    public void editExperience() {
        if (experienceList.getExperiences().isEmpty()) {
            System.out.println("\nNo experiences available to edit.");
        }
        System.out.println("\nSelect an experience to edit:");
        for (int i = 0; i < experienceList.getExperiences().size(); i++) {
        System.out.println("\t" + i + " -> " + experienceList.getExperiences().get(i).getPosition());

        System.out.print("\nEnter the number of the experience you want to edit: ");
        int index = input.nextInt();
        input.nextLine();
        if (index < 0 || index >= experienceList.getExperiences().size()) {
            System.out.println("Invalid selection.");
        }

        Experience selectedExperience = experienceList.getExperiences().get(index);

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
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp -> profile");
        System.out.println("\tx -> experience");
        System.out.println("\te -> education");
        System.out.println("\ts -> skills");
        System.out.println("\tq -> quit");
    }


    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }

}
