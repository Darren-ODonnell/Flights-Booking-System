package Flights;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SeatSelection extends JDialog implements ActionListener{
    boolean debug = false;



    final int MAX_SEATS = 10;
    //Used for holding email addresses
    String[] seats = new String[MAX_SEATS];

    private static final long serialVersionUID = 1L;

    JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    // Background colours for buttons
    Color booked = new Color(255, 0 , 0);   //red - seat booked
    Color free = new Color(0,255,50);       //green - seat available

    Font font;
    JButton[] buttons = new JButton[MAX_SEATS];
    JButton cancel;
    JButton confirm;
    Image img;

    Integer seatSelected = null;

    public SeatSelection() throws IOException {
        seatSelected=null;
        //Sample seats
        if (debug) {
            seats[0] = "";
            seats[1] = "abc@123";
            seats[2] = "abc@123";
            seats[3] = "";
            seats[4] = "";
            seats[5] = "abc@123";
            seats[6] = "abc@123";
            seats[7] = "";
            seats[8] = "";
            seats[9] = "abc@123";
        }

    }

    public Integer display(String[] seats) throws IOException {

        this.seats = seats;

        setTitle("Seating Layout");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        font = new Font("Arial", Font.PLAIN, 18);
        img = getImage("seat.png");

        // set default constraints
        Insets inset = new Insets(15,25, 15,25);
        c.weightx = .5;
        c.ipady = 10;
        c.ipadx = 10;
        c.insets = inset;
        c.fill = GridBagConstraints.HORIZONTAL;

        // To keep track of each button, they are placed in an array
        // each indexed position corresponds to a seat position on the plane
        // this will allow a button reset, which is useful when the passenger Cancels
        // or wants to change a booking.

        // create array of buttons

        // add buttons
        addSeatButtons();
        addCancelButton();
        addConfirmButton();

        seatsSelected(); // update view with seats already booked

        // add these buttons to panel.
        addToPane(buttons[0], 0,0);
        addToPane(buttons[1], 1,0);
        addToPane(buttons[2], 0,1);
        addToPane(buttons[3], 1,1);
        addToPane(buttons[4], 0,2);
        addToPane(buttons[5], 1,2);
        addToPane(buttons[6], 0,3);
        addToPane(buttons[7], 1,3);
        addToPane(buttons[8], 0,4);
        addToPane(buttons[9], 1,4);

        addToPane(cancel    , 0,6);
        addToPane(confirm   , 1,6);

        pack();

        // enforces the minimum size of both frame and component
        Dimension dim = new Dimension(400,800);
        setMinimumSize(dim);
        setMaximumSize(dim);
        this.setLayout(new BorderLayout());
        this.add(pane,BorderLayout.CENTER);

        //This gets the size of screen and bases the location of pop-up box to centre
        Dimension dim2 = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dim2.width/2-this.getSize().width/2;
        int y = dim2.height/2-this.getSize().height/2;
        this.setLocation(x,y);

        setModal(true); // block next windows until this exists
        setVisible(true);
        return seatSelected;
    }

    //
    public void seatsSelected() {
        // Red = Booked, and Green = free

        for(int i = 0; i < MAX_SEATS; i++) {
            // email at seat implies seat has been booked already
            String email = seats[i];
            if (email == null || email.length() == 0) {
                buttons[i].setBackground(free);
                buttons[i].setEnabled(true);
            }
            else {
                // seat selected , disable button
                buttons[i].setBackground(booked);
                buttons[i].setEnabled(false);
            }
        }
        // disable confirm button
        confirm.setEnabled(false);
    }

    private void addSeatButtons() {

        // create new buttons
        // set font, img, text and actionListener
        // add to array
        for(int i = 0; i < MAX_SEATS; i++) {
            JButton button = new JButton();
            button.setFont(font);
            button.setIcon(new ImageIcon(img));
            button.setText(""+(i+1));   // adding number to a string will always return a string

            int index = i;              // listener will not accept i but will accept a copy in index - not sure why.
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    actionSelectSeat(index);
                }
            });
            buttons[i] = button;
        }
    }
    private void addConfirmButton() {

        confirm = new JButton();
        confirm.setText("Confirm");
        confirm.setFont(font);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionConfirmSeat();
            }
        });
    }
    private void addCancelButton() {

        cancel = new JButton();
        cancel.setText("Cancel");
        cancel.setFont(font);

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionCancelSeat();
            }
        });

    }
    private void actionSelectSeat(int index) {
        // implies seat not previously selected
        String email = seats[index];
        Color color = buttons[index].getBackground();

        // given that seats booked are disabled
        // only seats which can be booked will be caught here

        if (seatSelected != null) { // ie a seat was previously selected

            if (index == seatSelected) { // implies same seat selected
                buttons[seatSelected].setBackground(free);
                seatSelected = null;
                // seat selected has been cleared - so confirm button is disabled again
                confirm.setEnabled(false);

            } else { // not same seat selected
                buttons[seatSelected].setBackground(free);
                buttons[index].setBackground(booked);
                seatSelected = index;
                // a seat has been booked - enable confirm button.
                confirm.setEnabled(true);
            }

        } else { // ie no seat previously selected (null)
            buttons[index].setBackground(booked);
            seatSelected = index;
            // a seat has been booked - enable confirm button.
            confirm.setEnabled(true);

        }

    }
    private void actionConfirmSeat() {
        /**
         * Get information back and perform actions on information
         *
         */

        this.dispose();

    }
    private void actionCancelSeat() {
        seatsSelected();
    }

    public Image getImage(String image) throws IOException {

        Image img = ImageIO.read(getClass().getResource("/seat60.png"));
        return img;
    }

    public void addToPane(JButton button, int gridx, int gridy)   {

        c.gridx = gridx;
        c.gridy = gridy;
        pane.add(button,c);

    }

    public static void main(String[] args) throws IOException {
        new SeatSelection();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
