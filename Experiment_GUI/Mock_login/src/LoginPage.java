import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel implements ActionListener {

    JButton b1;  

    JLabel userLabel, passLabel;
    JTextField  textField1, textField2;


 
    public LoginPage()
    {

        setLayout(new GridLayout(3,2));
//        this.reference=reference;
        //create label for username   
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1
        add(userLabel);
          
        textField1 = new JTextField();
        add(textField1);

        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2
        add(passLabel);


        textField2 = new JPasswordField(15);
        add(textField2);
        //create login button  
        b1 = new JButton("Login");  
        add(b1);
//        newPanel = new JPanel(new GridLayout(3, 1));
//       add(userLabel);
//       add(textField1);
//       add(passLabel);
//       add(textField2);
//       add(b1);
//        setBackground(Color.BLACK);
//
//        setLayout(new BorderLayout(0, 0));
          
        
        b1.addActionListener();
//        setTitle("LOGIN FORM");
    }  
      
    public void actionPerformed(ActionEvent ae)
    {
        String userValue = textField1.getText();//username from input
        String passValue = textField2.getText();//password from input

        if (userValue.length() < 30 && passValue.length() < 30) {  //check if in database ---- this is a placeholder

            MoviesPage page = new MoviesPage();

//            page.setVisible(true);

            //create a welcome label and set it to the new page
//            JLabel wel_label = new JLabel("Select From Available movies");
//            page.getContentPane().add(wel_label);
//            reference.setContentPane(page);

        }
        else{
            //show error message
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }
    }
}
 
