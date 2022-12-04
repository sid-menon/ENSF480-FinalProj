package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class AppController {


     private User user;
     private JDBC connection;


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

               return true;
          }else{
               System.out.println("fail to log in");
               return false;
          }



     }

     public void signUp(){
          Scanner scanner=new Scanner(System.in);
          System.out.println("Please the email:\n");
          String email=scanner.nextLine();
          System.out.println("Please enter the password:\n");
          String password=scanner.nextLine();
          System.out.println("Please provide payment information");
          String payInfo=scanner.nextLine();

          scanner.close();
          connection.sighUp(email,password,payInfo);

     }

     public ArrayList<MovieInfo> allMovies(){
          return connection.allMovies();
     }
     public void searchMov(){

     }

     public User getUser() {
          return user;
     }

     private void setUser(User user){
          this.user=user;
     }

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

     public ArrayList<Room> getAvailableRooms(Theater theater){
          return connection.getTheaterRooms(theater.getId());
     }

     public void createShow(MovieInfo movie,Theater theater,Room room){
          if(!user.getUserType().equals("admin")) return;
          connection.arrangeShow(movie,theater,room);
     }


     public ArrayList<Theater> movieToTheater(MovieInfo movie){
          return connection.getTheaterFromMovies(movie.getId());
     }

     public ArrayList<Timestamp> getShowTimes(Order order){
          return connection.getShowTimes(order.getTheater().getId(),order.getMovie().getId());
     }









}
