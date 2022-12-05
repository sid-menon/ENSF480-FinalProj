package frontEnd;

import controller.AppController;
import controller.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewTickets extends JFrame{
    private JPanel content;
    private JButton cancelTicketButton;
    private JList ticketList;
    private JButton browseMovies;

    private AppController controller;


    public ViewTickets(AppController controller) {
        this.controller=controller;
//        all ticket

        DefaultListModel<Order> orderDefaultListModel=new DefaultListModel<>();
        updateOrderList(orderDefaultListModel);

        ticketList.setModel(orderDefaultListModel);


        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Order orderSelected=(Order) ticketList.getSelectedValue();
                if(orderSelected!=null){
                    controller.deleteReservation(orderSelected);
                    updateOrderList((DefaultListModel) ticketList.getModel());
                }
            }
        });
        setContentPane(content);
        setTitle("Log in as "+controller.getUser().getEmail());
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browseMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MoviesPage(controller);
            }
        });
    }

    private void updateOrderList(DefaultListModel dlm){
        ArrayList<Order> orders=controller.getReservationsByUser(controller.getUser());
        dlm.removeAllElements();
        dlm.addAll(orders);
    }
}
