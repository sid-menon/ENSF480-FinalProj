package controller;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<MovieInfo> allMovies(){
        String allMovieStr="SELECT * FROM movies";
        ArrayList<MovieInfo> movies=new ArrayList<>();

        try{
            Statement allMovStatement=connection.createStatement();
            ResultSet resultSet=allMovStatement.executeQuery(allMovieStr);

            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");

                Timestamp date=resultSet.getTimestamp("announce_date");
                Time duration=resultSet.getTime("duration");
                System.out.println(id+". "+name+", "+date.toString());
                movies.add(new MovieInfo(id,name,date,duration));

            }
            resultSet.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return movies;

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

    public void theaterManaged(AdminUser user){
        String adminTheaters="Select * FROM manages as m, theater as t "+
                "WHERE m.theater_id=t.id " +
                "AND m.mgr_email=?";
        try(PreparedStatement statement= connection.prepareStatement(adminTheaters)){
            ArrayList<Theater> list=new ArrayList<>();
            statement.setString(1,user.getEmail());
            ResultSet resultSet=statement.executeQuery();

            while (resultSet.next()){
                int id=resultSet.getInt("theater_id");
                String theaterName=resultSet.getString("name");
                String address=resultSet.getString("address");
                list.add(new Theater(id,theaterName,address));
            }
            Theater[] theaters=list.toArray(new Theater[list.size()]);
            user.setTheaters(theaters);

            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public int getNumberOfRoomTheater(int theater_id){
        String countString="SELECT COUNT(*) FROM rooms"+
                " WHERE theater_id=?";
        int res=-1;
        try(PreparedStatement countStatement=connection.prepareStatement(countString)){

            countStatement.setInt(1,theater_id);
            ResultSet resultSet=countStatement.executeQuery();
            if(resultSet.next()){
                res=resultSet.getInt("COUNT(*)");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;

    }

    public int insertRoom(int theater_id,int roomNumber){
        String insertString="INSERT INTO rooms (theater_id,room_Number) "+
                "VALUES(?,?)";
        String countString="SELECT COUNT(*) FROM rooms";

        int res=-1;
        try(PreparedStatement insertStatement=connection.prepareStatement(insertString);
                PreparedStatement countStatement=connection.prepareStatement(countString)){

            insertStatement.setInt(1,theater_id);
            insertStatement.setInt(2,roomNumber);
            insertStatement.execute();

            ResultSet resultSet=countStatement.executeQuery();
            if(resultSet.next()){
                res=resultSet.getInt("COUNT(*)");
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return res;

    }
    public void insertSeat(char row,int col,int roomID){
        String seatInsertStr="INSERT INTO seats (room_id, rowChar, col) "+
                "VALUES (?,?,?)";
        try(PreparedStatement insertStatement=connection.prepareStatement(seatInsertStr)){
            insertStatement.setInt(1,roomID);
            insertStatement.setString(2,String.valueOf(row));
            insertStatement.setInt(3,col);

            insertStatement.execute();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public ArrayList<Room> getTheaterRooms(int theaterID){
        ArrayList<Room> rooms=new ArrayList<>();
        String roomStr="SELECT * FROM rooms "+
                "WHERE theater_id=?";

        try(PreparedStatement roomStatement=connection.prepareStatement(roomStr)){
            roomStatement.setInt(1,theaterID);
            ResultSet resultSet=roomStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                int roomNo=resultSet.getInt("room_Number");
                Timestamp nextAvailable=resultSet.getTimestamp("next_Available_Time");
                rooms.add(new Room(id,theaterID,roomNo,nextAvailable));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return rooms;

    }

    public void arrangeShow(MovieInfo movieInfo,Theater theater,Room room){
//        1. insert into show table
//        2. update to rooms next available time
        String insertShowStr="INSERT INTO shows (mov_id,theater_id,room_id,start_time,end_time) "+
                "VALUES(?,?,?,?,?)";
        String updateRoomsStr="UPDATE rooms " +
                "SET next_Available_Time=? " +
                "WHERE id=?";



        try(PreparedStatement insertShow=connection.prepareStatement(insertShowStr);
        PreparedStatement updateTime=connection.prepareStatement(updateRoomsStr)){
            insertShow.setInt(1,movieInfo.getId());
            insertShow.setInt(2,theater.getId());
            insertShow.setInt(3,room.getId());
            insertShow.setTimestamp(4,room.getNextAvailableTime());
            //        room next available time is updated
            room.updateNextAvailableTime(movieInfo.getDuration());
            insertShow.setTimestamp(5,room.getNextAvailableTime());
            insertShow.execute();

            updateTime.setTimestamp(1,room.getNextAvailableTime());
            updateTime.setInt(2,room.getId());
            updateTime.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }




    }





}

