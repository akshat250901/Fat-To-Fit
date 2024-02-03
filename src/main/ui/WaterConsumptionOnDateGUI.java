package ui;

import model.MonitorConsumption;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Displays a window which will ask for a date from user and display water consumed on that date
public class WaterConsumptionOnDateGUI implements ActionListener {
    private JFrame findFrame;
    private JPanel findPanel;
    private JButton findButton;
    private JLabel date;
    private JTextField dateInput;
    private JButton goBack;
    private MonitorConsumption readingWater;


    // MODIFIES: this
    //EFFECTS: initializes the window with respective panel and buttons
    public WaterConsumptionOnDateGUI() {
        findFrame = new JFrame("Total water consumption");
        findPanel = new JPanel();
        findFrame.setSize(450, 250);
        findFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        findFrame.add(findPanel);
        findPanel.setLayout(null);

        date = new JLabel("Input desired date (YYYY-MM-DD)");
        date.setBounds(20, 20, 300, 25);
        findPanel.add(date);

        dateInput = new JTextField();
        dateInput.setBounds(240, 20, 165, 25);
        dateInput.setBackground(Color.BLACK);
        dateInput.setForeground(Color.white);
        dateInput.setCaretColor(Color.white);
        findPanel.add(dateInput);

        addingComponents();

        findButton.setActionCommand("findingWater");
        findButton.addActionListener(this);
        goBack.setActionCommand("previousPage");
        goBack.addActionListener(this);
        findFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Adds components buttons to the window
    private void addingComponents() {
        findButton = new JButton("Find Water consumption");
        findButton.setBounds(110, 65, 200, 25);
        findPanel.add(findButton);

        goBack = new JButton("Go back");
        goBack.setBounds(135, 95, 150, 25);
        findPanel.add(goBack);
    }

    // MODIFIES: this
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("findingWater")) {
            JsonReader reader = new JsonReader("./data/consumption.json");
            try {
                readingWater = reader.read();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            getWaterOnThatDate();
            findButton.setEnabled(false);

        } else if (e.getActionCommand().equals("previousPage")) {
            new ConsumptionGUI();
            findFrame.setVisible(false);
        }
    }

    // EFFECTS: Prints the water consumed on a given date by user on the panel
    public void getWaterOnThatDate() {
        String chosenDate = dateInput.getText();
        JLabel outputWater = new JLabel("Your total water consumption for " + chosenDate + " is: "
                + readingWater.calculateWaterConsumedOnGivenDate(chosenDate));
        outputWater.setBounds(20, 135, 400, 25);
        outputWater.setForeground(Color.ORANGE);
        findPanel.add(outputWater);
        findPanel.revalidate();
        findPanel.repaint();
    }

}
