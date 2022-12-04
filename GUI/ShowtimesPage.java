import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ShowtimesPage extends JFrame implements ActionListener //list of Showtimes from user to choose from 
{ 
    JLabel ShowtimesLabel; 
    final JTextField textField1;
    JButton b1;
    static JList ShowtimesList;
    JFrame frame = new JFrame("Select Showtimes");
    
        
    
    ShowtimesPage()  
    {  
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ShowtimesLabel = new JLabel();  
        ShowtimesLabel.setText("Which Showtimes Would you like to See?");      //set label value for textField1

        String Showtimes[] ={"7:00pm", "7:00pm"}; //placeholder --use values from database
        ShowtimesList = new JList<>(Showtimes);
        
        ShowtimesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

          
          
        textField1 = new JTextField(15);

        b1 = new JButton("Submit");
        textField1.setLocation(300,225);
        frame.add(ShowtimesLabel);
        frame.add(textField1);
        frame.add(ShowtimesList);
        frame.add(b1);
        b1.addActionListener(this);     
    }  

    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
          
        if (userValue.length() < 30) {  //check if in database ---- this is a placeholder
              
            SeatsPage page = new SeatsPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Select From Available Seats");  
            page.getContentPane().add(wel_label);  
            System.out.println("Seats selected");
            //get rid of available seats from database
            frame.dispose();
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}
