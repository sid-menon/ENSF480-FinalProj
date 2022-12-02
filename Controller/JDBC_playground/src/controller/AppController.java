package controller;

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

     public void allMovies(){
          connection.allMovies();
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






}
