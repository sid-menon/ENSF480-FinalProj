package frontEnd;
/**
 *        File Name: ShowTimePage.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Siddharth Menon and Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */

import controller.AppController;
import controller.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;

class ShowtimesPage extends JFrame implements ActionListener //list of Showtimes from user to choose from 
{ 
    JLabel ShowtimesLabel; 
    final JTextField textField1;
    JButton b1;
    static JList ShowtimesList;

    private AppController controller;
    private Order order;

    
    ShowtimesPage(AppController controller,Order order)
    {
        this.controller=controller;
        this.order=order;


        JFrame frame = new JFrame("Select Showtimes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        Box titleText = Box.createHorizontalBox();
        JLabel title = new JLabel("<html><span style='color: black;'>Showtimes Availables</span></html>");
        title.setFont (title.getFont().deriveFont(32.0f));
      
        titleText.add(title);
        titleText.setAlignmentX(frame.getWidth() / 2);

       

        frame.add(titleText);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ShowtimesLabel = new JLabel();  
        ShowtimesLabel.setText("Which Showtimes Would you like to See?");      //set label value for textField1

        ArrayList<Timestamp> showTimes=controller.getShowtimesPageData(order); //use values from database
        ShowtimesList = new JList<>(showTimes.toArray());
        
        ShowtimesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

          
          
        textField1 = new JTextField(15);

        b1 = new JButton("Submit");
        textField1.setLocation(300,225);
        frame.add(ShowtimesLabel);
        frame.add(textField1);
        frame.add(ShowtimesList);
        frame.add(b1);
        b1.addActionListener(this);     
    }  

    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
          
        if (ae.getSource().equals(b1)) {  //check if showtimes is in database
            order.setShowTime((Timestamp) ShowtimesList.getSelectedValue());
            SeatsPage page = new SeatsPage(controller,order);
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Select From Available Seats");  
            page.getContentPane().add(wel_label);  
            System.out.println("Seats selected");
            //get rid of available seats from database
            dispose();
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid showtime", "INVALID SHOWTIME", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}
