/*** Flight Booking System 
 *   Author: Darren O'Donnell
 *   Student number: C19313413
 * 
 * 
 */ 

import java.io.IOException;

public class Driver{

    // all display methods
    static Display window = new Display();

    // all the input methods
    static Input input = new Input();

    // menu creation
    static Menu menu = new Menu();

    static Flights flights;

    public Driver() throws IOException {

        //Initialising Flights and Passengers Arrays
        final int NUM_FLIGHTS = 5;
        //5 flights and 10 people per flight. gives 50 total seats 

        flights  = new Flights();

        runApp();

    }

    public void runApp() throws IOException {
        int option = 0;
        int i = 0;

        do{
            // display menu
            option = input.number(menu.displayMenu(), menu.min, menu.max, menu.errMessage);

            //Menu Selection screen.
            switch (option) {
                case 1:
                    createPassenger();
                    break;
                case 2:
                    bookFlight();
                    break;
                case 3:
                    bookSeat();
                    break;
                case 4:
                    addLuggage();
                    break;
                case 5:
                    cancelSeat();
                    break;
                case 6:
                    removePassenger();
                    break;
                case 7:
                    displayFlightDetails();
                    break;
                case 8:
                    displayPassengers();
                    break;
                case 9:
                    displaySeats();
                    break;
                case 10:
                    exit();
                    break;
            }
        } while(option != menu.max);
    }

    private void removePassenger() {
        flights.removePassenger();
    }

    private void createPassenger() {
        flights.createPassenger();
    }

    private void displaySeats() {
        flights.displaySeats();
    }

    private void bookSeat() throws IOException {
        flights.bookSeatOnPlane();
    }

    public static void bookFlight() throws IOException {

        flights.bookFlight();
    }

    public static void cancelSeat() {
        flights.cancelSeat();
    }

    public static void displayFlightDetails(){
        String[] flightList = flights.getFlightList();
        window.showMessage(flightList);
    }

    public void displayPassengers() {
        String[] passengerListAll = flights.passengers.toStringArray();
        window.showMessage(passengerListAll);

    }

    public static void exit(){
        window.showMessage("Thank you for using this flight booking system");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        new Driver();
    }

    public void addLuggage() {
        flights.addLuggage();
    }
}