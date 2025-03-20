package ui;

import java.awt.*;

import javax.swing.*;

public class ResumeAppGUI extends JFrame {
    

    // MODIFIES: this
    // EFFECTS: initializes and creates ResumeAppGUI,
    public ResumeAppGUI() {
        super("Resume Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        initializeMenu();
        initailizePanel();
        initializeDiaplayArea();
        setVisible(true);
    }

    public static void main(String[] args) {
        new ResumeAppGUI();
    }

    // MODIFIES: this
    // EFFECTS: initializes the menu bar with save and load options
    private void initializeMenu() {
        
    }

    // MODIFIES: this
    // EFFECTS: initializes the button panel
    private void initailizePanel() {
        
    }

    // MODIFIES: this
    // EFFECTS: initializes the scrollable display area for resume content
    private void initializeDiaplayArea() {
        
    }

    // EFFECTS: Creates and returns a button with an action listener
    private JButton createButton(String label) {
        return new JButton(); //stub
        
    }

}
