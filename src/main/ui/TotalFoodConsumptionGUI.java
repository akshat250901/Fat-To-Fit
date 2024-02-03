package ui;

import model.Food;
import model.MonitorConsumption;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Displays the window which gives total food consumed by the user
public class TotalFoodConsumptionGUI implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton goBack;
    private MonitorConsumption displayingTotalConsumption;
    private JScrollPane scroll;
    private String food = "";

    // MODIFIES: this
    //EFFECTS: initializes the window with respective components
    public TotalFoodConsumptionGUI() {
        panel = new JPanel();
        frame = new JFrame();
        frame.setTitle("Total Food Consumption");
        frame.setSize(650, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JsonReader reader = new JsonReader("./data/consumption.json");
        try {
            displayingTotalConsumption = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < displayingTotalConsumption.totalFoodConsumed().size(); i++) {
            Food outputFood = displayingTotalConsumption.totalFoodConsumed().get(i);
            food = food + "\nName of food consumed: " + outputFood.name + "     " + "Type: "
                    + outputFood.getFoodType() + "    " + "Calories: " + outputFood.getCaloriesCount()
                    + "    " + "Date: " + outputFood.getDateString();
        }
        JTextArea printFood = new JTextArea(food);
        printFood.setEditable(false);
        panel.add(printFood);
        addingComponents();
    }

    // MODIFIES: this
    // EFFECTS: Adds components like text field, label and buttons to the window
    private void addingComponents() {
        scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(25, 50, 600, 100);
        JPanel foodPane = new JPanel(null);
        foodPane.add(scroll);
        frame.add(foodPane);

        goBack = new JButton("Go Back");
        goBack.setBounds(230, 175, 100, 25);
        foodPane.add(goBack);
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
