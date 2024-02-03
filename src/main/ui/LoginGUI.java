package ui;

import model.MonitorConsumption;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Displays a window which will setup the user's name and calorie goal
public class LoginGUI implements ActionListener {
    public static final String JSON_STORE = "./data/consumption.json";
    public static MonitorConsumption userConsumption;
    public static JsonWriter jsonWriter;
    public static JsonReader jsonReader;
    private JPanel panel;
    private JFrame frame;
    private JButton login;
    private JButton load;
    private JLabel welcome;
    private JLabel enterName;
    private JTextField name;
    private JLabel setCalories;
    private JTextField calories;

    // MODIFIES: this
    //EFFECTS: initializes the window with respective components
    public LoginGUI() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setTitle("FatToFit");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        panel.setLayout(null);

        welcome = new JLabel("Welcome to your Diet Manager");
        welcome.setBounds(85, 40, 250, 25);
        panel.add(welcome);

        enterName = new JLabel("Enter Name");
        enterName.setBounds(40, 100, 80, 25);
        panel.add(enterName);


        addingComponents();

        login.setActionCommand("login");
        login.addActionListener(this);
        load.setActionCommand("load");
        load.addActionListener(this);
        frame.setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: Adds components like text field, label and buttons to the window
    private void addingComponents() {
        name = new JTextField();
        name.setBounds(130, 100, 165, 25);
        name.setFont(new Font("Times New Roman", Font.BOLD, 15));
        name.setBackground(Color.BLACK);
        name.setForeground(Color.white);
        name.setCaretColor(Color.white);
        panel.add(name);

        setCalories = new JLabel("Enter Calories Goal");
        setCalories.setBounds(40, 140, 200, 25);
        panel.add(setCalories);

        calories = new JTextField();
        calories.setBounds(160, 140, 165, 25);
        calories.setBackground(Color.BLACK);
        calories.setForeground(Color.white);
        calories.setCaretColor(Color.white);
        panel.add(calories);

        login = new JButton("Login");
        login.setBounds(132, 250, 100, 25);
        panel.add(login);

        load = new JButton("Load");
        load.setBounds(132, 200, 100, 25);
        panel.add(load);
    }

    // MODIFIES: this
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("login")) {
            if (!name.getText().equals("") && !calories.getText().equals("")) {
                userConsumption = new MonitorConsumption(name.getText() + "'s  Consumption");
                String calorieGoalCount = calories.getText();
                int calorieGoalCountInteger = Integer.parseInt(calorieGoalCount);
                userConsumption.calorieGoal = userConsumption.calorieGoal + calorieGoalCountInteger;
                saveConsumption();
                JOptionPane.showMessageDialog(null, "Welcome " + name.getText() + " !");
                new ConsumptionGUI();
                frame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Fields cannot be empty");
            }
        } else if (e.getActionCommand().equals("load")) {
            login.setEnabled(false);
            loadConsumption();
            new ConsumptionGUI();
            frame.setVisible(false);
        }
    }

    // MODIFIES: userConsumption
    // EFFECTS: saves the consumption to file
    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    private void saveConsumption() {
        try {
            jsonWriter.open();
            jsonWriter.write(userConsumption);
            jsonWriter.close();
            System.out.println("Saved " + userConsumption.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: userConsumption
    // EFFECTS: loads consumption from file
    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    private void loadConsumption() {
        try {
            userConsumption = jsonReader.read();
            System.out.println("Loaded " + userConsumption.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
