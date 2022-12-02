package frontEnd;

import controller.AppController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage extends JFrame implements ActionListener {

    AppController controller;

    public UserPage(AppController controller){
        this.controller=controller;
//        user specific
        if(controller.isAdmin()){
            setTitle("Admin user");
        }else{
            setTitle("User");
        }


        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 700);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
