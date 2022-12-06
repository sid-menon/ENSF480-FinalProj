package frontEnd;
/**
 *        File Name: Admin_page.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Siddharth Menon
 *        Submission Date: Dec 5 2022
 */

import controller.AppController;
import controller.MovieInfo;
import controller.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MoviesPage extends JFrame implements ActionListener //list of movies from user to choose from 
{ 
    JLabel movieLabel; 
    final JTextField textField1;
    JButton b1;
    static JList movieList;

    private AppController controller;
        
    
    MoviesPage(AppController controller)
    {
        this.controller=controller;
        JFrame frame = new JFrame("Select Movies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(800,600));
        frame.setMinimumSize(new Dimension(600,450));

        Box titleText = Box.createHorizontalBox();
        JLabel title = new JLabel("<html><span style='color: black;'>Movie Availables</span></html>");
        title.setFont (title.getFont().deriveFont(32.0f));
      
        titleText.add(title);
       
        titleText.setAlignmentX(frame.getWidth() / 2);


        frame.add(titleText);
       
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        movieLabel = new JLabel();  
        movieLabel.setText("Which Movie Would you like to See?");      //set label value for textField1


        movieList = new JList<>(controller.getAllMovies().toArray());
        
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
        String userValue = textField1.getText();//Movie name from input        
          
        if (ae.getSource().equals(b1)) {  //check if movie name is in database

            Order order=new Order();
            order.setMovie((MovieInfo) movieList.getSelectedValue());
              
            TheaterPage page = new TheaterPage(controller,order); //if movie is in database send to select from theaters


            page.setVisible(true);  
              
            JLabel wel_label = new JLabel("Select From Available Theaters");  
            page.getContentPane().add(wel_label);  
            System.out.println("movie selected");
            dispose();
        }  
        else{  
            //show error message  
            JOptionPane.showMessageDialog(new JFrame(), "please enter an available movie", "MOVIE NOT FOUND", JOptionPane.ERROR_MESSAGE);
        }  
    } 
}
