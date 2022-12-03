package frontEnd;

import controller.AppController;
import controller.MovieInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Admin_page extends JFrame{

    private AppController controller;
    private JPanel panelMain;
    private JTable table1;
    private JComboBox theaterPick;
    private JButton confirmButton;
    private JButton announceMovieButton;
    private JComboBox unoccupiedRoom;
    private JLabel timeLabel;


    public Admin_page(AppController controller) {

        this.controller=controller;

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

//      movies table
        String[] moviesColumn={"movie name","announce date"};
        DefaultTableModel dtm=new DefaultTableModel(moviesColumn,0);
        table1.setModel(dtm);
        updateMoviesTable((DefaultTableModel) table1.getModel());
        JTableHeader cols=table1.getTableHeader();
        getContentPane().add(cols);


//        populate the table upon start


    }

    private void updateMoviesTable(DefaultTableModel dtm){
        ArrayList<MovieInfo> movies=controller.allMovies();
        for(MovieInfo movieInfo:movies){
            Object[] objects={movieInfo.getMovieName(),movieInfo.getDateOfAnnounce()};
            dtm.addRow(objects);
        }
    }

    public static void main(String[] args) {
        AppController controller=new AppController();
        Admin_page adminPage=new Admin_page(controller);
        adminPage.setContentPane(adminPage.panelMain);
        adminPage.setTitle("admin page");
        adminPage.setSize(700,700);
        adminPage.setVisible(true);
        adminPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
