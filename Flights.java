import java.io.IOException;
import java.util.Arrays;

public class Flights{
    final int MAX_FLIGHTS = 5;
    Flight[] flights = new Flight[MAX_FLIGHTS];
    boolean debug = false;
    static Input input = new Input();
    static Display window = new Display();
    Menu menu = new Menu();
    int numberOfFlights = 0;
    final String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday",
            "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};



    Passengers passengers;

    public Flights(){
        init();
    }

    public void init(){
        passengers = new Passengers(); // this will setup 10 sample pas
        if(debug){
            flights[0] = new Flight("EI123", "Monday", "England");
            flights[1] = new Flight("EI234", "Tuesday", "Scotland");
            flights[2] = new Flight("EI345", "Wednesday", "Wales");
            flights[3] = new Flight("EI456", "Thursday", "France");
            flights[4] = new Flight("EI567", "Friday", "Germany");

            // using just the first two flights
            // first 5 passengers added to 1st flight, 2nd 5 added to 2nd flight
            // this one has no seat booked
            passengers.getPassenger(0).setFlight(flights[0]);
            //passengers.getPassenger(0).getFlight().setSeat(0,passengers.getPassenger(0).getEmail());
            // next 4 have seats on flight (0)
            passengers.getPassenger(1).setFlight(flights[0]);
            passengers.getPassenger(1).getFlight().setSeat(1,passengers.getPassenger(1).getEmail());
            passengers.getPassenger(2).setFlight(flights[0]);
            passengers.getPassenger(2).getFlight().setSeat(5,passengers.getPassenger(2).getEmail());
            passengers.getPassenger(3).setFlight(flights[0]);
            passengers.getPassenger(3).getFlight().setSeat(8,passengers.getPassenger(3).getEmail());
            passengers.getPassenger(4).setFlight(flights[0]);
            passengers.getPassenger(4).getFlight().setSeat(9,passengers.getPassenger(4).getEmail());

            // next 5 have seats on flight (1)
            passengers.getPassenger(5).setFlight(flights[1]);
            passengers.getPassenger(5).getFlight().setSeat(1,passengers.getPassenger(5).getEmail());
            passengers.getPassenger(6).setFlight(flights[1]);
            passengers.getPassenger(6).getFlight().setSeat(2,passengers.getPassenger(6).getEmail());
            passengers.getPassenger(7).setFlight(flights[1]);
            passengers.getPassenger(7).getFlight().setSeat(6,passengers.getPassenger(7).getEmail());
            passengers.getPassenger(8).setFlight(flights[1]);
            passengers.getPassenger(8).getFlight().setSeat(7,passengers.getPassenger(8).getEmail());
            passengers.getPassenger(9).setFlight(flights[1]);
            passengers.getPassenger(9).getFlight().setSeat(9,passengers.getPassenger(9).getEmail());

            // Note passenger 10 has no flight booked

            numberOfFlights = 5;
        }else{
            for(int i = 0; i < 5; i++){

                Flight flight;

                flight = new Flight("","","");
                String prompt = "Enter the flight number for this flight: ";
                String code = "[E][I][0-9]{3}";
                String temp = "";
                temp = input.string(prompt, code);
                flight.setNumber(temp);
    
                prompt = "Enter destination for this flight: ";
                temp = input.string(prompt);
                flight.setDestination(temp);
    
                prompt = "Input the day of the week for this flight ";
                String errMessage = "String entered is not a day of the week ";

                temp = input.string(days, prompt, errMessage);
                flight.setDay(temp);

                flights[i] = flight;
            }
        }
    }
    
    public void add(String number, String day, String destination){
            Flight f = new Flight(number, day, destination);
            //insert flight
            if(numberOfFlights < MAX_FLIGHTS){
                //locate free slot to add flight
                flights[numberOfFlights] = f;
            }else{
                //return error message... no more flights can be added
            }
            numberOfFlights++;
    }
    
    public Integer locateFlight(String destination, String day) {
        //Has to be null or -1, as setting it to 0 would access the first entry instead
        Integer flightIndex = null;

        for(int i = 0; i<MAX_FLIGHTS;i++)  {
            Flight flight = getFlight(i);
            if(flight.getDestination().equalsIgnoreCase(destination) &&
                    flight.getDay().equalsIgnoreCase(day)) {
                flightIndex = i;
            }
        }
        return flightIndex;

    }

