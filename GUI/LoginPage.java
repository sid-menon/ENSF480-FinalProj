import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
 
public class LoginPage extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4;  
    JPanel newPanel;  
    JLabel userLabel, passLabel;  
    final JTextField  textField1, textField2;  
 
 
    LoginPage()  
    {     
        //create label for username   
        userLabel = new JLabel();  
        userLabel.setText("Username");      //set label value for textField1  
          
        textField1 = new JTextField(15);
        passLabel = new JLabel();  
        passLabel.setText("Password");      //set label value for textField2  
          
        textField2 = new JPasswordField(15);
        //create login button  
        b1 = new JButton("Login");
        b2 = new JButton("Continue as Guest");
        b3 = new JButton("Sign Up");
        b4 = new JButton("Cancel Ticket");   
          
        newPanel = new JPanel(new GridLayout(3, 1, 6, 5));  
        newPanel.setBorder( new EmptyBorder(338, 233, 0, 0) );
        newPanel.add(userLabel);    
        newPanel.add(textField1);   
        newPanel.add(passLabel);    
        newPanel.add(textField2);   
        newPanel.add(b1);   
        newPanel.add(b2); 
        newPanel.add(b3);
        newPanel.add(b4);        
          
        add(newPanel, BorderLayout.CENTER);  
          
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);  
        b4.addActionListener(this);    
        setTitle("LOGIN FORM");         
    }  
      
    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
        String passValue = textField2.getText();//password from input

        if(ae.getSource() == b3){
            RegistrationPage page = new RegistrationPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Create a new Account");  
            page.getContentPane().add(wel_label);
        }
        
        if(ae.getSource() == b2){
            MoviesPage page = new MoviesPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Select From Available movies");  
            page.getContentPane().add(wel_label);
        }
        
        if(ae.getSource() == b1){
            if (userValue.length() < 30 && passValue.length() < 30) {  //check if in database ---- this is a placeholder
              
                MoviesPage page = new MoviesPage();  
              
                page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
                JLabel wel_label = new JLabel("Select From Available movies");  
                page.getContentPane().add(wel_label);  
            }  
            else{  
            //show error message  
                JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
            }
        } 
        if(ae.getSource() == b4){
            CancelTicketPage page = new CancelTicketPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Create a new Account");  
            page.getContentPane().add(wel_label);
        }
    } 
}
 
 
 
