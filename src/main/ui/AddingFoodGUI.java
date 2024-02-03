package ui;

import model.Food;
import model.FoodType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static ui.LoginGUI.*;

//Displays a window which will ask for user input of food
public class AddingFoodGUI extends JFrame implements ActionListener {
    private JPanel foodPanel;
    private JFrame foodFrame;
    private JButton addFood;
    private JLabel foodName;
    private JLabel caloriesCount;
    private JLabel inputDate;
    private JLabel foodType;
    private JTextField name;
    private JTextField calories;
    private JTextField date;
    private JComboBox type;
    private JButton goBack;


    // MODIFIES: this
    //EFFECTS: initializes the window with respective components
    public AddingFoodGUI() {
        foodPanel = new JPanel();
        foodFrame = new JFrame();
        foodFrame.setTitle("Log in Food Consumption");
        foodFrame.setSize(460, 350);
        foodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        foodFrame.add(foodPanel);
        foodPanel.setLayout(null);

        foodName = new JLabel("Name of food");
        foodName.setBounds(40, 60, 250, 25);
        foodPanel.add(foodName);


        addingComponents();


        foodFrame.setVisible(true);

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

    // MODIFIES: this
    // EFFECTS: Adds components like text field, label and buttons to the window
    private void addingComponents() {

        name = new JTextField();
        name.setBounds(145, 60, 165, 25);
        name.setBackground(Color.BLACK);
        name.setForeground(Color.white);
        name.setCaretColor(Color.white);
        foodPanel.add(name);

        caloriesCount = new JLabel("Number of Calories (Integer)");
        caloriesCount.setBounds(40, 100, 300, 25);
        foodPanel.add(caloriesCount);

        calories = new JTextField();
        calories.setBounds(225, 100, 165, 25);
        calories.setBackground(Color.BLACK);
        calories.setForeground(Color.white);
        calories.setCaretColor(Color.white);
        foodPanel.add(calories);


        inputDate = new JLabel("Food consumption date (YYYY-MM-DD)");
        inputDate.setBounds(40, 140, 300, 25);
        foodPanel.add(inputDate);

        addingMoreComponents();
    }

    // MODIFIES: this
    // EFFECTS: Adds components like text field, label and buttons to the window
    private void addingMoreComponents() {


        date = new JTextField();
        date.setBounds(300, 140, 100, 25);
        date.setBackground(Color.BLACK);
        date.setForeground(Color.white);
        date.setCaretColor(Color.white);
        foodPanel.add(date);

        foodType = new JLabel("Select type of food");
        foodType.setBounds(40, 180, 200, 25);
        foodPanel.add(foodType);

        String[] typeOfFood = {"BREAKFAST", "LUNCH", "DINNER", "SNACK"};
        type = new JComboBox(typeOfFood);
        type.setBounds(165, 180, 130, 25);
        foodPanel.add(type);


        addFood = new JButton("Save this food");
        addFood.setBounds(95, 230, 200, 25);
        foodPanel.add(addFood);

        goBack = new JButton("Go back");
        goBack.setBounds(95, 260, 200, 25);
        foodPanel.add(goBack);

        goBack.setActionCommand("back");
        goBack.addActionListener(this);
        addFood.setActionCommand("addingFood");
        addFood.addActionListener(this);
    }

    // MODIFIES: this, userConsumption
    // EFFECTS: Performs the action when respective buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addingFood")) {
            loadConsumption();
            savingFood();
        } else if (e.getActionCommand().equals("back")) {
            new ConsumptionGUI();
            foodFrame.setVisible(false);
        }
    }

    // MODIFIES: this, userConsumption
    // EFFECTS: Saves the food input by the user and shows a dialog box with a message
    private void savingFood() {
        if (!name.getText().equals("") && !calories.getText().equals("")
                && !date.getText().equals("")) {
            String caloriesCount = calories.getText();
            int caloriesInteger = Integer.parseInt(caloriesCount);
            String dateString = date.getText();
            SimpleDateFormat dateInFormat = new SimpleDateFormat(dateString);
            userConsumption.addFood(new Food(name.getText(), caloriesInteger, givesFoodType(), dateInFormat));
            saveConsumption();
            JOptionPane.showMessageDialog(null, "Your Food was added!");
            new ConsumptionGUI();
            foodFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null,"Fields cannot be empty");
        }
    }

    // EFFECTS: Gives the type of food selected by the user
    private FoodType givesFoodType() {
        if (type.getSelectedItem().equals("BREAKFAST")) {
            return FoodType.BREAKFAST;
        } else if (type.getSelectedItem().equals("LUNCH")) {
            return FoodType.LUNCH;
        } else if (type.getSelectedItem().equals("DINNER")) {
            return FoodType.DINNER;
        } else {
            return FoodType.SNACK;
        }
    }


}
