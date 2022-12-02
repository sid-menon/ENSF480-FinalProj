package frontEnd;

import controller.AppController;

import javax.swing.*;


public class Login {
    public static void main(String[] a){

        AppController controller=new AppController();
        //test main class
        LoginPage frame=new LoginPage(controller);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
 
}