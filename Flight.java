package Flights;

public class Flight{

    final int MAX_SEATS = 10;
    String number;
    String day;
    String destination;
    String[] seatsBooked;

    
    public Flight(){
        this.number = "";
        this.day = "";
        this.destination = "";
        this.seatsBooked = new String[MAX_SEATS];
        // initialise seats array to all null
        for(int i = 0; i<MAX_SEATS;i++) {
            seatsBooked[i] = null;
        }
    }

    public Flight(String number, String day, String destination){
        this.number = number;
        this.day = day;
        this.destination = destination;
        this.seatsBooked = new String[MAX_SEATS];
        // initialise seats array to all null
        for(int i = 0; i < MAX_SEATS;i++) {
            seatsBooked[i] = new String();
        }
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getNumber(){
        return this.number;
    }

    public void setDay(String day){
        this.day = day;
    }

    public String getDay(){
        return this.day;
    }

    public void setDestination(String destination){
        this.destination = destination;
    }

    public String getDestination(){
        return this.destination;
    }

    public String[] getSeatsBooked() {
        return this.seatsBooked;
    }

    // count seats booked on flight
    public int countSeats() {
        int count = 0;
        for(String email : seatsBooked) {
            if (!email.equalsIgnoreCase(""))  count++;
        }
        return count;
    }

    public void setSeat(int index, String email) {
        seatsBooked[index] = email;
    }

    public boolean cancelSeat(String email) {
        boolean cancelled = false;
        for(int i=0; i<MAX_SEATS;i++) {
            if(seatsBooked[i] != null)
                if(seatsBooked[i].equalsIgnoreCase(email)) {
                    seatsBooked[i] = null;
                    cancelled = true;
            }
        }
        return cancelled;
    }

    public String toString(){
        String details = "Flight Number: " + getNumber()      + "\n" +
                "Day of FLight: " + getDay()         + "\n" +
                "Destination: "   + getDestination() + "\n" +
                "Seats Booked: "  + countSeats();
    return details;
    }

    public String[] toArray() {
        String[] data = new String[4];
        data[0] = getNumber();
        data[1] = getDay();
        data[2] = getDestination();
        data[3] = ""+countSeats();

        return data;

    }

    public String[] getHeaders() {
        String[] headers = new String[4];
        headers[0] = "Number";
        headers[1] = "Day";
        headers[2] = "Destination";
        headers[3] = "SeatsBooked";

        return headers;
    }

    // used to display seats on screen
    public String getSeatsToString() {
        String seats = "";
        for(int i = 0;i<MAX_SEATS;i++) {
            String line = (i+1) + ". ";

            if(seatsBooked[i] == null || seatsBooked[i].equalsIgnoreCase(""))
                seats += line + "- ";
            else
                seats += line + seatsBooked[i];

            seats+="\n";
        }

        return seats;
    }

    public boolean checkPassengerBooking(String email) {
        Boolean found = false;
        for(int i = 0;i<MAX_SEATS;i++) {
            if(seatsBooked[i]!= null) {
                if (seatsBooked[i].equalsIgnoreCase(email)) {
                    found = true;
                }
            }
        }
        return found;
    }

    public boolean isSeatBooked(String email) {
        boolean booked = false;
        for(String s : seatsBooked) {
            if (s.equalsIgnoreCase(email)) {
                booked = true;
            }
        }
        return booked;
    }

    public int getSeatNumber(String email) {
        int seatNumber=0;
        for(int i = 0;i<MAX_SEATS;i++) {
            if(seatsBooked[i]!=null)
                if(seatsBooked[i].equalsIgnoreCase(email))
                    seatNumber = i+1;

        }
        return seatNumber;
    }
}