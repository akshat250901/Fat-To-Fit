package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static ui.LoginGUI.*;
import static ui.LoginGUI.JSON_STORE;

// Displays a window with multiple buttons that perform different functions
public class ConsumptionGUI extends JFrame implements ActionListener {
    private JFrame consumptionFrame;
    private JPanel consumptionPanel;
    private JButton addFood;
    private JButton addWater;
    private JButton seeFoodConsumption;
    private JButton seeWaterConsumption;
    private JButton seeTotalFoodConsumption;
    private JButton seeTotalWaterConsumption;
    private JButton save;

    // MODIFIES: this
    //EFFECTS: initializes the window with different components
    public ConsumptionGUI() {
        consumptionFrame = new JFrame();
        consumptionPanel = new JPanel();
        consumptionFrame.setTitle("Log Consumption");
        consumptionFrame.setSize(600, 400);
        consumptionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        consumptionFrame.add(consumptionPanel);

        consumptionPanel.setLayout(null);

        addingComponents();
        addFood.setActionCommand("addingFood");
        addFood.addActionListener(this);
        addWater.setActionCommand("addingWater");
        addWater.addActionListener(this);
        seeWaterConsumption.setActionCommand("viewingWater");
        seeWaterConsumption.addActionListener(this);
        seeFoodConsumption.setActionCommand("viewingFood");
        seeFoodConsumption.addActionListener(this);
        seeTotalFoodConsumption.setActionCommand("totalFood");
        seeTotalFoodConsumption.addActionListener(this);
        seeTotalWaterConsumption.setActionCommand("totalWater");
        seeTotalWaterConsumption.addActionListener(this);
        save.setActionCommand("save");
        save.addActionListener(this);
        consumptionFrame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: Adds components like text field, label and buttons to the window
    private void addingComponents() {
        addFood = new JButton("Add Food");
        addFood.setBounds(200, 60, 200, 25);
        consumptionPanel.add(addFood);

        addWater = new JButton("Add Water");
        addWater.setBounds(200, 100, 200, 25);
        consumptionPanel.add(addWater);

        seeFoodConsumption = new JButton("Check your food consumption on a given date");
        seeFoodConsumption.setBounds(40, 140, 500, 25);
        consumptionPanel.add(seeFoodConsumption);

        seeWaterConsumption = new JButton("Check your water consumption on a given date");
        seeWaterConsumption.setBounds(40, 180, 500, 25);
        consumptionPanel.add(seeWaterConsumption);

        seeTotalFoodConsumption = new JButton("Check your total food consumption");
        seeTotalFoodConsumption.setBounds(40, 220, 500, 25);
        consumptionPanel.add(seeTotalFoodConsumption);

        seeTotalWaterConsumption = new JButton("Check your total water consumption");
        seeTotalWaterConsumption.setBounds(40, 260, 500, 25);
        consumptionPanel.add(seeTotalWaterConsumption);

        save = new JButton("Save");
        save.setBounds(40, 300, 500, 25);
        consumptionPanel.add(save);

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

    // MODIFIES: this
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addingFood")) {
            new AddingFoodGUI();
            consumptionFrame.setVisible(false);
        } else if (e.getActionCommand().equals("addingWater")) {
            new AddingWaterGUI();
            consumptionFrame.setVisible(false);
        } else if (e.getActionCommand().equals("viewingWater")) {
            new WaterConsumptionOnDateGUI();
            consumptionFrame.setVisible(false);
        } else if (e.getActionCommand().equals("viewingFood")) {
            new FoodConsumptionOnDateGUI();
            consumptionFrame.setVisible(false);
        } else if (e.getActionCommand().equals("totalFood")) {
            new TotalFoodConsumptionGUI();
            consumptionFrame.setVisible(false);
        } else if (e.getActionCommand().equals("totalWater")) {
            new TotalWaterConsumptionGUI();
            consumptionFrame.setVisible(false);
        } else if (e.getActionCommand().equals("save")) {
            saveConsumption();
            JOptionPane.showMessageDialog(null,
                    "You have successfully saved your application!");
        }
    }

}

