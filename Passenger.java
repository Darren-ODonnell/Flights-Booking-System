 

public class Passenger{
    String name;
    String address;
    String email;
    Flight flight;
    double baggage;
    double baggageCost;

    public Passenger(){
        setName("");
        setAddress("");
        setEmail("");
        setFlight(new Flight());
        setBaggage( 0 );
        this.baggageCost = 0;
    }
    public Passenger(String name, String address, String email){
        setName(name);
        setAddress(address);
        setEmail(email);
        setFlight(new Flight());
        setBaggage( 0 );
    }
    public Passenger(String name, String address, String email, double baggage){
        setName(name);
        setAddress(address);
        setEmail(email);
        setFlight(new Flight());
        setBaggage( baggage );
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setFlight(Flight flight){
        this.flight = flight;
    }

    public Flight getFlight(){
        return this.flight;
    }

    public void setBaggage(double baggage){
        this.baggage = baggage;
    }

    public double getBaggage(){
        return this.baggage;
    }

    public double getBaggageCost(){
        double baggageCost = 0;
        final double costPerKilo = 5;
        if(baggage > 20){
            baggageCost = baggage - 20;
            baggageCost *= costPerKilo;
        }
        return baggageCost;
    }

    //Not used as there will be no empty passengers
    public boolean seatBooked(Passenger passenger){
        boolean hasBooking = false;
        if(passenger.getFlight() != null){
            hasBooking = true;
        }
        return hasBooking;
    }

    public String toString(){
        String pString =  "Passenger Details: \n" +
                "Name: "    + getName()    + "\n" +
                "Address: " + getAddress() + "\n" +
                "Email: "   + getEmail()   + "\n" +
                "Weight of Baggage: " + getBaggage() + "\n" +
                "Cost of Extra Baggage: €" + getBaggageCost() + "\n";
        String fString = flight.toString();

        return pString + "\n" + fString;
    }

    public String toStringLine(){
        String seat = "";
        if (flight.getSeatNumber(getEmail()) == 0 ) {// no seat booked yet
            seat = "None Booked Yet";
        } else {
            seat = ""+flight.getSeatNumber(getEmail());
        }

        String pString =  "Passenger Details: " +
                "Name: "    + getName()    + "      " +
                "Address: " + getAddress() + "      " +
                "Email: "   + getEmail()             + "      " +
                "Weight of Baggage: " + getBaggage() + "KG      " +
                "Cost of Extra Baggage: €" + getBaggageCost() + "      " +
                "Flight Number: " + flight.getNumber() + "      " +
                "Seat Number: " + seat  ;

        return pString;
    }

}