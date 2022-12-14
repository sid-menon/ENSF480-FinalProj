package frontEnd;
/**
 *        File Name: Swing_Login.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */
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
    private JButton guestBtn;


    public Swing_Login(AppController controller) {

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean success=controller.login(userEmail.getText(),new String(userPassword.getPassword()));

                if(success&&controller.getUser().getUserType().equals("admin")){
                    new Admin_page(controller);
                    setVisible(false);
//                    vanish if log in successful
                } else if (success) {
                    new ViewTickets(controller);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"user email or password is wrong","fail to log in",JOptionPane.ERROR_MESSAGE);
                    userEmail.setText("");
                    userPassword.setText("");
                }

            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationPage(controller);
            }
        });

        setContentPane(panelMain);
        setTitle("Login");
        setSize(600,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        guestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new MoviesPage(controller);
            }
        });
    }

    public static void main(String[] args) {
        AppController controller=new AppController();
        Swing_Login helloDemo=new Swing_Login(controller);



    }


}
