package controller;

import java.sql.*;

public class JDBC {

    private Connection connection;

    public void newConnection() {
        String url="jdbc:mysql://localhost:3306/theatre_app";
        String uname="temp_user";
        String password="mypassword";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try{
            connection= DriverManager.getConnection(url,uname,password);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User userLogin(String email, String password){
        User user=null;
        String loginString="SELECT * FROM users "+
                "WHERE email=?"+
                "AND password=?";
        try(PreparedStatement loginStatement=connection.prepareStatement(loginString)) {
            loginStatement.setString(1,email.trim());
            loginStatement.setString(2,password.trim());
            ResultSet result = loginStatement.executeQuery();
            if(result.next()){
                String temp_email = result.getString("email");
                String temp_password = result.getNString("password");
                String temp_payInfo = result.getString("paymentInfo");
                String userType=result.getString("userType");


                if (userType.compareTo("admin")==0) {
                    user = new AdminUser(temp_email, temp_password, temp_payInfo);
                } else {
                    user = new RegisteredUser(temp_email, temp_password, temp_payInfo);
                }
            }


            result.close();


        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return user;

    }

    public void sighUp(String email,String password,String paymentInfo){
//        check if user with the email already exists
        String checkString="Select COUNT(*) FROM users WHERE email=?";
        try(PreparedStatement checkStatement=connection.prepareStatement(checkString)){
            checkStatement.setString(1,email);
//            System.out.println(checkStatement.toString());
            ResultSet resultSet=checkStatement.executeQuery();
            if(resultSet.next()){

                if(resultSet.getInt("COUNT(*)")!=0){
                    System.out.println("The email has been signed up by other user");
                    return;
                }
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        String insertString="INSERT INTO users (email,password,paymentInfo) "+
                            "VALUES(?,?,?)";
        try(PreparedStatement insertStatement=connection.prepareStatement(insertString)){
            insertStatement.setString(1,email);
            insertStatement.setString(2,password);
            insertStatement.setString(3,paymentInfo);
//            System.out.println(insertStatement.toString());
            insertStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void allMovies(){
        String allMovieStr="SELECT * FROM movies";
        try{
            Statement allMovStatement=connection.createStatement();
            ResultSet resultSet=allMovStatement.executeQuery(allMovieStr);

            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                System.out.println(id+". "+name);
            }
            resultSet.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void searchMov(String movName){
        String seachStr="SELECT * FROM movies WHERE name=?";

        try(PreparedStatement searchStatement= connection.prepareStatement(seachStr)){
            searchStatement.setString(1,movName);
            ResultSet result=searchStatement.executeQuery();
//            the id of the movie is extracted, we can use the result to find all the theatre and time
//            we will need the id to look for the theater and show time



        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}

