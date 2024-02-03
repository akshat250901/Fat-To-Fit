package model;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test class for Food class
public class FoodTest {
    public String name;
    public int calories;
    public Food food;

    @Test
    public void testToString() {
        name = "Pizza";
        calories = 100;
        String dateString = "2019-02-09";
        SimpleDateFormat date1 = new SimpleDateFormat(dateString);
        food = new Food(name,calories,FoodType.BREAKFAST, date1);
        assertEquals("Name of Consumed food: " + name + " , "
                + "Calories of consumed food: " + Integer.toString(calories),
                food.toString());
        name = "Burger";
        calories = 200;
        String dateString1 = "2019-02-09";
        SimpleDateFormat date = new SimpleDateFormat(dateString1);
        food = new Food(name,calories,FoodType.BREAKFAST, date);
        assertEquals("Name of Consumed food: " + name + " , "
                        + "Calories of consumed food: " + Integer.toString(calories),
                food.toString());
    }
}
