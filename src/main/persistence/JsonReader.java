package persistence;

import model.Food;
import model.FoodType;
import model.MonitorConsumption;
import model.Water;
import model.exception.IncorrectDateFormatException;
import model.exception.NegativeWaterException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

// Represents a reader that reads consumption from JSON data stored in file
// This class was taken from the CPSC 210 JsonSerializationDemo repository
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Consumption from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MonitorConsumption read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMonitorConsumption(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Consumption from JSON object and returns it
    private MonitorConsumption parseMonitorConsumption(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        MonitorConsumption mc = new MonitorConsumption(name);
        addFoods(mc, jsonObject);
        addWaters(mc, jsonObject);
        addCalorieGoal(mc, jsonObject);
        return mc;
    }

    // MODIFIES: mc
    // EFFECTS: parses foods from JSON object and adds them to MonitorConsumption

    private void addFoods(MonitorConsumption mc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Food");
        for (Object json : jsonArray) {
            JSONObject nextFood = (JSONObject) json;
            addFood(mc, nextFood);
        }
    }

    // MODIFIES: mc
    // EFFECTS: parses food from JSON object and adds them to MonitorConsumption
    private void addFood(MonitorConsumption mc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer calories = jsonObject.getInt("calories");
        FoodType foodType = FoodType.valueOf(jsonObject.getString("foodtype"));
        String date = jsonObject.getString("date");
        SimpleDateFormat simpleDate = new SimpleDateFormat(date);
        Food food = new Food(name,calories,foodType,simpleDate);
        mc.addFood(food);
    }

    // MODIFIES: mc
    // EFFECTS: parses waters from JSON object and adds them to MonitorConsumption

    private void addWaters(MonitorConsumption mc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Water");
        for (Object json : jsonArray) {
            JSONObject nextWater = (JSONObject) json;
            addWater(mc, nextWater);
        }
    }

    // MODIFIES: mc
    // EFFECTS: parses water from JSON object and adds them to MonitorConsumption
    private void addWater(MonitorConsumption mc, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        SimpleDateFormat simpleDate = new SimpleDateFormat(date);
        Integer waterAmount = jsonObject.getInt("amount");
        Water water = null;
        try {
            water = new Water(waterAmount,simpleDate);
        } catch (NegativeWaterException | IncorrectDateFormatException e) {
            e.printStackTrace();
        }
        mc.addWater(water);
    }

    // MODIFIES: mc
    // EFFECTS: parses CalorieGoal from JSON object and adds them to MonitorConsumption
    private void addCalorieGoal(MonitorConsumption mc, JSONObject jsonObject) {
        Integer goal = jsonObject.getInt("CalorieGoal");
        mc.calorieGoal = goal;
    }


}

