import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SeatsPage extends JFrame implements ActionListener //list of Seats from user to choose from 
{ 
    JLabel SeatsLabel; 
    final JTextField textField1;
    JButton b1;
    static JList SeatsList;
    JFrame frame = new JFrame("Select Seats");
    
        
    
    SeatsPage()  
    {  
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        SeatsLabel = new JLabel();  
        SeatsLabel.setText("Which Seats Would you like to See?");      //set label value for textField1

        String Seats[] ={"Seats1", "Seats2"}; //placeholder --use values from database
        SeatsList = new JList<>(Seats);
        
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
          
        if (userValue.length() < 30) {  //check if in database ---- this is a placeholder
              
            PaymentPage page = new PaymentPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Payment");  
            page.getContentPane().add(wel_label);  
            System.out.println("Seats selected");
            frame.dispose();
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}