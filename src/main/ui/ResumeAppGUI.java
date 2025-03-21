package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

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
    private JTextArea displayArea;

    private Profile profile;
    private ExperienceList experienceList;

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
        experienceButton = createButton("Experience", this::handleExperienceMenu);
        educationButton = createButton("Education", e -> {
        });
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
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
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

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Profile", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            profile = new Profile(nameField.getText(), phoneField.getText(), emailField.getText(),
                    addressField.getText(), objectiveField.getText());
            displayArea.append("Profile successfully added!\n\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: shows options to add or remove experience
    private void handleExperienceMenu(ActionEvent e) {
        String[] options = { "Add Experience", "Remove Experience" };
        int choice = JOptionPane.showOptionDialog(this, "What would you like to do?", "Experience Options", 0,
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
        
    }

    // NOTE: this code is based off of SmartHome actionPerformed() code
    // MODIFIES: this
    // EFFECTS: opens a panel with 8 textfields to input experience details
    private void handleAddExperience() {
        JTextField positionField = new JTextField(10);
        JTextField institutionField = new JTextField(10);
        JTextField locationField = new JTextField(10);
        JTextField startYearField = new JTextField(5);
        JTextField startMonthField = new JTextField(5);
        JTextField endYearField = new JTextField(5);
        JTextField endMonthField = new JTextField(5);
        JTextField descriptionField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Position:"));
        panel.add(positionField);
        panel.add(new JLabel("Company:"));
        panel.add(institutionField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Start Year:"));
        panel.add(startYearField);
        panel.add(new JLabel("Start Month:"));
        panel.add(startMonthField);
        panel.add(new JLabel("End Year:"));
        panel.add(endYearField);
        panel.add(new JLabel("End Month:"));
        panel.add(endMonthField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Experience", JOptionPane.OK_CANCEL_OPTION);
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
            displayArea.append("Experience successfully added!\n\n");
        }
    }

    // EFFECTS: generates resume and displays content in the display area
    // REQUIRES: model data to be collected before this method is called
    private void handleGenerateResume(ActionEvent e) {
        if (profile != null) {
            displayArea.append("\n=========== RESUME ===========\n");
            displayArea.append("Name: " + profile.getName() + "\n");
            displayArea.append("Phone: " + profile.getNumber() + "\n");
            displayArea.append("Email: " + profile.getEmail() + "\n");
            displayArea.append("Address: " + profile.getAddress() + "\n");
            displayArea.append("Objective: " + profile.getObjective() + "\n\n");
        } else {
            displayArea.append("No profile added.\n\n");
        }

        if (experienceList != null && !experienceList.getExperiences().isEmpty()) {
            displayArea.append("EXPERIENCE\n\n");
            for (Experience exp : experienceList.getExperiences()) {
                displayArea.append(exp.getPosition() + " - " + exp.getInstitution() + "\n");
                if (exp.getEndYear().equals("0")) {
                    displayArea.append(exp.getLocation() + " | " + exp.getStartMonth() + "/" + exp.getStartYear() + " - Present\n");
                } else {
                    displayArea.append(exp.getLocation() + " | " + exp.getStartMonth() + "/" + exp.getStartYear()
                            + " - " + exp.getEndMonth() + "/" + exp.getEndYear() + "\n");
                }
                displayArea.append(exp.getDescription() + "\n\n");
            }
        } else {
            displayArea.append("\nNo experiences to display");
        }
    }
}