    public Flight getFlight(int index) {
        return flights[index];
    }

    public void bookFlight() {
        /* Input a destination and day to travel
         * create passenger
         * call the bookSeat method in that flight
         * add person to flight array of their choice
         * save updated flight back to flights
         * add passenger to passengers
         */

        // get destination
        // null is also a possible return value, hence integer type.

        // Display the list of flights available
        // enter email - must already exist
        String email = getPassengerEmailwithArray();
        // get passenger index by email
        int passengerIndex  = passengers.findByEmailAddress(email);


        displayFlightsAsTable();
        Integer flightIndex = selectFlight();
        // retrieve flight
        Flight flight = getFlight(flightIndex);

        // save copy of flight in passengers at passengerIndex
        passengers.setPassengerFlight(passengerIndex, flight);

        // show passengers on this flight
        // passengers.display(flightIndex);

    }

    public void bookSeatOnPlane() throws IOException {

        /** I need email to get passenger, from passenger I can get the flight and then seats
         * enter email and retrieve passenger
         * get flight from passenger
         * get seats from flight and pass to bookSeat()
         * finally update seats with booked seat number.
         */
        String prompt = "Enter your email address";
        String errMessage = "Email does not exist - please re-enter";
        String[] emailAddresses = passengers.getEmailAddresses();
        String email = input.string(emailAddresses, prompt, errMessage);

        // get passenger by email address
        Passenger passenger = passengers.getPassenger(email);

        // get flight via FlightNumber and FlightIndex
        String flightNumber = passenger.getFlight().getNumber();
        int flightIndex = getFlightIndex(flightNumber);

        if(flightIndex == -1) {
            window.showMessage("You have not selected a flight Yet");
        } else {

            if (!flights[flightIndex].isSeatBooked(email)) { // seat not booked by this passenger

                // select seat
                SeatSelection pickSeats = new SeatSelection();
                Integer seatNumber = pickSeats.display(flights[flightIndex].getSeatsBooked());
                pickSeats.dispose();

                // mark chosen seat as taken (by entering email against seat number.
                flights[flightIndex].setSeat(seatNumber, passenger.getEmail());

            } else { // seat already booked by this passenger
                window.showMessage("You have already selected a seat on this flight");
            }
        }
    }

    // return flightIndex given FlightNumber
    private int getFlightIndex(String flightNumber) {
        int index = -1;

        for(int i = 0;i<MAX_FLIGHTS;i++) {
            if (flights[i].getNumber().equalsIgnoreCase(flightNumber))
                index = i;
        }
        return index;
    }

    // create new passenger
    public void createPassenger() {
        String name = "";
        String address = "";
        String email = "";
        name = input.string("Input Name: ");
        address = input.string("Input Address: ");
        String[] emailAddresses = passengers.getEmailAddresses();
        email = input.stringExists(emailAddresses,"Input Email Address: ","Email address already exists please re-enter or use another");

        passengers.add(new Passenger(name, address, email));
    }

    // select flight by flightIndex
    public Integer selectFlight() {
        //Checks Day & Destination input to be valid as well as checking if there is a flight available
        String[] destinations = getDestinationList();
        String prompt = "Enter a destination";
        String errMessage = "Invalid destination, please enter another";
        String promptDay = "Input day of departure";
        String errMessageDay = "Not a valid weekday";
        Integer flightIndex = null;
        do{
            String destination = input.string(destinations,prompt, errMessage);
            String day = input.string(days,promptDay,errMessageDay);

            if ((flightIndex = locateFlight(destination, day)) == null)
                window.showMessage("We do not have a flight with these details,"
                        + "Please enter new details. ");

        } while( flightIndex == null );

        return flightIndex;
    }

    // cancel booking / removing passenger and associated seat booked.
    public void cancelSeat(){
        //Getting email from passenger array
        String email = getPassengerEmailwithArray();

        Passenger passenger = passengers.getPassenger(email);

        // get flight passenger is on from flight object in passenger
        String flightNumber = passenger.getFlight().getNumber();

        // get flight object from flights array
        Flight flight = getFlight(flightNumber);

        // cancel seat on this flight based on email address
        if(!flight.cancelSeat(email)) {
            window.showMessage("You do not have a seat booked on this flight: " + flightNumber);
        } else {
            window.showMessage("Your seat has been cancelled on this flight: " + flightNumber);
        }

    }

