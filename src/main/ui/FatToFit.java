package ui;

import model.*;
import model.MonitorConsumption;
import model.exception.IncorrectDateFormatException;
import model.exception.NegativeWaterException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

// Runs the UI for the web-app
public class FatToFit {
    private static final String JSON_STORE = "./data/consumption.json";
    private Scanner input;
    MonitorConsumption userConsumption;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    public FatToFit() throws FileNotFoundException {
        initialize();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the application
    public void initialize() throws FileNotFoundException {
        input = new Scanner(System.in);
        userConsumption = new MonitorConsumption("Akshat's Consumption");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFatToFit();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // This function was taken from the CPSC 210 TellerApp application
    private void runFatToFit() {
        boolean keepProcessing = true;
        while (keepProcessing) {
            displayMenu();
            String cmd = input.next();
            cmd = cmd.toLowerCase();

            if (cmd.equals("q")) {
                keepProcessing = false;
            } else {
                processCommand(cmd);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: Displays the initial menu to user
    // REFERENCE: FatToFit
    public void displayMenu() {
        System.out.println("\nSelect the desired option:");
        System.out.println("\tsetCalories -> Set your goal for calories (Default = 0)");
        System.out.println("\tadd -> Add Food");
        System.out.println("\ttotal -> Total Consumption of food");
        System.out.println("\tfoodOnDate -> Total Consumption on a given date");
        System.out.println("\taddWater -> Add Water");
        System.out.println("\ttotalWater -> Total consumption of water in ml");
        System.out.println("\twaterOnDate -> Total consumption of water on a given date");
        System.out.println("\ts -> Save your consumption");
        System.out.println("\tl -> Load your consumption");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: Processes commands given by the user
    private void processCommand(String input) {
        switch (input) {
            case "setcalories": setCalorieGoal();
                break;
            case "add": askWhichMeal();
                break;
            case "total": givesTotal();
                break;
            case "foodondate": consumptionOnThatDate();
                break;
            case "addwater": addWaterConsumption();
                break;
            case "totalwater": getWaterConsumption();
                break;
            case "waterondate": waterConsumptionOnDate();
                break;
            case "s": saveConsumption();
                break;
            case "l": loadConsumption();
                break;
            default:
                System.out.println("Choose from the given options...");

        }
    }

    // EFFECTS: Asks the user which type of meal they want to add
    public void askWhichMeal() {
        System.out.println("\nWhich meal of the day it is?");
        System.out.println("\tb -> Breakfast");
        System.out.println("\tl -> Lunch");
        System.out.println("\td -> Dinner");
        System.out.println("\ts -> Snack");
        System.out.println("\tq -> quit");
        String inputFoodType = input.next();
        inputFoodType = inputFoodType.toLowerCase();
        if (inputFoodType.equals("q")) {
            System.out.println("\nGoodbye");
            System.out.println("\nInput q again to exit the program");

        } else {
            commandToAddFood(inputFoodType);
        }
    }

    // EFFECTS: Gives the total calorie consumption of the user
    public int givesTotal() {
        return userConsumption.totalCalories();
    }


    // EFFECTS: Stores the food added by the user
    public void commandToAddFood(String inputFood) {
        switch (inputFood) {
            case "b":
                addToBreakfast();
                break;
            case "l":
                addToLunch();
                break;
            case "d":
                addToDinner();
                break;
            case "s":
                addToSnack();
                break;
            default:
                System.out.println("Try Again! Choose from the given options only");
        }
    }

    // MODIFIES: this
    // EFFECTS: Stores the food eaten by the user in breakfast
    public void addToBreakfast() {
        System.out.println("Add your food with a name, calories>=0, and date in the format 'yyyy-MM-dd'");
        System.out.println("name: ");
        String nameOfFood = input.next();
        System.out.println("calories: ");
        int calories = input.nextInt();
        System.out.println("date in the format YYYY-MM-DD: ");
        String dateInString = input.next();
        SimpleDateFormat date = new SimpleDateFormat(dateInString);
        Food breakfastFood = new Food(nameOfFood, calories, FoodType.BREAKFAST, date);
        userConsumption.addFood(breakfastFood);
        if (withinCalorieGoal() == true) {
            System.out.println("You have crossed your calorie goal by: "
                    + (givesTotal() - userConsumption.calorieGoal));
        } else {
            continueAddingFood();
        }
    }

    // MODIFIES: this
    // EFFECTS: Stores the food eaten by the user in lunch
    public void addToLunch() {
        System.out.println("Add your food with a name, calories>=0, and date in the format 'yyyy-MM-dd'");
        System.out.println("name: ");
        String name = input.next();
        System.out.println("calories: ");
        int calories = input.nextInt();
        System.out.println("date in the format YYYY-MM-DD: ");
        String dateInString = input.next();
        SimpleDateFormat date = new SimpleDateFormat(dateInString);
        Food lunchFood = new Food(name, calories, FoodType.LUNCH, date);
        userConsumption.addFood(lunchFood);
        if (withinCalorieGoal() == true) {
            System.out.println("You have crossed your calorie goal by: "
                    + (givesTotal() - userConsumption.calorieGoal));
        } else {
            continueAddingFood();
        }

    }

    // MODIFIES: this
    // EFFECTS: Stores the food eaten by the user in dinner
    public void addToDinner() {
        System.out.println("Add your food with a name, calories>=0, and date in the format 'yyyy-MM-dd'");
        System.out.println("name: ");
        String name = input.next();
        System.out.println("calories: ");
        int calories = input.nextInt();
        System.out.println("date in the format YYYY-MM-DD: ");
        String dateInString = input.next();
        SimpleDateFormat date = new SimpleDateFormat(dateInString);
        Food dinnerFood = new Food(name, calories, FoodType.DINNER, date);
        userConsumption.addFood(dinnerFood);
        if (withinCalorieGoal() == true) {
            System.out.println("You have crossed your calorie goal by: "
                    + (givesTotal() - userConsumption.calorieGoal));
        } else {
            continueAddingFood();
        }

    }

    // MODIFIES: this
    // EFFECTS: Stores the food eaten by the user in snacks
    public void addToSnack() {
        System.out.println("Add your food with a name, calories>=0, and date in the format 'yyyy-MM-dd'");
        System.out.println("name: ");
        String name = input.next();
        System.out.println("calories: ");
        int calories = input.nextInt();
        System.out.println("date in the format YYYY-MM-DD: ");
        String dateInString = input.next();
        SimpleDateFormat date = new SimpleDateFormat(dateInString);
        Food snackFood = new Food(name, calories, FoodType.SNACK, date);
        userConsumption.addFood(snackFood);
        if (withinCalorieGoal() == true) {
            System.out.println("You have crossed your calorie goal by: "
                    + (givesTotal() - userConsumption.calorieGoal));
        } else {
            continueAddingFood();
        }

    }


    // EFFECTS: Asks the user if they want to add more food after adding it once
    public void continueAddingFood() {
        System.out.println("Do you want to add more food items?");
        System.out.println("yes");
        System.out.println("no");
        String addMore = input.next();
        addMore = addMore.toLowerCase();
        if (addMore.equals("yes")) {
            askWhichMeal();
        } else {
            runFatToFit();
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the maximum daily consumption of calories which a user wants to consume
    public void setCalorieGoal() {
        System.out.println("Set your daily goal for calories where calories >= 0:");
        int dailyCalorieConsumption = input.nextInt();
        userConsumption.calorieGoal = dailyCalorieConsumption;

    }


    // MODIFIES: this
    // EFFECTS: Takes the amount of water consumed by the user
    public void addWaterConsumption() {
        System.out.println("Add your water consumption in ml:");
        int waterConsumed = input.nextInt();
        System.out.println("Add the date on which water was consumed in YYYY-MM-DD format:");
        String dateInString = input.next();
        SimpleDateFormat date = new SimpleDateFormat(dateInString);
        Water userWater = null;
        try {
            userWater = new Water(waterConsumed, date);
        } catch (NegativeWaterException | IncorrectDateFormatException e) {
            System.out.println("Input data is invalid");
        }
        userConsumption.water.add(userWater);
    }


    // EFFECTS: Returns true if calorie consumed exceeds the calorie goal, false otherwise
    public boolean withinCalorieGoal() {
        if (givesTotal() > userConsumption.calorieGoal) {
            return true;
        } else {
            return false;
        }
    }


    // EFFECTS: Prints the list of food consumed and totally calorie consumed on a given date by user
    public void consumptionOnThatDate() {
        System.out.println("Enter date in yyyy-mm-dd format:");
        String chosenDate = input.next();
        System.out.println("Your total food consumption for " + chosenDate + " is: ");
        for (int i = 0; i < userConsumption.foodConsumedOnGivenDate(chosenDate).size(); i++) {
            Food outputFood = userConsumption.foodConsumedOnGivenDate(chosenDate).get(i);
            System.out.println(outputFood);
        }
        System.out.println("Your total calorie consumption for " + chosenDate + " is: "
                + userConsumption.caloriesConsumedOnGivenDate(chosenDate));
    }


    // EFFECTS: Gives total water consumption of the user in ml
    public void getWaterConsumption() {
        System.out.println("Your total water consumption is: " + userConsumption.calculateWaterConsumed());
    }


    // EFFECTS: Gives total water consumption of user on a given date
    public void waterConsumptionOnDate() {
        System.out.println("Enter date in yyyy-mm-dd format:");
        String chosenDate = input.next();
        System.out.println("Your total water consumption for " + chosenDate + " is: "
                + userConsumption.calculateWaterConsumedOnGivenDate(chosenDate));
    }

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
