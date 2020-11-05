package Flights;

import java.awt.*;

public class Menu {
    //Menu options
    int min = 1;
    int max = 10;
    String menu = "";
    String errMessage = "";


    private final String option1 = "1. Create Passenger";
    private final String option2 = "2. Book a Flight";
    private final String option3 = "3. Select Seat";
    private final String option4 = "4. Add Baggage";
    private final String option5 = "5. Cancel Seat";
    private final String option6 = "6. Remove Passenger";
    private final String option7 = "7. Display Flight Schedule";
    private final String option8 = "8. Display Passenger Details";
    private final String option9 = "9. Display Seats";
    private final String option10 = "10. Exit Flights";
    final String prompt = "Please select one of the options above";

    public Menu() {

        displayMenu();
    }

    public String displayMenu() {
        min = 1;

        menu = option1 + "\n"
                + option2 + "\n"
                + option3 + "\n"
                + option4 + "\n"
                + option5 + "\n"
                + option6 + "\n"
                + option7 + "\n"
                + option8 + "\n"
                + option9 + "\n"
                + option10 + "\n\n"
                + prompt;

        max = 10;
        //if answer is outside of range, error message will display
        errMessage = "Please enter in range " + min + " to " + max;
        return menu;
    }

}