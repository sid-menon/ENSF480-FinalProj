package frontEnd;

import controller.AppController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloDemo extends JFrame{
    private JPanel panelMain;
    private JTextField userEmail;
    private JButton signInButton;
    private JPasswordField userPassword;




    public HelloDemo(AppController controller) {

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean success=controller.login(userEmail.getText(),new String(userPassword.getPassword()));
                if(success) System.out.println("log-in successful");
            }
        });
    }

    public static void main(String[] args) {
        AppController controller=new AppController();
        HelloDemo helloDemo=new HelloDemo(controller);
        helloDemo.setContentPane(helloDemo.panelMain);
        helloDemo.setTitle("Hello world");
        helloDemo.setSize(300,300);

        helloDemo.setVisible(true);
        helloDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}
