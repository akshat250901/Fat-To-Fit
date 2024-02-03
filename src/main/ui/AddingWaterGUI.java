package ui;

import model.Water;
import model.exception.IncorrectDateFormatException;
import model.exception.NegativeWaterException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static ui.LoginGUI.*;
import static ui.LoginGUI.JSON_STORE;

// Displays a window which will ask for user input of water
public class AddingWaterGUI implements ActionListener {
    private JPanel waterPanel;
    private JFrame waterFrame;
    private JButton addWater;
    private JLabel waterAmount;
    private JLabel waterDate;
    private JTextField amount;
    private JTextField date;
    private JButton goBack;


    // MODIFIES: this
    // EFFECTS: Initializes the window with different components
    public AddingWaterGUI() {
        waterPanel = new JPanel();
        waterFrame = new JFrame();
        waterFrame.setTitle("Log in Water Consumption");
        waterFrame.setSize(500, 250);
        waterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        waterFrame.add(waterPanel);

        waterPanel.setLayout(null);

        waterAmount = new JLabel("Amount of water in ml (Integer)");
        waterAmount.setBounds(40, 40, 350, 25);
        waterPanel.add(waterAmount);

        amount = new JTextField();
        amount.setBounds(245, 40, 50, 25);
        amount.setBackground(Color.BLACK);
        amount.setForeground(Color.white);
        amount.setCaretColor(Color.white);
        waterPanel.add(amount);

        addingComponents();

        goBack.setActionCommand("previous");
        goBack.addActionListener(this);
        addWater.setActionCommand("addingWater");
        addWater.addActionListener(this);
        waterFrame.setVisible(true);

    }


    // MODIFIES: userConsumtpion
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

    // MODIFIES: this
    // EFFECTS: Adds components like text field, label and buttons to the window
    private void addingComponents() {
        waterDate = new JLabel("Water consumption date (YYYY-MM-DD)");
        waterDate.setBounds(40, 80, 300, 25);
        waterPanel.add(waterDate);

        date = new JTextField();
        date.setBounds(300, 80, 150, 25);
        date.setBackground(Color.BLACK);
        date.setForeground(Color.white);
        date.setCaretColor(Color.white);
        waterPanel.add(date);

        addWater = new JButton("Save this water");
        addWater.setBounds(140, 120, 200, 25);
        waterPanel.add(addWater);

        goBack = new JButton("Go back");
        goBack.setBounds(140, 150, 200, 25);
        waterPanel.add(goBack);


    }

    // MODIFIES: this, userConsumption
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addingWater")) {
            loadConsumption();
            savingWater();
        } else if (e.getActionCommand().equals("previous")) {
            new ConsumptionGUI();
            waterFrame.setVisible(false);
        }
    }


    // MODIFIES: this, userConsumption
    // EFFECTS: Saves the water input by the user and shows a dialog box with a message
    private void savingWater() {
        if (!amount.getText().equals("") && !date.getText().equals("")) {
            String waterString = amount.getText();
            int waterInteger = Integer.parseInt(waterString);
            String dateString = date.getText();
            SimpleDateFormat dateInFormat = new SimpleDateFormat(dateString);
            try {
                userConsumption.addWater(new Water(waterInteger,dateInFormat));
                saveConsumption();
                JOptionPane.showMessageDialog(null, "Water consumption was added!");
                new ConsumptionGUI();
                waterFrame.setVisible(false);
            } catch (NegativeWaterException | IncorrectDateFormatException e) {
                JOptionPane.showMessageDialog(null,"Input data is invalid");
            }


        } else {
            JOptionPane.showMessageDialog(null,"Fields cannot be empty");
        }
    }

}
