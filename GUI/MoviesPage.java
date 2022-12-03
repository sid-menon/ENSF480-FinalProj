import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

class MoviesPage extends JFrame implements ActionListener //list of movies from user to choose from 
{ 
    JLabel movieLabel; 
    final JTextField textField1;
    JButton b1;
    static JList movieList;
    
        
    
    MoviesPage()  
    {  
        JFrame frame = new JFrame("Select Movies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        Box titleText = Box.createHorizontalBox();
        JLabel title = new JLabel("<html><span style='color: black;'>Movie Availables</span></html>");
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

        movieLabel = new JLabel();  
        movieLabel.setText("Which Movie Would you like to See?");      //set label value for textField1

        String movies[] ={"movie1", "movie2"}; //placeholder --use values from database
        movieList = new JList<>(movies);
        
        movieList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

          
          
        textField1 = new JTextField(15);

        b1 = new JButton("Submit");
        textField1.setLocation(300,225);
        frame.add(movieLabel);
        frame.add(textField1);
        frame.add(movieList);
        frame.add(b1);
        b1.addActionListener(this);     
    }  

    public void actionPerformed(ActionEvent ae)    
    {  
        String userValue = textField1.getText();//username from input        
          
        if (userValue.length() < 30) {  //check if in database ---- this is a placeholder
              
            TheaterPage page = new TheaterPage();  
              
            page.setVisible(true);  
              
            //create a welcome label and set it to the new page  
            JLabel wel_label = new JLabel("Select From Available Theaters");  
            page.getContentPane().add(wel_label);  
            System.out.println("movie selected");
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter an available movie", "MOVIE NOT FOUND", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}
