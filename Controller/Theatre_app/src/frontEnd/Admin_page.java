package frontEnd;
/**
 *        File Name: Admin_page.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */

import controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JButton logOutButton;


    public Admin_page(AppController controller) {

        this.controller=controller;

//      initialize the available room list
        Room noRoomSelect=new Room(-1,-1,-1,null);
        DefaultComboBoxModel<Room> roomModel=new DefaultComboBoxModel<>();
//        roomModel.addElement(noRoomSelect);
        roomOfTheater.setModel(roomModel);


        Theater noTheaterSelect=new Theater(-1,"Please select theater","");
        DefaultComboBoxModel<Theater> theaterModel=new DefaultComboBoxModel<>();
        theaterModel.addElement(noTheaterSelect);
        theaterPick.setModel(theaterModel);



//        movie list
        DefaultListModel<MovieInfo> dlm=new DefaultListModel<>();
        movieList.setModel(dlm);
        updateMoviesList(dlm);




//        populate the table upon start
        //populate the option in theater pick
        updateTheaterList((DefaultComboBoxModel) theaterPick.getModel());


        setContentPane(panelMain);
        setTitle("admin page");
        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        newRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Theater selected=(Theater) theaterPick.getSelectedItem();
                if(selected.equals(noTheaterSelect)) return;

                String confirmMessage="New room will be created at "+selected+"\n" +
                        "Do you wish to proceed";
                if(JOptionPane.showConfirmDialog(null,confirmMessage,"confirm: create new room",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    controller.createRoom(selected);
                    updateRoomList((DefaultComboBoxModel) roomOfTheater.getModel(),selected);
                }


            }
        });

//        when theater is select, the program will update rooms available

        theaterPick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Theater selected=(Theater) theaterPick.getModel().getSelectedItem();
                DefaultComboBoxModel<Room> newRoomModel=new DefaultComboBoxModel<>();
                roomOfTheater.setModel(roomModel);
                updateRoomList((DefaultComboBoxModel) roomOfTheater.getModel(),selected);


            }
        });

//        when room of the theater is selected, time will be display

        roomOfTheater.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Room selectedRoom=(Room) roomOfTheater.getSelectedItem();
                if(selectedRoom!=null)
                    nextAvailableTime.setText("Next available time: "+selectedRoom.getNextAvailableTime());
            }
        });
//
        newShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                Theater selected

                Theater theater=(Theater) theaterPick.getModel().getSelectedItem();
                Room room=(Room) roomOfTheater.getModel().getSelectedItem();
                MovieInfo movie=(MovieInfo) movieList.getSelectedValue();

                if(theater.getId()!=-1&&room.getId()!=-1&&movie!=null){
                    Theater selectedTheater=(Theater) theaterPick.getModel().getSelectedItem();
                    Room selectedRoom=(Room) roomOfTheater.getModel().getSelectedItem();
                    String confirmMessage="new show will be created at "+selectedTheater+"\n" +
                            "Room Number: "+room.getRoomNumber()+", show time: "+room.getNextAvailableTime()+"\n" +
                            "Do you wish to proceed?";
                    if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(null,confirmMessage,"confirm:create new show",JOptionPane.YES_NO_OPTION)){
                        controller.createShow(movie,theater,room);
                        nextAvailableTime.setText("");

                    }

                }

            }

        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("java -jar Theatre_App.jar");
                    System.exit(0);
                } catch (IOException ioException){
                    ioException.printStackTrace();
                }

            }
        });
    }



    private void updateMoviesList(DefaultListModel dlm){
        ArrayList<MovieInfo> movies=controller.getAllMovies();
        for(MovieInfo movieInfo:movies){
            dlm.addElement(movieInfo);
        }
    }

    private void updateRoomList(DefaultComboBoxModel model,Theater theaterSelected){

        ArrayList<Room> rooms=controller.getAvailableRooms(theaterSelected);

        if(model!=null&&model.getSize()!=0) model.removeAllElements();
        for(Room room:rooms){
            model.addElement(room);
        }

    }

    private void updateTheaterList(DefaultComboBoxModel model){
        AdminUser admin=(AdminUser) controller.getUser();

        Theater[] theaters=admin.getTheaters();
        model.removeAllElements();
        for (Theater theater:theaters){
            model.addElement(theater);
        }

    }



}
