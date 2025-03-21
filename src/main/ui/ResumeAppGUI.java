package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;

import model.Education;
import model.EducationList;
import model.Experience;
import model.ExperienceList;
import model.Profile;

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

    private Profile profile;
    private ExperienceList experienceList;
    private EducationList educationList;

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
    }

    public static void main(String[] args) {
        new ResumeAppGUI();
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu bar with save and load options
    private void initializeMenu() {
        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: initializes the button panel
    private void initializePanel() {
        panel = new JPanel(new GridLayout(1, 5));
        addProfileButton = createButton("Add Profile", this::handleAddProfile);
        addProfileButton.setPreferredSize(new Dimension(140, 35));
        experienceButton = createButton("Experience", this::handleExperienceMenu);
        educationButton = createButton("Education", this::handleEducationMenu);
        skillsButton = createButton("Skills", e -> {
        });
        generateResumeButton = createButton("Generate Resume", this::handleGenerateResume);
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
    private JButton createButton(String label, ActionListener a) {
        JButton button = new JButton(label);
        button.addActionListener(a);
        return button;
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

        int result = JOptionPane.showConfirmDialog(this, eduPanel, "Enter Education", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Education edu = new Education(gpaField.getText(), institutionField.getText(), locationField.getText(),
                    startYearField.getText(), startMonthField.getText(), endYearField.getText(),
                    endMonthField.getText(), descriptionField.getText());
            if (educationList == null)
                educationList = new EducationList();
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
        printProfile();
        printExperiences();
        printEducations();
    }

    // EFFECTS: prints out profile on resumeDisplay
    private void printProfile() {
        if (profile != null) {
            resumeDisplay.append("\n=========== RESUME ===========\n\n");
            resumeDisplay.append(profile.getName() + "\n");
            resumeDisplay.append(profile.getNumber() + "|" + profile.getEmail() + "|" + profile.getAddress()+ "\n");
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
}
