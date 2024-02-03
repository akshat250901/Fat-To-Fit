package ui;

import java.io.FileNotFoundException;

// Runs the FatToFit: A diet managing system
public class Main {
    public static void main(String[] args) {
        try {
            new FatToFit();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
