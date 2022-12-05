package frontEnd;
import controller.AppController;
import controller.RegisteredUser;
import controller.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class RegistrationPage extends JFrame implements ActionListener {

    JButton b1;  
    JPanel newPanel;  
    JLabel nameLabel, cardLabel;  
    final JTextField  textField1, textField2;  

    private AppController controller;
    RegistrationPage(AppController controller)
    {     
        //create label for username   
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
        nameLabel.setText("Create a Username");      //set label value for textField1  
          
        textField1 = new JTextField(15);
        cardLabel = new JLabel();  
        cardLabel.setText("Create a Password");      //set label value for textField2  
          
        textField2 = new JTextField(15);

        //create login button  
        b1 = new JButton("Submit");  
          
        


        frame.add(nameLabel);    
        frame.add(textField1);   
        frame.add(cardLabel); 
        frame.add(textField2);
        frame.add(b1);           
          
          
        
        b1.addActionListener(this);     
    }  
      
    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
        String passValue = textField2.getText();//password from input        
          
        if (userValue.length() < 30 && passValue.length() < 30) {  //check if username not in database database ---- this is a placeholder

            User newUser=new RegisteredUser(userValue,passValue);
            controller.setUser(newUser);
            PaymentPage page=new PaymentPage(PaymentPageMode.REGISTRATION);
            page.setController(controller);



            //add new Registered user to the data base 
            System.out.println("account created"); 
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}