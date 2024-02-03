package persistence;

import model.Food;
import model.FoodType;
import model.MonitorConsumption;
import model.Water;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for JsonReader
public class JsonReaderTest extends JsonTest {
    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MonitorConsumption mc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testReaderEmptyConsumption() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyConsumption.json");
        try {
            MonitorConsumption mc = reader.read();
            assertEquals("My Testing Room", mc.getName());
            assertEquals(0, mc.numFood());
            assertEquals(0, mc.numWater());
            assertEquals(0, mc.getGoal());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testReaderEmptyFood() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFood.json");
        try {
            MonitorConsumption mc = reader.read();
            assertEquals("Akshat's Consumption", mc.getName());
            assertEquals(0, mc.numFood());
            String dateString = "2020-01-10";
            SimpleDateFormat date = new SimpleDateFormat(dateString);
            String dateString1 = "2019-09-09";
            SimpleDateFormat date1 = new SimpleDateFormat(dateString1);
            List<Water> waterList = mc.getListOfWater();
            assertEquals(2, waterList.size());
            checkWater(10, date, waterList.get(0));
            checkWater(1000, date1, waterList.get(1));
            assertEquals(2000, mc.getGoal());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }

    }

    @Test
    public void testReaderEmptyWater() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWater.json");
        try {
            MonitorConsumption mc = reader.read();
            assertEquals("Seb's Consumption", mc.getName());
            List<Food> foodList = mc.getListOfFood();
            String dateString1 = "2019-09-09";
            SimpleDateFormat date1 = new SimpleDateFormat(dateString1);
            checkFood("pizza",250, FoodType.BREAKFAST,date1,foodList.get(0));
            String dateString2 = "2018-10-09";
            SimpleDateFormat date2 = new SimpleDateFormat(dateString2);
            checkFood("momos",400, FoodType.LUNCH,date2,foodList.get(1));
            String dateString3 = "2019-09-10";
            SimpleDateFormat date3 = new SimpleDateFormat(dateString3);
            checkFood("burger",500, FoodType.SNACK,date3,foodList.get(2));
            assertEquals(3,foodList.size());
            assertEquals(3, mc.numFood());
            assertEquals(0, mc.numWater());
            assertEquals(1500, mc.getGoal());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }

    }

    @Test
    public void testReaderGeneralConsumption() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralConsumption.json");
        try {
            MonitorConsumption mc = reader.read();
            assertEquals("User's Consumption", mc.getName());
            assertEquals(3000, mc.getGoal());
            assertEquals(3, mc.numWater());
            String dateString = "2001-01-10";
            SimpleDateFormat date = new SimpleDateFormat(dateString);
            String dateString2 = "2015-09-22";
            SimpleDateFormat date2 = new SimpleDateFormat(dateString2);
            List<Water> waterList = mc.getListOfWater();
            checkWater(300, date, waterList.get(0));
            checkWater(100, date2, waterList.get(1));
            checkWater(900, date2, waterList.get(2));
            assertEquals(3, waterList.size());
            List<Food> foodList = mc.getListOfFood();
            checkFood("chicken",850,FoodType.DINNER,date,foodList.get(0));
            checkFood("rajma",300,FoodType.LUNCH,date2,foodList.get(1));
            assertEquals(2, mc.numFood());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception should not have been thrown");
        }
    }


}
