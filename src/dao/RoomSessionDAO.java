package dao;

import InterfaceDao.RoomSessionDao;
import models.RoomSession;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomSessionDAO implements RoomSessionDao {

    private String url;
    private String username;
    private String password;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public RoomSessionDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public RoomSessionDAO() {
    }

    @Override
    public List<RoomSession> getAllRoomSession() {
        List<RoomSession> list = new ArrayList<>();
        try {
            this.connection= DriverManager.getConnection(this.url,this.username,this.password);
            this.preparedStatement = this.connection.prepareStatement("SELECT * from room_session");
            this.resultSet = this.preparedStatement.executeQuery();

            while(resultSet.next()){
                Long idSession = resultSet.getLong("id_session");
                Long idRoom = resultSet.getLong("id_room");
                list.add(new RoomSession(idSession,idRoom));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createRoomSession(RoomSession roomSession) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);


                this.preparedStatement = this.connection.prepareStatement
                        ("insert into room_session (id_session,id_room) values (?,?)");

                this.preparedStatement.setLong(1, roomSession.getIdSession());
                this.preparedStatement.setLong(2,roomSession.getIdRoom());
                this.preparedStatement.execute();
                System.out.println(" saved into the database");




            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");
        }

    }


    @Override
    public RoomSession readRoomSession(Long idSession, Long idRoom) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("select * from room_session where id_session = ? AND id_room = ? ");
            this.preparedStatement.setLong(1, idSession);
            this.preparedStatement.setLong(2,idRoom);

            this.resultSet = this.preparedStatement.executeQuery();

            RoomSession roomSession = new RoomSession();

            while (this.resultSet.next()) {
                roomSession.setIdSession(this.resultSet.getLong("id_session"));
                roomSession.setIdRoom(this.resultSet.getLong("id_room"));
            }
            this.resultSet.close();
            this.preparedStatement.close();
            this.connection.close();
            return roomSession;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateRoom(RoomSession roomSession) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (roomSession.getIdSession() != null) {
                this.preparedStatement = this.connection.prepareStatement(
                        "update room_session set id_session= ? set id_room= ? ");
                this.preparedStatement.setLong(1, roomSession.getIdSession());
                this.preparedStatement.setLong(2,roomSession.getIdRoom());
                this.preparedStatement.execute();

            } else {
                this.preparedStatement = this.connection.prepareStatement
                        ("insert into room_session (id_session,id_room) values (?,?)  ;");
                this.preparedStatement.setLong(1, roomSession.getIdSession());
                this.preparedStatement.setLong(2,roomSession.getIdRoom());
                this.preparedStatement.execute();
            }
            System.out.println(" saved into the database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the site");
        }


    }

    @Override
    public void deleteRoom(RoomSession roomSession) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            preparedStatement = connection.prepareStatement("DELETE from room_session where (id_session) = ? AND id_room = ?  ");
            preparedStatement.setLong(1, roomSession.getIdSession());
            preparedStatement.setLong(2, roomSession.getIdRoom());

            this.preparedStatement.execute();
            System.out.println("La salle a bien été supprimée.");

            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   @Override
    public boolean checkIfAlreadyAssociated(String startTime, Long idRoom, String date){

        List<RoomSession> roomSession = new ArrayList<>();

        try {
            this.connection = DriverManager.getConnection(url, username, password);
            this.preparedStatement = this.connection.prepareStatement
                    ("SELECT * FROM session INNER JOIN room_session ON session.id = room_session.id_session and room_session.id_room = ? and session.date =?  and session.start_time =? ");

            this.preparedStatement.setLong(1, idRoom);
            this.preparedStatement.setString(3, startTime);
            this.preparedStatement.setString(2, date);

            this.resultSet =preparedStatement.executeQuery();

            while(resultSet.next())
            {
             roomSession.add(new RoomSession());
            }

            System.out.println(roomSession.size());

           if(roomSession.size() != 0 )
            {
                JOptionPane.showMessageDialog(null, "This room is already assigned");
                roomSession.clear();
                return true;

            }

           this.preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("unable to save the product");


        }


       return false;


    }
}
