package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for MonitorConsumption class
class MonitorConsumptionTest {
    private MonitorConsumption consumption;
    private Food food1;
    private Food food2;
    private Food food3;
    private Food food4;
    private Food food5;
    private String dateInString = "2020-09-25";
    SimpleDateFormat date = new SimpleDateFormat(dateInString);

    @BeforeEach
    public void setup(){
        consumption = new MonitorConsumption("Akshat's Consumption");
        food1 = new Food("Pani-Puri", 200, FoodType.BREAKFAST, date);
        food2 = new Food("Goalgappe", 250, FoodType.LUNCH, date);
        food3 = new Food("pav-bhaji", 750, FoodType.DINNER, date);
        food4 = new Food("momos", 500, FoodType.SNACK, date);
        food5 = new Food("chicken", 1000, FoodType.SNACK, date);
        consumption.addFood(food1);
        consumption.addFood(food2);
        consumption.addFood(food3);
        consumption.addFood(food4);
        consumption.addFood(food5);
    }



    @Test
    public void testAddFood(){
        assertEquals(1,consumption.breakfast.size());
        assertEquals(1,consumption.lunch.size());
        assertEquals(1,consumption.dinner.size());
        assertEquals(2,consumption.snack.size());
        assertEquals(5,consumption.totalConsumption.size());
        assertEquals(food1,consumption.breakfast.get(0));
        assertEquals(food2,consumption.lunch.get(0));
        assertEquals(food3,consumption.dinner.get(0));
        assertEquals(food4,consumption.snack.get(0));
        assertEquals(food5,consumption.snack.get(1));

    }

    @Test
    public void testTotalCalories(){
        assertEquals(2700,consumption.totalCalories());

    }

    @Test
    public void testFoodConsumedOnGivenDate(){
        String dateString = "2019-02-09";
        SimpleDateFormat date1 = new SimpleDateFormat(dateString);
        Food food6 = new Food("butter chicken", 1500, FoodType.SNACK, date1);
        consumption.addFood(food6);
        List<Food> foodByDate = consumption.foodConsumedOnGivenDate("2019-02-09");
        assertEquals(1,foodByDate.size());
        List<Food> foodByDate1 = consumption.foodConsumedOnGivenDate("2020-09-25");
        assertEquals(5,foodByDate1.size());

    }

    @Test
    public void testCaloriesConsumedOnGivenDate(){
        String dateString = "2019-02-09";
        SimpleDateFormat date1 = new SimpleDateFormat(dateString);
        Food food6 = new Food("butter chicken", 1500, FoodType.SNACK, date1);
        consumption.addFood(food6);
        int caloriesByDate = consumption.caloriesConsumedOnGivenDate("2019-02-09");
        assertEquals(1500,caloriesByDate);
        int caloriesByDate1 = consumption.caloriesConsumedOnGivenDate("2020-09-25");
        assertEquals(2700,caloriesByDate1);
    }

    @Test
    public void testTotalFoodConsumed() {
        List<Food> list = new ArrayList<>();
        list.add(food1);
        list.add(food2);
        list.add(food3);
        list.add(food4);
        list.add(food5);
        assertEquals(list,consumption.totalFoodConsumed());
    }

}
