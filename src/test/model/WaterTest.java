package model;

import model.exception.IncorrectDateFormatException;
import model.exception.NegativeWaterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Water class
public class WaterTest {
    public Water water1;
    public Water water2;
    public Water water3;
    public MonitorConsumption monitorConsumption;
    private String dateInString = "2020-09-25";
    SimpleDateFormat date = new SimpleDateFormat(dateInString);

    @BeforeEach
    public void setup() throws NegativeWaterException, IncorrectDateFormatException {
        monitorConsumption = new MonitorConsumption("Akshat's Consumption");
        water1 = new Water(200, date);
        water2 = new Water(500, date);
        water3 = new Water(100, date);
        monitorConsumption.addWater(water1);
        monitorConsumption.addWater(water2);
        monitorConsumption.addWater(water3);
    }

    @Test
    public void testPositiveWaterNoException() {
        MonitorConsumption monitorConsumption1 = new MonitorConsumption("XYZ consumption");
        try {
            Water water = new Water(500,date);
            monitorConsumption1.addWater(water);
            assertEquals(500,monitorConsumption1.calculateWaterConsumed());
            assertEquals(1,monitorConsumption1.numWater());
        } catch (NegativeWaterException e) {
            fail("Exception not expected");
        } catch (IncorrectDateFormatException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void testNegativeWaterException() {
        MonitorConsumption monitorConsumption1 = new MonitorConsumption("XYZ consumption");
        try {
            Water water = new Water(-500,date);
            fail("Exception should have been thrown");
        } catch (NegativeWaterException e) {
            // expected
            assertEquals(0,monitorConsumption1.numWater());
        } catch (IncorrectDateFormatException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void testIncorrectDateFormatException() {
        MonitorConsumption monitorConsumption1 = new MonitorConsumption("XYZ consumption");
        String dateInStringInvalid = "202009-25";
        SimpleDateFormat dateInvalid = new SimpleDateFormat(dateInStringInvalid);
        try {
            Water water = new Water(500,dateInvalid);
            fail("Exception should have been thrown");
        } catch (NegativeWaterException e) {
            fail("Exception not expected");
        } catch (IncorrectDateFormatException e) {
            //expected
            assertEquals(0,monitorConsumption1.numWater());
        }
    }

    @Test
    public void testCalculateWaterConsumed() {
        assertEquals(800,monitorConsumption.calculateWaterConsumed());
    }

    @Test
    public void testCalculateWaterConsumedOnGivenDate() {
        String dateInString = "2020-10-30";
        SimpleDateFormat date1 = new SimpleDateFormat(dateInString);
        Water water4 = null;
        try {
            water4 = new Water(1000, date1);
        } catch (NegativeWaterException | IncorrectDateFormatException e) {
            e.printStackTrace();
        }
        monitorConsumption.addWater(water4);
        assertEquals(800,monitorConsumption.calculateWaterConsumedOnGivenDate("2020-09-25"));
        assertEquals(1000,monitorConsumption.calculateWaterConsumedOnGivenDate("2020-10-30"));
    }

    @Test
    public void testAddWater() {
        assertEquals(3,monitorConsumption.water.size());
        String dateInString = "2020-10-30";
        SimpleDateFormat date1 = new SimpleDateFormat(dateInString);
        Water water4 = null;
        try {
            water4 = new Water(1000, date1);
        } catch (NegativeWaterException | IncorrectDateFormatException e) {
            e.printStackTrace();
        }
        monitorConsumption.addWater(water4);
        assertEquals(4,monitorConsumption.water.size());
    }

    @Test
    public void testTotalWaterConsumed() {
        List<Water> list = new ArrayList<>();
        list.add(water1);
        list.add(water2);
        list.add(water3);
        assertEquals(list,monitorConsumption.totalWaterConsumed());
    }
}
