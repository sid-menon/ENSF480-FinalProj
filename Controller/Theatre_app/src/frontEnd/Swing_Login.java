package frontEnd;

import controller.AppController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swing_Login extends JFrame{
    private JPanel panelMain;
    private JTextField userEmail;
    private JButton signInButton;
    private JPasswordField userPassword;
    private JButton signUpButton;


    public Swing_Login(AppController controller) {

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean success=controller.login(userEmail.getText(),new String(userPassword.getPassword()));
                setVisible(false);
                new Admin_page(controller);

            }
        });


    }

    public static void main(String[] args) {
        AppController controller=new AppController();
        Swing_Login helloDemo=new Swing_Login(controller);
        helloDemo.setContentPane(helloDemo.panelMain);
        helloDemo.setTitle("Login");
        helloDemo.setSize(300,300);

        helloDemo.setVisible(true);
        helloDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
