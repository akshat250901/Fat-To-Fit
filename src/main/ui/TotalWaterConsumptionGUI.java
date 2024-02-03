package ui;

import model.MonitorConsumption;
import model.Water;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Displays the window which gives total water consumed by the user
public class TotalWaterConsumptionGUI implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton goBack;
    private MonitorConsumption displayingTotalConsumption;
    private JScrollPane scroll;

    // MODIFIES: this
    //EFFECTS: initializes the window with respective components
    public TotalWaterConsumptionGUI() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setTitle("Total Food Consumption");
        frame.setSize(480, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JsonReader reader = new JsonReader("./data/consumption.json");
        try {
            displayingTotalConsumption = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String printWater = "";
        for (int i = 0; i < displayingTotalConsumption.totalWaterConsumed().size(); i++) {
            Water outputWater = displayingTotalConsumption.totalWaterConsumed().get(i);
            printWater = printWater + "\nAmount of Water consumed: " + outputWater.getAmountOfWater()
                    + "        " + "Date: " + outputWater.getDateString();
        }
        JTextArea water = new JTextArea(printWater);
        water.setEditable(false);
        panel.add(water);

        addingComponents();

    }

    // MODIFIES: this
    // EFFECTS: Adds components like text field, label and buttons to the window
    private void addingComponents() {

        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(40, 50, 400, 100);
        JPanel waterPane = new JPanel(null);
        waterPane.add(scroll);
        frame.add(waterPane);

        goBack = new JButton("Go Back");
        goBack.setBounds(175, 175, 100, 25);
        waterPane.add(goBack);
        goBack.setActionCommand("back");
        goBack.addActionListener(this);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("back")) {
            new ConsumptionGUI();
            frame.setVisible(false);
        }
    }
}
