package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *        File Name: JDBC.java
 *        Assignment: Term project
 *        Lab section: B01
 *        Completed by: Chun-chun Huang
 *        Submission Date: Dec 5 2022
 */

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
                String userType=result.getString("userType");


                if (userType.compareTo("admin")==0) {
                    user = new AdminUser(temp_email, temp_password);
                } else {
                    user = new RegisteredUser(temp_email, temp_password);
                }
            }


            result.close();


        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return user;

    }

    public boolean sighUp(String email,String password,PaymentInfo paymentInfo){


        String insertString="INSERT INTO users (email,password) "+
                            "VALUES(?,?)";

        try(PreparedStatement insertStatement=connection.prepareStatement(insertString)){
            insertStatement.setString(1,email);
            insertStatement.setString(2,password);
//            System.out.println(insertStatement.toString());
            insertStatement.execute();



        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return addPayment(email,paymentInfo);

    }

    public boolean addPayment(String email,PaymentInfo paymentInfo){
        String insertPayment="INSERT INTO paymentInfo (user_email,card_holder,card_number,cvv) " +
                "VALUES(?,?,?,?)";
        try (PreparedStatement paymentStatement=connection.prepareStatement(insertPayment)){
            paymentStatement.setString(1,email);
            paymentStatement.setString(2,paymentInfo.getCardHolderName());
            paymentStatement.setString(3,paymentInfo.getCardNumber());
            paymentStatement.setInt(4,paymentInfo.getCvv());


            paymentStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<MovieInfo> getAllMovies(){
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

    public void adminTheaters(AdminUser user){
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
    public void insertSeat(int roomID,int row,int col){
        String seatInsertStr="INSERT INTO seats (room_id, rowNum, colNum) "+
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

        Date startTime=room.getNextAvailableTime();
        room.updateNextAvailableTime(movieInfo.getDuration());
        Date endTime=room.getNextAvailableTime();


        try(PreparedStatement insertShow=connection.prepareStatement(insertShowStr);
        PreparedStatement updateTime=connection.prepareStatement(updateRoomsStr)){
            insertShow.setInt(1,movieInfo.getId());
            insertShow.setInt(2,theater.getId());
            insertShow.setInt(3,room.getId());
            insertShow.setTimestamp(4,new Timestamp(startTime.getTime()));
            //        room next available time is updated
            insertShow.setTimestamp(5,new Timestamp(endTime.getTime()));
            insertShow.execute();

            updateTime.setTimestamp(1,new Timestamp(endTime.getTime()));
            updateTime.setInt(2,room.getId());
            updateTime.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }




    }

    public ArrayList<Theater> getTheaterPageData(int movID){

        String selectStr="SELECT DISTINCT id,name,address FROM shows as s, theater as t " +
                "WHERE t.id=theater_id" +
                " AND mov_id=?";
        ArrayList<Theater> theaters=new ArrayList<>();
        try(PreparedStatement query=connection.prepareStatement(selectStr)){
            query.setInt(1,movID);
            ResultSet resultSet=query.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String address=resultSet.getString("address");
                theaters.add(new Theater(id,name,address));
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return theaters;
    }


    public ArrayList<Timestamp> getShowtimesPageData(int theaterID, int movieID){

        ArrayList<Timestamp> showTimes=new ArrayList<>();
        String showTimeStr="SELECT * FROM shows " +
                "WHERE mov_id=? " +
                "AND theater_id=?";
        try (PreparedStatement query=connection.prepareStatement(showTimeStr)){
            query.setInt(1,movieID);
            query.setInt(2,theaterID);
            ResultSet resultSet= query.executeQuery();
            while (resultSet.next()){
                Timestamp time=resultSet.getTimestamp("start_time");
                showTimes.add(time);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return showTimes;
    }

    public ArrayList<Seat> getSeatPageData(int movieID,int theaterID, Timestamp showTime){

        String preparedStr="SELECT * FROM rooms as r, seats as s ,shows as sh" +
                " WHERE sh.mov_id=?" +
                " AND sh.theater_id=?" +
                " AND sh.start_time=?" +
                " AND r.id=sh.room_id" +
                " AND r.id=s.room_id";
        ArrayList<Seat> seats=new ArrayList<>();
        try (PreparedStatement query=connection.prepareStatement(preparedStr)){
            query.setInt(1,movieID);
            query.setInt(2,theaterID);
            query.setTimestamp(3,showTime);
            System.out.println(query.toString());
            ResultSet resultSet=query.executeQuery();
            while (resultSet.next()){
                int row=resultSet.getInt("rowNum");
                int col=resultSet.getInt("colNum");
                int roomNumber=resultSet.getInt("room_Number");
                int roomID=resultSet.getInt("room_id");
                boolean isTaken=resultSet.getBoolean("occupied");
                Seat seat=new Seat(row,col);
                seat.setRoomNum(roomNumber);
                seat.setRoomID(roomID);
                seat.setTaken(isTaken);
                seats.add(seat);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return seats;
    }


//    read from individual table (helper method)


//    CRUD for reservations table
//    public ArrayList<Order> getAllReservationByUser(String email){
//
//    }
    public void insertIntoReservations(String email,int movID,int theaterID,int roomID,int roomNum,int row,int col,Timestamp start){
        String insertStr="INSERT INTO reservations (customer_email,mov_id,theater_id,room_id,room_Number,rowNum,colNum,startTime) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement insertStatement=connection.prepareStatement(insertStr)){
            insertStatement.setString(1,email);
            insertStatement.setInt(2,movID);
            insertStatement.setInt(3,theaterID);
            insertStatement.setInt(4,roomID);
            insertStatement.setInt(5,roomNum);
            insertStatement.setInt(6,row);
            insertStatement.setInt(7,col);
            insertStatement.setTimestamp(8,start);

            insertStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


// CRUD for theater table

    public Theater getTheaterByID(int id){
        String str="SELECT * FROM theater WHERE id=?";
        Theater theater=null;
        try (PreparedStatement statement= connection.prepareStatement(str)){
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                String name=resultSet.getString("name");
                String address=resultSet.getString("address");
                theater=new Theater(id,name,address);
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return theater;
    }

//    CRUD for movies table
    public MovieInfo getMovieByID(int id){
        String str="SELECT * FROM movies WHERE id=?";
        MovieInfo movieInfo=null;
        try(PreparedStatement statement= connection.prepareStatement(str)){
            statement.setInt(1,id);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                String name=resultSet.getString("name");
                Timestamp dataOfAnnounce=resultSet.getTimestamp("announce_date");
                Time duration=resultSet.getTime("duration");
                movieInfo=new MovieInfo(id,name,dataOfAnnounce,duration);
            }
            resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return movieInfo;
    }

//    CRUD for seats table

    public void occupySeat(int roomID,int row,int col){
        String str="UPDATE seats" +
                " SET occupied=true" +
                " WHERE room_id=?" +
                " AND rowNum=?" +
                " AND colNum=?";
        try(PreparedStatement statement= connection.prepareStatement(str)){
            statement.setInt(1,roomID);
            statement.setInt(2,row);
            statement.setInt(3,col);
            statement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

//    CRUD for payment
    public PaymentInfo getPaymentInfoByEmail(String email){

        String readStr="SELECT * FROM paymentInfo WHERE user_email=?";
        ArrayList<PaymentInfo> paymentInfoList=new ArrayList<>();
        try(PreparedStatement readStatement=connection.prepareStatement(readStr)){
            readStatement.setString(1,email);
            System.out.println(readStatement.toString());
            ResultSet resultset=readStatement.executeQuery();
            while (resultset.next()){
                String cardHolder=resultset.getString("card_holder");
                String cardNumber=resultset.getString("card_number");
                int cvv=resultset.getInt("cvv");
                paymentInfoList.add(new PaymentInfo(cardHolder,cardNumber,cvv));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return paymentInfoList.get(0);
    }





}

