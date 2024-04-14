package bearbytes.dev.hotel.reservation;

import bearbytes.dev.hotel.floor.Room;
import bearbytes.dev.hotel.interfaces.IReservationDAO;
import bearbytes.dev.hotel.accounts.Account;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationDAO implements IReservationDAO {

    public ReservationDAO() {}

    public boolean checkAvailability(int roomNumber, String start, String end) throws ClassNotFoundException, SQLException {
        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        // Properties for user and password. Here the user
        // is root and the password is password
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "password");

        // Now try to connect
        Connection c = null;
        try {
            c = DriverManager.getConnection(CONNECTION, p);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM myDB.Reservations");
            while (rs.next()) {
                int id = rs.getInt("reservationID");
                String name = rs.getString("username");
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }

        return true;
    }

    
    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException, ParseException {
        // Class.forName(xxx) loads the jdbc classes and
        // creates a drivermanager class factory
        Class.forName(dbClassName);

        // Properties for user and password. Here the user
        // is root and the password is password
        Properties p = new Properties();
        p.put("user", "root");
        p.put("password", "password");

        // Now try to connect
        Connection c = null;
        try {
            c = DriverManager.getConnection(CONNECTION, p);
            // Prepare the query's
            String query = "INSERT INTO myDB.Reservations(reservationID, username, start, end) values(?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(query);
            query = "INSERT INTO myDB.Bookings(bookingID, reservationID, roomNumber) values(?,?,?)";
            PreparedStatement ps2 = c.prepareStatement(query);
            String dbStart = reservation.getStartDate();
            String dbEnd = reservation.getEndDate();

            // Add the reservation
            ps.setInt(1, reservation.getReservationID());
            ps.setString(2, reservation.getUsername());
            ps.setString(3, dbStart);
            ps.setString(4, dbEnd);
            ps.executeUpdate();

            // Add the rooms to the reservation
            for(int i = 0; i < reservation.getRooms().size(); ++i) {
                ps2.setInt(1, reservation.getReservationID() + reservation.getRooms().get(i).getNumber());
                ps2.setInt(2, reservation.getReservationID());
                ps2.setInt(3, reservation.getRooms().get(i).getNumber());
                ps2.executeUpdate();
            }
            return true;
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
        }
        return false;
    }

    public boolean remove(Reservation r) throws SQLException {
        Connection c = getDBConnection();
        PreparedStatement ps = null;

        try {
            String query = "DELETE r, b FROM myDB.Reservations r JOIN myDB.Bookings b " +
                           "ON r.reservationID = b.reservationID WHERE r.reservationID = ?";
            ps = c.prepareStatement(query);
            ps.setInt(1, r.getReservationID());
            ps.executeUpdate();
        } catch( SQLException e) {
            e.printStackTrace();
            return false;
        } finally{
            if(c != null) {
                c.close();
            }
            if(ps != null) {
                ps.close();
            }
        }

        return true;
    }

    public Collection<Reservation> getAll(String username) throws SQLException {
        Connection c = getDBConnection();
        PreparedStatement ps = null;
        List<Reservation> reservations = new ArrayList<>();
        username = username.substring(1, username.length() - 1);

        try {
            // Prepare the query's
            String query = "SELECT * FROM myDB.Reservations r JOIN myDB.Bookings b ON r.reservationID = b.reservationID " +
                    "JOIN myDB.Rooms rm ON rm.roomNumber = b.roomNumber WHERE r.username = ?";
            ps = c.prepareStatement(query);

            // Retrieve the reservations
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            // Add the rooms to the reservation
            Map<Integer, List<Room>> resRooms = new HashMap<>();
            Integer prevResID;
            Integer reservationID = null;
            boolean hasReservations = false;
            String start = null, end = null;
            while (rs.next()) {
                prevResID = reservationID;
                reservationID = rs.getInt("reservationID");
                if(prevResID == null) {
                    prevResID = reservationID;
                    hasReservations = true;
                }
                start = rs.getString("start");
                end = rs.getString("end");
                // Get room information
                Integer roomNumber = rs.getInt("roomNumber");
                Integer floor = rs.getInt("floor");
                Integer numBeds = rs.getInt("numBeds");
                Double dailyRate = rs.getDouble("dailyRate");
                Boolean smokingAllowed = rs.getBoolean("smokingAllowed");
                String bedSize = rs.getString("bedSize");
                String type = rs.getString("type");
                String quality = rs.getString("quality");

                List<Room> rooms = resRooms.getOrDefault(reservationID, new ArrayList<Room>());
                rooms.add(new Room(roomNumber, floor, numBeds, dailyRate, smokingAllowed,
                        Room.BedType.getEnum(bedSize), Room.RoomType.getEnum(type), Room.QualityLevel.getEnum(quality)));
                resRooms.put(reservationID, rooms);

                if(!prevResID.equals(reservationID)) {
                    reservations.add(new Reservation(resRooms.get(prevResID), start, end, username));
                }
            }
            if(hasReservations) {
                reservations.add(new Reservation(resRooms.get(reservationID), start, end, username));
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally{
            if(c != null) {
                c.close();
            }
            if(ps != null) {
                ps.close();
            }
        }

        return reservations;
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(dbClassName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "password");
            dbConnection = DriverManager.getConnection(CONNECTION, p);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}