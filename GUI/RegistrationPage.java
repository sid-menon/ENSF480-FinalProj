import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class RegistrationPage extends JFrame implements ActionListener {

    JButton b1;  
    JPanel newPanel;  
    JLabel nameLabel, cardLabel;  
    final JTextField  textField1, textField2;  
    JFrame frame = new JFrame("Register as User");
 
 
    RegistrationPage()  
    {     
        //create label for username   



        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        
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

            //add new Registered user to the data base 
            System.out.println("account created"); 
            frame.dispose();
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}