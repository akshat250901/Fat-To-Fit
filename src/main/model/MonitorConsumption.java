package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// Represents a class which monitors consumption
public class MonitorConsumption implements Writable {
    public String name;
    public List<Food> totalConsumption;  // List of all food items consumed
    public List<Food> breakfast;         // List of all food items consumed breakfast
    public List<Food> lunch;             // List of all food items consumed in lunch
    public List<Food> dinner;            // List of all food items consumed in dinner
    public List<Food> snack;             // List of all food items consumed in snack
    public List<Water> water;            // List of Water consumed
    public int calorieGoal;              // Calorie goal


    // Initializes the lists of consumed food and water along with setting initial calorie goal to 0
    public MonitorConsumption(String name) {
        this.name = name;
        totalConsumption = new LinkedList<>();
        breakfast = new LinkedList<>();
        lunch = new LinkedList<>();
        dinner = new LinkedList<>();
        snack = new LinkedList<>();
        this.water = new LinkedList<>();
        this.calorieGoal = 0;

    }

    // EFFECTS: Returns the name of consumption
    public String getName() {
        return name;
    }


    // MODIFIES: this
    // EFFECTS: Adds the input food in total consumption and also separates each input food based on type
    public void addFood(Food food) {
        FoodType typeOfFood = food.getFoodType();
        if (typeOfFood == FoodType.BREAKFAST) {
            breakfast.add(food);
        } else if (typeOfFood == FoodType.LUNCH) {
            lunch.add(food);
        } else if (typeOfFood == FoodType.DINNER) {
            dinner.add(food);
        } else {
            snack.add(food);
        }
        totalConsumption.add(food);
    }


    // EFFECTS: Gives the total calories consumed
    public int totalCalories() {
        int caloriesCount = 0;
        for (Food f : totalConsumption) {
            caloriesCount = caloriesCount + f.getCaloriesCount();
        }
        return caloriesCount;
    }

    // REQUIRES: The string date should be in the format yyyy-MM-dd
    // EFFECTS: This gives the food consumed on a given date
    public List<Food> foodConsumedOnGivenDate(String date) {
        List<Food> foodConsumed = new ArrayList<>();
        for (Food f : totalConsumption) {
            if (f.getDateString().equals(date)) {
                foodConsumed.add(f);
            }
        }
        return foodConsumed;
    }


    // EFFECTS: This gives the total food consumed till now
    public List<Food> totalFoodConsumed() {
        List<Food> foodConsumed = new ArrayList<>();
        for (Food f : totalConsumption) {
            foodConsumed.add(f);
        }
        return foodConsumed;
    }

    // REQUIRES: The string date should be in the format yyyy-MM-dd
    // EFFECTS: This gives the calories consumed on a given date
    public int caloriesConsumedOnGivenDate(String date) {
        int caloriesCount = 0;
        for (Food f : totalConsumption) {
            if (f.getDateString().equals(date)) {
                caloriesCount = f.getCaloriesCount() + caloriesCount;
            }
        }
        return caloriesCount;
    }

    // EFFECTS: Calculates the total amount of water in list of water
    public int calculateWaterConsumed() {
        int totalWaterConsumed = 0;
        for (Water w : water) {
            totalWaterConsumed = w.getAmountOfWater() + totalWaterConsumed;
        }
        return totalWaterConsumed;
    }

    // EFFECTS: Calculates the total amount of water in list of water
    public List<Water> totalWaterConsumed() {
        List<Water> totalWaterConsumed = new ArrayList<>();
        for (Water w : water) {
            totalWaterConsumed.add(w);
        }
        return totalWaterConsumed;
    }

    // REQUIRES: Input date should be of the format YYYY-MM-DD
    // EFFECTS: Takes a date and Calculates the total amount of water consumed on that date
    public int calculateWaterConsumedOnGivenDate(String date) {
        int waterConsumed = 0;
        for (Water w : water) {
            if (w.getDateString().equals(date)) {
                waterConsumed = waterConsumed + w.getAmountOfWater();
            }
        }
        return waterConsumed;
    }

    // MODIFIES: this
    // EFFECTS: Takes in a water and adds it to the list of water
    public void addWater(Water addingWater) {
        water.add(addingWater);
    }

    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Food", foodToJson());
        json.put("Water", waterToJson());
        json.put("CalorieGoal", calorieGoal);
        return json;
    }

    // EFFECTS: returns things in this consumption as a JSON array
    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    private JSONArray foodToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food t : totalConsumption) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this consumption as a JSON array
    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    private JSONArray waterToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Water t : water) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: Returns the size of total consumption which is list of food
    public int numFood() {
        return totalConsumption.size();
    }

    // EFFECTS: Returns the size of list of water
    public int numWater() {
        return water.size();
    }

    // EFFECTS: Returns the calorie goal
    public int getGoal() {
        return calorieGoal;
    }

    // EFFECTS: returns an unmodifiable list of water in this consumption
    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    public List<Water> getListOfWater() {
        return Collections.unmodifiableList(water);
    }

    // EFFECTS: returns an unmodifiable list of food in this consumption
    // This function was taken from CPSC 210 JsonSerializationDemo repository on github
    public List<Food> getListOfFood() {
        return Collections.unmodifiableList(totalConsumption);
    }


}
