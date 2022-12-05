package frontEnd;

import controller.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTickets {
    private JPanel content;
    private JButton cancelTicketButton;
    private JList ticketList;

    public ViewTickets() {

//        all ticket
        DefaultListModel<Order> orderDefaultListModel=new DefaultListModel<>();

        ticketList.setModel(orderDefaultListModel);


        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
