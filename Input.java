package Flights;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Input {


    Display win = new Display();
    Font font = new Font("Arial", Font.BOLD, 18);

    public Input() {


    }
    
    //read in string with a defined minimum only
    public String string(String prompt, int length, String errMessage) {
        String inputStr = "";

        do {
            inputStr = win.input(prompt);
            if (inputStr.length() < length){

                win.showMessage(errMessage);
            }
        } while (inputStr.length() < length);

        return inputStr;
    }

    //read in string with a defined minimum and maximum length
    public String string(String prompt, int min,int max, String errMessage) {
        String inputStr = "";

        do {
            inputStr = win.input(prompt);
            if (inputStr.length() < min || inputStr.length() > max)
                win.showMessage(errMessage);

        } while (inputStr.length() < min || inputStr.length() > max);

        return inputStr;
    }

    //read in string no no min or max length defined
    public String string(String prompt) {
        String inputStr = win.input(prompt);
        return inputStr;
    }

    // simple regex match using code
    public String string(String prompt, String code) {
           String inputStr = "";
        do{
            inputStr = win.input(prompt);
        }while(!inputStr.matches(code));
        
        return inputStr;
    }

    // input valid if in list
    public String string(String[] myList, String prompt, String errMessage) {
        String inputStr = "";
        boolean valid = false;

        do {
            // take in a string
            inputStr = win.input(prompt);


            if  (!listContains(myList, inputStr)) {
                win.showMessage(errMessage);
            } else {
                valid = true;
            }

        } while (!valid);

        return inputStr;
    }

    // input valid if not already in list
    public String stringExists(String[] myList, String prompt, String errMessage) {
        String inputStr = "";
        boolean valid = false;

        do {
            // take in a string
            inputStr = win.input(prompt);


            if  (listContains(myList, inputStr)) {
                win.showMessage(errMessage);
            } else {
                valid = true;
            }

        } while (!valid);

        return inputStr;
    }

    // check if a list contains a value (ignoring case)
    private boolean listContains(String[] list, String item) {
        boolean found = false;

        for(String value : list)
            if(value != null)
                if(value.equalsIgnoreCase(item))
                    found = true;

        return found;
    }

    //int input with min & max values
    public int number(String prompt, int min, int max, String errMessage)     {
        int number = min-1;
        String numStr ="";
        do {
            numStr = win.input(prompt);

            try {
                number = Integer.parseInt(numStr);
                if (number < min || number > max)
                    win.showMessage(errMessage);
            } catch (NumberFormatException ex) {
                win.showMessage(errMessage);
            }
        } while (number < min || number > max);

        return number;
    }

    //double input with min & max values
    public double number(String prompt, double min, double max, String errMessage)     {
        double number = min-1;
        String numStr = "";
        do {
            numStr = win.input(prompt);

            try {
                number = Double.parseDouble(numStr);
                if (number < min || number > max)
                    win.showMessage(errMessage);
            } catch (NumberFormatException ex) {
                win.showMessage(errMessage);
            }
        } while (number < min || number > max);

        return number;

    }

    //float input with min & max values
    public float number(String prompt, float min, float max, String errMessage)     {
        float number = min-1;

        String numStr = "";
        do {
            numStr = win.input(prompt);

            try {
                number = Float.parseFloat(numStr);
                if (number < min || number > max)
                    win.showMessage(errMessage);
            } catch (NumberFormatException ex) {
                win.showMessage(errMessage);
            }
        } while (number < min || number > max);
        return number;

    }

    //String to double conversion
    public double number(String prompt) {
        double number = 0;
        boolean valid = false;
        String numStr = "";

        do {
            numStr = win.input(prompt);

            try {
                number = Double.parseDouble(numStr);
                valid = true;

            } catch (NumberFormatException ex) {
                win.showMessage("Invalid Entry please try again");
            }
        } while (!valid);

        return number;

    }

    //When selling products check balance and stock and return error if value exceeds them
    public int number(String prompt, int min, int max, 
                        double price, double balance, 
                        String errMessage, String errMessage2){
        int number = min-1;
        String numStr ="";
        do {
            numStr = win.input(prompt);

            try {
                number = Integer.parseInt(numStr);
                if (number < min || number > max){
                    win.showMessage(errMessage);
                }
                if(checkBalance(number, price, balance)){
                   win.showMessage(errMessage2);
                   //Force it to stay in loop
                   number = min - 1;
                }
            } catch (NumberFormatException ex) {
                win.showMessage(errMessage);
            }
        } while (number < min || number > max);

        return number;
    }
    public boolean checkBalance(int quantity, double price, double balance){
        return balance < quantity * price;
    }


}
