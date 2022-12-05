package frontEnd;

import controller.AppController;
import controller.Order;
import controller.Seat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class SeatsPage extends JFrame implements ActionListener //list of Seats from user to choose from 
{ 
    JLabel SeatsLabel; 
    final JTextField textField1;
    JButton b1;
    static JList SeatsList;
    
    private AppController controller;
    private Order order;

    
    SeatsPage(AppController controller,Order order)
    {
        this.controller=controller;
        this.order=order;

        JFrame frame = new JFrame("Select Seats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        Box titleText = Box.createHorizontalBox();
        JLabel title = new JLabel("<html><span style='color: black;'>Seats Availables</span></html>");
        title.setFont (title.getFont().deriveFont(32.0f));
        //JLabel version = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Version 1.0<br>Created by Luke Carr</html>");
        //JLabel slogan = new JLabel("<html>Full Potential<br>Minimal Knowledge</html>");
        //titleText.add(version);
        titleText.add(title);
        //titleText.add(slogan);
        titleText.setAlignmentX(frame.getWidth() / 2);

        //Box inputContent = Box.createHorizontalBox();
        //JTextArea code = new JTextArea(35,65);
        //code.setEditable(true);
        //code.setBorder(null);
        //inputContent.add(code);

        frame.add(titleText);
        //frame.add(inputContent);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        SeatsLabel = new JLabel();  
        SeatsLabel.setText("Which Seats Would you like to See?");      //set label value for textField1


//        now the movie, showtime, theater is picked, and room number is picked for the user
//        we will get roomID from movie,theater,and showtime in shows table
//        and join with seats table, extract unoccupied seats



        ArrayList<Seat> seats=controller.getSeatPageData(order); //placeholder --use values from database
        SeatsList = new JList<>(seats.toArray());
        
        SeatsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

          
          
        textField1 = new JTextField(15);

        b1 = new JButton("Submit");
        textField1.setLocation(300,225);
        frame.add(SeatsLabel);
        frame.add(textField1);
        frame.add(SeatsList);
        frame.add(b1);
        b1.addActionListener(this);     
    }  

    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
          
        if (ae.getSource().equals(b1)) {  //check if in database ---- this is a placeholder
            PaymentPage page;
            Seat selectedSeat=(Seat) SeatsList.getSelectedValue();
            if(selectedSeat!=null){
                order.setSeat(selectedSeat);
                if(controller.getUser()==null){
                    page=new PaymentPage(PaymentPageMode.TICKET_ORDER_VISITOR,controller,order);
                }else{
                    page=new PaymentPage(PaymentPageMode.TICKET_ORDER_USER,controller,order);
                }
                //create a welcome label and set it to the new page
                JLabel wel_label = new JLabel("Payment");
                page.getContentPane().add(wel_label);
                System.out.println("Seats selected");
                setVisible(false);
                page.setVisible(true);
                dispose();
            }
            else JOptionPane.showMessageDialog(null,"Please select a seat","error",JOptionPane.ERROR_MESSAGE);



        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}