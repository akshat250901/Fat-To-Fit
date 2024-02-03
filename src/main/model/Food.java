package model;

import org.json.JSONObject;
import persistence.Writable;

import java.text.SimpleDateFormat;

// Represents a food having name, calories, type and date on which it was consumed
public class Food implements Writable {
    public String name;               // Name of the food
    public int calories;              // Calories in the given food
    public FoodType foodType;         // Type of the given food
    private SimpleDateFormat date;    // Date on which food was added

    //REQUIRES: name has a non-zero length, calories>=0 and date should be in format yyyy-MM-dd
    //MODIFIES: this
    //EFFECTS: creates a food with given name,calories,foodtype and date
    public Food(String name, int calories, FoodType foodType,SimpleDateFormat date)  {
        this.date = date;
        this.name = name;
        this.calories = calories;
        this.foodType = foodType;
    }

    // EFFECTS: Returns the name of food
    public String getFoodName() {
        return name;
    }

    // EFFECTS: Gets the calories
    public int getCaloriesCount() {
        return calories;
    }

    // EFFECTS: Gets the food type
    public FoodType getFoodType() {
        return foodType;
    }


    // EFFECTS: Prints name and calories of food in UI instead of memory location when printing list of foods
    @Override
    public String toString() {
        String stringFood = "Name of Consumed food: " + name + " , "
                + "Calories of consumed food: " + Integer.toString(calories);
        return stringFood;
    }

    // EFFECTS: Gets the date as string on which item was added
    public String getDateString() {
        SimpleDateFormat inputDate = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        inputDate = date;
        return inputDate.toLocalizedPattern();
    }

    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("calories", calories);
        json.put("foodtype", foodType);
        json.put("date", getDateString());
        return json;
    }

}

