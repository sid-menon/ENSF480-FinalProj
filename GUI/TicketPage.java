import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicketPage extends JFrame implements ActionListener //list of Theater from user to choose from 
{ 
    JLabel theaterLabel; 
    final JTextField textField1;
    JButton b1;
    JFrame frame = new JFrame("Select Theater");

    JTextArea ticketInfo;
    
        
    
    TicketPage()  
    {  
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        
        //frame.add(inputContent);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        theaterLabel = new JLabel();  
        theaterLabel.setText("Enter your email for a receipt");      //set label value for textField1

        ticketInfo = new JTextArea("Ticket ID is: " + "\n" + "At theater for movie" +"\n" + "at showtime: "+"\n");
        
          
          
        textField1 = new JTextField(30);

        b1 = new JButton("Submit");
        textField1.setLocation(300,225);
        frame.add(ticketInfo);
        frame.add(theaterLabel);
        frame.add(textField1);
        frame.add(b1);
        b1.addActionListener(this);     
    }  

    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
          
        if (userValue.length() < 30) {  //check if in database ---- this is a placeholder
              
            //ShowtimesPage page = new ShowtimesPage();  
              
            //page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            //JLabel wel_label = new JLabel("Select From Available shwotimes");  
            //page.getContentPane().add(wel_label);  
            System.out.println("ticket amde");
            //frame.dispose();
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}