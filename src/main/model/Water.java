package model;

import model.exception.IncorrectDateFormatException;
import model.exception.NegativeWaterException;
import org.json.JSONObject;
import persistence.Writable;

import java.text.SimpleDateFormat;

// Represents a glass of water with amount of water and date
public class Water implements Writable {
    public int amount;              // Amount of water in ml
    private SimpleDateFormat date;    // Date on which food was added


    //MODIFIES: this
    // EFFECTS: Initializes water with given amount of water and given date and throws
    // NegativeWaterException if amount of water is less than 0 and throws IncorrectDateFormatException
    // if date is not in the correct format
    public Water(int water, SimpleDateFormat date) throws NegativeWaterException, IncorrectDateFormatException {
        this.date = date;
        this.amount = water;
        if (water < 0) {
            throw new NegativeWaterException();
        }

        String dateString = this.getDateString();
        if (!dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IncorrectDateFormatException();
        }
    }

    // EFFECTS: Gets the amount of water
    public int getAmountOfWater() {
        return amount;
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
        json.put("amount", amount);
        json.put("date", getDateString());
        return json;
    }
}
