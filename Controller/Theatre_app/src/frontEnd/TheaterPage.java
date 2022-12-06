package frontEnd;
/**
 *        File Name: TheaterPage.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Siddharth Menon and Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */

import controller.AppController;
import controller.Order;
import controller.Theater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class TheaterPage extends JFrame implements ActionListener //list of Theater from user to choose from 
{ 
    JLabel theaterLabel; 
    final JTextField textField1;
    JButton b1;
    static JList theaterList;
    private AppController controller;
    private Order order;
        
    
    TheaterPage(AppController controller, Order order)
    {
        this.controller=controller;
        this.order=order;
        JFrame frame = new JFrame("Select Theater");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        Box titleText = Box.createHorizontalBox();
        JLabel title = new JLabel("<html><span style='color: black;'>theater Availables</span></html>");
        title.setFont (title.getFont().deriveFont(32.0f));
        
        titleText.add(title);
        titleText.setAlignmentX(frame.getWidth() / 2);


        frame.add(titleText);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        theaterLabel = new JLabel();  
        theaterLabel.setText("Which theater Would you like to See?");      //set label value for textField1

        ArrayList<Theater> theaters=controller.getTheaterPageData(order.getMovie());
        theaterList = new JList<>(theaters.toArray()); //create list of theaters

        
        theaterList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

          
          
        textField1 = new JTextField(15);

        b1 = new JButton("Submit");
        textField1.setLocation(300,225);
        frame.add(theaterLabel);
        frame.add(textField1);
        frame.add(theaterList);
        frame.add(b1);
        b1.addActionListener(this);     
    }  

    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
          
        if (ae.getSource().equals(b1)&&userValue.length() < 30) {  //check if theater in database

            order.setTheater((Theater) theaterList.getSelectedValue());
            ShowtimesPage page = new ShowtimesPage(controller,order);
              
            page.setVisible(true);  

            setVisible(false);
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Select From Available shwotimes");  
            page.getContentPane().add(wel_label);  
            System.out.println("showtimes selected");
            dispose();
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid theater", "INVALID THEATER", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}
