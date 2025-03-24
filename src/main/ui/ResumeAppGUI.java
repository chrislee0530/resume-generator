package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

import model.*;
import persistence.JsonWriter;
import persistence.JsonReader;

public class ResumeAppGUI extends JFrame {
    private JMenuBar menuBar;
    private JPanel panel;
    private JButton addProfileButton;
    private JButton experienceButton;
    private JButton educationButton;
    private JButton skillsButton;
    private JButton generateResumeButton;
    private JTextArea workDisplay;
    private JTextArea resumeDisplay;
    private ImageIcon profileImg;
    private ImageIcon expImg;
    private ImageIcon eduImg;
    private ImageIcon skillsImg;
    private ImageIcon checkImg;

    private Profile profile;
    private ExperienceList experienceList;
    private EducationList educationList;
    private Skills skills;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/resume.json";

    // MODIFIES: this
    // EFFECTS: initializes and creates ResumeAppGUI,
    public ResumeAppGUI() {
        super("Resume Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        initializeMenu();
        initializePanel();
        initializeDisplayArea();
        setVisible(true);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    public static void main(String[] args) {
        new ResumeAppGUI().showSplashScreen();
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu bar with save and load options
    private void initializeMenu() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        saveItem.addActionListener(this::handleSave);
        loadItem.addActionListener(this::handleLoad);
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: initializes the button panel
    private void initializePanel() {
        profileImg = new ImageIcon("/Users/chrislee/Desktop/profile.png");
        expImg = new ImageIcon("/Users/chrislee/Desktop/experience.png");
        eduImg = new ImageIcon("/Users/chrislee/Desktop/education.png");
        skillsImg = new ImageIcon("/Users/chrislee/Desktop/skills.png");
        checkImg = new ImageIcon("/Users/chrislee/Desktop/check.png");
        panel = new JPanel(new GridLayout(1, 5));
        addProfileButton = createButton("Add Profile", this::handleAddProfile, profileImg);
        addProfileButton.setPreferredSize(new Dimension(140, 35));
        experienceButton = createButton("Experience", this::handleExperienceMenu, expImg);
        educationButton = createButton("Education", this::handleEducationMenu, eduImg);
        skillsButton = createButton("Skills", this::handleSkillsMenu, skillsImg);
        generateResumeButton = createButton("Generate Resume", this::handleGenerateResume, checkImg);
        panel.add(addProfileButton);
        panel.add(experienceButton);
        panel.add(educationButton);
        panel.add(skillsButton);
        panel.add(generateResumeButton);
        add(panel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes the scrollable display area for resume content
    private void initializeDisplayArea() {
        workDisplay = new JTextArea(20, 50);
        resumeDisplay = new JTextArea(20, 50);
        workDisplay.setEditable(false);
        resumeDisplay.setEditable(false);
        JScrollPane workScrollPane = new JScrollPane(workDisplay);
        JScrollPane resumeScrollPane = new JScrollPane(resumeDisplay);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, workScrollPane, resumeScrollPane);
        splitPane.setDividerLocation(300);
        add(splitPane, BorderLayout.CENTER);
    }

    // EFFECTS: Creates and returns a button with an action listener
    private JButton createButton(String label, ActionListener a, ImageIcon img) {
        JButton button = new JButton(label, img);
        button.addActionListener(a);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: stores all information in one resume and saves
    private void handleSave(ActionEvent e) {
        Resume resume = new Resume();
        if (profile != null) {
            resume.setProfile(profile);
        }
        if (experienceList != null) {
            for (Experience exp : experienceList.getExperiences()) {
                resume.addExperience(exp);
            }
        }
        if (educationList != null) {
            for (Education edu : educationList.getEducations()) {
                resume.addEducation(edu);
            }
        }
        if (skills != null) {
            for (Skill s : skills.getSkills()) {
                resume.addSkill(s);
            }
        }
        saveResume(resume);
    }

    // MODIFIES: this
    // EFFECTS: saves resume into JSON file
    private void saveResume(Resume resume) {
        try {
            jsonWriter.open();
            jsonWriter.write(resume);
            jsonWriter.close();
            workDisplay.append("Saved your resume to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            workDisplay.append("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads resume from JSON file and sets data to local variables
    private void handleLoad(ActionEvent e) {
        try {
            Resume resume = jsonReader.read();
            this.profile = resume.getProfile();
            this.experienceList = resume.getExperienceList();
            this.educationList = resume.getEducationList();
            this.skills = resume.getSkillsList();
            workDisplay.append("Loaded your resume from " + JSON_STORE);
        } catch (IOException exception) {
            workDisplay.append("Unable to write to file: " + JSON_STORE);
        }
    }

    // NOTE: this code is based off of SmartHome actionPerformed() code
    // MODIFIES: this
    // EFFECTS: opens a panel with 5 textfields to input profile details
    private void handleAddProfile(ActionEvent e) {
        JTextField nameField = new JTextField(10);
        JTextField phoneField = new JTextField(10);
        JTextField emailField = new JTextField(10);
        JTextField addressField = new JTextField(10);
        JTextField objectiveField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Objective:"));
        panel.add(objectiveField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Enter Profile", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            profile = new Profile(nameField.getText(), phoneField.getText(), emailField.getText(),
                    addressField.getText(), objectiveField.getText());
            workDisplay.append("Profile successfully added!\n\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: shows options to add, remove, or reorder skills
    private void handleSkillsMenu(ActionEvent e) {

        String[] options = { "Add Skill", "Remove ALL Skills", "Reorder Skills" };
        int choice = JOptionPane.showOptionDialog(this, "What would you like to do?",
                "Skills Options", 0, 3, null, options, options[0]);

        if (choice == 0) {
            handleAddSkill();
        } else if (choice == 1) {
            handleRemoveSkills();
        } else if (choice == 2) {
            handleReorderSkills();
        }
    }

    // MODIFIES: this
    // EFFECTS: opens an skills panel with 2 textfields to input skill detail
    private void handleAddSkill() {
        JTextField titleField = new JTextField(10);
        JTextField levelField = new JTextField(5);

        JPanel skillPanel = new JPanel(new GridLayout(2, 2));
        skillPanel.add(new JLabel("Title:"));
        skillPanel.add(titleField);
        skillPanel.add(new JLabel("Level:"));
        skillPanel.add(levelField);

        int result = JOptionPane.showConfirmDialog(this, skillPanel, "Enter Skill", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Skill skill = new Skill(titleField.getText(), Integer.parseInt(levelField.getText()));
            if (skills == null) {
                skills = new Skills();
            }
            skills.addSkill(skill);
            workDisplay.append("Skill successfully added!\n\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes all skills from user resume
    private void handleRemoveSkills() {
        if (skills.getSkills().isEmpty()) {
            workDisplay.append("No skills to remove.");
        } else {
            skills.getSkills().removeAll(skills.getSkills());
            workDisplay.append("All skills removed!\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: reorders all skills from highest level to lowest
    private void handleReorderSkills() {
        if (skills.getSkills().isEmpty()) {
            workDisplay.append("No skills to reorder.");
        } else {
            ArrayList<Skill> skillList = skills.getSkills();
            skillList = skills.topSkills(skills.getSkills().size());
            skills.getSkills().removeAll(skills.getSkills());
            for (Skill s : skillList) {
                skills.addSkill(s);
            }
            workDisplay.append("Skills reordered from highest to lowest level!\n\n");
        }

    }

    // MODIFIES: this
    // EFFECTS: shows options to add or remove education
    private void handleEducationMenu(ActionEvent e) {
        String[] options = { "Add Education", "Remove ALL Educations" };
        int choice = JOptionPane.showOptionDialog(this, "What would you like to do?",
                "Education Options", 0,
                3, null, // I NEED TO ADD IMAGE HERE LATER
                options, options[0]);

        if (choice == 0) {
            handleAddEducation();
        } else if (choice == 1) {
            handleRemoveEducation();
        }
    }

    // MODIFIES: this
    // EFFECTS: user can choose which education to remove and removes from list
    private void handleRemoveEducation() {
        ArrayList<Education> educations = educationList.getEducations();
        if (educations.isEmpty()) {
            workDisplay.append("No educations to remove.\n\n");
            return;
        }
        educations.removeAll(educations);
        workDisplay.append("All educations removed!\n");
    }

    @SuppressWarnings("methodlength")
    // NOTE: this code is based off of SmartHome actionPerformed() code
    // MODIFIES: this
    // EFFECTS: opens an education panel with 8 textfields to input education
    // details
    private void handleAddEducation() {
        JTextField gpaField = new JTextField(10);
        JTextField institutionField = new JTextField(10);
        JTextField locationField = new JTextField(10);
        JTextField startYearField = new JTextField(5);
        JTextField startMonthField = new JTextField(5);
        JTextField endYearField = new JTextField(5);
        JTextField endMonthField = new JTextField(5);
        JTextField descriptionField = new JTextField(10);

        JPanel eduPanel = new JPanel(new GridLayout(8, 2));
        eduPanel.add(new JLabel("GPA:"));
        eduPanel.add(gpaField);
        eduPanel.add(new JLabel("Institution:"));
        eduPanel.add(institutionField);
        eduPanel.add(new JLabel("Location:"));
        eduPanel.add(locationField);
        eduPanel.add(new JLabel("Start Year:"));
        eduPanel.add(startYearField);
        eduPanel.add(new JLabel("Start Month:"));
        eduPanel.add(startMonthField);
        eduPanel.add(new JLabel("End Year:"));
        eduPanel.add(endYearField);
        eduPanel.add(new JLabel("End Month:"));
        eduPanel.add(endMonthField);
        eduPanel.add(new JLabel("Description:"));
        eduPanel.add(descriptionField);

        addEducation(eduPanel, gpaField, institutionField, locationField,
                startYearField, startMonthField, endYearField, endMonthField, descriptionField);
    }

    // EFFECTS: adds an experience to user resume
    private void addEducation(JPanel eduPanel, JTextField gpaField, JTextField institutionField,
            JTextField locationField, JTextField startYearField, JTextField startMonthField, JTextField endYearField,
            JTextField endMonthField, JTextField descriptionField) {

        int result = JOptionPane.showConfirmDialog(this, eduPanel, "Enter Education", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Education edu = new Education(gpaField.getText(), institutionField.getText(), locationField.getText(),
                    startYearField.getText(), startMonthField.getText(), endYearField.getText(),
                    endMonthField.getText(), descriptionField.getText());
            if (educationList == null) {
                educationList = new EducationList();
            }
            educationList.addEducation(edu);
            workDisplay.append("Education successfully added!\n\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: shows options to add or remove experience
    private void handleExperienceMenu(ActionEvent e) {
        String[] options = { "Add Experience", "Remove ALL Experiences" };
        int choice = JOptionPane.showOptionDialog(this, "What would you like to do?",
                "Experience Options", 0,
                3, null, // I NEED TO ADD IMAGE HERE LATER
                options, options[0]);

        if (choice == 0) {
            handleAddExperience();
        } else if (choice == 1) {
            handleRemoveExperience();
        }
    }

    // MODIFIES: this
    // EFFECTS: user can choose which experience to remove and removes from list
    private void handleRemoveExperience() {
        ArrayList<Experience> experiences = experienceList.getExperiences();
        if (experiences.isEmpty()) {
            workDisplay.append("No experiences to remove.\n\n");
            return;
        }
        experiences.removeAll(experiences);
        workDisplay.append("All experiences removed!\n");
    }

    @SuppressWarnings("methodlength")
    // NOTE: this code is based off of SmartHome actionPerformed() code
    // MODIFIES: this
    // EFFECTS: opens an experience panel with 8 textfields to input experience
    // details
    private void handleAddExperience() {
        JTextField positionField = new JTextField(10);
        JTextField institutionField = new JTextField(10);
        JTextField locationField = new JTextField(10);
        JTextField startYearField = new JTextField(5);
        JTextField startMonthField = new JTextField(5);
        JTextField endYearField = new JTextField(5);
        JTextField endMonthField = new JTextField(5);
        JTextField descriptionField = new JTextField(10);

        JPanel expPanel = new JPanel(new GridLayout(8, 2));
        expPanel.add(new JLabel("Position:"));
        expPanel.add(positionField);
        expPanel.add(new JLabel("Company:"));
        expPanel.add(institutionField);
        expPanel.add(new JLabel("Location:"));
        expPanel.add(locationField);
        expPanel.add(new JLabel("Start Year:"));
        expPanel.add(startYearField);
        expPanel.add(new JLabel("Start Month:"));
        expPanel.add(startMonthField);
        expPanel.add(new JLabel("End Year:"));
        expPanel.add(endYearField);
        expPanel.add(new JLabel("End Month:"));
        expPanel.add(endMonthField);
        expPanel.add(new JLabel("Description:"));
        expPanel.add(descriptionField);

        addExperience(expPanel, positionField, institutionField, locationField, startYearField,
                startMonthField, endYearField, endMonthField, descriptionField);
    }

    // EFFECTS: adds an experience to user resume
    private void addExperience(JPanel expPanel, JTextField positionField, JTextField institutionField,
            JTextField locationField, JTextField startYearField, JTextField startMonthField, JTextField endYearField,
            JTextField endMonthField, JTextField descriptionField) {

        int result = JOptionPane.showConfirmDialog(this, expPanel, "Enter Experience", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Experience exp = new Experience(
                    positionField.getText(),
                    institutionField.getText(),
                    locationField.getText(),
                    startYearField.getText(),
                    startMonthField.getText(),
                    endYearField.getText(),
                    endMonthField.getText(),
                    descriptionField.getText());
            if (experienceList == null) {
                experienceList = new ExperienceList();
            }
            experienceList.addExperience(exp);
            workDisplay.append("Experience successfully added!\n\n");

        }
    }

    // REQUIRES: model data to be collected before this method is called
    // EFFECTS: generates resume and displays content in the display area
    private void handleGenerateResume(ActionEvent e) {
        resumeDisplay.setText("");
        printProfile();
        printExperiences();
        printEducations();
        printSkills();
    }

    // EFFECTS: prints out profile on resumeDisplay
    private void printProfile() {
        if (profile != null) {
            resumeDisplay.append("\n=========== RESUME ===========\n\n");
            resumeDisplay.append(profile.getName() + "\n");
            resumeDisplay.append(profile.getNumber() + "|" + profile.getEmail() + "|" + profile.getAddress() + "\n");
            resumeDisplay.append(profile.getObjective() + "\n\n");
        } else {
            resumeDisplay.append("No profile added.\n\n");
        }
    }

    // EFFECTS: prints out experiences on resumeDisplay
    private void printExperiences() {
        if (experienceList != null && !experienceList.getExperiences().isEmpty()) {
            resumeDisplay.append("EXPERIENCE\n\n");
            for (Experience exp : experienceList.getExperiences()) {
                resumeDisplay.append(exp.getPosition() + " - " + exp.getInstitution() + "\n");
                if (exp.getEndYear().equals("0")) {
                    resumeDisplay.append(exp.getLocation() + " | " + exp.getStartMonth() + "/" + exp.getStartYear()
                            + " - Present\n");
                } else {
                    resumeDisplay.append(exp.getLocation() + " | " + exp.getStartMonth() + "/" + exp.getStartYear()
                            + " - " + exp.getEndMonth() + "/" + exp.getEndYear() + "\n");
                }
                resumeDisplay.append(exp.getDescription() + "\n\n");
            }
        } else {
            resumeDisplay.append("\nNo experiences to display.\n");
        }
    }

    // EFFECTS: prints out educations on resumeDisplay
    private void printEducations() {
        if (educationList != null && !educationList.getEducations().isEmpty()) {
            resumeDisplay.append("EDUCATION\n\n");
            for (Education edu : educationList.getEducations()) {
                resumeDisplay.append(edu.getInstitution() + " - " + edu.getLocation() + "\n");
                resumeDisplay.append("GPA: " + edu.getGpa() + "\n");
                if (edu.getEndYear().equals("0")) {
                    resumeDisplay.append(edu.getLocation() + " | " + edu.getStartMonth() + "/" + edu.getStartYear()
                            + " - Present\n");
                } else {
                    resumeDisplay.append(edu.getLocation() + " | " + edu.getStartMonth() + "/" + edu.getStartYear()
                            + " - " + edu.getEndMonth() + "/" + edu.getEndYear() + "\n");
                }
                resumeDisplay.append(edu.getDescription() + "\n\n");
            }
        } else {
            resumeDisplay.append("\nNo education to display.\n");
        }
    }

    // EFFECTS: prints out skills on resumeDisplay
    private void printSkills() {
        if (skills != null && !skills.getSkills().isEmpty()) {
            resumeDisplay.append("SKILLS\n\n");
            for (Skill s : skills.getSkills()) {
                resumeDisplay.append(s.getTitle() + " | " + "Level: " + s.getLevel() + "\n");
            }
        } else {
            resumeDisplay.append("\nNo skills to display.\n");
        }
    }

    // EFFECTS: shows a resume icon for 2 seconds in the middle of the screen
    // NOTE: Here are the reference links I used to build this method:
    // https://stackoverflow.com/questions/69275599/jframe-splash-screen-wont-display-an-image
    // https://docs.oracle.com/javase/tutorial/uiswing/misc/splashscreen.html
    // https://www.geeksforgeeks.org/java-program-to-print-screen-resolution/

    private void showSplashScreen() {
        JWindow splash = new JWindow();
        ImageIcon splashImage = new ImageIcon("/Users/chrislee/Desktop/resume.png");
        JLabel imageLabel = new JLabel(splashImage);
        splash.getContentPane().add(imageLabel);
        splash.pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - splash.getSize().width) / 2;
        int y = (screenSize.height - splash.getSize().height) / 2;
        splash.setLocation(x, y);

        splash.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // shouldn't catch anything
        }

        splash.setVisible(false);
        splash.dispose();
    }
}
