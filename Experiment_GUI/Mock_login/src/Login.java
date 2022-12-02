import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login implements ActionListener {
    public static void main(String[] a){
        //test main class
        JFrame frame=new JFrame();
        frame.setTitle("Login Form");

        frame.setBounds(10,10,700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
//        JPanel panel=new JPanel();
//        JButton b1=new JButton("Button 1");
//        panel.add(b1);
        JPanel panel=new LoginPage();

        frame.setContentPane(panel);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}