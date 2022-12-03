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
    
        
    
    SeatsPage()  
    {  
        JFrame frame = new JFrame("Select Seats");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        Box titleText = Box.createHorizontalBox();
        JLabel title = new JLabel("<html><span style='color: black;'>Seats Availables</span></html>");
        title.setFont (title.getFont().deriveFont(32.0f));
        //JLabel version = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Version 1.0<br>Created by Luke Carr</html>");
        //JLabel slogan = new JLabel("<html>Full Potential<br>Minimal Knowledge</html>");
        //titleText.add(version);
        titleText.add(title);
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
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter valid username and password", "INVALID USERNAME/PASSWORD", JOptionPane.ERROR_MESSAGE);
        }  
    }

    public static void main(String[] args) {
        SeatsPage seatsPage=new SeatsPage();
    }
}