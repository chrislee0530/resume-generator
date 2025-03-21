package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

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
        experienceButton = createButton("Add Experience", this::handleAddExperience);
        educationButton = createButton("Add Education", e -> {});
        skillsButton = createButton("Add Skill", e -> {});
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

    // NOTE: this code is based off of SmartHome actionPerformed() code
    // MODIFIES: this
    // EFFECTS: opens a panel with 8 textfields to input experience details
    private void handleAddExperience(ActionEvent e) {
    //     JTextField positionField = new JTextField(10);
    //     JTextField institutionField = new JTextField(10);
    //     JTextField locationField = new JTextField(10);
    //     JTextField startYearField = new JTextField(5);
    //     JTextField startMonthField = new JTextField(5);
    //     JTextField endYearField = new JTextField(5);
    //     JTextField endMonthField = new JTextField(5);
    //     JTextField descriptionField = new JTextField(10);

    //     JPanel panel = new JPanel(new GridLayout(8, 2));
    //     panel.add(new JLabel("Position:"));
    //     panel.add(positionField);
    //     panel.add(new JLabel("Company:"));
    //     panel.add(institutionField);
    //     panel.add(new JLabel("Location:"));
    //     panel.add(locationField);
    //     panel.add(new JLabel("Start Year:"));
    //     panel.add(startYearField);
    //     panel.add(new JLabel("Start Month:"));
    //     panel.add(startMonthField);
    //     panel.add(new JLabel("End Year:"));
    //     panel.add(endYearField);
    //     panel.add(new JLabel("End Month:"));
    //     panel.add(endMonthField);
    //     panel.add(new JLabel("Description:"));
    //     panel.add(descriptionField);

    //     int result = JOptionPane.showConfirmDialog(this, panel, "Enter Experience", JOptionPane.OK_CANCEL_OPTION);
    //     if (result == JOptionPane.OK_OPTION) {
    //         String expText = "Experience:\n" +
    //                 positionField.getText() + " - " + institutionField.getText() + "\n" +
    //                 locationField.getText() + " | " +
    //                 startMonthField.getText() + "/" + startYearField.getText() +
    //                 " - "
    //                 + (endYearField.getText().equals("0") ? "Present"
    //                         : endMonthField.getText() + "/" + endYearField.getText())
    //                 +
    //                 "\n" + descriptionField.getText() + "\n\n";
    //         displayArea.append(expText);
    //    }
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
    }
}
