package frontEnd;

import controller.AppController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class LoginPage extends JFrame implements ActionListener {

    JButton b1;  
    JPanel newPanel;  
    JLabel userLabel, passLabel;  
    final JTextField  textField1, textField2;

    AppController controller;
 
 
    LoginPage(AppController controller)
    {

        this.controller=controller;
        //create label for username   
        userLabel = new JLabel();  
        userLabel.setText("Username");      //set label value for textField1  
          
        textField1 = new JTextField(15);
        passLabel = new JLabel();  
        passLabel.setText("Password");      //set label value for textField2  
          
        textField2 = new JPasswordField(15);
        //create login button  
        b1 = new JButton("Login");  
          
        newPanel = new JPanel(new GridLayout(3, 1));  
        newPanel.add(userLabel);    
        newPanel.add(textField1);   
        newPanel.add(passLabel);    
        newPanel.add(textField2);   
        newPanel.add(b1);           
          
        add(newPanel, BorderLayout.CENTER);  
          
        
        b1.addActionListener(this);     
        setTitle("LOGIN FORM");         
    }  
      
    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
        String passValue = textField2.getText();//password from input
        Object obj=ae.getSource();

        if (obj.equals(b1) && userValue.length() < 30 && passValue.length() < 30) {  //check if in database ---- this is a placeholder

            //if log in successful, render the user page
            boolean success=controller.login(userValue,passValue);
            UserPage userPage;
            if(success){
                userPage=new UserPage(controller);
                setVisible(false);
            }
//            MoviesPage page = new MoviesPage();
//
//            page.setVisible(true);
//
//            //create a welcome label and set it to the new page
//            JLabel wel_label = new JLabel("Select From Available movies");
//            page.getContentPane().add(wel_label);
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}
 
