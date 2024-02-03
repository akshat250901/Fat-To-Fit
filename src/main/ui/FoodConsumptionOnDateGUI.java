package ui;

import model.Food;
import model.MonitorConsumption;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Displays a window which will ask for a date from user and display food consumed on that date
public class FoodConsumptionOnDateGUI implements ActionListener {
    private JFrame findFrame;
    private JPanel findPanel;
    private JButton findButton;
    private JLabel date;
    private JTextField dateInput;
    private JLabel outputConsumption;
    private JButton goBack;
    private MonitorConsumption readingConsumption;
    private String chosenDate;

    // MODIFIES: this
    //EFFECTS: initializes the window with respective panel and buttons
    public FoodConsumptionOnDateGUI() {
        findFrame = new JFrame("Total food consumption");
        findPanel = new JPanel();
        findFrame.setSize(600, 800);
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

        findButton.setActionCommand("findingFood");
        findButton.addActionListener(this);
        goBack.setActionCommand("goingBack");
        goBack.addActionListener(this);
        findFrame.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: Adds components buttons to the window
    private void addingComponents() {
        findButton = new JButton("Find Food");
        findButton.setBounds(135, 65, 150, 25);
        findPanel.add(findButton);

        goBack = new JButton("Go back");
        goBack.setBounds(135, 95, 150, 25);
        findPanel.add(goBack);
    }


    // MODIFIES: this
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("findingFood")) {
            JsonReader reader = new JsonReader("./data/consumption.json");
            try {
                readingConsumption = reader.read();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            getConsumptionOnThatDate();
            findButton.setEnabled(false);
        } else if (e.getActionCommand().equals("goingBack")) {
            new ConsumptionGUI();
            findFrame.setVisible(false);
        }
    }

    // EFFECTS: Prints the list of food consumed and totally calorie consumed on a given date by user on the panel
    public void getConsumptionOnThatDate() {
        chosenDate = dateInput.getText();

        printTotalCalorie();

        if (readingConsumption.caloriesConsumedOnGivenDate(chosenDate) > readingConsumption.calorieGoal) {
            JLabel exceeded = new JLabel("You have exceeded your calorie goal by: "
                    + (readingConsumption.caloriesConsumedOnGivenDate(chosenDate) - readingConsumption.calorieGoal));
            exceeded.setBounds(50,160,400,25);
            findPanel.add(exceeded);
        }

        outputConsumption = new JLabel("Your total food consumption for " + chosenDate + " is: ");
        outputConsumption.setBounds(50, 190, 400, 25);
        findPanel.add(outputConsumption);

        for (int i = 0; i < readingConsumption.foodConsumedOnGivenDate(chosenDate).size(); i++) {
            int y = 210;
            Food outputFood = readingConsumption.foodConsumedOnGivenDate(chosenDate).get(i);
            y = y + i * 20;
            JLabel printFood = new JLabel("Name of food consumed: " + outputFood.name + "     " + "Type: "
                    + outputFood.getFoodType() + "    " + "Calories: " + outputFood.getCaloriesCount());
            printFood.setBounds(50, y, 600, 25);
            findPanel.add(printFood);
        }

    }

    // MODIFIES: this
    // EFFECTS: Prints total calorie consumed by the user on that date on the panel
    private void printTotalCalorie() {
        JLabel printCalorie = new JLabel("Your total calorie consumption for " + chosenDate + " is: "
                + readingConsumption.caloriesConsumedOnGivenDate(chosenDate));
        printCalorie.setBounds(50, 130, 400, 25);
        findPanel.add(printCalorie);
        findPanel.revalidate();
        findPanel.repaint();
    }

}