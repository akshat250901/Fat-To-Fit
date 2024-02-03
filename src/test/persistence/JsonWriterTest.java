package persistence;

import model.Food;
import model.FoodType;
import model.MonitorConsumption;
import model.Water;
import model.exception.IncorrectDateFormatException;
import model.exception.NegativeWaterException;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for JsonWriter Class
public class JsonWriterTest extends JsonTest {

    String dateString = "2018-12-29";
    SimpleDateFormat date = new SimpleDateFormat(dateString);
    String dateString1 = "2010-12-30";
    SimpleDateFormat date1 = new SimpleDateFormat(dateString1);
    String dateString2 = "2018-01-25";
    SimpleDateFormat date2 = new SimpleDateFormat(dateString2);
    String dateString3 = "2017-12-15";
    SimpleDateFormat date3 = new SimpleDateFormat(dateString3);

    @Test
    public void testWriterInvalidFile() {
        try {
            MonitorConsumption mc = new MonitorConsumption("My Testing Room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyConsumption() {
        try {
            MonitorConsumption mc = new MonitorConsumption("My Testing Room");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyConsumption.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyConsumption.json");
            mc = reader.read();
            assertEquals("My Testing Room", mc.getName());
            assertEquals(0, mc.getGoal());
            assertEquals(0, mc.numWater());
            assertEquals(0, mc.numFood());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterEmptyFood() {
        try {
            MonitorConsumption mc = new MonitorConsumption("Akshat's Consumption");
            mc.addWater(new Water(200, date));
            mc.addWater(new Water(500, date1));
            mc.calorieGoal = 2000;
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFood.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFood.json");
            mc = reader.read();
            assertEquals("Akshat's Consumption",mc.getName());
            assertEquals(2000,mc.getGoal());
            assertEquals(0,mc.numFood());
            assertEquals(2,mc.numWater());
            List<Water> waterList = mc.getListOfWater();
            assertEquals(2,waterList.size());
            checkWater(200,date,waterList.get(0));
            checkWater(500,date1,waterList.get(1));
        } catch (IOException | NegativeWaterException | IncorrectDateFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriterEmptyWater() {
        try {
            MonitorConsumption mc = new MonitorConsumption("Seb's Consumption");
            mc.addFood(new Food("pizza",490, FoodType.DINNER,date3));
            mc.addFood(new Food("maggi",300, FoodType.LUNCH,date));
            mc.addFood(new Food("eggs",50, FoodType.BREAKFAST,date1));
            mc.calorieGoal = 3000;
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWater.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWater.json");
            mc = reader.read();
            assertEquals("Seb's Consumption",mc.getName());
            assertEquals(3000,mc.getGoal());
            assertEquals(0,mc.numWater());
            List<Food> foodList = mc.getListOfFood();
            assertEquals(3,foodList.size());
            checkFood("pizza",490,FoodType.DINNER,date3,foodList.get(0));
            checkFood("maggi",300,FoodType.LUNCH,date,foodList.get(1));
            checkFood("eggs",50,FoodType.BREAKFAST,date1,foodList.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriterGeneralConsumption() {
        try {
            MonitorConsumption mc = new MonitorConsumption("User's Consumption");
            mc.addFood(new Food("rajma",490, FoodType.DINNER,date3));
            mc.addWater(new Water(750, date1));
            mc.calorieGoal = 5000;
            JsonWriter writer = new JsonWriter("./data/testWriterConsumption.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterConsumption.json");
            mc = reader.read();
            assertEquals("User's Consumption",mc.getName());
            assertEquals(5000,mc.getGoal());
            List<Water> waterList = mc.getListOfWater();
            assertEquals(1,waterList.size());
            checkWater(750,date1,waterList.get(0));
            List<Food> foodList = mc.getListOfFood();
            assertEquals(1,foodList.size());
            checkFood("rajma",490,FoodType.DINNER,date3,foodList.get(0));
        } catch (IOException | NegativeWaterException | IncorrectDateFormatException e) {
            e.printStackTrace();
        }
    }

}
