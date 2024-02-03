package persistence;

import model.Food;
import model.FoodType;
import model.Water;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class extended by JsonWriterTest and JsonReaderTest
public class JsonTest {

    public String convertDateToString(SimpleDateFormat date) {
        SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        inputDate = date;
        return inputDate.toLocalizedPattern();
    }

    protected void checkWater(int amount, SimpleDateFormat date, Water water) {
        assertEquals(amount, water.getAmountOfWater());
        assertEquals(convertDateToString(date), water.getDateString());
    }

    protected void checkFood(String name, int calorie, FoodType foodType, SimpleDateFormat date, Food food) {
        assertEquals(name, food.getFoodName());
        assertEquals(calorie, food.getCaloriesCount());
        assertEquals(foodType,food.getFoodType());
        assertEquals(convertDateToString(date), food.getDateString());
    }
}
