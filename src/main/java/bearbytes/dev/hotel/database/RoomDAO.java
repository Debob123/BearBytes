package bearbytes.dev.hotel.database;

import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.interfaces.IRoomDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class RoomDAO implements IRoomDAO {


    public RoomDAO() {
    }

    public boolean add(Room room) throws SQLException {

        Connection c = null;

        try {
            c = getDBConnection();
            // Retrieve rooms from the database
            String query = "SELECT roomNumber FROM Rooms";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Check if the room number is taken
            while(rs.next()) {
                if(rs.getInt("roomNumber") == room.getNumber()) {
                    return false;
                }
            }

            // Add the room to the database
            String query2 = "INSERT INTO Rooms(roomNumber, floor, numBeds, dailyRate, " +
                           "smokingAllowed, bedSize, type, quality) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(query2);

            ps.setInt(1, room.getNumber());
            ps.setInt(2, room.getFloor());
            ps.setInt(3, room.getNumBeds());
            ps.setDouble(4, room.getDailyRate());
            ps.setBoolean(5, room.getSmokingAllowed());
            ps.setString(6, Room.BedType.toString(room.getBedSize()));
            ps.setString(7, Room.RoomType.toString(room.getType()));
            ps.setString(8, Room.QualityLevel.toString(room.getQuality()));
            ps.executeUpdate();
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }

        return true;
    }

    public boolean modify(Room[] rooms) throws ClassNotFoundException, SQLException{
        PreparedStatement ps = null;
        Connection c = null;

        // Now try to connect
        try {

            Collection<Room> r = getAll();

            // Check if the room to change is valid
            if(!r.contains(rooms[0])) {
                System.out.println(rooms[0]);
                System.out.println("Room not present in database");
                return false;
            }

            // Check if the room number is available to change to
            if(rooms[0].getNumber() != rooms[1].getNumber()) {
                for (Room room : r) {
                    if (room.getNumber() == rooms[1].getNumber()) {
                        System.out.println("This room number is already taken");
                        return false;
                    }
                }
            }

            c = getDBConnection();

            // Query for the rooms
            String query = "UPDATE APP.Rooms SET roomNumber = ?, floor = ?," +
                    " numBeds = ?, dailyRate = ?, smokingAllowed = ?, bedSize = ?," +
                    " type = ?, quality = ? WHERE roomNumber = ?";
            ps = c.prepareStatement(query);
            ps.setInt(1, rooms[1].getNumber());
            ps.setInt(2, rooms[1].getFloor());
            ps.setInt(3, rooms[1].getNumBeds());
            ps.setDouble(4, rooms[1].getDailyRate());
            ps.setBoolean(5, rooms[1].getSmokingAllowed());
            ps.setString(6, Room.BedType.toString(rooms[1].getBedSize()));
            ps.setString(7, Room.RoomType.toString(rooms[1].getType()));
            ps.setString(8, Room.QualityLevel.toString(rooms[1].getQuality()));
            ps.setInt(9, rooms[0].getNumber());
            ps.executeUpdate();
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(ps != null) {
                ps.close();
            }
            if(c != null) {
                c.close();
            }
        }

        return true;
    }

    public boolean remove(Room r) {
        return true;
    }

    public Collection<Room> getAll() throws ClassNotFoundException, SQLException {
        List<Room> rooms = new ArrayList<>();

        Connection c = null;
        try {
            c = getDBConnection();
            // Query for the rooms
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM APP.Rooms";
            ResultSet rs = stmt.executeQuery(query);
            // Iterate over the returned rooms

            while(rs.next()) {
                Room r = extractRoom(rs);
                rooms.add(r);
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }

        return rooms;
    }

    public Collection<Room> getAvailable(String[] dates) throws SQLException {
        List<Room> rooms = new ArrayList<>();

        Connection c = null;
        try {
            c = getDBConnection();
            // Query for the rooms
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM APP.Rooms";
            ResultSet rs = stmt.executeQuery(query);
            // Iterate over the returned rooms

            while(rs.next()) {
                Room r = extractRoom(rs);
                rooms.add(r);
            }

            checkAvailability(rooms, dates);

        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }

        return rooms;
    }

    public void checkAvailability(List<Room> rooms, String[] dates) throws SQLException {
        Connection c = null;
        try {
            String wantedStart = dates[0];
            String wantedEnd = dates[1];
            c = getDBConnection();
            // Query for the rooms
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM APP.Rooms JOIN APP.Bookings ON APP.Bookings.roomNumber = APP.Rooms.roomNumber" +
                    " JOIN APP.Reservations ON APP.Bookings.reservationID = APP.Reservations.reservationID";
            ResultSet rs = stmt.executeQuery(query);
            // Iterate over the returned rooms

            while(rs.next()) {
                String startDate = rs.getString("startDate");
                String endDate = rs.getString("endDate");
                // Compare whether the start comes after the end of the end comes before the start
                if( !((wantedStart.compareTo(endDate) >= 0) || (wantedEnd.compareTo(startDate) <= 0)) ) {
                    Room r = extractRoom(rs);
                    rooms.remove(r);
                }
            }

        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
    }

    private Room extractRoom(ResultSet rs) throws SQLException {
        int roomNum = rs.getInt("roomNumber");
        int numBeds = rs.getInt("numBeds");
        int floor = rs.getInt("floor");
        double dailyRate = rs.getDouble("dailyRate");
        boolean smokingAllowed = rs.getBoolean("smokingAllowed");
        Room.BedType bedSize = Room.BedType.valueOf(rs.getString("bedSize"));
        Room.RoomType roomType = Room.RoomType.valueOf(rs.getString("type"));
        Room.QualityLevel quality = Room.QualityLevel.valueOf(rs.getString("quality"));
        return new Room(roomNum, numBeds, floor, dailyRate, smokingAllowed, bedSize, roomType, quality);
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
