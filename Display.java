package Flights;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Scanner;

public class Display {
    /*
     * Generic Display module
     * all prompts and data written to screen should be done here
       */
    Font font;
    int rowHeight=0;


    public Display() {
        // the app appears different on different displays
        // Laptop / College desktop / home desktop
        // I have fixed the positioning of the windows, but the font I cant quite get right
        // I have used a scaling factor of 100 so that the
        // when creating the table, a row height is set based on the fontsize (fontsize + 50%)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        //
        int scalingFactor = 100;
        int fontSize = screen.height/scalingFactor;
        font = new Font("Arial", Font.BOLD, fontSize);
        rowHeight = (int) (fontSize * 1.5);

        // I could not set JOPtionpane font within the class, I expected JOptionPane.setFont() to work ... it didnt, Instead the following method was used.
        UIManager.put("OptionPane.messageFont", font);

    }

    //Display message with ok box
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    //Display message with ok box and dialog title
    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE,null);

    }

    // display array
    public void showMessage(String[] message) {
        String list = "";
        for(String s : message) {
            //This stops it displaying all the empty passenger variables
            if(s!=null)
                list += s + "\n";
        }
        list +="\n";
        showMessage(list);
    }


    //Yes/No Dialog box
    public int confirmYesNo( String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title
                , JOptionPane.YES_NO_OPTION);
    }

    //Input processing box
    public String input(String prompt) {

        return JOptionPane.showInputDialog(prompt);
    }

    public void showTable(String title, String[] headers, String[][] data) {

        int border = 100;
        int tableStartX = 40;
        int tableStartY = 70;
        int tableWidth = 250;

        int dialogWidth = tableWidth + border;
        int tableHeight = 500;
        int dialogHeight = tableHeight + border;

        // Init the JTable
        JTable jt = setupTable(tableStartX,tableStartY,tableWidth,tableHeight, data, headers);

        // Init the JDialog
        JDialog jd = setupDialog(dialogWidth, dialogHeight, title, jt.getTableHeader());
        
        // grab header from table and place header at top of dialog box
        JTableHeader header = jt.getTableHeader();
        header.setFont(font);
        jd.add(header, BorderLayout.NORTH);

        // add table to centre of Dialog
        jd.add(jt,BorderLayout.CENTER);
        // Frame Visible = true
        // This will block any additional windows interfering until user has finished with this window

        jd.setModal(true);
        jd.setVisible(true);
    }

    public JDialog setupDialog(int width, int height, String title, JTableHeader tableHeader) {
        JDialog jd = new JDialog();

        // get screen dimensions
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        // specify JDialog size
        int dialogWidth = 700;
        int dialogHeight = 300;
        // specify x,y based on screen centre and size of Dialog/Table
        int x = screen.width/2 - dialogWidth/2;
        int y = screen.height/2 - dialogHeight/2;

        jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jd.setTitle(title);
        jd.setLayout(new BorderLayout());

        // use these to place and size the dialog box
        jd.setBounds(x,y, dialogWidth, dialogHeight);

        return jd;

    }

    public JTable setupTable(int x, int y, int width, int height, String[][] data, String[] headers) {

        /**
         * My initial table allowed for cell editing - it took a while to find a solution to this,
         * This one appears to work ok. The JTable has a method called isCellEditable() this must be overriden
         * as follows. Another solution would be to extend JTable and override the method there.
         */
        JTable jt = new JTable(data, headers){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        // stop column dragging in table
        jt.setBounds(x,y, width, height);
        // default font details
        jt.setFont(font);

        // the rowHeight = fontsize*1.5 or 50% bigger
        jt.setRowHeight(rowHeight);

        return jt;

    }

    /**
     * Attempting to add two objects onto a JDialog.
     * I setup the basic JOptionPane, but did not have enough time to complete.
     * This not for use now, but will probably be used for other projects.
     */
    public JOptionPane setupJOptionPane(int width, int height, String message) {
        JOptionPane jop = new JOptionPane();
        jop.setPreferredSize(new Dimension(width, height));
        jop.setMessage(message);

        return jop;

    }

}
