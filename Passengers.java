package Flights;


/**
 * Write a description of class Passengers here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Passengers
{
    /**
     * Have a max at 50 so that i does not exceed passenger limit
     */
    final int MAX_PASSENGERS = 50;
    final boolean debug = false;
    Passenger[] passengers;

    /**
     * Constructor for objects of class Passengers
     */
    public Passengers()     {
        passengers = new Passenger[MAX_PASSENGERS];
        init();
    }

    private void init() {
        if (debug) {
            // add a couple of passengers

            passengers[0] = new Passenger("John","1 main st","john@gm.com",22);

            passengers[1] = new Passenger("Mary","2 main st","Mary@gm.com",26);
            passengers[2] = new Passenger("Philip","3 main st","Philip@gm.com",24.5);
            passengers[3] = new Passenger("Darren","4 main st","Darren@gm.com",10);
            passengers[4] = new Passenger("Eileen","5 main st","Eileen@gm.com",18);

            passengers[5] = new Passenger("Brian","6 main st","Brian@gm.com",10);
            passengers[6] = new Passenger("Marion","7 main st","@Mariongm.com",18);
            passengers[7] = new Passenger("Auveen","8 main st","Auveen@gm.com",17);
            passengers[8] = new Passenger("Liam","9 main st","Liam@gm.com",16);
            passengers[9] = new Passenger("Joey","10 main st","Joey@gm.com",14);

            passengers[10] = new Passenger("Darren","11 main st","dar@gm.com",10);
            for(int i = 11;i<MAX_PASSENGERS;i++) {
                passengers[i] = null;
            }


        } else {
            // initialise
            for(int i = 0; i < MAX_PASSENGERS;i++)
                passengers[i] = null;
        }

    }


    // add passenger to array in next free slot
    public void add(Passenger passenger) {
        // find first empty slot
        int slot = findNextArraySlot();

        // save passenger to slot in array
        passengers[slot] = new Passenger();
        passengers[slot] = passenger;
    }

    //locate next free array slot - ie first slot == null
    private int findNextArraySlot() {
        int slot = -1;
        for(int i = 0; i < MAX_PASSENGERS; i++) {
            if (passengers[i] == null)
                slot = i;
        }
        return slot;
    }




    //locate passenger by their email address and return this index in array
    public int findByEmailAddress(String email) {
        int index = 0;
        for(int i = 0; i < MAX_PASSENGERS; i++) {

            if(passengers[i] != null) {
                if (passengers[i].getEmail().equalsIgnoreCase(email))
                    index = i;
            }
        }
        return index;
    }

    public void display(Integer flightIndex) {
        String listing="";

        for(Passenger p : passengers) {

        }

    }

    // get passenger based on email address
    public Passenger getPassenger(String email) {
        Passenger passenger = new Passenger();

        for(int i=0;i<MAX_PASSENGERS;i++) {
            // fixes a Null pointer exception error, needs a check to see if its not null before getting email
            if(passengers[i] != null) {
                if (passengers[i].getEmail().equalsIgnoreCase(email)) {
                    passenger = passengers[i];
                }
            }
        }
        return passenger;
    }

    // get passenger based on email address
    public Passenger getPassenger(int index) {
        return passengers[index];
    }

    // get passenger list as a string array for display purposes
    public String[] toStringArray() {
        String[] passList = new String[MAX_PASSENGERS];
        for(int i = 0;i<MAX_PASSENGERS;i++) {
            if(passengers[i] != null)
                passList[i] = passengers[i].toStringLine();
            //else
            //    passList[i] = "";
        }
        return passList;
    }

    public String[] getEmailAddresses() {
        String[] emails = new String[MAX_PASSENGERS];

        for(int i = 0; i < MAX_PASSENGERS;i++) {
            if (passengers[i] != null )
                emails[i] = passengers[i].getEmail();
        }
        return emails;
    }

    public Passenger findPassenger(String email) {
        Passenger passenger = new Passenger();
        for (int i = 0; i<MAX_PASSENGERS; i++){
            if(passengers[i] != null ) {
                if (passengers[i].getEmail().equalsIgnoreCase(email)) {
                    passenger = passengers[i];
                }
            }
        }
        return passenger;
    }

    public void removePassenger(Integer passengerIndex) {
        passengers[passengerIndex] = null;
    }

    public Integer getIndex(String email) {
        Integer index = null;

        for(int i = 0;i<MAX_PASSENGERS;i++) {
            if(passengers[i] != null) {
                if(passengers[i].getEmail().equalsIgnoreCase(email)) {
                    index = i;
                }
            }

        }
        return index;

    }

    public void setPassengerFlight(int passengerIndex, Flight flight) {
        passengers[passengerIndex].setFlight(flight);
    }
}
