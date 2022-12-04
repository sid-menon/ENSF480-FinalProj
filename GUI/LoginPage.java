import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
 
public class LoginPage extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4;  
    JLabel userLabel, passLabel;  
    private JFrame frame;
	private JPanel southPanel = new JPanel();
	private JPanel masterPanel = new JPanel();
    private JTextArea theText;
	private JScrollPane scroll;  
 
 
    LoginPage()  
    {     

        
        frame = new JFrame();

        frame.setTitle("Guest Menu");
		
		frame.setResizable(false); 		//user can't resize GUI on their own
		frame.setSize(1000,200);
          
 
        b1 = new JButton("Login");
        b2 = new JButton("Continue as Guest");
        b3 = new JButton("Sign Up");
        b4 = new JButton("Cancel Ticket");   
          
        southPanel = new JPanel(new GridLayout(3, 1, 6, 5));  
        southPanel.setBorder( new EmptyBorder(338, 233, 0, 0));
  
        southPanel.add(b1);   
        southPanel.add(b2); 
        southPanel.add(b3);
        southPanel.add(b4);
        
        theText = new JTextArea();
		scroll = new JScrollPane(theText);
		
		masterPanel.setLayout(new BorderLayout());
		
		masterPanel.add(southPanel, BorderLayout.PAGE_END);
		masterPanel.add(scroll,BorderLayout.CENTER);
		
		frame.add(masterPanel);
		frame.setVisible(true);
          
          
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);  
        b4.addActionListener(this); 
        
        theText.setEditable(false);
		frame.setLocationRelativeTo(null);
    }  
      
    public void actionPerformed(ActionEvent ae)    
    {  


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
            
              
            SignInPage page = new SignInPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Please Sign In");  
            page.getContentPane().add(wel_label);  
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
 
