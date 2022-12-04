import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPage extends JFrame implements ActionListener {

    JButton b1;  
    JPanel newPanel;  
    JLabel nameLabel, passLabel;  
    final JTextField  textField1, textField2; 
    JFrame frame = new JFrame("Sign In");
 
 
 
    SignInPage()  
    {     



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

       
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        nameLabel = new JLabel();  
        nameLabel.setText("Username");      //set label value for textField1  
          
        textField1 = new JTextField(15);
        passLabel = new JLabel();  
        passLabel.setText("Password");      //set label value for textField2  
          
        textField2 = new JTextField(15);

        //create login button  
        b1 = new JButton("Submit");  
          
        


        frame.add(nameLabel);    
        frame.add(textField1);   
        frame.add(passLabel); 
        frame.add(textField2);
        frame.add(b1);           
          
          
        
        b1.addActionListener(this);     
    }  
      
    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
        String passValue = textField2.getText();//password from input        
          
        if (userValue.length() < 30 && passValue.length() < 30) {  //check if in database ---- this is a placeholder
              
            MoviesPage page = new MoviesPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Select From Available movies");  
            page.getContentPane().add(wel_label); 
            System.out.println("paid");
            frame.dispose(); 
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}