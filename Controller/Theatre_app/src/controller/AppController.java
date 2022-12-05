package controller;

/**
 *        File Name: AppController.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */

import java.sql.Timestamp;
import java.util.ArrayList;

public class AppController {


     private User user;
     private JDBC connection;



//     connection and login function

     public AppController(){
          connection=new JDBC();
          connection.newConnection();
     }
     public boolean login(String email,String password){

          User loginUser=connection.userLogin(email,password);

          if(loginUser!=null){
               System.out.println("log in successful");
               setUser(loginUser);


               if(isAdmin()){
                    connection.adminTheaters((AdminUser) user);
               }
               if(!isAdmin()){
                    PaymentInfo paymentInfo=connection.getPaymentInfoByEmail(user.getEmail());
                    user.setPaymentInfo(paymentInfo);
               }

               return true;
          }else{

               return false;
          }



     }

     public boolean signUp(){
          return connection.sighUp(user.getEmail(),user.getPassword(),user.getPaymentInfo());
     }


     public void searchMov(){

     }

     public User getUser() {
          return user;
     }

     public void setUser(User user){
          this.user=user;
     }


//     admin specific function
     public boolean isAdmin(){
          return (user.getUserType().compareTo("admin")==0);
     }

     public void createRoom(Theater theater){
          if(!user.getUserType().equals("admin")) return;
//          extract the number of room in the theater
          int roomNumber = connection.getNumberOfRoomTheater(theater.getId())+1;
//          use the result to insert new room with the room number
          int newRoomID= connection.insertRoom(theater.getId(),roomNumber);
//         room_id= Count(*) of rooms table since it is auto_increment
//          populate the seats into the seat table
          for(int c=1;c<5;c++){
               for(int i=1;i<=5;i++){
                    connection.insertSeat(newRoomID,c,i);
               }
          }


     }
     public void createShow(MovieInfo movie,Theater theater,Room room){
          if(!user.getUserType().equals("admin")) return;
          connection.arrangeShow(movie,theater,room);
     }







//     page specific for guest and ordinary user
     public ArrayList<Room> getAvailableRooms(Theater theater){
          return connection.getTheaterRooms(theater.getId());
     }


     public ArrayList<Theater> getTheaterPageData(MovieInfo movie){
          return connection.getTheaterPageData(movie.getId());
     }

     public ArrayList<Timestamp> getShowtimesPageData(Order order){
          return connection.getShowtimesPageData(order.getTheater().getId(),order.getMovie().getId());
     }

     public  ArrayList<Seat> getSeatPageData(Order order){
          int movieID=order.getMovie().getId();
          int theaterID=order.getTheater().getId();
          Timestamp showTime=order.getShowTime();
          return connection.getSeatPageData(movieID,theaterID,showTime);
     }



     public void userReserve(Order order){
//          insert the order into the reservation table
          MovieInfo movie=order.getMovie();
          Theater theater=order.getTheater();
          Room room=order.getRoom();
          Seat seat=order.getSeat();
          connection.insertIntoReservations(user.getEmail(),
                  movie.getId(),
                  theater.getId(),
                  seat.getRoomID(),
                  seat.getRoomNum(),
                  seat.getRow(),
                  seat.getCol(),
                  order.getShowTime()
          );
//          change the seat to occupied once the reservation is confirmed
          connection.occupySeat(seat.getRoomID(), seat.getRow(), seat.getCol());

     }



// general purpose function
     public ArrayList<MovieInfo> getAllMovies(){
          return connection.getAllMovies();
     }





}
