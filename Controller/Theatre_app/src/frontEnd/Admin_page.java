package frontEnd;

import controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Admin_page extends JFrame{

    private AppController controller;
    private JPanel panelMain;
    private JTable table1;
    private JComboBox theaterPick;
    private JButton newShow;
    private JButton announceMovieButton;
    private JComboBox roomOfTheater;
    private JLabel nextAvailableTime;
    private JButton newRoom;
    private JList movieList;


    public Admin_page(AppController controller) {

        this.controller=controller;


        Room noRoomSelect=new Room(-1,-1,-1,null);
        roomOfTheater.addItem(noRoomSelect);
        Theater noSelect=new Theater(-1,"Please select theater","");
        theaterPick.addItem(noSelect);
        theaterPick.setSelectedIndex(0);

//        movie list
        DefaultListModel<MovieInfo> dlm=new DefaultListModel<>();
        movieList.setModel(dlm);
        updateMoviesList(dlm);




//        populate the table upon start
        //populate the option in theater pick


        AdminUser admin=(AdminUser) controller.getUser();
        for(Theater theater:admin.getTheaters()){
            theaterPick.addItem(theater);
        }


        setContentPane(panelMain);
        setTitle("admin page");
        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        newRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.createRoom((Theater) theaterPick.getSelectedItem());
            }
        });

//        when theater is select, the program will update rooms available

        theaterPick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Theater selected=(Theater) theaterPick.getSelectedItem();
                if(selected.getId()!=-1){
                    ArrayList<Room> rooms=controller.getAvailableRooms(selected);
                    for(Room room:rooms){
                        roomOfTheater.addItem(room);
                    }
                }


            }
        });

//        when room of the theater is selected, time will be display

        roomOfTheater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room selectedRoom=(Room) roomOfTheater.getSelectedItem();
                nextAvailableTime.setText("Next available time: "+selectedRoom.getNextAvailableTime());
            }
        });
//
        newShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Theater selected

                Theater theater=(Theater) theaterPick.getSelectedItem();
                Room room=(Room) roomOfTheater.getSelectedItem();
                MovieInfo movie=(MovieInfo) movieList.getSelectedValue();

                if(theater.getId()!=-1&&room.getId()!=-1&&movie!=null) controller.createShow(movie,theater,room);

                theaterPick.setSelectedIndex(0);
                roomOfTheater.setSelectedIndex(0);
                nextAvailableTime.setText("");


            }

        });
    }



    private void updateMoviesList(DefaultListModel dlm){
        ArrayList<MovieInfo> movies=controller.allMovies();
        for(MovieInfo movieInfo:movies){
            dlm.addElement(movieInfo);
        }
    }

    public static void main(String[] args) {
        AppController controller=new AppController();
        Admin_page adminPage=new Admin_page(controller);


    }

}
