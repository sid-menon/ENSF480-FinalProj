package frontEnd;

import controller.AppController;
import controller.Order;
import controller.PaymentInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class PaymentPage extends JFrame implements ActionListener {

    JButton b1;  
    JPanel newPanel;  
    JLabel nameLabel, cardLabel, exprLabel, cvvLabel;  
    final JTextField  textField1, textField2,textField3,textField4;  
    private AppController controller;
    private Order order;

    private PaymentPageMode pageMode;



    PaymentPage(PaymentPageMode mode,AppController controller,Order order)
    {     
        //create label for username   

        this.pageMode=mode;
        this.order=order;
        this.controller=controller;

        JFrame frame = new JFrame("Make Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        Box titleText = Box.createHorizontalBox();
        //JLabel title = new JLabel("<html><span style='color: black;'>Showtimes Availables</span></html>");
        //title.setFont (title.getFont().deriveFont(32.0f));
        //JLabel version = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Version 1.0<br>Created by Luke Carr</html>");
        //JLabel slogan = new JLabel("<html>Full Potential<br>Minimal Knowledge</html>");
        //titleText.add(version);
        //titleText.add(title);
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

        nameLabel = new JLabel();  
        nameLabel.setText("Name");      //set label value for textField1  
          
        textField1 = new JTextField(15);

        cardLabel = new JLabel();  
        cardLabel.setText("Card Number");      //set label value for textField2  
          
        textField2 = new JTextField(15);

        exprLabel = new JLabel();  
        exprLabel.setText("Expiration Date");      //set label value for textField3 
          
        textField3 = new JTextField(15);

        cvvLabel = new JLabel();  
        cvvLabel.setText("              CVV");      //set label value for textField4 
          
        textField4 = new JTextField(15);
        //create login button  
        b1 = new JButton("Submit");  

        if(pageMode.equals(PaymentPageMode.TICKET_ORDER_USER)){
            PaymentInfo userPayment=controller.getUser().getPaymentInfo();
            textField1.setText(userPayment.getCardHolderName());
            textField2.setText(userPayment.getCardNumber());
            textField4.setText(String.valueOf(userPayment.getCvv()));
        }
        


        frame.add(nameLabel);    
        frame.add(textField1);   
        frame.add(cardLabel); 
        frame.add(textField2);

        frame.add(cvvLabel);   
        frame.add(textField4);      
        frame.add(b1);           
          
          
        
        b1.addActionListener(this);     
    }  
      
    public void actionPerformed(ActionEvent ae)    
    {  


          
        if (ae.getSource().equals(b1)) {  //check if in database ---- this is a placeholder
              
            //MoviesPage page = new MoviesPage();  
              
            //page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            //JLabel wel_label = new JLabel("Select From Available movies");  
            //page.getContentPane().add(wel_label);

            if(pageMode.equals(PaymentPageMode.REGISTRATION)){
                String cardHolder = textField1.getText();//username from input
                String cardNumber = textField2.getText();//password from input
                int cvv=Integer.valueOf(textField4.getText());
                PaymentInfo paymentInfo=new PaymentInfo(cardHolder,cardNumber,cvv);
                controller.getUser().setPaymentInfo(paymentInfo);
                if(controller.signUp()){
                    JOptionPane.showMessageDialog(null,"sign up successful","congratulation",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,"user email already exists","fail to sign up",JOptionPane.ERROR_MESSAGE);
                }

            }

            if(pageMode.equals(PaymentPageMode.TICKET_ORDER_USER)){
//                insert the user into reservation
                String confirmation="Movie: "+order.getMovie().getMovieName()+"\n" +
                        "Theater: "+order.getTheater().getName()+"\n" +
                        "ShowTime: "+order.getShowTime()+"\n" +
                        "Room #: "+order.getSeat().getRoomNum()+"\n" +
                        "Row: "+order.getSeat().getRow()+", "+"Column: "+order.getSeat().getCol()+"\n" +
                        "Do you wish to proceed with this ticket purchase?";

                if(JOptionPane.showConfirmDialog(null,confirmation,"order confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    controller.userReserve(order);
                    dispose();
                }
//                change the seat status


            }





        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}