    // return flight based on flightNumber
    public Flight getFlight(String flightNumber) {
        Flight flight = new Flight();
        //Checks if flightNumber is equal to any of the array indexes
        for(Flight f : flights) {
            if(f.getNumber().equalsIgnoreCase(flightNumber)) {
                flight = f;
            }
        }
        return flight;
    }

    // show all flights in a JTable - although table displayed - could not get rid of it
    public void displayFlightsAsTable(){
        //An array of arrays is needed to put data in the table
        String[][] data = toArrayofArrays();

        String title = "Flight Schedule";
        String[] headers = flights[0].getHeaders();

        window.showTable(title, headers, data);

    }

   // return list of flight destinations for display as a menu
    public String[] getDestinationList() {
        String[] destinations = new String[MAX_FLIGHTS];
        for(int i=0; i<MAX_FLIGHTS;i++) {
            destinations[i] = flights[i].getDestination();
        }
        return destinations;
    }

    // get list of flights as array of strings
    public String[] getFlightList() {
        String[] flightsStr = new String[MAX_FLIGHTS];
        for (int i = 0; i < MAX_FLIGHTS; i++) {
            flightsStr[i] = flights[i].toString() + "\n";
        }
        return flightsStr;
    }

    // take flights and put into an array of arrays for JTable
    public String[][] toArrayofArrays() {

        String[][] data = new String[MAX_FLIGHTS][4];
        String[] emptyArray = new String[4];
        emptyArray[0] = "No Flight Detail";
        emptyArray[1] = "";
        emptyArray[2] ="";
        emptyArray[3] ="";

        for(int i = 0; i<MAX_FLIGHTS;i++) {
            if (flights[i] != null)
                data[i] = flights[i].toArray();
            else
                data[i] = emptyArray;
        }
        return data;
    }

    public void addLuggage() {
        // get passenger

        String prompt = "Enter your email address: ";
        String errMessage = "Email address not found please re-enter";
        String[] emailAddresses = passengers.getEmailAddresses();
        String email = input.string(emailAddresses, prompt, errMessage);

        // return passenger based on email address
        Passenger passenger = passengers.findPassenger(email);

        prompt = "Input the weight of your extra luggage: ";
        double min = 0;
        double max = 30;
        errMessage = "Invalid weight or outside of bounds 0-30KG";
        double baggage = input.number(prompt, min, max, errMessage);

        passenger.setBaggage(baggage);

    }

    public void displaySeats() {

        // get flight Number
        // get seats from this flight
        // display seats

        String prompt = "Enter Flight Number";
        String errMessage = "Invalid flight number entered - please re-enter";

        String[] flightNumbers = getFlightNumbers();
        String flightNumber = input.string(flightNumbers,prompt,errMessage);

        Integer flightIndex = getFlightIndex(flightNumber);
        String seats = flights[flightIndex].getSeatsToString();

        String title = "Seats Booked on Flight " + flightNumber;
        window.showMessage(seats,title);



    }

    // get array of flight numbers
    private String[] getFlightNumbers() {
        String[] flightNumbers = new String[MAX_FLIGHTS];

        for(int i = 0;i<MAX_FLIGHTS;i++) {
            flightNumbers[i] = flights[i].getNumber();
        }
        return flightNumbers;


    }

    public void removePassenger() {
        // enter email of passenger
        String email = getPassengerEmailwithArray();
        // check if passenger has a seat booked (if so cannot remove until seat is cancelled
        Passenger passenger = passengers.getPassenger(email);
        // make index in passengers array = null to remove the passenger
        Integer passengerIndex = passengers.getIndex(email);


        if(passenger.getFlight()!=null) {
            if(!passenger.getFlight().checkPassengerBooking(email)){
                passenger.setFlight(null);
                passengers.removePassenger(passengerIndex);


            } else {
              // error cannot continue customer
              window.showMessage("Cannot remove Passenger - seat booking exists, please remove this first");
            }
        }

    }

    public String getPassengerEmailwithArray() {
        String prompt = "Input Email of passenger";
        String email = "";
        String errMessage = "Email address not found - Please re-enter";

        email = input.string(passengers.getEmailAddresses(),prompt,errMessage);

        return email;

    }

}